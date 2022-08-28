package telran.oop.people;

public class Person implements Comparable<Person>{
	private long id;
	private int birthYear;
	private String email;
	
	public Person(long id, int birthYear, String email) {
		this.id = id;
		this.birthYear = birthYear;
		setEmail(email);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		
		//Regex simplified <first part>@<second part>
		// <first part> - letter, digit, dot, dash
		//second part - letter, dot
		if (email != null && !email.matches(emailRegex())) {
			throw new IllegalArgumentException(String.format("%s wrong email", email));
		}
		this.email = email;
	}
	private String emailRegex() {
		String prefix = "[A-Za-z\\d]([A-Za-z\\d]|[._-][A-Za-z\\d])+";
		String domain = "[A-Za-z\\d]([A-Za-z\\d]|-[A-Za-z\\d])+(\\.[A-Za-z\\d]([A-Za-z\\d]|-[A-Za-z\\d])+){1,4}";
		return String.format("(%s)@(%s)", prefix, domain);
	}
	public long getId() {
		return id;
	}
	public int getBirthYear() {
		return birthYear;
	}
	@Override
	public int compareTo(Person o) {
		
		return Long.compare(this.id, o.id);
	}
	
	

}
