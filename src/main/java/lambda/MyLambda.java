package lambda;

public class MyLambda {
    public static void main(String[] args ) {
        HalloInterface hallo = (name) -> System.out.println( "Hallo " + name + ", I am here" );

        hallo.sayHallo("Sam");
    }

    interface HalloInterface {
        void sayHallo(String name);
    }
}
