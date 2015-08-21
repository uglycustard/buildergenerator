package uk.co.buildergenerator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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

    public static void deleteGeneratedFiles() {
        URL url = ClassLoader.getSystemResource("integrationtest/generatedbuilder");
        if (url == null) {
        	// if its null then directory doesn't exist yet in which case there is nothing to do 
        	return;
        }
        // https://weblogs.java.net/blog/kohsuke/archive/2007/04/how_to_convert.html
        File directory;
        try {
            directory = new File(url.toURI());
        } catch(URISyntaxException e) {
            directory = new File(url.getPath());
        }
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
        }
    }
}
