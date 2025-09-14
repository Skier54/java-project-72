package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public final class Url {
    private Long id;

    @ToString.Include
    private String name;

    private LocalDateTime createdAt;
    private List<UrlCheck> urlCheck;

    public Url(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
}
