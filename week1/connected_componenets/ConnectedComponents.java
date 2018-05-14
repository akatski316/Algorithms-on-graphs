import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    public static int[] visited;
    static int cc = 0; 
    private static int numberOfComponents(ArrayList<Integer>[] adj, int n) {
        //cc = 0;
        for(int i = 0;i < n;i++)
        {
            if(visited[i] == 0)
            {
                Explore(i,adj);
                cc++;
            }
        }
        return cc;
    }

    private static void Explore(int x, ArrayList<Integer>[] adj) 
    {
        visited[x] = 1;
        for(int i = 0;i < adj[x].size();i++ )
            if(visited[adj[x].get(i)] == 0)
                Explore(adj[x].get(i),adj);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        visited = new int[n];
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) 
        {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }

        System.out.println(numberOfComponents(adj,n));
    }
}

