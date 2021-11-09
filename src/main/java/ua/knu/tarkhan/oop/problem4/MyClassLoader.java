package ua.knu.tarkhan.oop.problem4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyClassLoader {
    private Class<?> objectClass;

    public MyClassLoader(String name) {
        try {
            this.objectClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showAllInfo() {
        Class<?> superclass = objectClass.getSuperclass();
        String extension;
        if (superclass == Object.class) {
            extension = "";
        } else {
            extension = " extends " + superclass.getName();
        }
        Class<?>[] interfaces = objectClass.getInterfaces();
        String implementations;
        if (interfaces.length == 0) {
            implementations = "";
        } else {
            implementations = " implements " + Arrays.stream(interfaces)
                    .map(Class::getName)
                    .collect(Collectors.joining(", "));
        }
        System.out.println(objectClass.getName() + extension + implementations);
        System.out.println();

        Constructor<?>[] constructors = objectClass.getDeclaredConstructors();
        System.out.println("Constructors:");
        for (Constructor<?> constructor : constructors) {
            System.out.println("\t" + constructor);
        }
        System.out.println();

        Field[] fields = getFields(objectClass);
        System.out.println("Fields:");
        for (Field field : fields) {
            System.out.println("\t" + field);
        }
        System.out.println();

        Method[] methods = getMethods(objectClass);
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println("\t" + method);
        }
    }

    private Field[] getFields(Class<?> objectClass) {
        Field[] declaredFields = objectClass.getDeclaredFields();
        if (objectClass.getSuperclass() != Object.class) {
            declaredFields = Stream.of(declaredFields, getFields(objectClass.getSuperclass()))
                    .flatMap(Stream::of)
                    .toArray(Field[]::new);
        }
        return declaredFields;
    }

    private Method[] getMethods(Class<?> objectClass) {
        Method[] declaredMethods = objectClass.getDeclaredMethods();
        if (objectClass.getSuperclass() != Object.class) {
            declaredMethods = Stream.of(declaredMethods, getMethods(objectClass.getSuperclass()))
                    .flatMap(Stream::of)
                    .toArray(Method[]::new);
        }
        return declaredMethods;
    }
}
