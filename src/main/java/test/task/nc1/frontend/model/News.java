package test.task.nc1.frontend.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
    private String title;
    private String description;
    private LocalDateTime publicationTime;
}
