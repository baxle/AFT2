import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParametrizedTest {
    private static WebDriver chromeDriver;
    private static String baseUrl = "https://www.rgs.ru/products/private_person/health/dms/generalinfo/index.wbp";
    private static RgsRequest rgsRequest;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иванов", "Иван", "Иванович"},
                {"Петров", "Петр", "Петрович"},
                {"Сергеев", "Сергей", "Сергеевич"}
        });
    }

    @Parameterized.Parameter
    public String lastName;

    @Parameterized.Parameter(1)
    public String firstName;

    @Parameterized.Parameter(2)
    public String middleName;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "drv/newgeckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

      rgsRequest = PageFactory.initElements(chromeDriver, RgsRequest.class);

        rgsRequest.open(baseUrl);

        rgsRequest.verificationLinkDms();
        rgsRequest.openSendRequestForm();
        rgsRequest.verificationTitle();
    }



    @Test(timeout=50000)
    public void chromeTesting() throws Exception {



        rgsRequest.fillLastName(""+lastName);
        rgsRequest.fillFirstName(""+firstName);
        rgsRequest.fillMiddleName(""+middleName);

        rgsRequest.checkLastName(""+lastName);
        rgsRequest.checkFirstName(""+firstName);
        rgsRequest.checkMiddleName(""+middleName);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        chromeDriver.quit();
    }
}