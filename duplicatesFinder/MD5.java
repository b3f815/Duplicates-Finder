package duplicatesFinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//MD5 checksum gives a unique representation for the contents of a file..this is used for comparison of two files.

public class MD5 {
    private static String checksum(MessageDigest digest,
                                   File file) throws IOException{
        
        
        FileInputStream fis = new FileInputStream(file);
        
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;
        while ((bytesCount = fis.read(byteArray)) != -1)
        {
            digest.update(byteArray, 0, bytesCount);
        };     
        fis.close();      
        byte[] bytes = digest.digest();
 
        StringBuilder sb = new StringBuilder();
       
        for (int i = 0; i < bytes.length; i++) {

            sb.append(Integer
                    .toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }
    public static String getMD5(String dir) throws IOException, NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");  
        File file = new File(dir);
        String checksum = checksum(md, file);
        
        return checksum;
    }
    
}
