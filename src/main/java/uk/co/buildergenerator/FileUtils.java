package uk.co.buildergenerator;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public File newFile(String path) {
        return new File(path);
    }
    
    public File newFile(File parent, String path) {
        return new File(parent, path);
    }

    public void createDirectoriesIfNotExists(File directory) {
        if (!directory.exists()) {
            if(!directory.mkdirs()) {
                throw new RuntimeException(String.format("failed to create directory %s", directory.getPath()));
            }
        }
    }

    public void createFileIfNotExists(File file) {
        
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
