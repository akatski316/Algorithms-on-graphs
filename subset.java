import java.util.*;

public class subset 
{
	public static List<Integer> set = new ArrayList<Integer>();
	public static int d;
	public static int count = 0;
	public static boolean[] restriction;
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		System.out.println("enter the number of elements of the set");
		int n = in.nextInt();

		restriction = new boolean[n];
		

		for(int i = 0;i < n;i++)
			set.add(in.nextInt());

		System.out.println("Enter sum");
		d = in.nextInt();


		List<Integer> subset = new ArrayList<Integer>();
		answer(subset,0);
		System.out.println(count);

	} 

	public static void answer(List<Integer> subset,int sum) 
	{
		if(sum > d)
			return;

		if(sum == d)
		{
			print(subset);
			count++;
			return;
		}

		for(int i = 0;i < set.size();i++)
		{
			if(!restriction[i])
			{	
				subset.add(set.get(i));
				restriction[i] = true;
				sum += set.get(i);
				answer(subset,sum);
				subset.remove(subset.size() - 1);
				sum -= set.get(i);
				restriction[i] = false;
			}	
		}
	}

	public static void print(List<Integer> subset)
	{
		for( Integer i : subset)
			System.out.print(i+" ");
		
		System.out.println();
	}
}