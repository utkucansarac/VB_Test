import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WebTestScenario {

    private static int xOffset;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Web sitesini aç
            driver.get("https://www.vakifbank.com.tr/");

            // Taba tıkla
            WebElement tabElement = driver.findElement(By.id("ctl00_ctl06_ctl00_rptMenu_ctl00_hypLink"));
            tabElement.click();

            // Xpathe tıkla
            WebElement xpathElement = driver.findElement(By.xpath("//*[@id=\"Form1\"]/div[6]/div/div/div/div[2]/div/div/div/div[1]/div/a/h2"));
            xpathElement.click();

            // Yeni sayfada belirli bir XPath'i kontrol etmek
            List<WebElement> xpathElements = driver.findElements(By.xpath("//*[@id=\"ctl00_ctl10_ctl00_txtVade\"]"));

            if (!xpathElements.isEmpty()) {
                // Belirtilen XPath ile eşleşen element bulunuyor, devam edebilirsiniz.
                // İşlem yapın
                // ComboBox'ı aç
                WebElement comboBoxElement = driver.findElement(By.id("select2-ctl00_ctl10_ctl00_ddlKredi-container"));

                if (comboBoxElement.isEnabled()) {
                    comboBoxElement.click();
                    WebElement optionElement = driver.findElement(By.xpath("//li[@class='select2-results__option' and text()='TAKSİTLİ EK HESAP']"));
                    optionElement.click();
                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));

                    //Vade Giris
                    WebElement sliderElement = driver.findElement(By.className("ui-slider-handle"));
                    Actions action = new Actions(driver);
                    action.dragAndDropBy(sliderElement, xOffset, 80).build().perform();
                    WebElement inputElement = driver.findElement(By.id("ctl00_ctl10_ctl00_txtVade"));
                    inputElement.clear();
                    inputElement.sendKeys("10");



                    WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement buttonElement = driver.findElement(By.id("ctl00_ctl10_ctl00_btnHesapla"));
                    WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    buttonElement.click();
                    WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
                } else {
                    System.out.println("ComboBox etkin değil.");
                }

            } else {
                System.out.println("Element not found");
            }
        } catch (Exception e) {
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            e.printStackTrace();
        } finally {
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.quit();
        }
    }
}
