package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.ParserUrls;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import io.micrometer.core.instrument.util.IOUtils;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class AppTest {
    private static Javalin app;
    public static MockWebServer mockWebServer;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        app = App.getApp();
        UrlRepository.clear();
        UrlCheckRepository.clear();
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

            var responseGet = client.get("/urls");
            assertThat(responseGet.code()).isEqualTo(200);
            assertThat(responseGet.body().string()).contains("https://uchi.ru");
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
    public void testHostWithoutDot() {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("http://localhost");
        });

        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("http://test");
        });

        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("http://123");
        });
    }

    @Test
    public void testNullOrEmptyHost() {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("http://");
        });

        Assertions.assertThrows(MalformedURLException.class, () -> {
            ParserUrls.parseUrl("http://null");
        });
    }

    @Test
    public void testCheckUrl() throws SQLException {
        var url = new Url("https://example.com");
        UrlRepository.save(url);

        var urlCheck = new UrlCheck(200, "Test Title", "Test H1", "Test Description", url.getId());
        UrlCheckRepository.saveCheck(urlCheck);

        var savedCheck = UrlCheckRepository.findCheck(url.getId()).get(0);
        assertThat(savedCheck.getStatusCode()).isEqualTo(200);
        assertThat(savedCheck.getTitle()).isEqualTo("Test Title");
        assertThat(savedCheck.getH1()).isEqualTo("Test H1");
        assertThat(savedCheck.getDescription()).isEqualTo("Test Description");
        assertThat(savedCheck.getUrlId()).isEqualTo(url.getId());
    }

    @Test
    void testFindLastCheckSingleUrl() throws SQLException, InterruptedException {
        var url = new Url("https://single.com");
        UrlRepository.save(url);

        var check1 = new UrlCheck(200, "Title-1", "H1-1", "Desc-1", url.getId());
        var check2 = new UrlCheck(404, "Title-2", "H1-2", "Desc-2", url.getId());

        UrlCheckRepository.saveCheck(check1);
        Thread.sleep(10);
        UrlCheckRepository.saveCheck(check2);

        var lastChecks = UrlCheckRepository.findLastCheck();

        assertThat(lastChecks.size()).isEqualTo(1);
        var lastCheck = lastChecks.get(url.getId());
        assertThat(lastCheck.getStatusCode()).isEqualTo(404);
        assertThat(lastCheck.getTitle()).isEqualTo("Title-2");
        assertThat(lastCheck.getH1()).isEqualTo("H1-2");
        assertThat(lastCheck.getDescription()).isEqualTo("Desc-2");
    }

    @Test
    public void testChecks() throws SQLException {
        var baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        var stream = this.getClass().getClassLoader().getResourceAsStream("testHtml.html");
        var htmlBody = IOUtils.toString(stream);

        var mockResponse = new MockResponse()
                .setHeader("Content-Type", "text/html; charset=utf-8")
                .setBody(htmlBody);
        mockWebServer.enqueue(mockResponse);

        var website = new Url(baseUrl);
        UrlRepository.save(website);

        var savedWebsite = UrlRepository.findByName(baseUrl);
        assertThat(savedWebsite).isPresent();
        var websiteId = savedWebsite.get().getId();

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urlChecksPath(websiteId));
            assertThat(response.code()).isIn(200, 302);

            var checks = UrlCheckRepository.findCheck(websiteId);
            assertThat(checks).isNotEmpty();

            var check = checks.get(0);
            assertThat(check.getStatusCode()).isEqualTo(200);
            assertThat(check.getTitle()).isEqualTo("Привет");
            assertThat(check.getH1()).isEqualTo("Пока");
            assertThat(check.getDescription()).isEqualTo("description");
            assertThat(check.getUrlId()).isEqualTo(websiteId);
            assertThat(check.getCreatedAt()).isNotNull();
        });
    }

    @Test
    public void testCreateWithEmptyUrl() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls", Map.of("url", ""));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Поле не должно быть пустым");
        });
    }
}

