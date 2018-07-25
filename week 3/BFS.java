import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        int[] dist = new int[adj.length];

        ArrayList<Integer> Q = new ArrayList<Integer>();

        for(int i = 0;i < adj.length;i++)
            dist[i] = adj.length + 1;

        dist[s] = 0;
        int p = s;
        int i = 0;
        //System.out.println("before the loop Q was ");
        //print(Q);

        while(dist[t] == adj.length + 1)  //m == 0 || Q.size() != 0)
        {   //System.out.println("at this iter");
            //print(Q);    
            //System.out.println("our elements is "+adj[p].get(i)+" with distance "+dist[adj[p].get(i)]);
            if(dist[adj[p].get(i)] == adj.length + 1)  //..if elements distance is infinity
            { 
                Q.add(adj[p].get(i));
                dist[adj[p].get(i)] = dist[p] + 1;
               // System.out.println(adj[p].get(i)+" has been added to Q with distatnce "+(dist[p] + 1));
            }   
            //print(Q);
            if(i == adj[p].size() - 1)    //..if last element is reached
            {
                //System.out.println("now we've reached the last element of list "+p);
                if(Q.size() == 0)
                {
                  //  System.out.print(" and size of Q is "+Q.size());
                    return -1;
                }
                //System.out.println("curently ouw Q is as follows and we will take "+Q.get(0)+" ");
                //print(Q);
                p = Q.get(0);
                //System.out.println("now p is "+p);
                Q.remove(0);        
                //System.out.println("Q has been dequed");
                i = 0;
                //print(Q);
                continue;   
            }
            //if(Q.size() == 0)
              //  return -1;
            //print(Q);  
            //System.out.println();
            i++;
        }
        return dist[t];
        /*
        if(dist[t] != adj.length + 1)
            
        else
            return -1; 
        */                
    }

    public static void print(ArrayList<Integer> Q)
    {
        for(int i = 0;i < Q.size();i++)
            System.out.print(Q.get(i)+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        if(x == y)
        {
            System.out.println("-1");
            System.exit(0);
        }
      /*  System.out.println("here is our adj list");
        for(int i = 0;i < adj.length;i++)
        {
            print(adj[i]);
        }
        */
        System.out.println(distance(adj, x, y));
    }
}

