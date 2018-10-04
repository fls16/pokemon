package engine.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    public static List<File> listFilesForFolder(String path) {
	File folder = new File(path);
	List<File> files = new ArrayList<>();
	for (final File fileEntry : folder.listFiles()) {
	    if (fileEntry.isDirectory()) {
		// listFilesForFolder(fileEntry);
	    } else {
		files.add(fileEntry);
		System.out.println(fileEntry.getName());
	    }
	}
	return files;
    }

}
