import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) 
    {
        //write your code here
        distance[s] = 0;
        reachable[s] = 1;

        for(int i = 0;i < adj.length;i++)
        {
            //System.out.println("its iter "+(i+1));
            boolean changeoccured = false;

            for(int j = 0;j < adj.length;j++)
            {
                //System.out.println("\tcurrently AT list of node "+(j+1)+" with distance from source is "+distance[j]);
                for(int k = 0;k < adj[j].size();k++)
                {
                    //System.out.println("\t\t"+(k+1)+"th node is "+(adj[j].get(k)+1)+" with distance "+distance[adj[j].get(k)]+" distance from "+(j+1)+" is "+cost[j].get(k));
                    if( distance[adj[j].get(k)] > (distance[j] + (long)(cost[j].get(k)) ) && distance[j] != Long.MAX_VALUE) 
                    {
                        distance[adj[j].get(k)] = ( distance[j] + (long)(cost[j].get(k)) );
                        
                        changeoccured = true;

                        if(i == adj.length -1 ) //&& shortest[adj[j].get(k)] == 1) //j != s && 
                            getReachables(adj[j].get(k),adj,shortest,s);
                    }

                    if(reachable[j] == 1)
                        reachable[adj[j].get(k)] = 1; 
                }
            }

            // printdistances(distance);

            if(!changeoccured)
            {
                //System.out.println("no change occured returning at iter "+(i+1));
                return;
            }
        }
    }

    public static void getReachables(int vertex,ArrayList<Integer>[] adj, int[] shortest,int s)
    {
        ArrayList<Integer> Queue = new ArrayList<Integer>();
        Queue.add(vertex);

        while(Queue.size() > 0)
        {
            int temp = Dequeue(Queue);

            for(int i = 0;i < adj[temp].size();i++)
            {
                if(shortest[adj[temp].get(i)] != 0)
                {
                    shortest[adj[temp].get(i)] = 0;
                    Queue.add(adj[temp].get(i));
                }    
            }
        }
    }

    public static int Dequeue(ArrayList<Integer> Queue)
    {
        int temp = Queue.get(0);
        Queue.remove(0);
        return temp;
    }

    public static void printdistances(long[] distance)
    {
        for(int i = 0;i < distance.length;i++)
        {
            System.out.println((i+1)+" | "+distance[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];

        for (int i = 0; i < n; i++) 
        {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) 
        {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }

        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];

        for(int i = 0; i < n; i++) 
        {
            distance[i] = Long.MAX_VALUE;//(long)Integer.MAX_VALUE * 10 * 10*10;
            reachable[i] = 0;
            shortest[i] = 1;
        }

        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) 
        {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else if(i != s){
                System.out.println(distance[i]);
            }
            else
                System.out.println("0");                   //..changed here
        }
    }

}

