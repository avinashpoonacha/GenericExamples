package Training.connPool;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class BasicConnectionTest {

    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void setUpBasicConnectionPoolInstance() throws SQLException {
        connectionPool = BasicConnectionPool.create("jdbc:mysql://localhost:3306/Avi?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","Iroko12me");
    }

    @Test
    public void testConnection() throws SQLException, ClassNotFoundException {
        assertTrue(connectionPool.getConnection().isValid(1));

    }

    @Test
    public void givenBasicConnectionPoolInstance_whenCalledreleaseConnection_thenCorrect() throws SQLException, ClassNotFoundException {
        Connection connection = connectionPool.getConnection();
        boolean test = connectionPool.releaseConnection(connection);
       assertTrue(test=true);
    }

    @Test(expected = RuntimeException.class)
    public void givenBasicConnectionPoolInstance_whenAskedForMoreThanMax_thenError() throws Exception {
        // this test needs to be independent so it doesn't share the same connection pool as other tests
        ConnectionPool cp = BasicConnectionPool.create("jdbc:mysql://localhost:3306/Avi?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","Iroko12me");

        final int MAX_POOL_SIZE = 20;
        for (int i = 0; i < MAX_POOL_SIZE + 1; i++) {
            cp.getConnection();
        }
        fail();
    }

    @Test
    public void givenBasicConnectionPoolInstance_whenShutdown_thenEmpty() throws Exception {
       ConnectionPool cp = BasicConnectionPool.create("jdbc:mysql://localhost:3306/Avi?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","Iroko12me");
       int t =  ((BasicConnectionPool) cp).getPoolSize();
        assertTrue( t== 10);

        ((BasicConnectionPool) cp).shutdown();
        assertTrue(((BasicConnectionPool) cp).getPoolSize()== 0);
    }



}