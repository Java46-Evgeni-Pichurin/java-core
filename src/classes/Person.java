package classes;

public class Person {
    private static final String EMAIL_REGEX = "^[\\w\\-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    private long id;
    private int birthYear;
    private String email;

    public Person(long id, int birthYear, String email) {
        this.id = id;
        this.birthYear = birthYear;
        this.email = emailValidator(email);
    }

    public void setEmail(String email) {
        this.email = emailValidator(email);
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

    private String emailValidator(String email) {
        return email.matches(EMAIL_REGEX) ? email : null;
    }

}
