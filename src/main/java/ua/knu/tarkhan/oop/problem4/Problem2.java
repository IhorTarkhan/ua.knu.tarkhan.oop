package ua.knu.tarkhan.oop.problem4;

public class Problem2 {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("ua.knu.tarkhan.oop.problem4.Person");
        myClassLoader.showAllInfo();
    }
}
