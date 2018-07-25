import java.util.*;
import java.lang.*;

public class DistWithCoords {
    private static class Impl {
        // Number of nodes
        int n;
        // Coordinates of nodes
        int[] x;
        int[] y;
        Entry Entries[];
        // See description of these fields in the starters for friend_suggestion
        ArrayList<Integer>[][] adj;
        ArrayList<Double>[][] cost;
        ArrayList<Double>[][] L_pi;

        Double[][] distance;
        ArrayList<PriorityQueue<Entry>> queue;
        boolean[] visited;
        ArrayList<Integer> workset;
        final double INFINITY = (double)(Integer.MAX_VALUE);

        Impl(int n) 
        {
            this.n = n;
            visited = new boolean[n];
            x = new int[n];
            y = new int[n];
            Arrays.fill(visited, false);
            workset = new ArrayList<Integer>();
            distance = new Double[][] {new Double[n], new Double[n]};
            
            for (int i = 0; i < n; ++i) 
                distance[0][i] = distance[1][i] = INFINITY;
         
            queue = new ArrayList<PriorityQueue<Entry>>();
            queue.add(new PriorityQueue<Entry>(n));
            queue.add(new PriorityQueue<Entry>(n));
        }

        // See the description of this method in the starters for friend_suggestion
        void clear() 
        {
        	Arrays.fill(distance[0],INFINITY);
            Arrays.fill(distance[1],INFINITY);

            for (int v : workset) 
            {
                distance[0][v] = distance[1][v] = INFINITY;
                visited[v] = false;
            }

            workset.clear();
            queue.get(0).clear();
            queue.get(1).clear();
        }

        // See the description of this method in the starters for friend_suggestion
        void visit(int side, int v, double dist) 
        {
            // Implement this method yourself
            if(distance[side][v] > dist)
            {
            	distance[side][v] = dist;
            	
            	if(queue.contains(Entries[v]))
            		queue.remove(Entries[v]);

            	Entries[v] = new Entry(dist,v);	
            	queue.get(side).add(Entries[v]);
            }
        }

        // Returns the distance from s to t in the graph.
        Long query(int s, int t) {
            clear();
            Entries = new Entry[n];
            if(s == t)
            	return 0L;
            visit(0, s, 0D);
            visit(1, t, 0D);
            // Implement the rest of the algorithm yourself
            do
            {
            	Entry temp = queue.get(0).poll();

            	if(temp == null)
            		return -1L;

            	Process(0,temp.node,t,s);
            	if( visited[temp.node] )//&& temp.node != s)
            	{
            		//System.out.println((temp.node+1)+" is already visited by reverse direction");
            		return (long)ShortestPath(s,t);
            	}
            	else
            		visited[temp.node]=true;
            	workset.add(temp.node);
            		

            	temp = queue.get(1).poll();
            	if(temp == null)
            		return -1L;
 				Process(1,temp.node,t,s);
 	           	if( visited[temp.node] )//&& temp.node != t )
            	{
            		//System.out.println((temp.node+1)+" is already visited by forward direction");
            		return (long)ShortestPath(s,t);
            	}
            	else
            		visited[temp.node] = true;
                workset.add(temp.node);
            }
 			while(true);
        }

        public void Process(int side,int node,int t,int s)
        {
        	//System.out.println("while processing node "+(node+1)+" in direction "+side);
        	for(int i = 0;i < adj[side][node].size();i++)
        	{
        		//System.out.println("\tcurrent vetex being relaxed is "+(adj[side][node].get(i)+1));
        		if(side == 0 && distance[side][node] != INFINITY)
        		{
        			//System.out.println("\t\t l_pi_forward for nodes "+(node+1)+" and "+(adj[side][node].get(i)+1)+" is "+l_pi_forward(node,i,t,s));
        			visit( side,adj[side][node].get(i),distance[side][node]+l_pi_forward(node,i,t,s) );//cost[side][node].get(i));
        		}
        		else if(side == 1 && distance[side][node] != INFINITY)// && l_pi_reverse(node,i,t,s) < 0)
        		{
        			//System.out.println("\t\t l_pi_reverse for nodes "+(node+1)+" and "+(adj[side][node].get(i)+1)+" is "+l_pi_reverse(node,i,t,s));
        			visit( side,adj[side][node].get(i),distance[side][node]+l_pi_reverse(node,i,t,s) );
        		}
        	}
        	
        }

        public double ShortestPath(int s,int t)
        {
        	double Distance = INFINITY;
        	int temp = 0;
        	for(int i = 0; i < workset.size();i++)
        	{
        		//System.out.println("\tcurrent vertex in workset is "+(workset.get(i)+1)+" and Distance is "+Distance);
        		if(distance[0][workset.get(i)] + distance[1][workset.get(i)] < Distance)
        		{
        			//System.out.println("\t\tnow this node is inside the if statement with forwar dist as "+distance[0][workset.get(i)]+" and backward dist as "+distance[1][workset.get(i)]);
        			temp = i;
        			Distance = distance[0][workset.get(i)] + distance[1][workset.get(i)];
        		}
        	}
        	/*
        	System.out.println("we got it at "+(temp+1));
        	System.out.println("distatnce is "+Distance);
        	System.out.println("Pi_f(temp,t,s) is "+Pi_f(temp,t,s));
        	System.out.println("Pi_r(temp,t,s) is "+Pi_r(temp,t,s));
        	System.out.println("Pi_f(t,t,s) is "+Pi_f(t,t,s));
        	System.out.println("Pi_r(s,t,s) is "+Pi_r(s,t,s));
			*/
        	if(Distance == INFINITY)
        		return -1;
        	else
        		return (Distance + Pi_f(temp,t,s)+Pi_r(temp,t,s)-Pi_f(t,t,s)-Pi_r(s,t,s));
        }

        public double l_pi_forward(int u,int v,int t,int s)   //..index of node and index of destination node
        {
        	/*
        	System.out.println("\t\tdistance b/w "+(u+1)+" and "+(adj[0][u].get(v)+1)+" in orig. graph is "+cost[0][u].get(v));
        	System.out.println("\t\tPi_f for "+(u+1)+" is "+Pi_f(u,t,s));
        	System.out.println("\t\tPi_f for "+(adj[0][u].get(v)+1)+" is "+Pi_f(adj[0][u].get(v),t,s));
        	*/	
        	return cost[0][u].get(v) + (Pi_f(adj[0][u].get(v),t,s) - Pi_f(u,t,s)); 
        }

        public double l_pi_reverse(int u,int v,int t,int s)
        {
        	/*
        	System.out.println("\t\tdistance b/w "+(u+1)+" and "+(adj[1][u].get(v)+1)+" in orig. graph is "+cost[1][u].get(v));
        	System.out.println("\t\tPi_f for "+(u+1)+" is "+Pi_r(u,t,s));
        	System.out.println("\t\tPi_f for "+(adj[1][u].get(v)+1)+" is "+Pi_r(adj[1][u].get(v),t,s));
        	*/
        	return cost[1][u].get(v) + (Pi_r(adj[1][u].get(v),t,s) - Pi_r(u,t,s) );
        }

        public double Pi_f(int u,int t,int s)
        {
        	return (pi(u,t) - pi(u,s))/(double)2;
        }

        public double Pi_r(int u,int t,int s)
        {
        	return (double)-1 * ((pi(u,t) - pi(u,s))/(double)2);
        }

        public double pi(int v,int t)
        {
        	double x_diff_square = ((double)x[v] - (double)x[t]) * ((double)x[v] - (double)x[t]);
        	double y_diff_square = ((double)y[v] - (double)y[t]) * ((double)y[v] - (double)y[t]);

        	return Math.sqrt(x_diff_square + y_diff_square);
        }

        class Entry implements Comparable<Entry>
        {
            double cost;
            int node;
          
            public Entry(double cost, int node)
            {
                this.cost = cost;
                this.node = node;
            }
         
            public int compareTo(Entry other)
            {
                return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
            }
        }
    }

    public static void main(String args[]) 
    {
        Scanner in = new Scanner(System.in);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
        int n = in.nextInt();
        int m = in.nextInt();
        Impl DistWithCoords = new Impl(n);
        DistWithCoords.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        DistWithCoords.cost = (ArrayList<Double>[][])new ArrayList[2][];
        for (int side = 0; side < 2; ++side) {
            DistWithCoords.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            DistWithCoords.cost[side] = (ArrayList<Double>[])new ArrayList[n];
           // DistWithCoords.L_pi[side] = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                DistWithCoords.adj[side][i] = new ArrayList<Integer>();
                DistWithCoords.cost[side][i] = new ArrayList<Double>();
                //DistWithCoords.L_pi[side][i] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < n; i++) { 
            int x, y;
            x = in.nextInt();
            y = in.nextInt();
            DistWithCoords.x[i] = x;
            DistWithCoords.y[i] = y;
        }

        for (int i = 0; i < m; i++) 
        {
            int x, y;
            double c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextDouble();
            DistWithCoords.adj[0][x - 1].add(y - 1);
            DistWithCoords.cost[0][x - 1].add(c);
            //DistWithCoords.L_pi[0][x - 1].add(c - Pi_f(x - 1,s,t))
            DistWithCoords.adj[1][y - 1].add(x - 1);
            DistWithCoords.cost[1][y - 1].add(c);
        	
        }

        int t = in.nextInt();

        for (int i = 0; i < t; i++) 
        {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            System.out.println(DistWithCoords.query(u-1, v-1));
        }
    }
}
