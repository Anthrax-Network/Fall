package me.hackusatepvp.fall.util;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.entity.Player;

import java.util.*;

public class GFG {
  
    // function to sort hashmap by values 
    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm)
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Double> > list =
               new LinkedList<Map.Entry<String, Double> >(hm.entrySet());
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
  
    // Driver Code 
    public static void main(Player player)
    {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        HashMap<String, Double> hm = new HashMap<String, Double>();
  
        // enter data into hashmap 
        hm.put(player.getName(), profile.getBountyamount());
        Map<String, Double> hm1 = sortByValue(hm);
  
        // print the sorted hashmap 
        for (Map.Entry<String, Double> en : hm1.entrySet()) {
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
    } 
} 