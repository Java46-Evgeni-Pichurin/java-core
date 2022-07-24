package telran.people;

public class Person implements Comparable<Person>{
    private static final String EMAIL_REGEX = "^[\\w\\-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    private long id;
    private int birthYear;
    private String email;

    public Person(long id, int birthYear, String email) {
        this.id = id;
        this.birthYear = birthYear;
        setEmail(email);
    }

    public void setEmail(String email) {
        if (!emailValidator(email)) {
            throw new IllegalArgumentException(String.format("%s - wrong email format", email));
        }
        else {
            this.email = email;
        }
    }

    public long getId() {
        return id;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person { " +
                "id=" + id +
                ", birthYear=" + birthYear +
                ", email='" + email + '\'' +
                " }";
    }
	@Override
	public int compareTo(Person o) {
		
		return Long.compare(this.id, o.id);
	}

    private boolean emailValidator(String email) {
        return email.matches(EMAIL_REGEX);
    }

}
