package sn.galsen.dev.coopar_dev.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import sn.galsen.dev.coopar_dev.cucumber.config.AbstractSteps;
import sn.galsen.dev.coopar_dev.model.AccountDTO;


import java.util.List;
import java.util.Map;

public class AccountSteps extends AbstractSteps {

    private Response response;

    private AccountDTO accountDTO;
    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = RestAssured.get(baseUrl() + endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @And("the response body should contain a list of AccountDTO")
    public void theResponseBodyShouldContainAListOfAccountDTO() {
        List<AccountDTO> accountDTOList = response.getBody().jsonPath().getList(".", AccountDTO.class);
        Assertions.assertNotNull(accountDTOList);
        Assertions.assertFalse(accountDTOList.isEmpty());
    }

    @Given("the account service is available")
    public void theAccountServiceIsAvailable() {
        response = RestAssured.get(baseUrl() + "/api/accounts");
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @And("the response body should contain a AccountDTO")
    public void theResponseBodyShouldContainAAccountDTO() {
        accountDTO = response.getBody().as(AccountDTO.class);
        Assertions.assertNotNull(accountDTO);
    }

    @When("I send a POST request to {string} with the following body")
    public void iSendAPOSTRequestToWithTheFollowingBody(String endpoint, Map<String, String> requestBody) {
        response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl() + endpoint);
    }
}
