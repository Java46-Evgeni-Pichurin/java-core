package telran.messages;

public class SmsMessage implements Message {
    String phoneNumber;

    public SmsMessage(String phoneNumber) {
        if (phoneNumber.matches("^05\\d-?\\d{7}$")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void send(String text) {
        System.out.printf("sms message '%s' has been sent to phone %s\n", text, phoneNumber);
    }
}
