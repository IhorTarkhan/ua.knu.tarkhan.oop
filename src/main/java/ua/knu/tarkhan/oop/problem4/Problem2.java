package ua.knu.tarkhan.oop.problem4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Problem2 {
    public static void main(String[] args) {
        Class<?> objectClass = Person.class;
        printInfo(objectClass);
    }

    private static void printInfo(Class<?> objectClass) {
        System.out.println(objectClass.getName());
        System.out.println();

        Constructor<?>[] constructors = objectClass.getDeclaredConstructors();
        System.out.println("Constructors:");
        for (Constructor<?> constructor : constructors) {
            System.out.println("\t" + constructor);
        }
        System.out.println();

        Field[] fields = objectClass.getDeclaredFields();
        System.out.println("Fields:");
        for (Field field : fields) {
            System.out.println("\t" + field);
        }
        System.out.println();

        Method[] methods = objectClass.getDeclaredMethods();
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println("\t" + method);
        }
    }
}
