import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class RgsRequest {

    private WebDriver driver;
    private Wait<WebDriver> wait;
    private static boolean evenOneIsUnchecked;

    public RgsRequest(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
    }

    @FindBy(xpath = "//*[contains(@class,'btn')][contains(text(),'Отправить заявку')]")
    private WebElement sendRequest;

    @FindBy(xpath = "//h4[@class='modal-title']")
    private WebElement titleElement;

    @FindBy(name = "LastName")
    private WebElement lastNameElement;

    @FindBy(name = "MiddleName")
    private WebElement middleNameElement;

    @FindBy(name = "FirstName")
    private WebElement firstNameElement;

    @FindBy(name = "Region")
    private WebElement regionElement;

    @FindBy(name = "Email")
    private WebElement emailElement;

    @FindBy(xpath = "//*[text()='Эл. почта']/..//span[@class='validation-error-text']")
    private WebElement emailErrorMassageElement;

    @FindBy(name = "Comment")
    private WebElement commentElement;

    @FindBy(xpath = "//*[contains(@class,'form-control')][contains(@data-bind, 'Phone')]")
    private WebElement phoneElement;

    @FindBy(name = "ContactDate")
    private WebElement dateElement;

    @FindBy(xpath = "//input[@class='checkbox']")
    private WebElement checkBoxElement;

    @FindBy(xpath = "//button[@id='button-m']")
    private WebElement buttonM_Element;

    public void open(String address) {
        driver.get(address);
    }

    public void verificationLinkDms() {
        assertEquals(driver.getTitle(), "ДМС 2019 | Рассчитать стоимость добровольного медицинского страхования и оформить ДМС в Росгосстрах");
    }

    public void openSendRequestForm() {
        wait.until(ExpectedConditions.visibilityOf(sendRequest));
        sendRequest.click();
    }

    public void verificationTitle() {
        wait.until(ExpectedConditions.visibilityOf(titleElement));
    }

    public void fillLastName(String lastName) {
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    public void fillFirstName(String firstName) {
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void fillMiddleName(String middleName) {
        middleNameElement.clear();
        middleNameElement.sendKeys(middleName);
    }

    public void fillRegion(String region) {
        new Select(regionElement).selectByVisibleText(region);
    }

    public void fillEmail(String email) {
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void fillPhone(String phone) {
        do {
            phoneElement.clear();
            phoneElement.sendKeys(phone);
        } while (!(phoneElement.getAttribute("value")).equals("+7 (800) 555-35-35"));
    }

    public void fillComment(String comment) {
        commentElement.clear();
        commentElement.sendKeys(comment);
    }

    public void fillDate(String date) {
        do {
            dateElement.clear();
            dateElement.sendKeys(date);
        } while (!(dateElement.getAttribute("value")).equals(date));
    }

    public void checkBoxClicker() {
        checkBoxElement.click();
    }

    public void buttonM_Clicker() {
        buttonM_Element.click();

    }

    public void checkLastName(String lastName) {
        assertEquals(lastNameElement.getAttribute("value"), lastName);
    }

    public void checkMiddleName(String middleName) {
        assertEquals(middleNameElement.getAttribute("value"), middleName);
    }

    public void checkFirstName(String firstName) {
        assertEquals(firstNameElement.getAttribute("value"), firstName);
    }

    public void checkEmail(String email) {
        assertEquals(emailElement.getAttribute("value"), email);
    }

    public void checkComment(String comment) {
        assertEquals(commentElement.getAttribute("value"), comment);
    }

    public void checkRegion(String region) {
        assertEquals(new Select(regionElement).getAllSelectedOptions().get(0).getText(), region);
    }

    public void checkPhone(String phone) {
        assertEquals(phoneElement.getAttribute("value"), phone);
    }


    public void checkDate(String date) {
        assertEquals(dateElement.getAttribute("value"), date);
    }

    public void checkErrorEmail(String errorMessage) {

        assertEquals("Введите адрес электронной почты",
                driver.findElement(By.xpath("//span[@class='validation-error-text']")).getText());
        System.out.println(emailErrorMassageElement.getText().equals(errorMessage));
    }

    public void checkCheckBox(){
        if (!checkBoxElement.isSelected()){
            checkBoxElement.click();
        }
        Assert.assertEquals(checkBoxElement.isSelected(),true);
    }


}
