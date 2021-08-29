package duplicatesFinder;

import java.util.ArrayList;
import java.util.HashMap;


//This class holds all golbal variables thats always used in the program
//These maps are all singleton instances..they are modified and looked into from various programs.

public class globalvars {
    private globalvars(){}
    public static HashMap<String, ArrayList<String>> dupeMap; //maps from original directory to duplicate directory
    public static HashMap<Long, ArrayList<String>> sizeMap; //maps from size of file to locations of files with that size
    public static HashMap<String, node> DirNodeMap;  //maps location of a file/folder to its node
    static{
        dupeMap = new HashMap<String, ArrayList<String>>();
        sizeMap = new HashMap<Long, ArrayList<String>>();
        DirNodeMap = new HashMap<String, node>();
    }
    
    public static HashMap<Long, ArrayList<String>> getsizeMap(){
        if(sizeMap == null){
            sizeMap = new HashMap<Long,ArrayList<String>>();
        }
        return sizeMap;
    }
    public static HashMap<String, node> getDirNodeMap(){
        if(DirNodeMap == null){
            DirNodeMap = new HashMap<String,node>();
        }
        return DirNodeMap;
    }

}
    
    

