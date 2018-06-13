package ru.ncedu.Dmdrozhzhin;

import javax.annotation.Generated;
import javax.annotation.Resource;
import javax.xml.ws.soap.Addressing;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@myAnn
@Deprecated
public class TestClass extends ParentTestClass implements Serializable, Cloneable {
    private int field;
    @myAnn
    protected String[] strings;
    private static final Long serialNumber = 500L;
    public List array;

    public  TestClass() {

    }

    public TestClass(Integer field) {
        this.field = field;
    }
    public TestClass(Integer field,String str) {
        this.field = field;
    }
    @myAnn
    @Deprecated
    protected static void method(@myAnn String[] params, @Deprecated Integer i) { }


    public void foo() {
        System.out.println("FOO");
    }
    @myAnn
    @Override
    public String toString() {
        return "Test{" +
                "field=" + field +
                '}';
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface myAnn{
    boolean value() default  true;
}