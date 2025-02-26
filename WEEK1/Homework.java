//team english: Dan Mircea Me Denisa 25th march

public class Homework {

    static void generateGraph(int[][]graph, int n)
    {
        for(int i = 0; i < n; i++)
        {
            for(int j = i; j < n; j++)
            {
                if(i == j)
                {
                    graph[i][j] = 0;
                }
                else 
                {
                    int random = (int)(Math.random() * 2);
                    graph[i][j] = random;
                    graph[j][i] = random;
                }
            }
        }
    }

    static void addClique(int[][] graph, int n, int k)
    {
        for(int i=0; i<k; i++)
        {
            for(int j=i; j<k; j++)
            {
                if(i!=j)
                {
                    graph[i][j]=1;
                    graph[j][i]=1;
                }
            }
        }
    }

    static void addStable(int[][] graph, int n, int k)
    {
        for(int i=0; i<k; i++)
        {
            for(int j=i; j<k; j++)
            {
                if(i!=j)
                {
                    graph[i][j]=0;
                    graph[j][i]=0;
                }
            }
        }
    }

    static int getEdges(int[][] graph, int n)
    {
        int m = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=i; j<n; j++)
            {
                if(graph[i][j]==1)
                {
                    m++;
                }
            }
        }

        return m;
    }

    static void printGraph(int[][] graph, int n)
    {
        for(int i=0; i<n; i++)
        {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++)
            {
                if(graph[i][j]==1)
                {
                    char c = '\u25A0';
                    sb.append(c).append(' ');
                }
                else 
                {
                    char c='\u25A1';
                    sb.append(c).append(' ');
                }
            }
            System.out.println(sb);
        }
    }

    static void getDegrees(int[][] graph, int[] degrees, int n)
    {
        for (int i=0; i<n; i++)
        {
            int deg = 0;
            for(int j=0; j<n; j++)
            {
                if(graph[i][j]==1)
                {
                    deg++;
                }
            }
            degrees[i] = deg;
        }
    }

    static int maxDeg(int[] degrees)
    {
        int max = degrees[0];
        {
            for(int i=1; i<degrees.length; i++)
            {
                if(max<degrees[i]) max=degrees[i];
            }
        }
        return max;
    }

    static int minDeg(int[] degrees)
    {
        int min = degrees[0];
        {
            for(int i=1; i<degrees.length; i++)
            {
                if(min>degrees[i]) min=degrees[i];
            }
        }
        return min;
    }

    static int Arrsum(int[] degrees)
    {
        int sum=0;
        for (int i=0; i<degrees.length; i++)
        {
            sum+=degrees[i];
        }

        return sum;
    }
    

    

    
    public static void main(String[] args)
    {
        if(args.length < 2)
        {
            System.err.println("USAGE: java Homework <n> <k>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int m;
        int sumdeg;

        //Creating a random graph as adjacent matrix
        int[][] graph = new int[n][n];
        int[] degrees = new int[n];
        generateGraph(graph, n);

        


        //Get the number of edges
        m = getEdges(graph, n);
        
        //Get the all degrees of each node
        getDegrees(graph, degrees, n);
        
        //Compute the sum of all degrees
        sumdeg = Arrsum(degrees);


        printGraph(graph, n);


        //check wether the sum of all degrees is 2*m
        if(sumdeg == 2*m) 
        {
            System.out.println("This is a graph: 2 * " + m + " = " + sumdeg);
        }
        else 
        {
            System.out.println("Is not a graph");
        }
        

        System.out.println("The total number of edges: "+m);

        //Max Degree
        System.out.println("\u0394(G) = " + maxDeg(degrees));
        
        //Min Degree
        System.out.println("\u03B4(G) = " + minDeg(degrees));

    }
}

//Time complexity of O(n^2)
