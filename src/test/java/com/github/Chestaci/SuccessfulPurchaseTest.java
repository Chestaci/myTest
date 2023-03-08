package com.github.Chestaci;

import com.github.Chestaci.pages.*;
import com.github.Chestaci.utils.ConfProperties;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import io.qameta.allure.Description;

@DisplayName("Тесты операций с покупками")
@Feature("Тесты проверки покупок")
public class SuccessfulPurchaseTest extends MyTest {

    private ProductsPage productsPage;
    private CompletePage completePage;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeEach
    @Step("Последняя настройка перед началом тестов." +
            " Создание начальной страницы, ее открытие и авторизация в ней." +
            " Создание страницы Products для теста")
    public void setup() {
        MainPage mainPage = new MainPage(driver);

        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("main_page"));

        //Инициализация ProductPage
        productsPage = mainPage.signIn(
                ConfProperties.getProperty("username"),
                ConfProperties.getProperty("password")
        );
    }

    /**
     * тестовый метод для осуществления проверки успешной покупки
     */
    @Test
    @DisplayName("Проверка успешной покупки")
    @Description("Тест для осуществления проверки успешной покупки")
    @Story("Тест успешной покупки")
    public void successfulPurchaseTest() {
        //добавление первого продукта в корзину
        productsPage.addItemToCart(1);
        //получение конечной страницы
        completePage = productsPage
                .clickCartButton()
                .clickCheckoutButton()
                .checkout(
                        ConfProperties.getProperty("first_name"),
                        ConfProperties.getProperty("last_name"),
                        ConfProperties.getProperty("zip_code")
                )
                .clickFinishButton();

        String completePageUrl = driver.getCurrentUrl();

        Assertions.assertEquals(ConfProperties.getProperty("redirect"), completePageUrl);

        String resultMessage = completePage.getCompleteField();

        Assertions.assertTrue(resultMessage.toUpperCase().contains(ConfProperties.getProperty("message").toUpperCase()));
    }

    /**
     * осуществление выхода из аккаунта
     */
    @AfterEach
    @Step("Завершающие действия после выполнения тестов. Logout.")
    public void tearDown() {
        completePage.clickMenuButton();
        completePage.clickLogoutButton();
    }
}
