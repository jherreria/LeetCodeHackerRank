package com.jorgeherreria/leetcode
/** Solution for https://leetcode.com/problems/min-cost-to-connect-all-points/ */
/** 
  Edge cases: 
  1. either X or Y are at the Integer.MAX_VALUE or Integer.MIN_VALUE -- Not covered by solution
  2. a point is 0,0 -- any special consideration?
  3. A single point as input param - Based in examples solutions, the point will be connected to [0,0]
  
  Solution thoughts:
  Keep a Map<Coordinates, Cost>, this will store the mininum cost found. It does not care where is connected to, just the cost.
     Ccoordinates is "x,y" String
  Connect each point to another, calculate the cost of each connection. Update the map with the cheapest cost:
      If the new cost is less than existing cost, use new cost
  The Sum(map<cost>) is the result
**/  
  
import java.util.*;

class Solution {
  
  private String xyKey(int x, int y){
        return x+","+y;
  }
  
  public int minCostConnectPoints(int[][] points) {     
      /** Map<"x,y", $minCost> **/
      HashMap<String,Integer> map = new HashMap<>();

      /** Map<EdgeA,EdgeB>  - This graph is not needed but helps to know what edge got connected to which **/
      HashMap<String,String> graph = new HashMap<>();

      //Insert "0,0"  edge at $0 cost as the starting edge
      map.put(xyKey(0,0),0);
      
      //Calculate cost for all combinations and choose the minimum cost
      for(int i=0; i < points.length-1; i++){            
          for(int j=i+1; j < points.length; j++){                
              //cost for the connection i, j
              int cost = Math.abs(points[j][0] - points[i][0]) + 
                         Math.abs(points[j][1] - points[i][1]);
              // Key for J
              String xy = xyKey(points[j][0],points[j][1]); 
              
              //Keep track of connections (graph) -- Not needed but helps to understand the logic by printing
              int existingCost = map.getOrDefault(xy, Integer.MAX_VALUE);          
              if(existingCost > cost){
                  graph.remove(xy);
                  String xy2 = xyKey(points[i][0],points[i][1]); 
                  graph.put(xy, xy2);                        
              }

              //Update the smallest cost for the edge
              map.put(xy, Math.min(cost, existingCost));           
          }
      }

      // the sum of each individual connection is the total cost
      int totalCost = 0;
      for(int value : map.values()){
          totalCost += value;
      }

      //Print the connections and costs
      for(String k :graph.keySet()){
          System.out.println(String.format("[%s] <-> [%s]  $%d", 
               k, graph.get(k), map.get(k)));
      }

      return totalCost;
  }
   
}
