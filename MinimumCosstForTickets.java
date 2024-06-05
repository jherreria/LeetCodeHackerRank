/**

A solution for https://leetcode.com/problems/minimum-cost-for-tickets/description/

**/
package com.jorgeherreria.leetcode;

import java.util.Map;
import java.util.HashMap; 

public class MinimumCosstForTickets {
    
    /** tikct[x] covers 'value' days **/
    int []ticketDuration ={1,7,30};
    
    /** Dynamic Programming: remmember the calculated day
    Map<TravelDay, minCost> **/
    Map<Integer, Integer> costAtDay = new HashMap<>();
    
    public static void main(String[] args){
        MinimumCosstForTickets m = new MinimumCosstForTickets();
        int[] days = {4,6,7,8,20};
        int[] costs ={2,7,15};
        System.out.print("For days: ");
        for(int d: days) System.out.print(d+",");
        System.out.println ("  it's expected to cost: $9 ");
        System.out.println("The calculated cost is:" + m.mincostTickets(days, costs));
        
        int[] days2 ={1,2,3,4,5,6,7,8,9,10,30,31};
        m.costAtDay.clear();
        System.out.print("For days: ");
        for(int d: days2) System.out.print(d+",");
        System.out.println("  it's expected to cost: $17 ");
        System.out.println("The calcualted cost is:" + m.mincostTickets(days2, costs));
    }
    
    public int mincostTickets(int[] days, int[] costs) {
        
        return minCostRecursive(days, costs, 0); // start at day 1;    
    }

    public int minCostRecursive(int[] days, int[] costs, int idx){
        int minCost = Integer.MAX_VALUE;
    
        //travel Index out fo the range, you are not traveling that day, 
        // therefor no cost
        if(idx >= days.length) return 0;
    
        // if the day has been calculated already
        if(costAtDay.containsKey(days[idx])){
            return costAtDay.get(days[idx]);
        }
        // Compute min cost for each ticket
        for(int td=0; td < ticketDuration.length; td++){
            // find the last traveling day the ticket (td) covers            
            int j=idx;
            while ( j < days.length &&  days[j] < days[idx] + ticketDuration[td]){
                j++;
            }
            //so far we found upto what day the ticket cover (days[j])
            
            minCost = Math.min(minCost, costs[td] + minCostRecursive(days, costs, j));
        }
        costAtDay.put(days[idx],  minCost);
        return minCost;
    }
}
