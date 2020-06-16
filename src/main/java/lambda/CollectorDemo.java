package lambda;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

public class CollectorDemo {
    final List<Person> people = Arrays.asList(
            Person.builder().withName("John").withAge(20).build(),
            Person.builder().withName("Sara").withAge(21).build(),
            Person.builder().withName("Jane").withAge(21).build(),
            Person.builder().withName("Greg").withAge(35).build());


    public void findPeopleOlderThan20() {
        List<Person> olderThan20 = new ArrayList<>();
        people.stream()
                .filter(person -> person.age > 20)
                .forEach(person -> olderThan20.add(person));
        System.out.println("People older than 20: " + olderThan20);

        List<Person> olderThan20_1 =
                people.stream()
                        .filter(person -> person.age > 20)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + olderThan20_1);

        List<Person> olderThan20_2 =
                people.stream()
                        .filter(person -> person.age > 20)
                        .collect(toList());
        System.out.println("People older than 20: " + olderThan20_2);
    }

    public void groupByAge() {
        Map<Integer, List<Person>> peopleByAge =
                people.stream()
                        .collect(groupingBy(Person::getAge));
        System.out.println("Grouped by age: " + peopleByAge);

        Map<Integer, List<String>> nameOfPeopleByAge =
                people.stream()
                        .collect(
                                groupingBy(Person::getAge, mapping(Person::getName, toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);
    }

    public void groupByFirstLetterOfName() {
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter =
                people.stream()
                        .collect(groupingBy(person -> person.getName().charAt(0),
                                reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person of each letter:");
        System.out.println(oldestPersonOfEachLetter);

    }

    public boolean testProperty( String property ) {
        return System.getProperty( property ) != null && !System.getProperty( property ).equalsIgnoreCase( "false" );
    }

    public boolean testPropertyNegate( String property ) {
        return System.getProperty( property ) != null && !Boolean.getBoolean(property);
    }

    public static void main(String args[]) {
        CollectorDemo demo = new CollectorDemo();

        demo.findPeopleOlderThan20();
        demo.groupByAge();
        demo.groupByFirstLetterOfName();
        System.out.println(demo.testProperty( "test" ));
        System.out.println(demo.testProperty( "test1" ));

        System.out.println(demo.testPropertyNegate( "test" ));
        System.out.println(demo.testPropertyNegate( "test1" ));

    }
}
