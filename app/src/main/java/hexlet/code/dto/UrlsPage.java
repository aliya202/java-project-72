package hexlet.code.dto;

import hexlet.code.model.Url;
import lombok.Getter;

import java.util.List;

@Getter
public class UrlsPage extends BasePage {

    private final List<Url> urls;

    public UrlsPage(List<Url> urls, String flash, String flashType) {
        super();
        this.urls = urls;
        super.setFlash(flash);
        super.setFlashType(flashType);
    }
}
