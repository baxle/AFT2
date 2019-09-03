import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RgsRequestTest {

    private WebDriver driver;
    private String baseUrl = "https://www.rgs.ru";
    private Wait<WebDriver> wait;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 50);
        driver.manage().window().maximize();
    }

    @Test
    public void testing() throws Exception {

        RgsStart rgsStart = PageFactory.initElements(driver, RgsStart.class);

        rgsStart.open(baseUrl);

        rgsStart.getDmsPage();

        RgsRequest rgsRequest = PageFactory.initElements(driver, RgsRequest.class);

        rgsRequest.verificationLinkDms();

        rgsRequest.openSendRequestForm();

        rgsRequest.verificationTitle();
        rgsRequest.fillLastName("Путин");
        rgsRequest.fillFirstName("Владимир");
        rgsRequest.fillMiddleName("Владимирович");
        rgsRequest.fillRegion("Москва");
        rgsRequest.fillEmail("qwertyqwerty");
        rgsRequest.fillPhone("8005553535");
        rgsRequest.fillComment("Без комментариев.");
        rgsRequest.fillDate("12.12.2019");

        rgsRequest.checkLastName("Путин");
        rgsRequest.checkFirstName("Владимир");
        rgsRequest.checkMiddleName("Владимирович");
        rgsRequest.checkRegion("Москва");
        rgsRequest.checkEmail("qwertyqwerty");
        rgsRequest.checkErrorEmail("Введите адрес электронной почты");
        rgsRequest.checkPhone("+7 (800) 555-35-35");
        rgsRequest.checkComment("Без комментариев.");
        rgsRequest.checkDate("12.12.2019");
        rgsRequest.checkBoxClicker();
        rgsRequest.buttonM_Clicker();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}