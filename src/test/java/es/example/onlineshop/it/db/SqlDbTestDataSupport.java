package es.example.onlineshop.it.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;

public class SqlDbTestDataSupport {

  protected void importSqlResource(DataSource dataSource, final String sqlResource) throws SQLException {
    final InputStream is = getClass().getResourceAsStream(sqlResource + ".sql");
    Assertions.assertNotNull(is, "SQL Resource " + sqlResource + " not found!");
    try (final Connection connection = dataSource.getConnection()) {
      importSQL(connection, is);
      if (!connection.getAutoCommit()) {
        connection.commit();
      }
    }
  }

  private void importSQL(Connection conn, InputStream in) throws SQLException {
    Scanner s = new Scanner(in);
    s.useDelimiter("(;(\r)?\n)|(--\n)");
    Statement st = null;
    try {
      st = conn.createStatement();
      while (s.hasNext()) {
        String line = s.next();
        if (line.startsWith("/*!") && line.endsWith("*/")) {
          int i = line.indexOf(' ');
          line = line.substring(i + 1, line.length() - " */".length());
        }

        if (line.trim().length() > 0) {
          st.execute(line);
        }
      }
    } finally {
      if (st != null) {
        st.close();
      }
    }
  }
}
