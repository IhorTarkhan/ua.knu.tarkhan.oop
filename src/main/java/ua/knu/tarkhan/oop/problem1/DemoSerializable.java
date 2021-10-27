package ua.knu.tarkhan.oop.problem1;

import java.io.Serializable;

class DemoSerializable implements Serializable {
    public int a;
    public String b;

    public DemoSerializable(int a, String b) {
        this.a = a;
        this.b = b;
    }
}
