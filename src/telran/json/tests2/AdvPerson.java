package telran.json.tests2;

import java.time.LocalDate;

public class AdvPerson {
	String name;
    int age;
    Address address;
    double salary;
    LocalDate bday;

    public AdvPerson() {}
    
	public AdvPerson(String name, int age, Address address, double salary, LocalDate bday) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.salary = salary;
		this.bday = bday;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((bday == null) ? 0 : bday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AdvPerson))
			return false;
		AdvPerson other = (AdvPerson) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (bday == null) {
			if (other.bday != null)
				return false;
		} else if (!bday.equals(other.bday))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
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
