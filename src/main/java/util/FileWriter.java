package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class FileWriter {

    private Logger logger = LoggerFactory.getLogger(FileWriter.class);

    public void writeToFile(String fileName, ConcurrentHashMap<String, Long> map) {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String key : map.keySet()) {
                writer.write("link:" + key + " count:" + map.get(key));
                writer.newLine();
            }
            logger.info("File created in '{}'", path);
        } catch (IOException ex) {
            logger.error("Error during file creation '{}'", path, ex);
        }
    }
}
