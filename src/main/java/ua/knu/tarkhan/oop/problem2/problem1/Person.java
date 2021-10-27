package ua.knu.tarkhan.oop.problem2.problem1;

import java.math.BigDecimal;

public class Person {
    public static Long id;
    protected String name;
    int age;
    private BigDecimal graduate;

    public Person() {
    }

    public Person(Long id, String name, int age, BigDecimal graduate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.graduate = graduate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getGraduate() {
        return graduate;
    }

    public void setGraduate(BigDecimal graduate) {
        this.graduate = graduate;
    }

}
