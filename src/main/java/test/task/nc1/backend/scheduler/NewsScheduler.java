package test.task.nc1.backend.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import test.task.nc1.backend.service.NewsClient;
import test.task.nc1.backend.service.NewsService;

@Component
@RequiredArgsConstructor
public class NewsScheduler {
    private final NewsClient newsClient;
    private final NewsService newsService;

    @Scheduled(cron = "0 0/1 * * * *")
    public void newsGathering() {
        newsClient.getNews();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void cleaningNews() {
        newsService.deleteAll();
    }
}
