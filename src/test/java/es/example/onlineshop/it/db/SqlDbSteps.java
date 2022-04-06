package es.example.onlineshop.it.db;

import io.cucumber.java.en.Given;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

public class SqlDbSteps extends SqlDbTestDataSupport {

  @Autowired
  private DataSource dataSource;

  @Given("the database is populated with {}")
  public void executeDbScript(final String dataFileDescription) throws SQLException {
    importSqlResource(dataSource, "/db/" + dataFileDescription);
  }
}
