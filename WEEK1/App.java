public class App {

    static int digitSum(int n)
    {
        int sum=0;
        while(n>0)
        {
            sum+=n%10;
            n/=10;
        }

        return sum;
    }

    public static void main(String[] args)
    {
        //DISPLAY HELLO WORLD
        System.out.println("Hello World!");

        //DEFINE AN ARRAY OF STRINGS
        String[] a = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        //GENERATING A RANDOM INTEGER
        int n = (int)(Math.random()*1000000);

        System.out.println(n);

        //MULTIPLY n with 3, add bin nr 10101 to resuld, add FF to result, Multiply result by 6
        int result = (n*3 + 0b10101 + 0xFF)*6;
        System.out.println(result);

        //compute the sum of digits of the result(recursively till is 1 digit)
        while(result>9)
        {
            result = digitSum(result);
            System.out.println(result);
        }

        System.out.println("Willy-nilly, in this semester I shall learn the " + a[result] + " language.");
        
    }
}

//NOTE: From many running instances, will cause every time to display the last element from the languages array. Therefore, will alway say I will learn Java this semester
//(In other words, another shitty motivational pressuring from professors!)