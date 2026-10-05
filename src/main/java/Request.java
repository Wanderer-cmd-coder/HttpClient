import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class Request {
    private final String method;
    private final String path;
    private final List<NameValuePair> queryParams;

    public Request(String method, String target) {
        this.method = method;
        final var uri = URI.create(target);   // "/index.html?last=10"
        this.path = uri.getPath();            // "/index.html"
        this.queryParams = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    // все параметры: ключ может повторяться, поэтому список пар, а не Map
    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    // значение одного параметра по имени
    public Optional<String> getQueryParam(String name) {
        return queryParams.stream()
                .filter(p -> p.getName().equals(name))
                .map(NameValuePair::getValue)
                .findFirst();
    }
}
