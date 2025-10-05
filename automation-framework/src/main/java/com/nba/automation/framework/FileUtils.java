package com.nba.automation.framework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    /** Writes a list of strings to a file, one per line */
    public static void writeListToFile(String filePath, List<String> lines) throws IOException{
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : lines) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }
    }
}


