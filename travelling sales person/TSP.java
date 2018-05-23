import java.util.*;

class TSP
{
	int cost;                       //..current cost
	int valueofprevvertex;          //..what was previous vertex
	TSP prevvertex;                 //..a reference to this vertex to keep track

	TSP(int a,int b, TSP c)
	{
		this.cost = a;
		this.valueofprevvertex = b;
		this.prevvertex = c;	
	}

	public static int n;
	public static int weight[][];
	public static int tour[];
	

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in); 
		System.out.println("Enter no. of nodes:=>"); 
		n=s.nextInt();
		List<Integer> all = new ArrayList<Integer>();
		weight=new int[n][n]; 
		tour=new int[n-1];
		for(int i=0;i<n;i++)
		{
			if(i != 0)
				all.add(i); 	//..all vertices other than starting vetex i.e 0
			for(int j=0;j<n;j++)
			{
				if(i!=j)
				{
					//System.out.print("Enter weight of "+(i+1)+" to "+(j+1)+":=>");
					weight[i][j]=s.nextInt();
				}
			}
		}

		System.out.println();
		System.out.println("Starting node assumed to be node 1.");
		List<Integer> restrictedset = new ArrayList<Integer>();  //..we keep on removing vertices to reduce problem
								//..those vertices are send in this list
		
		System.out.println(getMinCost(restrictedset,all,0,0).cost);
	}
	/* this method returns a TSP object that contains minimum possible cost
	*/
	public static TSP getMinCost(List<Integer> restrictedset,List<Integer> all,int prevvertex,int backsum)
	{
		restrictedset.add(prevvertex);
		List<TSP> temp = new ArrayList<TSP>();   //..temp is a list tsp object that contains
		int index = 0;
		int min = 999999;

		if(restrictedset.size() > 1)
			backsum = backsum + weight[prevvertex][restrictedset.get(restrictedset.size()-2)];
		
		for(int i = 0;(i < all.size() && restrictedset.size() > all.size());i++)  //gets executed only if all vertices are  
		{								//..added to restricted set
			if(restrictedset.get(restrictedset.size() - 1) == all.get(i))
			{
				restrictedset.remove(restrictedset.size() - 1);
				return new TSP(weight[0][all.get(i)]+backsum, all.get(i), null);
			}		
		}
		int k = 0;        
		for(int i = 0;(i < all.size());i++)
		{
			if(!restrictedset.contains(all.get(i)))
			{
				temp.add(getMinCost(restrictedset, all, all.get(i),backsum));
				if(temp.size() > 0 && temp.get(temp.size() - 1).cost < min)
				{
					min = temp.get(temp.size() - 1).cost;	
					index = k;
					k++;
				}
			}
		}
		restrictedset.remove(restrictedset.size() - 1);
		return new TSP(min,temp.get(index).valueofprevvertex,temp.get(index));
	}
}



