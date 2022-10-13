package telran.io;

import java.io.*;

public class CopyClass {
    static final int BYTES_IN_MB = 1_048_576;
    String[] params;

    public CopyClass(String[] params) {
        this.params = params;
    }

    public void getCopy() {
        try {
            startApp(params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void startApp(String[] args) throws Exception {
        if (args.length >= 2) {
            copyFile(args);
        } else {
            throw new Exception("Unknown error");
        }
    }

    private void copyFile(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        File source = new File(args[0]);
        File destination = new File(args[1]);
        boolean isOverwriting = isOverwriting(args);
        checkFiles(args, isOverwriting, source, destination);
        getStreams(source, destination);
        getMessage(source, startTime);
    }

    private static boolean isOverwriting(String[] args) {
        return args.length >= 3 && args[2].equals("overwritten");
    }

    private static void checkFiles(String[] args, boolean isOverwriting, File source, File destination) {
        try {
            if (!source.exists()) {
                throw new FileNotFoundException("Source file <" + args[0] + "> does not exist");
            }
            if (!destination.exists()) {
                try {
                    destination.createNewFile();
                } catch (IOException e) {
                    throw new FileNotFoundException("Destination <" + args[1] + "> has non existed directory in the path");
                }
            } else if (!isOverwriting) {
                throw new Exception("Destination <" + args[1] + "> cannot be overwritten");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getStreams(File source, File destination) throws IOException {
        FileInputStream fis;
        FileOutputStream fos;
        long sourceSize = source.length();
        fis = new FileInputStream(source);
        fos = new FileOutputStream(destination);
        try {
            while (sourceSize > 0) {
                byte[] buffer = new byte[BYTES_IN_MB];
                sourceSize -= fis.read(buffer);
                fos.write(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getMessage(File source, long startTime) {
        System.out.printf("Copied %d bytes for %d milliseconds.\n",
                source.length(),
                System.currentTimeMillis() - startTime);
    }
}