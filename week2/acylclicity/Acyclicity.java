import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
	public static int Preorder[];
	public static int Postorder[];
	public static boolean visited[];
	public static int clock = 1;
	public static ArrayList<Integer>[] revadj;
	public static ArrayList<Integer>[] adj;

    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here


        //..creating reverse graph
        int count = 0;
        revadj = (ArrayList<Integer>[])new ArrayList[adj.length];  //..this array of losts contains 
       														//..reverse graph
        for (int i = 0; i < revadj.length; i++) {
            revadj[i] = new ArrayList<Integer>();
        }
       
        for(int i = 0;i < adj.length;i++)
        {
        	for(int j = 0;j < adj[i].size();j++)
        	{
        		revadj[adj[i].get(j)].add(i);
        	}
        }

        DFS(revadj);
        //..Postorder got filled up for reverse graph

        for(int i = 0;i < revadj.length;i++)
	       	visited[i] = false;

	     //..since in last call to DFS all vertices were marked visited so the are reinitialised to false  

        while(true)      //..a forever loop
        {
        	int v = FindNextLargestPstOrdr();  //..finding next largest postorder vertex of reverse graph

        	if(v != -1 && !visited[v])      //..if all re visited the v will get -1 and loop is needed to be 
        	{								//..broken
        		Explore2(v);
        		count++;
        	}
        	else if(v == -1)
        		break;

        }

        if(count == revadj.length)
        	return 0;
        return 1;
    }
    public static int FindNextLargestPstOrdr()
    {
    	int temp = 0,index = -1;
    	for(int i = 0;i < Postorder.length;i++)
    		if(!visited[i] && Postorder[i] > temp)
    		{
    			temp = Postorder[i];
    			index = i;
    		}
    	//Postorder[index] = -1;
    	return index;
    }
    public static void DFS(ArrayList<Integer>[] revadj)
    {
    	for(int i = 0;i < revadj.length;i++)
    	{
    		if(!visited[i])
    			Explore(i);
    	}
    }
    public static void Explore2(int k){
    	visited[k] = true;
    	for(Integer i : adj[k])
    	{
    		if(!visited[i])
    			Explore2(i);
    	}

    }
    public static void Explore(int k){
    	visited[k] = true;
    	Preorder[k] = clock;
    	clock++;
    	for(Integer i : revadj[k])
    	{
    		if(!visited[i])
    			Explore(i);
    	}
    	Postorder[k] = clock;
    	clock++;

    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Preorder = new int[n];
        Postorder = new int[n];
        visited = new boolean[n];
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
        System.out.println(acyclic(adj));
    }
}

