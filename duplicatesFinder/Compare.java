package duplicatesFinder;

import static duplicatesFinder.globalvars.sizeMap;
import static duplicatesFinder.globalvars.DirNodeMap;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
public class Compare {
    public static boolean ComparetwoFolders(node n,String dir) throws NoSuchAlgorithmException, IOException{
        ArrayList<String> dirChildSet = new ArrayList<String>(); //To store names of childrenFolders anh hashed value of childFiles
        File f = new File(dir);
        for( String s : f.list()){
            if((s.charAt(0) == '.') || (s.charAt(0) == '_')){ //ignore hidden files and system files
                continue; 
            }
            File f1 = new File(dir + "/" + s);  //opening first file
            if(f1.isDirectory() == false){
                if(sizeMap.containsKey(f1.length())){ //to check if a similar sized file exists in our folder
                    String hash1 = MD5.getMD5(f1.getCanonicalPath()); 
                    dirChildSet.add(hash1); //add hashed string of child files content
                }
                else{
                    return false;
                }
            }
            else{
                dirChildSet.add(s); //if its a folder,add the folder name

            }
        
        }
        Collections.sort(dirChildSet); 
        
        boolean innercompare = true;
        if (n.childList.equals(dirChildSet) ){
            
            for (String s : f.list()){
                if((s.charAt(0) == '.') || (s.charAt(0) == '_')){
                    continue;
                }  
                File f1 = new File(f.getCanonicalPath() + "/" + s);
                
                if(f1.isDirectory()){  //subsequently compare children folders with same name

                    innercompare = innercompare & ComparetwoFolders(DirNodeMap.get(n.dir + "/" + s), f1.getCanonicalPath());
                }
                
            }
            
            return innercompare; 
            
        }
    return false;    
    }
    
    
    
        
      
}   
