package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlCheck {

    private Long id;
    private LocalDateTime createdAt;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private Long urlId;
}
