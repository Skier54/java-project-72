package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.ParserUrls;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


class AppTest {
    private static Javalin app;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        app = App.getApp();
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
    public void testUrlsPagePost() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls");
            assertThat(response.code()).isEqualTo(200);
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
}
