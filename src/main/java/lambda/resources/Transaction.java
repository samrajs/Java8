package lambda.resources;

@FunctionalInterface
public interface Transaction {
    public void execute(Connection connection);
}
