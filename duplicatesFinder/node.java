package duplicatesFinder;

import java.util.ArrayList;
import static duplicatesFinder.globalvars.DirNodeMap;
//A node can represent a file or a folder

public class node {
     //to create a map between directory locations and their nodes
    public String dir;      //the directory of the folder/file this node represents.
    public ArrayList<node> children;  //a list of its children nodes
    public boolean isDir;           //is true if this is Directory
    public ArrayList<String> childList; //A list for the names of its subforlders and md5 checksum of its file children
    public node parent;   // its parent node
    public node(String dir){
        
        this.dir = dir;
        this.parent = null;
        //System.out.println("node created");
        childList = new ArrayList<String>();
        children = new ArrayList<node>();
        DirNodeMap.put(dir,this); //map this directory to this node.
    
    }
    public void addChild(node e){
        this.children.add(e);
    }
    
}
