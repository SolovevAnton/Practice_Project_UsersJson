package com.solovev.util;

import java.io.BufferedInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * Class to generate JSON files got from given URL
 */
public class FromURLtoFile {
    private final URL url;
    private String content;

    public FromURLtoFile(URL url) {
        this.url = url;
    }

    /**
     * Method that takes content from content string
     * saves it to file in a root with the given name
     * Use after read method
     */
    public void save(String fileName) throws IOException {
        try(FileWriter fw = new FileWriter(fileName)) {
            fw.write(content);
        }
    }

    /**
     * Method that reads a content from the given URL and writes it to memory of the object
     *
     * @return string contains what was read
     * @throws IOException Generation Exception
     */
    public String read() throws IOException {
        try (BufferedInputStream inStream = new BufferedInputStream(url.openStream())) {
            byte[] bytes = inStream.readAllBytes();
            content = new String(bytes);
            return content;
        }
    }

    /**
     * Method to read data from given URL and save date to file with the given name
     * @param fileName - name of the file
     * @throws IOException
     */
    public void readAndSave(String fileName) throws IOException {
        read();
        save(fileName);
    }

    public URL getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}
