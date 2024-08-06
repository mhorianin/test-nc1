package test.task.nc1.backend.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import test.task.nc1.backend.model.News;

@Component
@RequiredArgsConstructor
public class NewsClient {
    private static final String URL = "https://tsn.ua/news";
    private final NewsService newsService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private final Set<String> titles = new HashSet<>();

    public void getNews() {
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements newsElements = doc.select("article.c-card");

            for (Element element : newsElements) {
                String title = element.select(".c-card__title a.c-card__link").text();
                String link = element.select(".c-card__title a.c-card__link").attr("href");
                String publicationTimeStr = element.select("time").attr("datetime");
                LocalDateTime publicationTime = LocalDateTime.parse(publicationTimeStr, formatter);
                String description = getNewsDescription(link);

                if (titles.contains(title)
                        && publicationTime.toLocalDate().equals(LocalDate.now())) {
                    continue;
                }

                News news = new News();
                news.setTitle(title);
                news.setDescription(description);
                news.setPublicationTime(publicationTime);

                System.out.println(news);

                titles.add(title);
                newsService.save(news);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't send http request", e);
        }
    }

    private String getNewsDescription(String link) {
        try {
            Document doc = Jsoup.connect(link).get();
            Element descriptionElement = doc.select("div.c-article__body").first();
            if (descriptionElement == null) {
                return "No description";
            }
            return descriptionElement.text();

        } catch (IOException e) {
            throw new RuntimeException("Can't send http request", e);
        }
    }
}
