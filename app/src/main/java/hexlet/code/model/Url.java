package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private UrlCheck lastCheck;
}
