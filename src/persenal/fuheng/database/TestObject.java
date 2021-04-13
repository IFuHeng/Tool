package com.example.testh264.sqlite;

@SQLiteTable("Test_Table")
public class TestObject<T> {
    int int1;
    Integer int2;
    short short1;
    Short short2;
    byte byte1;
    Byte byte2;
    float float1;
    Float Float2;
    double double1;
    Double Double2;
    long long1;
    Long Long2;
    @SQLiteField("假装变量")
    boolean boolean1;
    Boolean Boolean2;
    String string;
    T t;
}
