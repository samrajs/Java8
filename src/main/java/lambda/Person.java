package lambda;

import java.util.function.Predicate;

public class Person {
    protected String name;
    protected String firstName;
    protected int age;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder{
        protected String name;
        protected String firstName;
        protected int age;

        private Builder() {

        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withFirstName( String firstName ) {
            this.firstName = firstName;
            return this;
        }

        public Builder withAge( int age ) {
            this.age = age;
            return this;
        }

        public Person build() {
            Person person = new Person();

            person.age = this.age;
            person.name = this.name;
            person.firstName = this.firstName;

            return person;
        }
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(final Person other ) {
        return age - other.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
