package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class UrlsPage extends BasePage {
    private List<Url> urls;
    private Map<Long, UrlCheck> lastChecks =  new HashMap<>();

    public UrlsPage(List<Url> urls, Map<Long, UrlCheck> lastChecks) {
        this.urls = urls;
        this.lastChecks = lastChecks;
    }

    public UrlsPage(List<Url> urls) {
        this.urls = urls;
        this.lastChecks = new HashMap<>();
    }
}
