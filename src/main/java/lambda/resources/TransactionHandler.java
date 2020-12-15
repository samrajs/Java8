package lambda.resources;

import io.vavr.control.Try;

public class TransactionHandler {

    public static void runInTransaction( Transaction transaction ) {
        System.out.println( "Creating connection" );
        Connection connection = getConnection();

        Try.run(
            ()-> {
                System.out.println( "Executing instructions" );
                transaction.execute(connection);
                connection.commit();
            }
        ).andFinally(
            () -> connection.close()
        ).onFailure(
                e -> System.out.println(e)
        );
    }

    public static Connection getConnection() {
        return new Connection(){
            public void commit() {
                System.out.println( "Committing transaction" );
            }

            public void close() throws RuntimeException {
                System.out.println( "Closing connection" );
                throw new RuntimeException( "exception in close" );
            }
        };
    }

    public static void main(String[] args) throws Exception {
        TransactionHandler.runInTransaction( connection -> {
            //All transactional code goes here.
        });
    }
}
