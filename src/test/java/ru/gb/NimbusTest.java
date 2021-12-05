package ru.gb;

import io.qameta.allure.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import ru.gb.Nimbus.*;

public class  NimbusTest extends AbstractTest{

    @Test
    @Epic("Тестирование авторизации")
    @Feature("Позитивное тестирование")
    @Description("Тест прохождения авторизации на сайте Nimbus существующего пользователя")
    @DisplayName("Авторизация на сайте. Позитивный тест")
    void authorizationPositiveTest() {
        new LoginPage(getDriver()).loginIn("ppvg@list.ru", "gfhjkmgf");

        //Убийство рекламы, рушащей тесты
        new AdBlockPage(getDriver()).killAdBlock();

        assertTrue(new SidebarPage(getDriver()).loginOk());
    }

    @Test
    @Epic("Тестирование авторизации")
    @Feature("Негативное тестирование")
    @Story("Несуществующий логин")
    @Description("Тест прохождения авторизации на сайте Nimbus несуществующего пользователя")
    @DisplayName("Авторизация на сайте. Негативный тест - логин")
    void authorizationNegativeLoginTest() {
        new LoginPage(getDriver()).loginIn("pvg@kremlin.ru", "gfhjkmgf");

        assertFalse(new SidebarPage(getDriver()).loginOk());
    }

    @Test
    @Epic("Тестирование авторизации")
    @Feature("Негативное тестирование")
    @Story("Неправильный пароль")
    @Description("Тест прохождения авторизации на сайте Nimbus существующего пользователя с неверным паролем")
    @DisplayName("Авторизация на сайте. Негативный тест - пароль")
    void authorizationNegativePasswordTest() {
        new LoginPage(getDriver()).loginIn("ppvg@list.ru", "password");

        assertFalse(new SidebarPage(getDriver()).loginOk());
    }

    @Test
    @Description("Тест создания новой заметки на сайте Nimbus")
    @DisplayName("Создание новой записи")
    void newNoteCreatingTest() throws InterruptedException {
        new LoginPage(getDriver()).loginIn("ppvg@list.ru", "gfhjkmgf");

        //Убийство рекламы, рушащей тесты
        new AdBlockPage(getDriver()).killAdBlock();

        //Создание новой заметки
        NotesListPage notesListPage = new NotesListPage(getDriver());
        notesListPage.creatingNewNote();

        //Наполнение новой заметки
        NoteBodyPage noteBodyPage = new NoteBodyPage(getDriver())
                .inputHeader("Morbi in nisl auctor")
                .inputFirstText("Lorem ipsum dolor sit amet, consectetur adipisicing elit,sheets containing Lorem" +
                                "Ipsum passages sed do, At vero eos et accusamus et iusto odio digs qui blanditiis\n")
                .inputNextText("Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis" +
                               "voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\n")
                .inputNumberedlist("Fugiat Quo Voluptas\n" +
                                   "FugQui In Ea Voluptate\n" +
                                   "Autem Vel Eum Iure\n" +
                                   "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse.\n" +
                                   "Assumenda Est Cliche Reprehenderit\n\n")
                .inputNextText("Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil" +
                               "molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\n")
                .inputTodo("Aut Perferendis Doloribus\n" +
                           "Repreh Qui In Ea Voluptate\n" +
                           "Maiores Alias Consequatur\n" +
                           "Voluptatibus Maiores Alias\n" +
                           "Dut Aut Reiciendis Maiores\n" +
                           "Doloribus Volupta Maiores\n\n")
                .inputNextText("Donec feugiat tellus sem, laoreet iaculis orci lobortis vel. Sed sed luctus orci," +
                               "at lacinia risus. Ut porttitor ante ac tincidunt elementum. Curabitur ex dolor, condimentum vitae nunc vel, pulvinar semper justo." +
                               "Vestibulum et aliquet magna, maximus dapibus enim. Vestibulum ex lectus, posuere eu viverra at, mattis et enim. Nam efficitur sem" +
                               "et lectus fringilla, at pharetra nulla luctus. Nunc cursus, augue ac ultricies volutpat, neque erat congue justo, ac pretium tellus" +
                               "eros a neque. Integer ipsum sem, consectetur a mollis ac, cursus eu ipsum.");

        assertTrue(notesListPage.creatingNewNoteOk());

        Thread.sleep(2000); // Полюбуемся пару секунд на созданную заметку, пред тем как удалить ее

        //Удаление созданной заметки
        new TopPanelActionsPage(getDriver()).deleteNote();

        //Очистка корзины
        SidebarPage sidebarPage = new SidebarPage(getDriver());
        sidebarPage.emptyTrash();

        assertTrue(sidebarPage.emptyTrashOk());
    }

    @Test
    @Epic("Тестирование поиска")
    @Feature("Позитивное тестирование")
    @Description("Тест поиска на сайте Nimbus среди существующих заметок встречающегося в них слова ")
    @DisplayName("Поиск на сайте. Позитивный тест")
    void searchPositiveTest() {
        new LoginPage(getDriver()).loginIn("ppvg@list.ru", "gfhjkmgf");

        //Убийство рекламы, рушащей тесты
        new AdBlockPage(getDriver()).killAdBlock();

        //Поиск
        NotesListPage notesListPage = new NotesListPage(getDriver());
        notesListPage.search("питание");

        //Проверка на наличие положительного позитивного и отрицательного негативного признаков результата поиска
        assertTrue(notesListPage.searchPositiveOk() && !notesListPage.searchNegativeOk());
    }

    @Test
    @Epic("Тестирование поиска")
    @Feature("Негативное тестирование")
    @Description("Тест поиска на сайте Nimbus среди существующих заметок заведомо невстречающегося в них слова")
    @DisplayName("Поиск на сайте. Негативный тест")
    void searchNegativeTest() {
        new LoginPage(getDriver()).loginIn("ppvg@list.ru", "gfhjkmgf");

        //Убийство рекламы, рушащей тесты
        new AdBlockPage(getDriver()).killAdBlock();

        //Поиск
        NotesListPage notesListPage = new NotesListPage(getDriver());
        notesListPage.search("ненаходимоеслово");

        //Проверка на наличие положительного негативного признаков результата поиска
        assertTrue(notesListPage.searchNegativeOk());
    }

    @Test
    @Description("Тест создания новой папки для заметок на сайте Nimbus")
    @DisplayName("Создание новой папки")
    void newFolderCreatingTest() throws InterruptedException {
        new LoginPage(getDriver()).loginIn("ppvg@list.ru", "gfhjkmgf");

        //Убийство рекламы, рушащей тесты
        new AdBlockPage(getDriver()).killAdBlock();

        //Создание новой папки
        SidebarPage sidebarPage = new SidebarPage(getDriver());
        sidebarPage.creatingNewFolder();

        assertTrue(sidebarPage.creatingNewFolderOk());

        Thread.sleep(2000); // Полюбуемся пару секунд на созданную папку, пред тем как удалить ее

        //Удаление созданной папки и очистка корзины
        sidebarPage.deletingNewFolder();
        sidebarPage.emptyTrash();

        assertTrue(sidebarPage.emptyTrashOk());
    }
}
