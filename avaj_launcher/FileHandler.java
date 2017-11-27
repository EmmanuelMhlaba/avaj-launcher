package avaj_launcher;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public final class FileHandler {
    private FileHandler () {}
    private static List<String> buffer = new ArrayList<String> ();

    public static void addToBuffer (String line) {
        buffer.add (line);
    }

    public static void writeSimFile (String filename) {
        writeFile (filename, buffer);
    }

    private static void fileError (String action, String message) {
        String msg = "Error while attempting to " + action + " the file (" + message + ").";
        System.out.println (msg);
    }

    public static List<String> readFile (String filename) {
        List<String> fileContent = new ArrayList<String> ();
        File file = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line;
        try {
            file = new File (filename);
            if (file.exists () && file.isDirectory () == false) {
                fileReader = new FileReader (file);
                bufferedReader = new BufferedReader (fileReader);
                while ((line = bufferedReader.readLine ()) != null) {
                    fileContent.add (line);
                }
            }
        } catch (IOException e) {
            fileError ("read", e.getMessage ());
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close ();
                }
                if (bufferedReader != null) {
                    bufferedReader.close ();
                }
            } catch (IOException e) {
                fileError ("close", e.getMessage ());
            }
        }
        return fileContent;
    }

    public static void writeFile (String filename, List<String> fileContent) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter (filename);
            bufferedWriter = new BufferedWriter (fileWriter);
            for (String line : fileContent) {
                bufferedWriter.write (line);
                bufferedWriter.newLine ();
            }
        } catch (IOException e) {
            fileError ("write", e.getMessage ());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close ();
                }
                if (fileWriter != null) {
                    fileWriter.close ();
                }
            } catch (IOException e) {
                fileError ("close", e.getMessage ());
            }
        }
    }
}