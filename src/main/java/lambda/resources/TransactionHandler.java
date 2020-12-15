package lambda.resources;

import io.vavr.control.Try;

public class TransactionHandler {

    public static void runInTransaction( Transaction transaction ) {
        System.out.println( "Creating connection" );
        Connection connection = getConnection();

        Try.run( () -> {
                System.out.println( "Executing instructions" );
                transaction.execute(connection);
                connection.commit();
            }).andFinally( () ->
             {
                System.out.println( "Closing connection" );
                connection.close();
            });
    }

    public static Connection getConnection() {
        return new Connection() {
            @Override
            public void commit() {
                System.out.println( "Committing transaction" );
            }

            @Override
            public void close() {
                System.out.println( "Closing connection" );
            }
        };
    }

    public static void main(String[] args) throws Exception {
        TransactionHandler.runInTransaction( connection -> {
            //All transactional code goes here.
        });
    }
}
