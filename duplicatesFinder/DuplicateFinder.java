package duplicatesFinder;

import static duplicatesFinder.globalvars.dupeMap;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class DuplicateFinder {
    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {
        
        String dir = args[0];            //input folder Path
        //String dir = "/Users/home/Zoho/Java/findDuplicateFiles/duplicatesFinder/A";
        node root = new node(dir);       //creating a new root for input folder.
        TreeCreator TC = new TreeCreator(); 
        
        root = TC.createTree(root);      // creates new nodes and builds a tree representing input folder hierarchy  
        String rootDir = "/Users/";       //the root of our File System
        
        
        FindDuplicates.find(rootDir);  //To search for duplicates.
        
        for(String s : dupeMap.keySet()){    //dupeMap :  a Dictionary to map original folder Directory to their duplicates
            for (String x : dupeMap.get(s))
            System.out.println("\nDuplicate of " + s + " found at" + x);
        }
        
    }
    
}
