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

    // Подрезает имя до 10 символов
    private static String StringResizer(String str, int n) {
        if (str == null) {
            return null;
        }
        return str.substring(0, Math.min(str.length(), n));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Dictionary goodGuys = new Dictionary(30);
        Dictionary badGuys = new Dictionary(30);

        FileReader reader = new FileReader(FILE_NAME);
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine()) {
            String line = StringResizer(scanner.nextLine(), NAME_SIZE);
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
            String name = parts.length > 1 ? parts[1] : "";

            switch (command) {
                case "?":
                    // Проверяем, к какому классу принадлежит человек
                    if (goodGuys.member(name)) {
                        System.out.println(name + " - хороший парень");
                    } else if (badGuys.member(name)) {
                        System.out.println(name + " - плохой парень");
                    } else {
                        System.out.println(name + " - неизвестный");
                    }
                    break;
                case "F":
                    if (badGuys.member(name)) {
                        goodGuys.insert(name);
                        badGuys.delete(name);
                        System.out.println(name + " - добавлен в список хороших парней");
                    } else if (badGuys.member(name)) {
                        System.out.println(name + " - уже хороший парень");
                    } else {
                        System.out.println(name + " - неизвестный");
                    }
                    break;
                case "U":
                    if (goodGuys.member(name)) {
                        badGuys.insert(name);
                        goodGuys.delete(name);
                        System.out.println(name + " - добавлен в список плохих парней");
                    } else if (badGuys.member(name)) {
                        System.out.println(name + " - уже плохой парень");
                    } else {
                        System.out.println(name + " - неизвестный");
                    }
                    break;
                case "P":
                    if (name.equalsIgnoreCase("goodGuys")) {
                        System.out.println("Список хороших парней:");
                        goodGuys.print();
                    } else if (name.equalsIgnoreCase("badGuys")) {
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
