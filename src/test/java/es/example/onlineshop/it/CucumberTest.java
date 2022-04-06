package es.example.onlineshop.it;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"es.example.prices.it", "es.example.prices.it.config"},
    plugin = {"pretty"},
    tags = "not @ignore")
public class CucumberTest {

}