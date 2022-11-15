package steps;

import cucumber.ScenarioContext;
import cucumber.TestContext;
import endpoints.PricesEndpoint;

public class BaseStep {
    private final PricesEndpoint pricesEndpoint;
    private final ScenarioContext scenarioContext;

    public BaseStep(TestContext testContext) {
        pricesEndpoint = testContext.getEndPoints();
        scenarioContext = testContext.getScenarioContext();
    }

    public PricesEndpoint getEndPoints() {
        return pricesEndpoint;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}