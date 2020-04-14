package interview.skilling;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@RequiredArgsConstructor
class Exercise2Support {
    private final WebDriver driver = DriverForBrowser.valueOf(System.getProperty("browser.to.test").toUpperCase()).getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, 10);

    void loadStartPage() {
        driver.get("http://computer-database.gatling.io/computers");
    }

    WebDriver getDriver() {
        return driver;
    }

    void waitForPage(String title) {
        wait.until(driver -> driver.findElement(By.cssSelector("#main h1")).getText().equalsIgnoreCase(title));
    }

    void waitForMessageContains(String messagePart) {
        WebElement successMessage = wait.until(presenceOfElementLocated(By.className("alert-message")));
        assertThat(successMessage.getText()).startsWith("Done!").contains(messagePart);
    }

    void saveNewComputer() {
        driver.findElement(By.cssSelector("input[type=submit].btn.primary")).click();
    }

    void fillNewComputerForm() {
        fillNewComputerForm(UUID.randomUUID().toString());
    }

    void fillNewComputerForm(String uuid) {
        driver.findElement(By.cssSelector("div.clearfix div.input input[name=name]")).sendKeys("some computer " + uuid);
        driver.findElement(By.cssSelector("div.clearfix div.input input[name=introduced]")).sendKeys("2020-04-15");
        driver.findElement(By.cssSelector("div.clearfix div.input input[name=discontinued]")).sendKeys("2020-08-20");
        new Select(driver.findElement(By.cssSelector("div.clearfix div.input select[name=company]"))).selectByVisibleText("RCA");
    }

    void clearComputerFormField(String name) {
        driver.findElement(By.cssSelector("div.clearfix div.input input[name=" + name + "]")).clear();
    }

    void setComputerFormFieldText(String name, String text) {
        driver.findElement(By.cssSelector("div.clearfix div.input input[name=" + name + "]")).sendKeys(text);
    }
    void assertNewComputerSearchResult(String uuid) {
        List<WebElement> rows = driver.findElements(By.cssSelector(".computers tbody tr"));
        assertThat(rows).hasSize(1);

        List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));

        assertThat(columns.get(0).getText()).isEqualTo("some computer " + uuid);
        assertThat(columns.get(1).getText()).isEqualTo("15 Apr 2020");
        assertThat(columns.get(2).getText()).isEqualTo("20 Aug 2020");
        assertThat(columns.get(3).getText()).isEqualTo("RCA");
    }

    void assertFieldError(String name, String message) {
        driver.findElement(By.cssSelector("div.clearfix.error div.input input[name=" + name + "]"));
        WebElement messageElement = driver.findElement(By.cssSelector("div.clearfix.error div.input span.help-inline"));

        assertThat(messageElement.getText()).isEqualTo(message);
    }

    void navigateToAddComputer() {
        driver.findElement(By.id("add")).click();
    }

    void navigateToUpdateFoundComputer(String uuid) {
        driver.findElement(By.cssSelector(".computers tbody a")).click();
    }

    void deleteComputer() {
        driver.findElement(By.cssSelector("input[type=submit].btn.danger")).click();
    }

    void searchFor(String term) {
        driver.findElement(By.id("searchbox")).sendKeys(term);
        driver.findElement(By.id("searchsubmit")).click();
    }

    void quit() {
        driver.quit();
    }
}
