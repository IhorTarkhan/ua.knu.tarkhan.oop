package ua.knu.tarkhan.oop.problem4;

import java.io.Serializable;
import java.math.BigDecimal;

public class Person extends PersonParent implements Serializable {
    public static Long id;
    int age;
    private BigDecimal graduate;

    public Person() {
    }

    public Person(Long id, String name, int age, BigDecimal graduate) {
        Person.id = id;
        this.name = name;
        this.age = age;
        this.graduate = graduate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Person.id = id;
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
