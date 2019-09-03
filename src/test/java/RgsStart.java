import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import static junit.framework.TestCase.assertEquals;

public class RgsStart {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    public RgsStart(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//ol[contains(@class,'rgs-menu')]/li/*[contains(text(),'Страхование')]")
    private WebElement insuranceButton;

    @FindBy(xpath = "//*[contains(text(),'ДМС')]")
    private WebElement dmsButton;

    public void open(String address) {
        driver.get(address);
    }

    public void getDmsPage() {
        insuranceButton.click();
        dmsButton.click();
    }

    public void checkLinkDms() {
        assertEquals(driver.getTitle(), "ДМС 2019 | Рассчитать стоимость добровольного медицинского страхования и оформить ДМС в Росгосстрах");
    }

}
