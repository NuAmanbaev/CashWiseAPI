import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberReport.html", "json:target/testReport.json"},
        features = "C:\\Users\\j_dan\\OneDrive\\Desktop\\CashWiseAPI\\src\\test\\resources\\features\\CreateInvalidSeller.feature",
        glue = "api",
        tags = "",
        dryRun = false
)


public class CucumberRunner {
}
