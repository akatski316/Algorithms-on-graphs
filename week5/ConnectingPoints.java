import java.util.*;

public class ConnectingPoints {

	public static Vertex[] vertices;
	public static class Edge implements Comparable<Edge>
	{
		public int point1;
		public int point2;
		public double weight;

		public Edge(int a,int b,double c)
		{
			point1 = a;
			point2 = b;
			weight = c;
		}

		@Override
		public int compareTo(Edge B)
		{
			if(this.weight > B.weight)
				return 1;
			else if(this.weight == B.weight)
				return 0;
			else 
				return -1;	
			
		}
	}

	public static class Vertex
	{
		public int VertexNumber;
		public Vertex parent;

		public Vertex(int i)
		{
			VertexNumber = i;
			parent = this;
		}

		public Vertex ID()
		{
			if(this.parent == this)
				return this;

			this.parent = this.parent.ID();

			return this.parent;
		}
	}

    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        //double[] weight = new double[(x.length * (x.length - 1))/2];
        //Edge[] Edges = new Edge[(x.length * (x.length - 1))/2];
        ArrayList<Edge> Edges = new ArrayList<Edge>();
        double weight;
        int k = 0;
        for(int i = 0;i < x.length;i++)
        {
        	for(int j = i+1;j < x.length;j++)
        	{
        		
        		double x_diff = x[i] - x[j];
        		double y_diff = y[i] - y[j];
        		weight = (x_diff)*(x_diff) + (y_diff)*(y_diff);
        		weight = Math.sqrt(weight);
        		//System.out.println("points are ("+x[i]+","+y[i]+") and ("+x[j]+","+y[j]+") distance between these are "+weight);
        		Edges.add(new Edge(i,j,weight));
        		k++;
        	}
        }
        //printEdges(Edges);

        Collections.sort(Edges);

        //printEdges(Edges);

        for(int i = 0;i < Edges.size();i++)
        {
        	int p1 = Edges.get(i).point1;
        	int p2 = Edges.get(i).point2;

        	//System.out.println("weight is "+Edges.get(i).weight+" and point are ("+x[p1]+","+y[p1]+") and ("+x[p2]+","+y[p2]+")");
        	//System.out.println("ID of "+p1+" vertex is "+vertices[p1].ID()+" and for p2 is"+vertices[p2].ID());

        	if(vertices[p1].ID() != vertices[p2].ID())
        	{
        		result += Edges.get(i).weight;
        		vertices[p1].parent.parent = vertices[p2].parent;
        	}
        }


        return result;
    }

    public static void printEdges(ArrayList<Edge> Edges)
    {
    	for(int i = 0;i < Edges.size();i++)
    	{
    		System.out.print(Edges.get(i).weight+" ");
    	}
    	System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        vertices = new Vertex[n];

        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
            vertices[i] = new Vertex(i);
        }

        System.out.println(minimumDistance(x, y));
    }
}

