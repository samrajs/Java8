package lambda;

import java.util.Arrays;
import java.util.List;

public class Parallel {
    static List<Integer> numbers = Arrays.asList(0,1,2,3,4,5,6,7,8,9);

    public static void print( int a ) {
        System.out.println( "Printing numbers :" + a + "->" + Thread.currentThread());
    }

    public static int map( int a ) {
        System.out.println( "Mapping numbers :" + a + "->" + Thread.currentThread());
        return a;
    }

    public static void main(String[] args) {
        numbers.stream()
                .parallel()
                .map(Parallel::map)
                .forEachOrdered(Parallel::print);
    }
}
