package lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String[] array = "о горячий суп наварили о великий суп наварили о шикарный суп наварили о роскошный суп наварили суп горячий суп ешь суп горячий суп".split(" ");
        HashMap<String, Integer> counters = new HashMap<>();
        for (String s : array) {
            if (counters.containsKey(s)) {
                counters.put(s, counters.get(s) + 1);
            } else {
                counters.put(s, 1);
            }
        }
        System.out.println("Список слов, из которых состоит текст:");
        for (String s : counters.keySet()) {
            System.out.println(s);
        }

        System.out.println("Частота повторения слов:");
        for (String s : counters.keySet()) {
            System.out.println(s + ": " + counters.get(s));
        }

        PhoneBook book = new PhoneBook();
        book.add("Шимаров", "+79996661122", "premier@gov.no");
        book.add("Страшный", "+78885550011", "vice@gov.no");
        book.add("Страшный", "+79994561122", "premier@gov.nl");
        book.add("Смешной", "+79973843122", "premier@gov.se");
        book.add("Хапайсало", "+79954522322", "ceo@ms.ro");

        printPhones(book, "Страшный");
        printPhones(book, "Грустный");

    }

    private static void printPhones(PhoneBook book, String familyName) {
        System.out.println(familyName);
        ArrayList<String> phones = book.getPhones(familyName);
        if (phones.isEmpty())
        {
            System.out.println("По фамилии " + familyName + " записей не найдено");
        } else {
            for (String phone : phones) {
                System.out.println(phone);
            }

        }
    }
}
