package ua.knu.tarkhan.oop.problem5;

public class Problem5 {
  public static void main(String[] args) {
    SkipList integers = new SkipList(1000);
    integers.add(1221);
    integers.add(1222);
    integers.add(1223);
    integers.add(1224);

    System.out.println(integers.size());
  }
}
