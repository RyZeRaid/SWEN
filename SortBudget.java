

import java.util.Comparator;

public class SortBudget implements Comparator<String[]>{
    
    
    /** 
     * @param list1 This is the first list that contains the specific item being compared  
     * @param list2 This is the second list that contains the specific item being compared
     */
    public int compare(String[] list1, String[] list2){
        return Integer.parseInt(list1[2]) - Integer.parseInt(list2[2]);
    }  
    
}
