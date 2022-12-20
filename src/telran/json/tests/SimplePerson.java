package telran.json.tests;

import java.time.LocalDate;
import java.util.Objects;

public class SimplePerson{
    String name;
    int age;
    String address;
    double salary;
    LocalDate bday;

    public SimplePerson() {
    }

    public SimplePerson(String name, int age, String address, double salary, LocalDate bday) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.bday = bday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimplePerson)) return false;
        SimplePerson that = (SimplePerson) o;
        return age == that.age &&
                Double.compare(that.salary, salary) == 0 &&
                name.equals(that.name) &&
                address.equals(that.address) &&
                bday.equals(that.bday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address, salary, bday);
    }

    @Override
    public String toString() {
        return "SimplePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", bday=" + bday +
                '}';
    }
}
