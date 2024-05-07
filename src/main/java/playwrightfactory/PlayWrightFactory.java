package playwrightfactory;
import com.microsoft.playwright.*;
public class PlayWrightFactory implements PlayWrightFactoryInterface {
    Browser browser;
    public Page initialization(String browserName) {
        Playwright playwright = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            case "edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("edge").setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                System.out.println("Enter the proper browser name : ");
                break;
        }
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("url");
        return page;
    }
}
