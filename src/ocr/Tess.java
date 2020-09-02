package ocr;
import java.io.*;
import net.sourceforge.tess4j.*;
import java.lang.reflect.Field;
import java.util.*;

public class Tess {

    public static void setEnv(String key, String value) {
        try {
            Map<String, String> env = System.getenv();
            Class<?> cl = env.getClass();
            Field field = cl.getDeclaredField("m");
            field.setAccessible(true);
            Map<String, String> writableEnv = (Map<String, String>) field.get(env);
            writableEnv.put(key, value);
        } catch (Exception e) {
            System.out.println("FAILED");
            throw new IllegalStateException("Failed to set environment variable", e);
        }
    }

    /**
     *
     * @param File_Path
     * @return 
     * 
     */
    public String doOCR(String File_Path) {
        String result = null;
        ITesseract tess = new Tesseract();
        String path = System.getProperty("user.dir");
        path = path + "/tessdata";
        setEnv("TESSDATA_PREFIX", "path");
        try {
            tess.setDatapath(path);
            tess.setLanguage("eng");
            result =tess.doOCR(new File(File_Path));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 
     * @param args 
     */
    public static void main(String args[]){
       File_picker fp= new File_picker();
       fp.setVisible(true);
    }
}
