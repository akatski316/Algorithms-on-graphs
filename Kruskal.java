import java.util.*;

public class Kruskal
{
	public static class IDTree
	{
		public IDTree parent = this;
		public int index;

		public IDTree getId()
		{
			if(this.parent == this)
				return this;

			this.parent = this.parent.getId();

			return this.parent;
		}

		public void setId(IDTree node)
		{
			this.parent = node.getId();
		}
	}

	public static class Edge
	{
		public int from;
		public int to;

		public Edge(int from,int to)
		{
			this.from = from;
			this.to = to;
		}
	}

	public static List<Edge> path = new ArrayList<Edge>();
	public static int[][] A;
	public static int answ = 0;
	public static IDTree[] ID;
	public static int count = 0;

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		System.out.println("Enter the number of vertices: ");
		int n = in.nextInt();
		A = new int[n][n];
		ID = new IDTree[n];

		for(int i = 0;i < n;i++)
		{
			ID[i] = new IDTree();
			ID[i].index = i;
			for(int j = 0;j < n;j++)
			{
				//System.out.println("Enter the distance from vertex "+i+" to vertex "+j);
				A[i][j] = in.nextInt();
			}
		}	
		System.out.println("The minimum cost of a spanning tree in this is ");
		findpath();
		System.out.println(answ);

	}

	public static void findpath()
	{
		if(path.size() > 0)
		{ 
			
			int from  = path.get(path.size() - 1).from;
			int to = path.get(path.size() - 1).to;

			int tempval = 9999;
			for(int i = 0;i < A.length;i++)
				for(int j = 0;j < A.length;j++)
				{
					if((A[i][j] < tempval) && (A[i][j] >= A[path.get(path.size() - 1).from][path.get(path.size() - 1).to]) && (ID[i].getId() != ID[j].getId()))
					{
						from = i;
						to = j;
						tempval = A[i][j];
					} 
				}

			if(from > to)
			{
				ID[from].setId(ID[to]);
			}	
			else
				ID[to].setId(ID[from]);

			System.out.println(from+" and "+to);
			path.add(new Edge(from,to));
			answ += A[from][to];
			//System.out.println(path.get(path.size() - 1).from+" >> "+path.get(path.size() - 1).to+" "+ID[path.get(path.size() - 1).from].getId()+" "+ID[path.get(path.size() - 1).to].getId());
			count++;
			if(count != (A.length - 1))
				findpath();
		}
		else
		{
			int from = 0;
			int to = 0;
			int tempval = 9999; 
			for(int i = 0;i < A.length;i++)
				for(int j = 0;j < A.length;j++)
				{
					//System.out.println("("+A[i][j]+" < "+tempval+") && ("+A[i][j]+" <= "+A[from][to]+") && ("+ID[i].getId()+" != "+ID[j].getId()+")");
					if((A[i][j] < tempval) && (A[i][j] <= A[from][to]) && (ID[i].getId() != ID[j].getId()))
					{
						from = i;
						to = j;
						tempval = A[i][j];
					} 
				}

			if(from > to)
			{
				ID[from].setId(ID[to]);
			}	
			else
				ID[to].setId(ID[from]);

			System.out.println(from+" and "+to);
			path.add(new Edge(from,to));
			answ += A[from][to];
			count++;
			if(count != (A.length - 1))
				findpath();
		}
	}
}