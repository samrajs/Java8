package lambda;

interface Fly {
    default void takeOff() { System.out.println("Fly::takeOff"); }
    default void land() { System.out.println("Fly::land"); }
    default void turn() { System.out.println("Fly::turn"); }
    default void cruise() { System.out.println("Fly::cruise"); }
}
interface FastFly extends Fly {
    default void takeOff() { System.out.println("FastFly::takeOff"); }
}
interface Sail {
    default void cruise() { System.out.println("Sail::cruise"); }
    default void turn() { System.out.println("Sail::turn"); }
}
class Vehicle {
    public void turn() { System.out.println("Vehicle::turn"); }
}

public class SeaPlane extends Vehicle implements FastFly, Sail {
    private int altitude;
    //...
    public void cruise() {
        System.out.print("SeaPlane::cruise currently cruise like: ");
        if(altitude > 0)
            FastFly.super.cruise();
        else
            Sail.super.cruise();
    }

    public static void main(String[] args) {
        SeaPlane seaPlane = new SeaPlane();

        seaPlane.cruise();
        seaPlane.turn();
        seaPlane.land();
        seaPlane.takeOff();
    }
}