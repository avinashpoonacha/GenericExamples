
import org.junit.Test;
import


public class BasicConnectionTest {

    @Test
    public void testConnection() {
        ConnectionPool pool = new BasicConnectionPool();
        pool.create("jdbc:mysql://localhost:3306/Avi?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","Iroko12me");
        assertTrue(pool.getConnection().isValid(1));

    }





}