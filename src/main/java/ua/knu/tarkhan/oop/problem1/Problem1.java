package ua.knu.tarkhan.oop.problem1;

import ua.knu.tarkhan.oop.Path;

import java.io.*;

public class Problem1 {
    public static void main(String[] args) {
        String filename = Path.ROOT + "problem1/file.ser";
        DemoSerializable object = new DemoSerializable(1, "test");

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

    private static DemoSerializable deserialize(String filename) {
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(file)) {
            return (DemoSerializable) in.readObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
