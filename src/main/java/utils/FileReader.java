package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FileReader {

    public static FileInputStream getTestData(String reqBodyFileName){
        FileInputStream fileInputStream;
        //converting file into file input stream
        try{
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/resources/testData/"+ reqBodyFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  fileInputStream;
    }
}
