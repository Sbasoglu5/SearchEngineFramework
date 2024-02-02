package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import Utils.Log;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SearchEngine extends CommonMethods {
    @When("user submits the search term {string}")
    public void user_submits_the_search_term(String text) {
        sendText(searchEnginePage.searchBox, text);
        submit(searchEnginePage.searchBox);
    }
    @When("user clicks on the yahoo link")
    public void user_clicks_on_the_yahoo_link() {
        doClick(searchEnginePage.yahooPage);
    }
    @Then("user submits the search term facebook")
    public void user_submits_the_search_term_facebook() {
        sendText(searchEnginePage.yahooSearchBox, ConfigReader.getPropertyValue("Search"));
        submit(searchEnginePage.yahooSearchBox);
    }


    @Then("the user should see search results")
    public void the_user_should_see_search_results() {
        takeScreenshot("search_result");
    }
    @Then("the first result should contain the expected information")
    public void the_first_result_should_contain_the_expected_information() {
        String firstResultText=(searchEnginePage.firstPage).getText();
        Assert.assertTrue("First result should contain the expected information",
                firstResultText.contains("Facebook - log in or sign up"));
    }
    @Then("the first result on yahoo should contain the expected information")
    public void the_first_result_on_yahoo_should_contain_the_expected_information() {
        Log.warning("this step might throw an noSuchElement exception due to loading wait." +
                "If it constantly fails, rerun and place it in try catch block");
        String firstResultText=(searchEnginePage.yahooFirstPage).getText();
        Assert.assertTrue("First result should contain the expected information",
                firstResultText.contains("Facebook - log in or sign up"));
    }

    @Then("the first result should verify {string} the results")
    public void the_first_result_should_verify_the_results(String verification) {
        Log.info("I am executing the monkey test assertions here");
        if (verification.equals("Facebook - log in or sign up")){
            Assert.assertTrue(searchEnginePage.firstPage.getText().contains("Facebook - log in or sign up"));
        }else{
            Assert.assertFalse(searchEnginePage.instagram.getText().contains("Facebook - log in or sign up"));
        }

    }

}
