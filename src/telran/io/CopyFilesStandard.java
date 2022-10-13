package telran.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class CopyFilesStandard extends CopyClass {

	public CopyFilesStandard(String[] params) {
		super(params);
	}

	@Override
	public void getStreams(File source, File destination) throws IOException {
		Files.copy(source.toPath(), new FileOutputStream(destination));
	}

	public static void main(String[] args) {
		CopyFilesStandard copy = new CopyFilesStandard(args);
		copy.getCopy();
	}
}