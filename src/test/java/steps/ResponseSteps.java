package steps;

import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.text.MessageFormat;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class ResponseSteps extends BaseStep {

    public ResponseSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("I receive {int} status code in response")
    public void iReceiveStatusCodeInResponse(int statusCode) {
        Response response = (Response) getScenarioContext().getContext(Context.PRICES_RESPONSE);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Then("Error message is {string}")
    public void errorMessageIs(String errorMessage) {
        Response response = (Response) getScenarioContext().getContext(Context.PRICES_RESPONSE);
        assertThat(response).isNotNull();
        assertThat(response.jsonPath().getString("errorMessage")).isEqualTo(errorMessage);
    }

    @Then("^Response has correct json schema \"([^\"]*)\"$")
    public void responseHasCorrectJsonSchema(String schemaFilename) throws Throwable {
        Response response = (Response) getScenarioContext().getContext(Context.PRICES_RESPONSE);
        assertThat(response).isNotNull();
        response.then().assertThat().body(matchesJsonSchemaInClasspath(MessageFormat.format("contracts/{0}", schemaFilename)));
    }
}
