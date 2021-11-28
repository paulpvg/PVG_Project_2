package ru.gb;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import org.slf4j.*;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class  NimbusTest{

    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(NimbusTest.class);

    @BeforeAll
    static void enableDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("incognito");
        chromeoptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeoptions);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://pvg.nimbusweb.me/auth/?f=login&success=/client");
    }

    @Test
    @DisplayName("Авторизация на сайте. Позитивный тест")
    void authorizationPositiveTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIn("ppvg@list.ru", "gfhjkmgf");

        Sidebar sidebar = new Sidebar(driver);

        assertTrue(sidebar.loginOk());

        sidebar.logout();
    }

    @Test
    @DisplayName("Авторизация на сайте. Негативный тест")
    void authorizationNegativeTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIn("pvg@kremlin.ru", "gfhjkmgf");

        Sidebar sidebar = new Sidebar(driver);

        assertFalse(sidebar.loginOk());
    }

    @Test
    @DisplayName("Создание новой записи")
    void newNoteCreatingTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIn("ppvg@list.ru", "gfhjkmgf");

        NotesList notesList = new NotesList(driver);
        notesList.creatingNewNote();

        AdBlock adBlock = new AdBlock(driver);
        adBlock.killAdBlock();

        NoteBody noteBody = new NoteBody(driver)
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

        assertTrue(notesList.creatingNewNoteOk());

        TopPanelActions topPanelActions = new TopPanelActions(driver);
        topPanelActions.deleteNote();

        Sidebar sidebar = new Sidebar(driver);
        sidebar.emptyTrash();

        assertTrue(sidebar.emptyTrashOk());
    }

    @Test
    @DisplayName("Поиск на сайте. Позитивный тест")
    void searchPositiveTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIn("ppvg@list.ru", "gfhjkmgf");

        NotesList notesList = new NotesList(driver);
        notesList.search("питание");

        assertTrue(notesList.searchPositiveOk() && !notesList.searchNegativeOk());
    }

    @Test
    @DisplayName("Поиск на сайте. Негативный тест")
    void searchNegativeTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIn("ppvg@list.ru", "gfhjkmgf");

        NotesList notesList = new NotesList(driver);
        notesList.search("ненаходимоеслово");

        assertTrue(notesList.searchPositiveOk() && notesList.searchNegativeOk());
    }

    @Test
    @DisplayName("Создание новой папки")
    void newFolderCreatingTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIn("ppvg@list.ru", "gfhjkmgf");

        Sidebar sidebar = new Sidebar(driver);
        sidebar.creatingNewFolder();

        assertTrue(sidebar.creatingNewFolderOk());

        sidebar.deletingNewFolder();
        sidebar.emptyTrash();

        assertTrue(sidebar.emptyTrashOk());
    }

    @AfterEach
    void releaseDriver() throws InterruptedException {
        if(driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
