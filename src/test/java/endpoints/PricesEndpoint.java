package endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import paths.PricesPaths;

public class PricesEndpoint {

    private final RequestSpecification request;
    private final PricesPaths paths = new PricesPaths();

    public PricesEndpoint(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    public Response optionsPrices() {
        String path = paths.prices();

        Response response = request
                .basePath(path)
                .when()
                .options();

        return response;
    }

    public Response getPricesWithFilters(String dno, String voltage, String startDate, String endDate) {
        String path = paths.prices();

        Response response = request
                .basePath(path)
                .param("dno", dno)
                .param("voltage", voltage)
                .param("start", startDate)
                .param("end", endDate)
                .when()
                .get();

        return response;
    }
}
