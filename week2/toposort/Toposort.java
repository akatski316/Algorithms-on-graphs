import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    public static int Preorder[];
    public static int Postorder[];
    public static boolean visited[];
    public static int clock = 1;
    public static ArrayList<Integer> order;
    public static ArrayList<Integer>[] adj;


    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        
        order = new ArrayList<Integer>();
        //write your code here
        int temp;
        dfs();

       /* for(int i = 0;i < adj.length;i++)
           visited[i] = false;

        temp = FindNextLargestPstOrdr();

        while(temp != -1)
        {
            //System.out.println("adding "+(temp + 1));
            order.add(temp);
            temp = FindNextLargestPstOrdr();
        }*/
        return order;
    }

    private static void dfs() {
      //write your code here
        for(int i = 0;i < adj.length;i++)
        {
            if(!visited[i])
            {
                //order.add((i+1));
                Explore(i);
            }
        }
    }

     public static void Explore(int k){
        visited[k] = true;
        
        Preorder[k] = clock;
        clock++;
        for(Integer i : adj[k])
        {
            if(!visited[i])
            {
                
                Explore(i);
            }
        }
        Postorder[k] = clock;
        clock++;
        order.add((k));
    }

   /* public static int FindNextLargestPstOrdr()
    {
        int temp = 0,index = -1;
        for(int i = 0;i < Postorder.length;i++)
            if(!visited[i] && Postorder[i] > temp)
            {
                temp = Postorder[i];
                index = i;
        
            }
        if(index != -1)    
            visited[index] = true;

        return index;
    }
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        visited = new boolean[n];
        Preorder = new int[n];
        Postorder = new int[n];
        int m = scanner.nextInt();
        adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        ArrayList<Integer> order = toposort(adj);
       // for (int x : order) {
            //System.out.print((x + 1) + " ");
        //}
        for(int x = order.size() - 1;x >= 0;x--)
           System.out.print((order.get(x)+1) + " "); 
    }
}

