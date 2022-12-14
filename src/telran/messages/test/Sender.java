package telran.messages.test;

import telran.messages.Message;
import telran.view.InputOutput;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Sender {
    private static final String BASE_PACKAGE = "telran.messages.";
    @SuppressWarnings("unchecked")
    public static void send(InputOutput io) {
        String type = io.readString("Enter type of your message: ");
        try {
            Class<Message> clazz = (Class<Message>) Class.forName(BASE_PACKAGE + type);
            String text = io.readString("Enter your message: ");
            connect(io, clazz, text);
        } catch (ClassNotFoundException e) {
            io.writeLine("Wrong type!");
            send(io);
        }
    }

    private static void connect(InputOutput io, Class<Message> clazz, String text) {
        String receiver = io.readString("Send to:");
        Message message;
        try {
            Constructor<Message> constructor = clazz.getConstructor(String.class);
            message = constructor.newInstance(receiver);
            clazz.getDeclaredMethod("send", String.class).invoke(message, text);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            io.writeLine(String.format("Wrong %s", clazz.getDeclaredFields()[0].getName()));
            connect(io, clazz, text);
        }
    }
}
