package test.task.nc1.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import test.task.nc1.backend.model.News;

public interface NewsService {
    List<News> getAll(LocalDateTime start, LocalDateTime end);

    News save(News news);

    void deleteById(Long id);

    void deleteAll();

    News updateById(Long id, News news);
}
