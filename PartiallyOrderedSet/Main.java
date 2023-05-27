package PartiallyOrderedSet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Main {
    private static final String FILE_NAME = "PartiallyOrderedSet/relations.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Set set = new Set();

        FileReader reader = new FileReader(FILE_NAME);
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String input = line.trim();
            String[] parts = input.split("<");
            set.addElement(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }

        set.print();
        set.sort();
        System.out.println();
        set.print();
    }

}
