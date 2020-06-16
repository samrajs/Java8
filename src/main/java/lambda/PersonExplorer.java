package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PersonExplorer {
    public void check( List<Person> persons, Predicate<Person> predicate ) {
        for (Person person:persons) {
            if ( predicate.test(person) ) {
                System.out.println(person);
            }
        }
    }

    public void check(List<Person> persons, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person person:persons) {
            if ( predicate.test(person) ) {
                consumer.accept(person);
            }
        }
    }

    public <X, Y> void check(List<X> persons,
                             Predicate<X> predicate,
                             Function<X, Y> function,
                             Consumer<Y> consumer) {
        for (X person:persons) {
            if ( predicate.test(person) ) {
                Y output = function.apply(person);
                consumer.accept(output);
            }
        }
    }

    public static void main( String[] args ) {
        List<Person> persons = new ArrayList<>();

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

        (new PersonExplorer()).check( persons, person -> person.age > 35 );
        (new PersonExplorer()).check( persons, (Person person) -> person.age < 35 );

        (new PersonExplorer()).check( persons, person -> person.age > 35, person -> System.out.println(person) );
        (new PersonExplorer()).check( persons, (Person person) -> person.age < 35, (Person person) -> System.out.println(person) );

        (new PersonExplorer()).check( persons,
                (Person person) -> person.age < 35,
                person -> person.firstName + " " + person.name,
                output -> System.out.println(output) );

        persons.stream()
                .filter(person -> person.age > 35)
                .map(person -> person.firstName + " " + person.name)
                .forEach(System.out::println);

        Person[] personArray = persons.toArray( new Person[ persons.size() ] );

        Arrays.sort(personArray, (a,b)-> {return a.age - b.age;} );
    }
}
