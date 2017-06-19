package controller;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConDAO {
  protected final Connection getConnection() { // regelt een connection met de database
    Connection result = null;

    try {
      InitialContext ic = new InitialContext();
      DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");//database driver
      
      result = ds.getConnection();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    return result;
  }
}
