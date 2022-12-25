package telran.git.service;

import java.nio.file.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

import telran.git.model.*;

public class GitRepositoryImpl implements GitRepository {
    private final String gitPath;
    private final HashMap<String, Commit> commits;
    private final HashMap<Path, CommitFile> commitFiles;
    private final HashMap<String, Branch> branches;
    private String head; // name of current branch or commit
    private String ignoreExpressions = "(\\" + GIT_FILE + ")";

    @Serial
    private static final long serialVersionUID = 1L;
    public static final String COMMIT_PERFORMED = "Commit performed ";
    public static final String COMMIT_NO_BRANCH = "Commit is allowed only to branch";
    public static final String NO_COMMITS = "Branch may be created for existing commit";
    public static final String BRANCH_ALREADY_EXISTS = "Branch already exists";
    public static final String BRANCH_CREATED = "Branch created";
    public static final String BRANCH_NO_EXISTS = "Branch No Exists";
    public static final String BRANCH_RENAMED = "Branch renamed";
    public static final String ACTIVE_BRANCH = "Current branch cannot be removed";
    public static final String ONE_BRANCH = "At least one branch should exist";
    public static final String BRANCH_DELETED = "Branch deleted";
    public static final String NO_HEAD = " No head yet";
    public static final String BRANCH_NAME = " branch ";
    public static final String COMMIT_NAME = " commit ";
    public static final String WRONG_EXPRESSION = " Wrong regex";
    public static final String SWITCH_WITHOUT_COMMIT = "SwitchTo may be done only after commit";
    public static final String SWITCHING_TO_CURRENT = "Switching to the current commit doesn't make a sense";
    public static final String SWITCHED_TO = "Switched to ";

    private GitRepositoryImpl(Path git) {
        this.gitPath = git.toString();
        this.commits = new HashMap<>();
        this.commitFiles = new HashMap<>();
        this.branches = new HashMap<>();
    }

    public static GitRepository init() throws Exception {
        Path path = Path.of(".").toAbsolutePath();
        Path git = path.resolve(GIT_FILE);
        GitRepository res;
        if (Files.exists(git)) {
            res = restoreFromFile(git);
        } else {
            res = new GitRepositoryImpl(git.normalize());
        }
        return res;
    }

    private static GitRepository restoreFromFile(Path gitPath) throws Exception {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(gitPath))) {
            return (GitRepository) input.readObject();
        }
    }

    @Override
    public String commit(String commitMessage) {
        return head == null ? commitHeadNull(commitMessage) : commitHeadNoNull(commitMessage);
    }

    private String commitHeadNoNull(String commitMessage) {
        String res;
        Branch branch = branches.get(head);
        if (branch == null) {
            res = COMMIT_NO_BRANCH;
        } else {
            Commit commit = createCommit(commitMessage, commits.get(branch.commitName));
            branch.commitName = commit.commitName;
            res = COMMIT_PERFORMED;
        }
        return res;
    }

    private Commit createCommit(String message, Commit prev) {
        Commit res = new Commit();
        res.commitName = getCommitName();
        res.commitMessage = message;
        res.prev = prev;
        res.commitFiles = getCommitContent(res.commitName);
        commits.put(res.commitName, res);
        return res;
    }

    private List<CommitFile> getCommitContent(String commitName) {
        List<FileState> files = info();
        return files.stream().filter(fs -> fs.status == Status.UNTRACKED || fs.status == Status.MODIFIED)
                .map(fs -> toCommitFile(fs, commitName)).toList();
    }

    private CommitFile toCommitFile(FileState fs, String commitName) {
        Instant timeModified;
        try {
            timeModified = Files.getLastModifiedTime(fs.path).toInstant();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        List<String> content = getFileContent(fs.path);
        CommitFile res = new CommitFile(fs.path, timeModified, content, commitName);
        //Assumption neither rename nor delete
        commitFiles.put(fs.path, res);
        return res;
    }

    private List<String> getFileContent(Path path) {
        try {
            return Files.lines(path).toList();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private String getCommitName() {
        String res = "";
        do {
            res = Integer.toString(ThreadLocalRandom.current()
                    .nextInt(0x1000000, 0xfffffff), 16);
        } while (commits.containsKey(res));
        return res;
    }

    private String commitHeadNull(String commitMessage) {
        Commit commit = createCommit(commitMessage, null);
        createInternalBranch("master", commit);
        return COMMIT_PERFORMED;
    }

    private void createInternalBranch(String branchName, Commit commit) {
        if (branches.containsKey(branchName)) {
            throw new IllegalStateException(String.format("Branch %s already exists", branchName));
        }
        Branch branch = new Branch();
        branch.branchName = branchName;
        branch.commitName = commit.commitName;
        branches.put(branchName, branch);
        head = branchName;
    }

    @Override
    public List<FileState> info() {
        Path directoryPath = Path.of(".");
        try {
            return Files.list(directoryPath).map(Path::normalize)
                    .filter(p -> !ignoreFilter(p))
                    .map(p -> {
                        try {
                            return new FileState(p, getStatus(p));
                        } catch (IOException e) {
                            throw new RuntimeException(e.toString());
                        }
                    }).toList();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private boolean ignoreFilter(Path p) {
        // Assumption no nested directories
        return !Files.isRegularFile(p) || p.toString().matches(ignoreExpressions);
    }

    private Status getStatus(Path p) throws IOException {
        CommitFile commitFile = commitFiles.get(p);
        return commitFile == null ? Status.UNTRACKED :
                getStatusFromCommitFile(commitFile, p);
    }

    private Status getStatusFromCommitFile(CommitFile commitFile, Path p) throws IOException {
        return Files.getLastModifiedTime(p).toInstant()
                .compareTo(commitFile.modificationTime) > 0 ? Status.MODIFIED
                : Status.COMMITTED;
    }

    @Override
    public String createBranch(String branchName) {
        String res;
        if (commits.isEmpty()) {
            res = NO_COMMITS;
        } else if (branches.containsKey(branchName)) {
            res = BRANCH_ALREADY_EXISTS;
        } else {
            Commit commit = getCommit();
            createInternalBranch(branchName, commit);
            res = BRANCH_CREATED;
        }
        return res;
    }

    private Commit getCommit() {
        Branch branch = branches.get(head);
        String commitName = branch != null ? branch.commitName : head;
        Commit res = commits.get(commitName);
        if (res == null) {
            throw new IllegalStateException("no commit with the name " + commitName);
        }
        return res;
    }

    @Override
    public String renameBranch(String branchName, String newName) {
        String res = branchName + " " + BRANCH_NO_EXISTS;
        Branch branch = branches.get(branchName);
        if (branch != null) {
            if (branches.containsKey(newName)) {
                res = newName + " " + BRANCH_ALREADY_EXISTS;
            } else {
                branch.branchName = newName;
                branches.remove(branchName);
                branches.put(newName, branch);
                if (head.equals(branchName)) {
                    head = branchName;
                }
                res = BRANCH_RENAMED;
            }
        }
        return res;
    }

    @Override
    public String deleteBranch(String branchName) {
        String res = BRANCH_NO_EXISTS;
        if (branches.containsKey(branchName)) {
            if (head.equals(branchName)) {
                res = ACTIVE_BRANCH;
            } else if (branches.size() == 1) {
                res = ONE_BRANCH;
            } else {
                branches.remove(branchName);
                res = BRANCH_DELETED;
            }
        }
        return res;
    }

    @Override
    public List<CommitMessage> log() {
        List<CommitMessage> res = new ArrayList<>();
        if (head != null) {
            Branch branch = branches.get(head);
            String commitName = branch != null ? branch.commitName : head;
            Commit commit = commits.get(commitName);
            if (commit == null) {
                throw new IllegalStateException("no commit with name " + commitName);
            }
            while (commit != null) {
                res.add(new CommitMessage(commit.commitName, commit.commitMessage));
                commit = commit.prev;
            }
        }
        return res;
    }

    @Override
    public List<String> branches() {

        return branches.values().stream().map(b -> {
            String res = b.branchName;
            if (head.equals(res)) {
                res += "*";
            }
            return res;
        }).toList();
    }

    @Override
    public List<Path> commitContent(String commitName) {
        Commit commit = commits.get(commitName);
        if (commit == null) {
            throw new IllegalArgumentException(commitName + " doesn't exist");
        }
        return commit.commitFiles.stream().map(cf -> cf.path).toList();
    }

    @Override
    public String switchTo(String name) {
        String res = "";
        Commit commit = commits.get(name);
        Status status;
        Path currentDirectory = Paths.get(".").toAbsolutePath().normalize();
        try {
            status = getStatus(currentDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (commit != null) {
            if (status == Status.UNTRACKED || status == Status.MODIFIED) {
                res = SWITCH_WITHOUT_COMMIT;
            }
            else if (name.equals(head)) {
                res = SWITCHING_TO_CURRENT;
            }
            else {
                switchContent(commit, currentDirectory);
                res = SWITCHED_TO + commit.commitName;
            }
        }
        else {
            throw new IllegalArgumentException(name + " doesn't exist");
        }
        return res;
    }

    private void switchContent(Commit commit, Path currentDirectory) {
        List<Path> direction = commitContent(commit.commitName);
        HashMap<Path, Path> map = new HashMap<>();
        direction.forEach(f -> map.put(f, f));
        currentDirectory.forEach(curFile -> {
            try {
                if (map.containsKey(curFile)
                        && Files.getLastModifiedTime(curFile)
                            .compareTo(Files.getLastModifiedTime(map.get(curFile))) != 0) {
                    curFile = map.get(curFile);
                }
                else {
                    Files.delete(curFile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public String getHead() {
        String res = NO_HEAD;
        if (head != null) {
            res = branches.containsKey(head) ? BRANCH_NAME : COMMIT_NAME;
            res += head;
        }
        return res;
    }

    @Override
    public void save() {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Path.of(gitPath)))) {
            output.writeObject(this);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String addIgnoredFileNameExp(String regex) {
        checkRegex(regex);
        ignoreExpressions += String.format("|(%s)", regex);
        return String.format("Regex for files ignored is %s", ignoreExpressions);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void checkRegex(String regex) {
        try {
            "test".matches(regex);
        } catch (Exception e) {
            throw new IllegalArgumentException(regex + WRONG_EXPRESSION);
        }
    }
}