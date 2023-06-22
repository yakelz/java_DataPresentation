package Dictionary;

import Dictionary.ClosedHash.Dictionary;
//import Dictionary.OpenHash.Dictionary;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "Dictionary/names.txt";
    private static final int NAME_SIZE = 10;
    public static void showHelp() {
        System.out.println("F <имя> - поместить человека в список хороших");
        System.out.println("U <имя> - поместить человека в список плохих");
        System.out.println("? <имя> - к какому классу принадлежит человек");
        System.out.println("P goodGuys - вывести список хороших");
        System.out.println("P badGuys - вывести список плохих");
        System.out.println("E - выход из программы" + "\n");
    }

    public static char[] StringToCharArray(String input) {
        char[] output = new char[NAME_SIZE];
        for (int i = 0; i < NAME_SIZE; i++) {
            if (i < input.length()) {
                output[i] = input.charAt(i);
            } else {
                output[i] = '\0';
            }
        }
        return output;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Dictionary goodGuys = new Dictionary(30);
        Dictionary badGuys = new Dictionary(30);

        FileReader reader = new FileReader(FILE_NAME);
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine()) {
            char[] line = StringToCharArray(scanner.nextLine());
            goodGuys.insert(line);
        }

        showHelp();
        scanner = new Scanner(System.in);

        while (true) {
            // Считываем ввод пользователя
            System.out.print("Введите команду: ");
            String input = scanner.nextLine().trim();
            // Разделяем команду и имя на две части
            String[] parts = input.split(" ");
            String command = parts[0].toUpperCase();
            String n = parts.length > 1 ? parts[1] : "";
            char[] name = StringToCharArray(n);

            switch (command) {
                case "?":
                    // Проверяем, к какому классу принадлежит человек
                    if (goodGuys.member(name)) {
                        System.out.println(n + " - хороший парень");
                    } else if (badGuys.member(name)) {
                        System.out.println(n + " - плохой парень");
                    } else {
                        System.out.println(n + " - неизвестный");
                    }
                    break;
                case "F":
                    if (badGuys.member(name)) {
                        goodGuys.insert(name);
                        badGuys.delete(name);
                        System.out.println(n + " - добавлен в список хороших парней");
                    } else if (badGuys.member(name)) {
                        System.out.println(n + " - уже хороший парень");
                    } else {
                        System.out.println(n + " - неизвестный");
                    }
                    break;
                case "U":
                    if (goodGuys.member(name)) {
                        badGuys.insert(name);
                        goodGuys.delete(name);
                        System.out.println(n + " - добавлен в список плохих парней");
                    } else if (badGuys.member(name)) {
                        System.out.println(n + " - уже плохой парень");
                    } else {
                        System.out.println(n + " - неизвестный");
                    }
                    break;
                case "P":
                    if (n.equalsIgnoreCase("goodGuys")) {
                        System.out.println("Список хороших парней:");
                        goodGuys.print();
                    } else if (n.equalsIgnoreCase("badGuys")) {
                        System.out.println("Список плохих парней:");
                        badGuys.print();
                    } else {
                        System.out.println("Некорректная команда P");
                    }
                    break;
                case "E":
                    System.out.println("Выход из программы");
                    System.exit(0);
                default:
                    System.out.println("Неизвестная команда");
                    break;
            }
        }
    }

}
