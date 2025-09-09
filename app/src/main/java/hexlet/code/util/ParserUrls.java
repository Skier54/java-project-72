package hexlet.code.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class ParserUrls {
    public static String parseUrl(String url) throws MalformedURLException {
        try {
            URI uri = new URI(url);
            URL result = uri.toURL();

            if (!isValidUrl(result)) {
                throw new MalformedURLException("Некорректный URL");
            }

            StringBuilder domain = new StringBuilder();
            domain.append(result.getProtocol())
                    .append("://")
                    .append(result.getHost());

            if (result.getPort() != -1) {
                domain.append(':').append(result.getPort());
            }

            return domain.toString();
        } catch (URISyntaxException e) {
            throw new MalformedURLException("Некорректный URL");
        }
    }

    private static boolean isValidUrl(URL url) {
        if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
            return false;
        }

        if (url.getHost() == null || url.getHost().isEmpty()) {
            return false;
        }

        if (!url.getHost().contains(".")) {
            return false;
        }

        return true;
    }
}
