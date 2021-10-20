package ua.knu.tarkhan.oop.problem1;

import java.io.*;

public class TestClass {
  public static void main(String[] args) {
    String filename = "file.ser";
    Demo object = new Demo(1, "test");

    serialize(object, filename);
    var deserialized = deserialize(filename);

    System.out.println("Object has been deserialized ");
    System.out.println("a = " + deserialized.a);
    System.out.println("b = " + deserialized.b);
  }

  private static void serialize(Serializable object, String filename) {
    try (FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file)) {
      out.writeObject(object);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static Demo deserialize(String filename) {
    try (FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file)) {
      return (Demo) in.readObject();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}

class Demo implements Serializable {
  public int a;
  public String b;

  // Default constructor
  public Demo(int a, String b) {
    this.a = a;
    this.b = b;
  }
}
