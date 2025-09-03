package hexlet.code;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
            assertThat(client.get("/").code()).isEqualTo(200);
        });
    }

}
