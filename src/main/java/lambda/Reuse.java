package lambda;

import sun.awt.geom.AreaOp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Reuse {
    final List<Person> persons = new ArrayList<>();
    Reuse() {
        persons.add(Person.builder()
                .withFirstName("Samraj")
                .withName( "Subramaniam" )
                .withAge(43)
                .build());

        persons.add(Person.builder()
                .withFirstName("Sujitha")
                .withName( "Chinnathambi" )
                .withAge(33)
                .build());
    }

    private void problem() {
        persons.stream()
                .filter(person -> person.name.startsWith( "S" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );

        persons.stream()
                .filter(person -> person.name.startsWith( "C" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );
    }

    private Predicate<Person> startsWith( String letter ) {
        return person -> person.name.startsWith( letter );
    }

    private void reuse1() {
        persons.stream()
                .filter( startsWith( "S" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );

        persons.stream()
                .filter( startsWith( "C" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );
    }

    private void reuse2() {
        Function<String, Predicate<Person>> function = (letter) -> {
            Predicate<Person> predicate = person -> person.name.startsWith(letter);
            return predicate;
        };

        persons.stream()
                .filter( function.apply( "S" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );

        persons.stream()
                .filter( function.apply( "C" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );
    }

    private void reuse3() {
        Function<String, Predicate<Person>> function = letter -> person -> person.name.startsWith(letter);

        persons.stream()
                .filter( function.apply( "S" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );

        persons.stream()
                .filter( function.apply( "C" ) )
                .map( person -> person.firstName + " " + person.name )
                .forEach( System.out::println );
    }

    public static void main( String[] args ) {
        Reuse reuse = new Reuse();

        System.out.println( "Problem" );
        reuse.problem();

        System.out.println( "Reuse1" );
        reuse.reuse1();

        System.out.println( "Reuse2" );
        reuse.reuse2();

        System.out.println( "Reuse3" );
        reuse.reuse3();
    }
}
