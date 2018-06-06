import java.util.*;

public class Floyd
{
	public static void main(String[] rag)
	{
		Scanner in = new Scanner(System.in);

		System.out.println("enter total number of vertices and edges:");
		int n = in.nextInt();
		int E = in.nextInt();
		int[][] A = new int[n][n]; 
		for(int i = 0;i < n;i++)
			for(int j = 0;j < n;j++)
				A[i][j] = 999999;

		System.out.println("as per zero indexing enter the edges in format of\nvertex1 <Space> vertex2 <space> distance:");
		
		for(int i = 0;i < E;i++)
		{
			int temp1 = in.nextInt();
			int temp2 = in.nextInt();
			A[temp1][temp2] = in.nextInt();
		}

		for(int rowcol = 0;rowcol < n;rowcol++)
		{
			for(int i = 0;i < n;i++)
				for(int j = 0;j < n;j++)
				{	
					if(i != j)
						A[j][i] = Math.min(A[j][i],(A[rowcol][i]+A[j][rowcol]));
				}
		}

		System.out.println("here is out adj matrix:");
		for(int i = 0;i < n;i++)
		{
			for(int j = 0;j < n;j++)
				System.out.print(A[i][j]+" ");
			System.out.println();
		}
	}
}