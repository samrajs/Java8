package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class CoffeeBar {
    public static void main(String[] args) {
        Coffee coffee = new FilterCoffee("Filter Coffee");
        Consumer<String> consumer = coffeeInfo-> {
                Coffee result = coffee.prepareCoffee();
                System.out.println(String.format("with %s: %s", coffeeInfo,
                        result.getName() + " " + result.getCost() ));
        };

        consumer.accept( "Nothing special" );

        coffee.addContiments( Mocha::new );

        consumer.accept( "Mocha" );

        coffee.addContiments( Whip::new );

        consumer.accept( "Whip" );

//        coffee.addContiments( Coffee::mocha, Coffee::whip );
        coffee.addContiments( Mocha::new, Whip::new );

        consumer.accept( "Mocha and Whip Coffee" );
    }
}

abstract class Coffee {
    String name;
    double cost;

    public String getName() { return "Unknown"; };
    public abstract double getCost();

    Function<Coffee, Coffee> contiments = null;

    public void addContiments(Function<Coffee, Coffee> ... contiments) {
        this.contiments = Stream.of(contiments)
                .reduce((contiment, next) -> next.compose(contiment))
                .orElse(coffee->coffee);
    }

    public Coffee prepareCoffee() {
        return contiments.apply(this );
    }
}

class FilterCoffee extends Coffee {


    public FilterCoffee( String name ) {
        this.name = name;
        this.cost = 1d;

        addContiments();
    }
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Coffee mocha() {
        FilterCoffee mocha = new FilterCoffee( this.name + ", Mocha" );
        mocha.cost = this.cost + 0.2d;
        return mocha;
    }

    public Coffee whip() {
        FilterCoffee whip = new FilterCoffee( this.name + ", Whip" );
        whip.cost = this.cost + 0.5d;
        return whip;
    }

    double cost() {
        return cost;
    }
}

abstract class CoffeeDecorator extends Coffee {

}

class Mocha extends CoffeeDecorator {
    Coffee coffee;

    public Mocha( Coffee coffee ) {
        this.coffee = coffee;
    }

    @Override
    public String getName() {
        return coffee.getName() + ", Mocha";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}

class Whip extends CoffeeDecorator {
    Coffee coffee;

    public Whip( Coffee coffee ) {
        this.coffee = coffee;
    }

    @Override
    public String getName() {
        return coffee.getName() + ", Whip";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}