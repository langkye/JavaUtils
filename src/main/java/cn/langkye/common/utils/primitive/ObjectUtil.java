package cn.langkye.common.utils.primitive;

import java.lang.reflect.Field;

/**
 * 对象工具类
 *
 * @author langkye
 * @date 2021/6/30
 */
public class ObjectUtil {

    /**
     * 判断对象是否为空，即null或""（排除序列字段[serialVersionUID]）
     *
     * @param object obj
     * @return true or false
     */
    public static boolean objectAllFieldsIsNull(Object object){
        if ( object == null || "".equals(object.toString().trim())){
            return true;
        }

        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                final String fieldName = field.getName();

                if ("serialVersionUID".equals(fieldName)){
                    continue;
                }
                if (field.get(object) != null && "".equals(field.get(object).toString().trim())){
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("ex: " + e.getMessage());
        }

        return true;
    }

    /**
     * 判断所有对象字段是否为空，即null或""（排除序列字段[serialVersionUID]）
     *
     * @param objects obj
     * @return true or false
     */
    public static boolean allObjectsAllFieldsIsNull(Object ...objects){
        if (objects == null || objects.length == 0){
            return true;
        }
        for (Object object : objects) {
            return objectAllFieldsIsNull(object);
        }

        return true;
    }
}
