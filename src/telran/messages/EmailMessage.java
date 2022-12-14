package telran.messages;

public class EmailMessage implements Message {
    String emailAddress;

    public EmailMessage(String emailAddress) {
		if (emailAddress.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}")) {
			this.emailAddress = emailAddress;
		}
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public void send(String text) {
        System.out.printf("email message '%s' has been sent to email address %s\n", text, emailAddress);
    }
}
