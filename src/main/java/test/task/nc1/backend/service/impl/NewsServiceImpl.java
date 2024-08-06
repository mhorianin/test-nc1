package test.task.nc1.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.nc1.backend.exception.EntityNotFoundException;
import test.task.nc1.backend.model.News;
import test.task.nc1.backend.repository.NewsRepository;
import test.task.nc1.backend.service.NewsService;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository repository;

    @Override
    public List<News> getAll(LocalDateTime start, LocalDateTime end) {
        return repository.findByPublicationTimeBetween(start, end);
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public News updateById(Long id, News updateNews) {
        News news = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id)
        );
        news.setTitle(updateNews.getTitle());
        news.setDescription(updateNews.getDescription());
        news.setPublicationTime(updateNews.getPublicationTime());
        return repository.save(news);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
