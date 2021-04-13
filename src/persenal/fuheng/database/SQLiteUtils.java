package com.example.testh264.sqlite;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class SQLiteUtils {

    /**
     * 创建sql的创建命令
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static final <T> String getSqlCreateCMD(Class<T> tClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE  IF NOT EXISTS ");
        sb.append(getTableName(tClass));
        sb.append(' ');
        sb.append("(ID INTEGER PRIMARY KEY   AUTOINCREMENT");
        for (Field declaredField : tClass.getDeclaredFields()) {

            Class<?> fieldClass = declaredField.getType();
            sb.append(',').append(getFieldName(declaredField)).append(' ');
            if (fieldClass == int.class || fieldClass == Integer.class) {
                sb.append("INT");
            } else if (fieldClass == short.class || fieldClass == Short.class) {
                sb.append("INT16");
            } else if (fieldClass == byte.class || fieldClass == Byte.class) {
                sb.append("INT8");
            } else if (fieldClass == long.class || fieldClass == Long.class) {
                sb.append("LONG");
            } else if (fieldClass == float.class || fieldClass == Float.class) {
                sb.append("FLOAT");
            } else if (fieldClass == double.class || fieldClass == Double.class) {
                sb.append("DOUBLE");
            } else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
                sb.append("INT2");
            } else if (fieldClass == String.class) {
                sb.append("TEXT");
            } else
                sb.append("TEXT");
        }
        sb.append(')').append(';');
        return sb.toString();
    }


    /**
     * 获取类型的注解信息当表格名。
     *
     * @param cls 类型
     * @return 如果类有注解，返回注解字符串，否则返回 {报名.类名}
     */
    static final String getTableName(Class cls) {
        String result = cls.getName();
        Annotation[] annotations = cls.getDeclaredAnnotations();
        if (annotations == null || annotations.length == 0)
            return result;

        for (Annotation annotation : annotations) {
            if (!(annotation instanceof SQLiteTable)) {
                continue;
            }
            result = ((SQLiteTable) annotation).value();
            break;
        }
        return result;
    }

    /**
     * 获取类型的注解信息当表格名。
     *
     * @param field 属性名
     * @return 如果类有注解，返回注解字符串，否则返回 {报名.类名}
     */
    private static final String getFieldName(Field field) {
        String result = field.getName();
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations == null || annotations.length == 0)
            return result;

        for (Annotation annotation : annotations) {
            if (!(annotation instanceof SQLiteField)) {
                continue;
            }
            result = ((SQLiteField) annotation).value();
            break;
        }
        return result;
    }

}
