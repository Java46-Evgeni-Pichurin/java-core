package telran.git.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Commit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    public String commitName;
    public String commitMessage;
    public Commit prev;
    public List<CommitFile> commitFiles;
}