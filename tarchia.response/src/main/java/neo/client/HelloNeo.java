package neo.client;
import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;
public class HelloNeo implements AutoCloseable {

    private final Driver driver;

    private HelloNeo(String uri, String username, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic( username, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public static void main(String... args) throws Exception {
        try (HelloNeo greeter = new HelloNeo("bolt://localhost:7687", "neo4j", "skuggfisk")) {
            greeter.sayHi("Message");
        }
    }

    private void sayHi(final String message) {
        try (Session session = driver.session()) {
            String result = session.writeTransaction(transaction -> {
                StatementResult res = transaction.run("CREATE (a:Greeting) " +
                        "set a.message = $message " +
                        "RETURN a.message + ', from node ' + id(a)",
                        parameters("message", message));
                return res.single().get(0).asString();
            });
            System.out.println(result);
        }
    }
}
