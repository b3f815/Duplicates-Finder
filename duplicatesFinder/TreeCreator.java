package duplicatesFinder;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TreeCreator {
    
    public node createTree(node root) throws IOException, NoSuchAlgorithmException{
        
        HashMap<Long,ArrayList<String>> sizeMap = globalvars.getsizeMap(); //sizeMap : a dictionary to map size of file to files with that size
        File f = new File(root.dir);
        root.isDir = f.isDirectory();
        if(root.isDir == true){
            for(String item : f.list()){
                if((item.charAt(0) == '.') || (item.charAt(0) == '_')){  //ignore all system files and hidden files
                    continue;
                }
                node n = new node(root.dir + "/" + item); //adding child node
                root.addChild(n);
                File x = new File(root.dir + "/" + item);
                if(x.isFile()){                             //to add file entries to sizeMap
                    if (sizeMap.containsKey(x.length())== false){
                        ArrayList<String> al = new ArrayList<String>();
                        
                        al.add(x.getAbsolutePath());
                        sizeMap.put(x.length(),al);
                        
                    }
                    else{
                        ArrayList<String> al = sizeMap.get(x.length());
                        al.add(x.getCanonicalPath());
                        sizeMap.put(x.length(), al);
                    }
                    root.childList.add(MD5.getMD5(x.getCanonicalPath()));  //add hashed file content to nodes child list .. this is useful during comparison of two folders.

                }
                else{
                    root.childList.add(item);  //if its a folder, just add the name
                }
            }
            Collections.sort(root.childList);  //keep it sorted for ease of comparison later
            TreeCreator TC1 = new TreeCreator();
            for(node n : root.children){        //recursively create nodes for its children
                n.parent = root;  //assigning parent node
                n = TC1.createTree(n);
            }
        }
        return root;    
    }

}
