import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"hooks", "stepdefs"},
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"}
)
public class CukeRunner extends AbstractTestNGCucumberTests {
}
