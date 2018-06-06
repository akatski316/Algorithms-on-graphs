import java.util.*;

public class Hamil
{
	public static boolean[] temprestriction;
	public static boolean[][] A;
	public static int count = 0;
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		System.out.println("enter the number of nodes");
		int n = in.nextInt();
		temprestriction = new boolean[n];
		System.out.println("enter the graph:");
		A = new boolean[n][n];

		for(int i = 0;i < n;i++)
			for(int j = 0;j < n;j++)
			{
				if(i != j)
				{
					System.out.println("is there a path between "+i+" and "+j);
					A[i][j] = in.nextBoolean();
				}
			} 

		List<Integer> currenttrack = new ArrayList<Integer>();
		
		currenttrack.add(0);		
		HamilTonian(currenttrack);	
		System.out.println(count);
	}

	public static void HamilTonian(List<Integer> currenttrack)
	{
		if(currenttrack.size() == A.length && A[currenttrack.get(currenttrack.size() - 1)][0])
		{
			print(currenttrack);
			count++;
			return;
		}

		for(int i = 1;i < A.length;i++)
		{
			if((temprestriction[i] == false) && A[currenttrack.get(currenttrack.size() - 1)][i])
			{ 
				currenttrack.add(i);
				temprestriction[i] = true;
				HamilTonian(currenttrack);
				temprestriction[i] = false;
				currenttrack.remove(currenttrack.size() - 1);
			}	
		}
		return;
	}

	public static void print(List<Integer> currenttrack)
	{
		for(Integer i:currenttrack)
			System.out.print(i+" ");
		System.out.print("0");	
		System.out.println();
	}
}