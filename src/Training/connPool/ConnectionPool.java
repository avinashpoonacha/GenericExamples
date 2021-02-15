package Training.connPool;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {

     /*
     A Connection pool interface needs common elements such as
     1. getting a connection
     3. releasing a connection
     4. get url , pwd , user
      */

     Connection getConnection() throws SQLException, ClassNotFoundException;
     boolean releaseConnection(Connection connection);
     String getUrl();
     String getUser();
     String getPassword();

}

