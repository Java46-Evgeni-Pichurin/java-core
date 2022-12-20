package telran.json.tests;

import telran.json.annotations.JsonField;

import java.time.LocalDate;
import java.util.Objects;

public class AdvPerson{
    String name;
    int age;
    Address address;
    double salary;
    LocalDate bday;

    public AdvPerson() {
    }

    public AdvPerson(String name, int age, Address address, double salary, LocalDate bday) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.bday = bday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdvPerson)) return false;
        AdvPerson advPerson = (AdvPerson) o;
        return age == advPerson.age &&
                Double.compare(advPerson.salary, salary) == 0 &&
                name.equals(advPerson.name) &&
                address.equals(advPerson.address) &&
                bday.equals(advPerson.bday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address, salary, bday);
    }

    @Override
    public String toString() {
        return "AdvPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", salary=" + salary +
                ", bday=" + bday +
                '}';
    }
}
