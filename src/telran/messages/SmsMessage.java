package telran.messages;

public class SmsMessage implements Message {
    String phoneNumber;

    public SmsMessage(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String text) {
        if (text.matches("^(\\d{3}[- .]?){2}\\d{4}$")) {
            System.out.printf("sms message '%s' has been sent to phone %s\n", text, phoneNumber);
        }
        else {
            throw new RuntimeException();
        }
    }
}
