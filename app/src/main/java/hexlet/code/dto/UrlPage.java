package hexlet.code.dto;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UrlPage extends BasePage {

    private Url url;
    private List<UrlCheck> checkList;

    public UrlPage(Url url, String flash, String flashType) {
        super();
        this.url = url;
        super.setFlash(flash);
        super.setFlashType(flashType);
    }

    public UrlPage(Url url) {
        super();
        this.url = url;
    }
}
