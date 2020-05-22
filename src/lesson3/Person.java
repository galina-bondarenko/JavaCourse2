package lesson3;

import java.util.Objects;

public class Person {
    String phone;
    String email;

    public Person(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return phone.equals(person.phone) &&
                email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
}
