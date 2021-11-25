

import java.util.Comparator;

public class SortName  implements Comparator<String[]>{
    
    
    /** 
     * @param list1 This is the first list that contains the specific item being compared  
     * @param list2 This is the second list that contains the specific item being compared
     */
    public int compare(String[] list1,String[] list2){
        return list1[1].compareTo(list2[1]);
    }
}
