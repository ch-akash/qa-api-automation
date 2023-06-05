package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyFirstStepDefs {

    @Given("I have a sample code to run")
    public void iHaveASampleCodeToRun() {
        System.out.println("I have a sample code to run");
    }

    @When("I print {string}")
    public void iPrint(String arg0) {
        System.out.println(arg0);
    }

    @Then("I verify {string} is printed")
    public void iVerifyIsPrinted(String arg0) {
        System.out.println(arg0);
    }

    @And("I will check if status code is {int}")
    public void iWillCheckIfStatusCodeIs(int arg0) {
        System.out.println("Status code is " + arg0);
    }
}
