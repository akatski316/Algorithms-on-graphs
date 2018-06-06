import java.util.*;

public class Dijkstra 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of vertices");
		int n = in.nextInt();

		int[] dist = new int[n];
		boolean[] mark = new boolean[n];
		for(int i = 0;i < n;i++)
		{
			dist[i] = 99999999;
			mark[i] = false;
		}

		int[][] adjmatrix = new int[n][n];

		for(int i = 0;i < n;i++)
		{
			for(int j = 0;j < n;j++)
			{
				adjmatrix[i][j] = in.nextInt();
			}	
		}

		int nodesadded = 0;
		dist[0] = 0;
		
		for(int i = 0;nodesadded < n;)
		{
			mark[i] = true;
			for(int j = 0;j < n;j++)
			{
				if(dist[j] > dist[i] + adjmatrix[i][j])
					dist[j] = dist[i] + adjmatrix[i][j];
			}
			i = findNextMin(dist,mark,adjmatrix);
			nodesadded++;
		}

		for(int i = 0 ;i < n;i++)
			System.out.println(dist[i]);

	}

	public static int findNextMin(int[] dist,boolean[] mark,int[][] adjmatrix)
	{
		int temp = 99999999;
		int i,index = 0;
		for(i = 0;i < dist.length;i++)
		{
			if(dist[i] < temp && !mark[i])
			{
				temp = dist[i];
				index = i;
			}
		}
		return index;
	}
}