package cucumber;

import endpoints.PricesEndpoint;
import dataproviders.ConfigFileReader;
import enums.Context;

public class TestContext {

    private final PricesEndpoint pricesEndpoint;
    private final ScenarioContext scenarioContext;

    public TestContext() {
        pricesEndpoint = new PricesEndpoint(ConfigFileReader.getInstance().getBaseUrl());
        scenarioContext = new ScenarioContext();
        scenarioContext.setContext(Context.USER_TOKEN, ConfigFileReader.getInstance().getUserToken());
    }

    public PricesEndpoint getEndPoints() {
        return pricesEndpoint;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
