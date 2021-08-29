package duplicatesFinder;

import static duplicatesFinder.globalvars.DirNodeMap;
import static duplicatesFinder.globalvars.dupeMap;
import static duplicatesFinder.globalvars.sizeMap;
import static duplicatesFinder.Ignore.ignoreList;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
public class FindDuplicates{
    
    public static void find(String dir) throws NoSuchAlgorithmException, IOException,NullPointerException{
        //System.out.println(dir);
        for(String s: ignoreList){  //ignoreList is a list of all directories to avoid while searching.
            if(dir.startsWith(s)){  //Add folders to avoid by adding them to this list at Ignore.java
                return;
            }
        }
        //System.out.println(dir);
        if (Files.exists(Paths.get(dir)) == false){ //check if the directory exists first
            return;
        }
        File f = new File(dir);
        if (f.isDirectory() == false){
            
            long l = f.length(); //get size of the file
            
            if(sizeMap.containsKey(l)){ //check if a similar size file exists in our folder
                String hash1 = MD5.getMD5(f.getCanonicalPath());
                if(sizeMap.get(l).size() > 0){
                    for(String s : sizeMap.get(l)){ //s is a path to file in our folder
                        
                        String hash2 = MD5.getMD5(s);
                        
                        if(hash1.equals(hash2) && (!dir.equals(s))){ //if they have the same content and different location
                            node n = DirNodeMap.get(s); //get the node
                            File f1 = f;
                            while((n.parent != null) && (f1.exists()) && Compare.ComparetwoFolders(n.parent,f1.getParent()) == true){
                                                             //compare if parent folders are identical
                                n = n.parent;
                                f1 = new File(f1.getParent()); 
                            }
                            
                            ArrayList<String> list;  
                            if(dupeMap.containsKey(n.dir)){
                                list = dupeMap.get(n.dir); //get dupeMao record of current folder
                                if(!list.contains(f1.getCanonicalPath())){  //ensure this is not already added
                                    list.add(f1.getCanonicalPath());
                                }
                            
                                dupeMap.put(n.dir,list);
                            }
                            else{
                                list = new ArrayList<String>();
                                if(!list.contains(f1.getCanonicalPath())){ //ensure this is not already added
                                    list.add(f1.getCanonicalPath());
                                }
                                dupeMap.put(n.dir,list); //adding to dupeMap new identical file/folder
                            }

                            return;
                    
                        }
                        
                        
                    }
                }   
            } 
        }
            
        else{
            
            if(f.list().length > 0){
                for(String s : f.list()){
                    if((s.charAt(0) == '.') || (s.charAt(0) == '_')){
                        continue;
                    }

                    //System.out.println("\n" + s);
                    FindDuplicates.find(f.getCanonicalPath() + "/" + s);
                    
                }
                
                
            }
            
            
        }
        return;

        
    }

}
