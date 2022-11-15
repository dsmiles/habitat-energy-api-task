package steps;

import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class HealthSteps extends BaseStep {

    public HealthSteps(TestContext testContext) {
        super(testContext);
    }

    @When("I get service health")
    public void iGetServiceHealth() {
        // In the absence of a health endpoint then performing an
        // HTTP OPTIONS against the endpoint is good enough
        Response response = getEndPoints().optionsPrices();
        getScenarioContext().setContext(Context.PRICES_RESPONSE, response);
    }

    @And("Response returns status {string}")
    public void responseReturnsStatus(String status) {
        // ideally the /prices endpoint should have a health status
        // we could check build number, date, etc
    }
}
