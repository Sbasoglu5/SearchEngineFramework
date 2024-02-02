package StepDefinitions;

import Pages.SearchEnginePage;

public class PageInitializer {
    public static SearchEnginePage searchEnginePage;
    public static void initializePageObjects() {
        searchEnginePage=new SearchEnginePage();
    }
}
