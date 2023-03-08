package com.github.Chestaci;

import com.github.Chestaci.utils.ConfProperties;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class MyTest {
    public static WebDriver driver;

    /**
     * Осуществление общей подготовительной настройки
     */
    @BeforeAll
    @Step("Подготовительные действия перед началом тестов. Инициализация WebDriver и начальная настройка.")
    public static void setupAll() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //создание экземпляра драйвера
        driver = new ChromeDriver(options);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Закрытие окна браузера
     */
    @AfterAll
    @Step("Окончательные завершающие действия после завершения тестов. Закрытие браузера.")
    public static void tearDownAll() {
        driver.quit();
    }
}
