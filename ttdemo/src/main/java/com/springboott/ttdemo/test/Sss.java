package com.springboott.ttdemo.test;

public class Sss {
    static class Grandpa {
        static {
            System.out.println("爷爷在静态代码块");
        }
    }

    static class Father extends Grandpa {
        static {
            System.out.println("爸爸在静态代码块");
        }

        public static int factor = 25;

        public Father() {
            System.out.println("我是爸爸~");
        }
    }

    static class Son extends Father {
        static {
            System.out.println("儿子在静态代码块");
        }

        public Son() {
            System.out.println("我是儿子~");
        }
    }

    public static void main(String[] args) {
        System.out.println("爸爸的岁数:" + Son.factor);  //入口
    }
}
