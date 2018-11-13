package engine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Florian
 *
 */
public class ReaderUtils {

    private interface WhileReading {
	public void action(String line) throws IOException;
    }

    /**
     * 
     * @param path
     *            - the path of the file.
     * @param wr
     *            - the function that is beeing called while the reader is reading.
     *            takes the current line as a parameter.
     */
    public static void readFile(String path, WhileReading wr) {
	try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
	    if (wr != null) {
		String line;
		while ((line = reader.readLine()) != null) {
		    wr.action(line);
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	List<String> strings = new ArrayList<>();
	ReaderUtils.readFile("C:\\Users\\dichjru\\Desktop\\test.txt", s -> {
	    strings.add(s);
	});
	System.out.println(strings);
    }

}
