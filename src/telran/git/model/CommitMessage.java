package telran.git.model;

public class CommitMessage {
    public String commitName;
    public String commitMessage;

    @Override
    public String toString() {
        return String.format("%s: %s", commitName, commitMessage);
    }

    public CommitMessage(String commitName, String commitMessage) {
        super();
        this.commitName = commitName;
        this.commitMessage = commitMessage;
    }
}
