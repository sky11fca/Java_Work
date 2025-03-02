import java.util.List;
import java.util.ArrayList;

public class Backtracking
{
    public static void main(String[] args)
    {
        int n = 10;
        int k = 4;

        List<List<Integer>> result = combine(n, k);

        for(List<Integer> combination : result)
        {
            System.out.println(combination);
        }
    }

    public static List<List<Integer>> combine(int n, int k)
    {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    public static void backtrack(List<List<Integer>> result, List<Integer> temp, int start, int n, int k)
    {
        if(temp.size()==k)
        {
            result.add(new ArrayList<>(temp));
            return;
        }

        int remaining = k-temp.size();
        for(int i = start; i <= n-remaining+1 ; i++)
        {
            temp.add(i);
            backtrack(result, temp, i+1, n, k);
            temp.remove(temp.size()-1);
        }
    }

}

