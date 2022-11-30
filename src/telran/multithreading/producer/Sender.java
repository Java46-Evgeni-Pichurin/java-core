package telran.multithreading.producer;

import telran.multithreading.MessageBox;

public class Sender extends Thread {
	private final MessageBox messageBox;
	private final int nMessages;

	public Sender(MessageBox messageBox, int nMessages) {
		this.nMessages = nMessages;
		this.messageBox = messageBox;
	}
	@Override
	public void run() {
		for(int i = 0; i < nMessages; i++) {
			try {
				messageBox.put(String.format("Message-%d from ", i));
			} catch (InterruptedException ignored) {}
		}
		while (!messageBox.finished()) {
			if (messageBox.take() == null) {
				messageBox.setFinished(true);
			}
		}
	}
}