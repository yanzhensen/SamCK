package com.springboott.ttdemo.util;

import com.springboott.ttdemo.common.enums.ErrorCodeEnum;
import com.springboott.ttdemo.common.exception.ApiAssert;
import com.springboott.ttdemo.po.AllTypeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * API工具类
 *
 * @author Caratacus
 */
@SuppressWarnings("ALL")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public abstract class ApiUtils {

    /**
     * MD5加密
     **/
    public static String toMD5(String plainText, Integer md5Type) {
        try {
            // 生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            // 使用指定的字节数组更新摘要。
//            md.update(plainText.getBytes());
            md.update(plainText.getBytes("UTF-8"));
            // 通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            // 生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            if (md5Type == 16) {
                return buf.toString().substring(8, 24);
            } else if (md5Type == 32) {
                return buf.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     *
     * @param sourceBean 被提取的对象bean
     * @param targetBean 用于合并的对象bean
     * @return targetBean, 合并后的对象
     */
    public static <T> T combineSydwCore(T sourceBean, T targetBean) {
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            if (Modifier.isStatic(sourceField.getModifiers())) {
                continue;
            }
            Field targetField = targetFields[i];
            if (Modifier.isStatic(targetField.getModifiers())) {
                continue;
            }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (!(sourceField.get(sourceBean) == null) && !"serialVersionUID".equals(sourceField.getName().toString())) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }

    /**
     * list<Map>转换list<bean>
     *
     * @param mapList
     * @param clazz
     * @return
     */
    public static <T> List<T> listMapToListBean(List<Map<String, Object>> mapList, Class<T> clazz) {
        List<T> res = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            res.add(mapToBean(map, clazz));
        }
        return res;
    }

    /**
     * map转实体
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            if (map != null) {
                Field[] declaredFields = clazz.getDeclaredFields();
                if (declaredFields != null) {
                    for (Field declaredField : declaredFields) {
                        declaredField.setAccessible(true);
                        Set<String> mapKeys = map.keySet();
                        for (String mapKey : mapKeys) {
                            //判断属性类型 进行转换,map中存放的是Object对象需要转换 实体类中有多少类型就加多少类型,实体类属性用包装类;
                            if (declaredField.getType().toString().contains("Integer")) {
                                if (declaredField.getName().equals(mapKey)) {
                                    declaredField.set(t, Integer.valueOf(map.get(mapKey).toString()));
                                    break;
                                }
                            }
                            //判断属性类型 进行转换; Double
                            if (declaredField.getType().toString().contains("Double")) {
                                if (declaredField.getName().equals(mapKey)) {
                                    declaredField.set(t, Double.valueOf(map.get(mapKey).toString()));
                                    break;
                                }
                            }
                            //判断属性类型 进行转换;
                            if (declaredField.getType().toString().contains("String")) {
                                if (declaredField.getName().equals(mapKey)) {
                                    declaredField.set(t, map.get(mapKey));
                                    break;
                                }
                            }
                            //判断属性类型 进行转换;LocalDate
                            if (declaredField.getType().toString().contains("LocalDateTime")) {
                                if (declaredField.getName().equals(mapKey)) {
                                    String value = map.get(mapKey).toString();
                                    LocalDateTime times = null;
                                    try {
                                        //专门针对 LocalDateTime 带T输出的字符串
                                        times = LocalDateTime.parse(value);
                                    } catch (Exception e) {
                                        //数据库时间字段读出来为.0 先去.0  尾缀
                                        if (value.indexOf(".0") > 0) {
                                            value = value.substring(0, value.length() - 2);
                                        }
                                        Timestamp timestamp = Timestamp.valueOf(value);
                                        times = timestamp.toLocalDateTime();
                                    }
                                    declaredField.set(t, times);
                                    break;
                                }
                            }
                            //判断属性类型 进行转换;LocalDate
                            if (declaredField.getType().toString().contains("LocalDate")) {
                                if (declaredField.getName().equals(mapKey)) {
                                    LocalDate time = LocalDate.parse(map.get(mapKey).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                    declaredField.set(t, time);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiAssert.isFalse(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("bean转换异常"), true);
        }
        return t;
    }


    /**
     * 获取该类所有字段属性
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static Map<String, String> getEntityType(Class clazz) {
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = AllTypeEntity.class.getDeclaredFields();
        if (declaredFields != null) {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                String type = declaredField.getType().toString();
                String name = declaredField.getName();
//                System.out.println("name = " + name + "\t type = " + type);
                map.put(name, type);
            }
        }
        return map;
    }

    @Test
    public void test() {
        List<Map<String, Object>> maplist = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("integerBigValue", "2");
        map2.put("doubleBigValue", "2.0");
        maplist.add(map2);
        List<AllTypeEntity> dtoRes = ApiUtils.listMapToListBean(maplist, AllTypeEntity.class);
        System.out.println("dtoRes = " + dtoRes);
        System.out.println("BIntegerValue = " + dtoRes.get(0).getIntegerBigValue());
        System.out.println("doubleBigValue = " + dtoRes.get(0).getDoubleBigValue());
    }

}
