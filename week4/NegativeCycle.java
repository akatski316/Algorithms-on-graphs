/*import java.util.*;

public class NegativeCycle {
	
	public static int infinity = 100000001; 
	public static int[] dist;
	public static int[] id;
	public static int n;
	public static ArrayList<Integer>[] adjdiff;
    public static ArrayList<Integer>[] costdiff;

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,ArrayList<Integer> A) 
    {
        // write your code here	        
    	//System.out.println((A.get(0)+1)+" has been dist set to zero");
        dist[A.get(0)] = 0;
        for(int i = 0;i < (adj.length);i++)    //..relaxation total |V| - 1 times
        {	
        	boolean changeoccured = false;
        	//System.out.println("it iter "+(i+1));
        	changeoccured = StartRelax(adj,cost,A);
        	
        	//System.out.println("\tafter "+(i+1)+" and changeoccured is "+changeoccured+" iteration dist are");
        	//printNodes();

        	if(!changeoccured)
        	{	
        		ArrayList<Integer> Q = new ArrayList<Integer>();
        		Q = checkforanother(A);
        		//System.out.println("now the probs arised and total number of unreachables are "+Q.size()+" and they are ");
        		//printAdjList(adj,Q);
        		if(Q.size() >= 2)
        		{
        			int check = createNewadj(Q);
        			if(check == 1)
        				return 1;
        		}
        		return 0;
        	}	
        	else if(changeoccured && i == (adj.length - 1))
        	{	
        		return 1;	
        	}
        }

        return 0;
    }

    public static int createNewadj(ArrayList<Integer> Q)
    {
    	int n = Q.size();
    	ArrayList<Integer>[] adjnew = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costnew = (ArrayList<Integer>[])new ArrayList[n];
        int i;
        //System.out.println("creating new adj list");
        for(i = 0 ;i < n;i++)
        {
        	int temp = Q.get(i);
        	adjnew[i] = adjdiff[temp];
        	costnew[i] = costdiff[temp];
        }
        //System.out.println("for cost its ");
        //printAdjList(costnew,Q);
        //printAdjList(adjnew,Q);

        return negativeCycle(adjnew,costnew,Q);
    }

    public static ArrayList<Integer> checkforanother(ArrayList<Integer> A)
    {
    	ArrayList<Integer> Q = new ArrayList<Integer>();
    	//int b = A.get(0);
    	int i;
    	for(i = 0;i < n;i++)
    	{
    		if(dist[i] == infinity)   //id[i] != b)
    		{
    			Q.add(i);
    		}
    	}

    	return Q;
    }



    public static void printNodes()
    {
    	for(int i = 0;i < dist.length;i++)
    	{
    		System.out.println("\tfor node "+(i + 1)+" distance is "+dist[i]+" from 1");
    	}
    }

    public static boolean StartRelax(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,ArrayList<Integer> Q)
    {
    	boolean changeoccured = false;
		//System.out.println();
    	int index = 0;
    	int j = 0;
    	int u = 0; 

    	while(u < Q.size())
    	{
    		try
    		{
    			//System.out.println("\tdist b/w "+(Q.get(u)+1)+" and "+(adj[u].get(index)+1)+" is "+dist[adj[u].get(index)]+" compared to "+ (cost[u].get(index) + dist[u]));
    			if(dist[adj[u].get(index)] > (cost[u].get(index) + dist[Q.get(u)]) && dist[Q.get(u)] != infinity)
	    		{
	    			//System.out.println("\tchanging dist of "+(adj[u].get(index)+1));
	    			changeoccured = true;
	    			dist[adj[u].get(index)] = (cost[u].get(index) + dist[Q.get(u)]);	
	    			
	    			//id[adj[u].get(index)] = Math.min(id[adj[u].get(index)],id[Q.get(u)]);
	    			//id[Q.get(u)] = id[adj[u].get(index)];
	    		}
    		}
    		catch(Exception e)
    		{
    			//System.out.println("\treached the end of this list");
    			try{
	    			index = 0;
	    			u++;
	    			
	    			//System.out.println("\tdist b/w "+(Q.get(u)+1)+" and "+(adj[u].get(index)+1)+" is "+dist[adj[u].get(index)]+" compared to "+ (cost[u].get(index) + dist[u]));

	    			if(dist[adj[u].get(index)] > (cost[u].get(index) + dist[Q.get(u)]) && dist[Q.get(u)] != infinity)
		    		{
		    			//System.out.println("\tchanging dist of "+(adj[u].get(index)+1));
		    			changeoccured = true;
		    			dist[adj[u].get(index)] = (cost[u].get(index) + dist[Q.get(u)]);	
		    		
			    		//id[adj[u].get(index)] = Math.min(id[adj[u].get(index)],id[Q.get(u)]);
		    			//id[Q.get(u)] = id[adj[u].get(index)];
		    		}
		    	}
		    	catch(Exception e2)
		    	{
		    		try
		    		{
			    		//System.out.println("\tthere are no node directly reachable from "+(u+1));
			    		index = 0;
			    		u++;
			    		continue;
			    		
			    	}
			    	catch(Exception e3)
			    	{
			    		return changeoccured;
			    	}	
		    	}	
    		}
    		index++;
    	}

    	return changeoccured;

    }

    public static void printAdjList(ArrayList<Integer>[] adj,ArrayList<Integer> Q)
    {
    	System.out.println("adjacency list are");
        for(int i = 0;i < adj.length;i++)
        {
        	System.out.print((Q.get(i)+1)+" ---> ");
            for(int j = 0;j < adj[i].size();j++)
                System.out.print((adj[i].get(j)+1)+" ");

            System.out.println();
        }
    }

    public static int Dequeue(ArrayList<Integer> Q)
    {
    	int temp = Q.get(0);
    	Q.remove(0);
    	return temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer> Q = new ArrayList<Integer>();
        adjdiff = (ArrayList<Integer>[])new ArrayList[n];
        costdiff = (ArrayList<Integer>[])new ArrayList[n];
        //id = new int[n];

        for (int i = 0; i < n; i++) {
        	Q.add(i);
        	//id[i] = i;
            adjdiff[i] = new ArrayList<Integer>();
            costdiff[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adjdiff[x - 1].add(y - 1);
            costdiff[x - 1].add(w);
        };
        dist = new int[n];
        Arrays.fill(dist,infinity);
        //printAdjList(adjdiff,Q);
        System.out.println(negativeCycle(adjdiff, costdiff,Q));
    }
}
*/

import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static final int inf = Integer.MAX_VALUE;
    
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        long[] dist = new long[adj.length];
        for (int i = 0;i < adj.length; i++) {
            dist[i] = inf;
        }
        dist[0] = 0;
        for (int i = 0; i < adj.length; i++) 
        {
            boolean changeoccured = false;
            for(int u = 0; u < adj.length; u++)
            {
                for (int v : adj[u]) 
                {
                    int v_index = adj[u].indexOf(v);
                    if (dist[v] > dist[u] + cost[u].get(v_index)) 
                    {
                        changeoccured = true;
                        dist[v] = dist[u] + cost[u].get(v_index);
                        if(i == adj.length - 1) 
                            return 1;
                    }
                }
            }
            if(!changeoccured)
                return 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}
