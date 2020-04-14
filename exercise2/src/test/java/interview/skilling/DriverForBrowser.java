package interview.skilling;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RequiredArgsConstructor
enum DriverForBrowser {
    CHROME(ChromeDriver.class),
    FIREFOX(FirefoxDriver.class);

    private final Class<? extends WebDriver> driverClass;

    @SneakyThrows
    WebDriver getDriver() {
        return driverClass.getConstructor().newInstance();
    }
}
