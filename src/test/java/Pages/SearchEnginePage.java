package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchEnginePage extends CommonMethods {
    public SearchEnginePage (){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "APjFqb")
    public WebElement searchBox;
    @FindBy(xpath = "//h3[text()='Facebook - log in or sign up']")
    public WebElement firstPage;
    @FindBy(xpath = "//h3[ contains(text(),'Yahoo | Mail, Weather,')]")
    public WebElement yahooPage;
    @FindBy(id = "ybar-sbq")
    public WebElement yahooSearchBox;
    @FindBy(xpath = "(//a[text()='Facebook - log in or sign up'])[1]")
    public WebElement yahooFirstPage;

    @FindBy(xpath = "(//h3[text()='Instagram'])[1]")
    public WebElement instagram;


}
