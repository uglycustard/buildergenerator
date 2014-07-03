package uk.co.buildergenerator;

import java.io.File;
import java.io.IOException;

class FileUtils {

    File newFile(String path) {
        return new File(path);
    }
    
    File newFile(File parent, String path) {
        return new File(parent, path);
    }

    void createDirectoriesIfNotExists(File directory) {
        if (!directory.exists()) {
            if(!directory.mkdirs()) {
                throw new RuntimeException(String.format("failed to create directory %s", directory.getPath()));
            }
        }
    }

    void createFileIfNotExists(File file) {
        
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
