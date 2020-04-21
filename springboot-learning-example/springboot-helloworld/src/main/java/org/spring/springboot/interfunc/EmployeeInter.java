package org.spring.springboot.interfunc;

public interface EmployeeInter {
    public static final String COMMON = "IT";

    /**
     * default
     * truck
     */
    public default void truck() {
        System.out.println("MMMMMMMMMMMMMMMMMMM!");
    }

    /**
     * //抽象方法
     */
    public abstract void print();

    /**
     * static 方法
     */
    static void staticMethod() {
        System.out.println("static method invoked!VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV ");
    }

    /**
     * train
     */
    public void train();

    /**
     * air
     */
    void air();
}
