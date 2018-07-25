import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        int[] colour = new int[adj.length];

        ArrayList<Integer> Q = new ArrayList<Integer>();

        for(int i = 0;i < adj.length;i++)
            colour[i] = -1;

        colour[0] = 0;
        int i;
        int color = 0,p;
        Q.add(0);

        while(Q.size() != 0)
        {

        	p = Q.get(0);
        	Q.remove(0);

        	for(i = 0;i < adj[p].size();i++)
        	{
        		if(colour[adj[p].get(i)] != colour[p] && colour[adj[p].get(i)] != -1)
        			continue;

        		if(colour[adj[p].get(i)] != colour[p] || colour[adj[p].get(i)] == -1)
        		{
        			Q.add(adj[p].get(i));
                	colour[adj[p].get(i)] = 1 - colour[p];
                	//System.out.println(adj[p].get(i)+" is coloured with "+color);
        		}
        		else
        		{
        			//System.out.println("now the colours are the same so returning 0");
        			return 0;
        		}
        	}
        }	

        return 1;
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

        System.out.println(bipartite(adj));
    }
}

