package telran.net.client;

import java.io.*;

public interface NetworkHandler extends Closeable {
    <T> T send(String requestType, Serializable requestData);
}