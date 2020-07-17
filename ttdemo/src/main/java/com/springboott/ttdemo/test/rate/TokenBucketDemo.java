package com.springboott.ttdemo.test.rate;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * <pre>
 * 令牌桶算法
 * </pre>
 */
public class TokenBucketDemo {

    // 默认桶大小个数 即最大瞬间流量是64M
    private static final int DEFAULT_BUCKET_SIZE = 1024 * 1024 * 64;

    // 一个桶的单位是1字节
    private int everyTokenSize = 1;

    // 瞬间最大流量
    private int maxFlowRate;

    // 平均流量
    private int avgFlowRate;

    // 队列来缓存桶数量：最大的流量峰值就是 = everyTokenSize*DEFAULT_BUCKET_SIZE 64M = 1 * 1024 * 1024 * 64
    private ArrayBlockingQueue<Byte> tokenQueue = new ArrayBlockingQueue<Byte>(DEFAULT_BUCKET_SIZE);

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private volatile boolean isStart = false;

    private ReentrantLock lock = new ReentrantLock(true);

    private static final byte A_CHAR = 'a';

    public TokenBucketDemo() {
    }

    public TokenBucketDemo(int maxFlowRate, int avgFlowRate) {
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public TokenBucketDemo(int everyTokenSize, int maxFlowRate, int avgFlowRate) {
        this.everyTokenSize = everyTokenSize;
        this.maxFlowRate = maxFlowRate;
        this.avgFlowRate = avgFlowRate;
    }

    public void addTokens(Integer tokenNum) {
        // 若是桶已经满了，就不再家如新的令牌 类似put操作 put会等待 offer空间满了就不执行 返回false
        for (int i = 0; i < tokenNum; i++) {
            tokenQueue.offer(Byte.valueOf(A_CHAR));
        }
        System.out.println("生成令牌 = " + tokenNum + "  " + System.currentTimeMillis());
    }

    public TokenBucketDemo build() {
        start();
        return this;
    }

    /**
     * 获取足够的令牌个数
     *
     * @return
     */
    public boolean getTokens(byte[] dataSize) {

//        Preconditions.checkNotNull(dataSize);
//        Preconditions.checkArgument(isStart,
//                "please invoke start method first !");

        int needTokenNum = dataSize.length / everyTokenSize + 1;// 传输内容大小对应的桶个数

        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            boolean result = needTokenNum <= tokenQueue.size(); // 是否存在足够的桶数量
            if (!result) {
                return false;
            }

            int tokenCount = 0;
            //使用take()函数，如果队列中没有数据，则线程wait释放CPU，而poll()则不会等待，直接返回null；
            // 同样，空间耗尽时offer()函数不会等待，直接返回false，而put()则会wait，
            // 因此如果你使用while(true)来获得队列元素，千万别用poll()，CPU会100%的。
            for (int i = 0; i < needTokenNum; i++) {
                Byte poll = tokenQueue.poll();//队列中没有数据则返回null
                if (poll != null) {
                    tokenCount++;
                }
            }
            return tokenCount == needTokenNum;
        } finally {
            lock.unlock();
        }
    }

    public void start() {
        // 初始化桶队列大小
        if (maxFlowRate != 0) {
            tokenQueue = new ArrayBlockingQueue<Byte>(maxFlowRate);
        }
        // 初始化令牌生产者
        TokenProducer tokenProducer = new TokenProducer(avgFlowRate, this);
        //从0秒开始 每1秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(tokenProducer, 0, 1, TimeUnit.SECONDS);
        isStart = true;
    }

    public void stop() {
        isStart = false;
        scheduledExecutorService.shutdown();
    }

    public boolean isStarted() {
        return isStart;
    }

    class TokenProducer implements Runnable {

        private int avgFlowRate;
        private TokenBucketDemo tokenBucket;

        public TokenProducer(int avgFlowRate, TokenBucketDemo tokenBucket) {
            this.avgFlowRate = avgFlowRate;
            this.tokenBucket = tokenBucket;
        }

        @Override
        public void run() {
            tokenBucket.addTokens(avgFlowRate);
        }
    }

    public static TokenBucketDemo newBuilder() {
        return new TokenBucketDemo();
    }

    public TokenBucketDemo everyTokenSize(int everyTokenSize) {
        this.everyTokenSize = everyTokenSize;
        return this;
    }

    public TokenBucketDemo maxFlowRate(int maxFlowRate) {
        this.maxFlowRate = maxFlowRate;
        return this;
    }

    public TokenBucketDemo avgFlowRate(int avgFlowRate) {
        this.avgFlowRate = avgFlowRate;
        return this;
    }

    private String stringCopy(String data, int copyNum) {
        //创建StringBuilder 初始容量 data*copyNum
        StringBuilder sbuilder = new StringBuilder(data.length() * copyNum);
        for (int i = 0; i < copyNum; i++) {
            sbuilder.append(data);
        }
        System.out.println("sbuilder.length() = " + sbuilder.toString().length());
        return sbuilder.toString();

    }

    private static void arrayTest() {
        ArrayBlockingQueue<Integer> tokenQueue = new ArrayBlockingQueue<Integer>(10);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        System.out.println(tokenQueue.size());
        System.out.println(tokenQueue.remainingCapacity());
    }

    private static void tokenTest() throws InterruptedException, IOException {
        //每秒创建512个字节令牌
        TokenBucketDemo tokenBucket = TokenBucketDemo.newBuilder().avgFlowRate(512).maxFlowRate(1024).build();
        //日志输出
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/ds_test")));
        String data = "xxxx";// 四个字节
        for (int i = 1; i <= 1000; i++) {
            Random random = new Random();
            int num = random.nextInt(100);//生成100个随机数
            boolean tokens = tokenBucket.getTokens(tokenBucket.stringCopy(data, num).getBytes());
            TimeUnit.MILLISECONDS.sleep(100);
            if (tokens) {
//                bufferedWriter.write("token pass --- index:" + num);
                System.out.println("token pass --- index：" + num + "  " + System.currentTimeMillis());
            } else {
//                bufferedWriter.write("token rejuect --- index" + num);
                System.out.println("token rejuect --- index：" + num + "  " + System.currentTimeMillis());
            }
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
        }
//        bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        tokenTest();
    }

}

