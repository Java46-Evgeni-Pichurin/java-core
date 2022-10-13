package telran.io;

import org.junit.jupiter.api.*;

import java.io.*;

class FileTests {
    static final String TYPE_DIR = " [type = dir]";
    static final String TYPE_FILE = " [type = file]";

    @Test
    void printDirectoryContent() {
        printDirectory("..", 3);
    }

    private void printDirectory(String pathName, int level) {
        File file = new File(pathName);
        print(file, "", level);
    }

    private void print(File file, String tabs, int level) {
        if (level-- == 0) {
            return;
        }
        if (file.isDirectory()) {
            System.out.println(tabs + file.getName() + TYPE_DIR);
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    if (subFile.isDirectory()) {
                        print(subFile, tabs + "\t", level);
                    } else {
                        System.out.println(tabs + "\t" + subFile.getName() + TYPE_FILE);
                    }
                }
            }
        } else {
            System.out.println(file.getName() + TYPE_FILE);
        }
    }
}
