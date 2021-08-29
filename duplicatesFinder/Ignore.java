package duplicatesFinder;

import java.util.ArrayList;
//This class holds the ignore list , alist with all folders to avoid while searching.
public class Ignore {
    public static ArrayList<String> ignoreList;
    static {
        ignoreList = new ArrayList<String>();  //add custom folders to avoid by adding them thi ArrayList.
        ignoreList.add("/Users/home/Library");
        ignoreList.add("/Users/home/Pictures/Photos");
    }
}
