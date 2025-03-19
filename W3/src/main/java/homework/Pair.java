package homework;

/**
 * Special Pair class for the landing time interval
 * @param <T> : first parameter
 * @param <U> : second parameter
 */

public class Pair<T, U>
{
    /**
     * Leftmost parameter
     */
    private T first;
    /**
     * Rightmost parameter
     */
    private U second;

    /**
     * Pair constructor
     * @param first T: Leftmost parameter;
     * @param second U: Rightmost parameter;
     */
    public Pair(T first, U second)
    {
        this.first = first;
        this.second = second;
    }

    /**
     * Getter for the leftmost parameter
     * @return T: first parameter
     */
    public T getFirst() {
        return first;
    }

    /**
     * Getter for the rightmost parameter;
     * @return U: second parameter
     */
    public U getSecond() {
        return second;
    }

    /**
     * Overriden method toString
     * @return String: Details about the pair object
     */
    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
