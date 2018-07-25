import java.util.*;

public class FriendSuggestion {

    private static class Impl 
    {
        // Number of nodes
        int n;
        // adj[0] and cost[0] store the initial graph, adj[1] and cost[1] store the reversed graph.
        // Each graph is stored as array of adjacency lists for each node. adj stores the edges,
        // and cost stores their costs.
        ArrayList<Integer>[][] adj;
        ArrayList<Integer>[][] cost;
        Entry Entries[];
        // distance[0] and distance[1] correspond to distance estimates in the forward and backward searches.
        Long[][] distance;
        // Two priority queues, one for forward and one for backward search.
        ArrayList<PriorityQueue<Entry>> queue;
        // visited[v] == true iff v was visited either by forward or backward search.
        boolean[] visited;
        // List of all the nodes which were visited either by forward or backward search.
        ArrayList<Integer> workset;
        final Long INFINITY = Long.MAX_VALUE / 4;

        Impl(int n) {
            this.n = n;
            visited = new boolean[n];
            Arrays.fill(visited, false);
            workset = new ArrayList<Integer>();
            distance = new Long[][] {new Long[n], new Long[n]};

            for (int i = 0; i < n; ++i) {
                distance[0][i] = distance[1][i] = INFINITY;
            }

            queue = new ArrayList<PriorityQueue<Entry>>();
            queue.add(new PriorityQueue<Entry>(n));
            queue.add(new PriorityQueue<Entry>(n));
        }

        // Reinitialize the data structures before new query and after the previous query
        void clear() {

            Arrays.fill(distance[0],INFINITY);
            Arrays.fill(distance[1],INFINITY);
            for (int v : workset) 
            {
                visited[v] = false;
            }
            workset.clear();
            queue.get(0).clear();
            queue.get(1).clear();
        }

        // Try to relax the distance from direction side to node v using value dist.
        void visit(int side, int v, Long dist) 
        {
            //..Implement this method yourself
            //System.out.println("\t\tvisiting "+(v+1)+" with distance "+distance[side][v]+" and dist is "+dist);
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
        Long query(int s, int t,int n) {
            clear();
            Entries = new Entry[n];
            if(s == t)
            	return 0L;
            visit(0, s, 0L);
            visit(1, t, 0L);
            //visited[s] = true;
            //visited[t] = true;
            //workset.add(s);
            //workset.add(t);
            // Implement the rest of the algorithm yourself

            do
            {
            	//if(queue.get(0).size() == 0)
            	//	return -1L;
            	Entry temp = queue.get(0).poll();
            	if(temp == null)
            		return -1L;
            	Process(0,temp.node);
            	if( visited[temp.node] )//&& temp.node != s)
            	{
            		//System.out.println((temp.node+1)+" is already visited by reverse direction");
            		return ShortestPath();
            	}
            	else
            		visited[temp.node]=true;
            	workset.add(temp.node);
            		

            	//if(queue.get(1).size() == 0)
            	//	return -1L;
            	temp = queue.get(1).poll();
            	if(temp == null)
            		return -1L;
 				Process(1,temp.node);
 	           	if( visited[temp.node] )//&& temp.node != t )
            	{
            		//System.out.println((temp.node+1)+" is already visited by forward direction");
            		return ShortestPath();
            	}
            	else
            		visited[temp.node] = true;
                workset.add(temp.node);
            }
 			while(true);

            
        }

        public void Process(int side,int node)
        {
        	//System.out.println("while processing node "+(node+1)+" in direction "+side);
        	for(int i = 0;i < adj[side][node].size();i++)
        	{
        		//System.out.println("\tcurrent vetex being relaxed is "+(adj[side][node].get(i)+1));
        		visit(side,adj[side][node].get(i),distance[side][node]+cost[side][node].get(i));
        	}
        	
        }

        public long ShortestPath()
        {
        	long Distance = INFINITY;
        	for(int i = 0; i < workset.size();i++)
        	{
        		//System.out.println("\tcurrent vertex in workset is "+(workset.get(i)+1));
        		if(distance[0][workset.get(i)] + distance[1][workset.get(i)] < Distance)
        			Distance = distance[0][workset.get(i)] + distance[1][workset.get(i)];
        	}
        	if(Distance == INFINITY)
        		return -1;
        	else
        		return Distance;
        }
        class Entry implements Comparable<Entry>
        {
            public Long cost;
            public int node;
          
            public Entry(Long cost, int node)
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
        int n = in.nextInt();
        int m = in.nextInt();
        Impl bidij = new Impl(n);
        

        bidij.adj = (ArrayList<Integer>[][])new ArrayList[2][];
        bidij.cost = (ArrayList<Integer>[][])new ArrayList[2][];

        for (int side = 0; side < 2; ++side) 
        {
            bidij.adj[side] = (ArrayList<Integer>[])new ArrayList[n];
            bidij.cost[side] = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) 
            {
                bidij.adj[side][i] = new ArrayList<Integer>();
                bidij.cost[side][i] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < m; i++) {
            int x, y, c;
            x = in.nextInt();
            y = in.nextInt();
            c = in.nextInt();
            bidij.adj[0][x - 1].add(y - 1);
            bidij.cost[0][x - 1].add(c);
            bidij.adj[1][y - 1].add(x - 1);
            bidij.cost[1][y - 1].add(c);
        }

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            System.out.println(bidij.query(u-1, v-1,n));
        }
    }
}
