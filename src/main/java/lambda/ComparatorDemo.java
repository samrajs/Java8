package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
//import Comparator;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class ComparatorDemo {
    final List<Person> people = Arrays.asList(
            Person.builder().withName("John").withAge(20).build(),
            Person.builder().withName("Sara").withAge(21).build(),
            Person.builder().withName("Jane").withAge(21).build(),
            Person.builder().withName("Greg").withAge(35).build());

    public void sort() {
        List<Person> ascendingAge =
                people.stream()
                        .sorted((person1, person2) -> person1.ageDifference(person2))
                        .collect(toList());
        printPeople("Sorted in ascending order by age: ", ascendingAge);

        ascendingAge =
                people.stream()
                        .sorted(Person::ageDifference)
                        .collect(toList());

        printPeople("Sorted in ascending order by age: ", ascendingAge);

        printPeople("Sorted in ascending order by name: ",
                people.stream()
                        .sorted((person1, person2) ->
                                person1.name.compareTo(person2.name))
                        .collect(toList()));
    }

    public void sortReuse() {
        Comparator<Person> compareAscending =
                (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();

        printPeople("Sorted in ascending order by age: ",
                people.stream()
                        .sorted(compareAscending)
                        .collect(toList())
        );
        printPeople("Sorted in descending order by age: ",
                people.stream()
                        .sorted(compareDescending)
                        .collect(toList())
        );

    }

    public void findMinMax() {
        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));

        people.stream()
                .max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));
    }

    public void sortByName() {
        printPeople("Sorted in descending order by name: ",
            people.stream()
                    .sorted((person1, person2) ->
                            person1.name.compareTo(person2.name))
                    .collect(toList()));

        final Function<Person, String> byName = person -> person.name;
        printPeople("Sorted in descending order by name: ", people.stream()
                .sorted(comparing(byName))
                .collect(toList())
        );
    }

    public void sortByAgeName() {
        final Function<Person, Integer> byAge = person -> person.age;
        final Function<Person, String> byTheirName = person -> person.name;

        printPeople("Sorted in ascending order by age and name: ",
                people.stream()
                        .sorted(comparing(byAge).thenComparing(byTheirName))
                        .collect(toList()));
    }

    public static void printPeople(
            final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    public static void main(String args[]) {

        ComparatorDemo instance = new ComparatorDemo();

        instance.sort();

        instance.sortReuse();

        instance.findMinMax();
        instance.sortByName();
        instance.sortByAgeName();
    }
}
