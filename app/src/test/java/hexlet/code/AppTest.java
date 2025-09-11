package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.ParserUrls;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;



import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


class AppTest {
    private static Javalin app;
    public static MockWebServer mockWebServer;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        app = App.getApp();
    }

    @BeforeAll
    static void serverStart() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void serverOff() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testRootPage() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://uchi.ru";
            var response = client.post("/urls", requestBody);

            assertThat(response.code()).isIn(200, 302);

            var maybeUrl = UrlRepository.findByName("https://uchi.ru");
            assertThat(maybeUrl).isPresent();

            var saved = maybeUrl.get();
            assertThat(saved.getName()).isEqualTo("https://uchi.ru");
            assertThat(saved.getId()).isGreaterThan(0);
            assertThat(saved.getCreatedAt()).isNotNull();
        });
    }

    @Test
    public void testFindByName() throws SQLException, MalformedURLException {
        var url = new Url("https://uchi.ru");
        UrlRepository.save(url);

        var entity = UrlRepository.findByName("https://uchi.ru");
        assertThat(entity).isPresent();

        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(entity.get().getId()));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://uchi.ru");
        });
    }

    @Test
    void testUrlNotFound() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testEntities() throws SQLException, MalformedURLException {
        var url1 = new Url(ParserUrls.parseUrl("https://example.com"));
        var url2 = new Url(ParserUrls.parseUrl("https://uchi.ru"));
        UrlRepository.save(url1);
        UrlRepository.save(url2);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://example.com")
                    .contains("https://uchi.ru");
        });
    }

    @Test
    public void testValidHttpUrl() throws Exception {
        String result = ParserUrls.parseUrl("http://example.com");
        Assertions.assertEquals("http://example.com", result);
    }

    @Test
    public void testValidHttpsUrl() throws Exception {
        String result = ParserUrls.parseUrl("https://example.com:8080");
        Assertions.assertEquals("https://example.com:8080", result);
    }

    @Test
    public void testInvalidProtocol() {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("ftp://example.com");
        });
    }

    @Test
    public void testInvalidFormat() {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("example.com");
        });
    }

    @Test
    public void testInvalidUri() {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("invalid://url");
        });
    }

    @Test
    public void testEmptyString() {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("");
        });
    }
}
