package Training.connPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BasicConnectionPool implements ConnectionPool {

    private static final int INIT_POOL_CAPACITY=10;
    private String user;
    private String url;
    private String password;
    private final List<Connection> connectionPool;
    private final List<Connection> UsedConnections=new ArrayList<Connection>();
    private static final int MAX_POOL_SIZE = 20;
    private static final int MAX_TIMEOUT = 5;

    public BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }



    /*
    let us create a connection pool that will check for init capacity AND CREATE CONNECTIONS
    returns our BasicConnectionPoo implementation
     */

    public static BasicConnectionPool create (String Url,String User,String Password) throws SQLException {

        List<Connection> pool = new ArrayList<Connection>(INIT_POOL_CAPACITY);
        IntStream.range(0,INIT_POOL_CAPACITY).forEach(
                e -> {
                    try {
                        pool.add(createConnection(Url, User, Password));
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
        );
    return new BasicConnectionPool(Url,User,Password,pool);
    }


    public void shutdown(){
        UsedConnections.forEach(this::releaseConnection);
        connectionPool.forEach(e -> {
            try {
                e.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        connectionPool.clear();
    }

    public static Connection createConnection(String Url,String User,String Password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(Url,User,Password);
    }

    public int getPoolSize(){
        return connectionPool.size()+ UsedConnections.size();
    }


    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connectionPool.isEmpty()){
            if (UsedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection(url, user, password));
            } else {
                throw new RuntimeException("Max Pool size reached ");
            }
        }
        Connection connection = connectionPool.remove(connectionPool.size()-1);
        if(!connection.isValid(MAX_TIMEOUT)){
            connectionPool.add(createConnection(url, user, password));
        }
            UsedConnections.add(connection);
            return connection;
        //throw new IllegalStateException("Connection pool is full , Unable to create a connection");
    }


    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
       return UsedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }




}
