package lambda.resources;

import java.sql.Connection;

public class TransactionHandler {

    public static void runInTransaction( Transaction transaction ) throws Exception {
        System.out.println( "Creating connection" );
        Connection connection = getConnection();

        try {
            System.out.println( "Executing instructions" );
            transaction.execute(connection);
        }
        finally {
            System.out.println( "Closing connection" );
            connection.close();
        }
    }

    public static Connection getConnection() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        TransactionHandler.runInTransaction( connection -> {});
    }
}
