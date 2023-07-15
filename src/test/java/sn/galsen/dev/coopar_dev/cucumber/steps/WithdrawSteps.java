package sn.galsen.dev.coopar_dev.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import sn.galsen.dev.coopar_dev.cucumber.config.AbstractSteps;
import sn.galsen.dev.coopar_dev.cucumber.init.DataInitializer;
import sn.galsen.dev.coopar_dev.model.AccountDTO;

public class WithdrawSteps extends AbstractSteps {

    private Response response;

    private AccountDTO accountDTO;
    private final DataInitializer dataInitializer;

    public WithdrawSteps(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    @Before
    public void beforeScenario() {
        dataInitializer.initializeDataList();
    }

    @After
    public void afterScenario() {
        dataInitializer.cleanData();
    }

    @Given("I have {double} in my account")
    public void iHaveBalanceInMyAccount(double balance) {
        // verify if balance is equal to the balance of the account
        Assertions.assertEquals(balance, accountDTO.getBalance());
    }

    @When("I choose to withdraw the fixed amount of {double}")
    public void iChooseToWithdrawTheFixedAmountOfWithdrawal( double withdrawal) {
        // update accountDTO send a POST request to /accounts/{account}
        double sold = accountDTO.getBalance() - withdrawal;
        accountDTO.setBalance(sold);
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(accountDTO)
                .put(baseUrl() + "/api/accounts/" + accountDTO.getId());
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Then("I should receive {double} cash")
    public void iShouldReceiveReceivedCash( double received) {
        // verify if received is equal to the withdrawal
        // Assertions.assertEquals(received, accountDTO.getBalance());
    }

    @And("the balance of my account should be {double}")
    public void theBalanceOfMyAccountShouldBeRemaining(double remaining) {
        // verify if remaining is equal to the balance of the account
        Assertions.assertEquals(remaining, accountDTO.getBalance());
    }

    @Given("I choose to withdraw cash from an account by phone number {string}")
    public void iChooseToWithdrawCashFromAnAccountByPhoneNumberPhone(String phone) {
        response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .get(baseUrl() + "/api/accounts/search/" + phone);
        accountDTO = response.getBody().as(AccountDTO.class);
        Assertions.assertNotNull(accountDTO);
        Assertions.assertEquals(phone, accountDTO.getPhone());
    }
}
