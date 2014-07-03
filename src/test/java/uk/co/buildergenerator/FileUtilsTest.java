package uk.co.buildergenerator;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.none;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileUtilsTest {

    private FileUtils testee = new FileUtils();
    
    @Rule
    public ExpectedException thrown = none();
   
    
    @Test
    public void newFileForGivenPath() {
        
        String path = "the" + File.separator + "path";
        File file = testee.newFile(path);
        assertEquals(path, file.getPath());
    }
    
    @Test
    public void newFileForGivenPathWithGivenParent() throws Exception {
        
        File parent = testee.newFile("parent");
        File file = testee.newFile(parent, "path");
        assertEquals("parent" + File.separator + "path", file.getPath());
    }

    @Test
    public void createsDirectoriesIfTheyDontExist() throws Exception {
        
        File file = mock(File.class);
        when(file.exists()).thenReturn(false);
        when(file.mkdirs()).thenReturn(true);
        testee.createDirectoriesIfNotExists(file);
        verify(file).mkdirs();
    }
    
    @Test
    public void doesNotCreateDirectoriesIfTheyExist() throws Exception {
        
        File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        testee.createDirectoriesIfNotExists(file);
        verify(file, never()).mkdirs();
    }
    
    @Test
    public void errorCreatingDirectoriesWhenTheyDontExist() throws Exception {
        
        File file = mock(File.class);
        when(file.getPath()).thenReturn("the/path");
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("failed to create directory the/path");
        
        when(file.exists()).thenReturn(false);
        when(file.mkdirs()).thenReturn(false);
        testee.createDirectoriesIfNotExists(file);
    }

    @Test
    public void createFileThatDoesNotExist() throws Exception {
        
        File file = mock(File.class);
        when(file.exists()).thenReturn(false);
        testee.createFileIfNotExists(file);
        verify(file).createNewFile();
    }

    @Test
    public void doesNotCreateFileThatAlreadyExists() throws Exception {
        
        File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        testee.createFileIfNotExists(file);
        verify(file, never()).createNewFile();
    }

    @Test
    public void ioExceptionCreatingFileThatDoesNotExist() throws Exception {
        
        File file = mock(File.class);
        when(file.exists()).thenReturn(false);
        IOException cause = new IOException();
        when(file.createNewFile()).thenThrow(cause);
        try {
            testee.createFileIfNotExists(file);
            fail("IOException was swallowed");
        } catch (RuntimeException e) {
            assertSame("wrong cause", cause, e.getCause());
        }
    }
}
