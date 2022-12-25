package telran.git.model;

import java.io.Serial;
import java.io.Serializable;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

public class CommitFile implements Serializable {

    @Serial
	private static final long serialVersionUID = 1L;
    public Path path;
    public Instant modificationTime;
    public List<String> content;
    public String commitName;

    public CommitFile(Path path, Instant modificationTime, List<String> content, String commitName) {
        this.path = path;
        this.modificationTime = modificationTime;
        this.content = content;
        this.commitName = commitName;
    }
}