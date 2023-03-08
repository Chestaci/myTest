package com.github.Chestaci;

import com.github.Chestaci.pages.MainPage;
import com.github.Chestaci.utils.ConfProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты главной страницы")
@Feature("Тест авторизации")
public class CheckLoginTest extends MyTest {
    private MainPage mainPage;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeEach
    @Step("Последняя настройка перед началом тестов. Создание начальной страницы и ее открытие.")
    public void setup() {
        mainPage = new MainPage(driver);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("main_page"));
    }

    /**
     * тестовый метод для осуществления проверки сообщения об ошибке при
     * попытке ввода логина на несуществующего пользователя
     */
    @Test
    @DisplayName("Проверка ошибки авторизации")
    @Description("Тест для осуществления проверки сообщения об ошибке" +
            " при попытке ввода логина на несуществующего пользователя")
    @Story("Тест аторизации с неверными данными")
    public void checkLoginTest() {
        mainPage.signIn(
                ConfProperties.getProperty("username1"),
                ConfProperties.getProperty("password1")
        );

        String errorField = mainPage.getErrorField();
        Assertions.assertTrue(errorField.toUpperCase().contains(ConfProperties.getProperty("message1").toUpperCase()));
    }
}
