package test.task.nc1.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import test.task.nc1.backend.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByPublicationTimeBetween(LocalDateTime start, LocalDateTime end);
}
