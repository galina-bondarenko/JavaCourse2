package lesson3;

import java.util.*;

public class PhoneBook {
    Map<String, Set<Person>> data = new HashMap<>();

    public ArrayList<String> getPhones(String familyName)
    {
        ArrayList<String> result = new ArrayList<>();
        if (data.containsKey(familyName))
        {
            for (Person person : data.get(familyName)) {
                result.add(person.phone);
            }

        }
        return result;
    }

    public ArrayList<String> getEmail(String familyName)
    {
        ArrayList<String> result = new ArrayList<>();
        if (data.containsKey(familyName))
        {
            for (Person person : data.get(familyName)) {
                result.add(person.email);
            }

        }
        return result;
    }

    public void add(String familyName, String phone, String email)
    {
        if (!data.containsKey(familyName))
            data.put(familyName, new HashSet<>());

        data.get(familyName).add(new Person(phone, email));
    }
}
