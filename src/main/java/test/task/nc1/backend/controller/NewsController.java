package test.task.nc1.backend.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.nc1.backend.model.News;
import test.task.nc1.backend.service.NewsService;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public List<News> getAllNews(@RequestParam String timePeriod) {
        LocalDateTime start;
        LocalDateTime end;
        switch (timePeriod.toLowerCase()) {
            case "morning":
                start = LocalDateTime.of(LocalDate.now(), LocalTime.of(6, 0));
                end = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));
                break;
            case "day":
                start = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));
                end = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
                break;
            case "evening":
                start = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
                end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0));
                break;
            default:
                throw new IllegalArgumentException("Invalid time period: " + timePeriod);
        }
        return newsService.getAll(start, end);
    }

    @PostMapping("/create")
    public News createNews(@RequestBody @Valid News news) {
        return newsService.save(news);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public News updateNews(@PathVariable Long id,
                           @RequestBody @Valid News news) {
        return newsService.updateById(id, news);
    }
}
