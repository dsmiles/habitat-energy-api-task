package steps;

import cucumber.TestContext;
import enums.Context;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Prices;
import org.apache.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PricesSteps extends BaseStep {

    public PricesSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("The Prices API is live")
    public void isApiUp() {
        // In the absence of a health endpoint then performing an
        // HTTP OPTIONS against the endpoint is good enough
        Response response = getEndPoints().optionsPrices();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        getScenarioContext().setContext(Context.PRICES_RESPONSE, response);
    }

    @When("I get prices using the following parameters {string}, {string}, {string}, {string}")
    public void iGetPricesUsingTheFollowingParameters(String dno, String voltage, String startDate, String endDate) {
        Response response = getEndPoints().getPricesWithFilters(dno,voltage, startDate, endDate);
        getScenarioContext().setContext(Context.PRICES_RESPONSE, response);
    }

    @And("There is price data present")
    public void thereIsPricesDataPresent() {
        Response response = (Response) getScenarioContext().getContext(Context.PRICES_RESPONSE);
        Prices pricesData = response.getBody().as(Prices.class);
        assertThat(pricesData.getDataValues()).isNotNull();
        assertThat(pricesData.getDataValues().getDataList()).isNotEmpty();
    }

    @And("There is no price data present")
    public void thereIsNoPricesDataPresent() {
        Response response = (Response) getScenarioContext().getContext(Context.PRICES_RESPONSE);
        Prices pricesData = response.getBody().as(Prices.class);
        assertThat(pricesData.getDataValues()).isNotNull();
        assertThat(pricesData.getDataValues().getDataList()).isEmpty();
    }
}

