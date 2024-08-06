package test.task.nc1.frontend.controller;

import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.springframework.web.client.RestTemplate;
import test.task.nc1.frontend.model.News;

public class NewsViewController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label publicationTimeLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;
    @FXML
    private RadioButton morning;
    @FXML
    private RadioButton day;
    @FXML
    private RadioButton evening;
    private List<News> newsList;
    private int index = 0;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://localhost:8080/news";

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        morning.setToggleGroup(group);
        day.setToggleGroup(group);
        evening.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadNews();
            }
        });

        loadNews();
    }

    private void loadNews() {
        String timePeriod;

        if (morning.isSelected()) {
            timePeriod = "morning";
        } else if (day.isSelected()) {
            timePeriod = "day";
        } else {
            timePeriod = "evening";
        }

        News[] newsArray = restTemplate
                .getForObject(apiUrl + "?timePeriod=" + timePeriod, News[].class);
        newsList = Arrays.asList(newsArray);

        if (!newsList.isEmpty()) {
            index = 0;
            updateNewsView();
        }
    }

    @FXML
    private void showPreviousNews() {
        if (index > 0) {
            index--;
            updateNewsView();
        }
    }

    @FXML
    private void showNextNews() {
        if (index < newsList.size() - 1) {
            index++;
            updateNewsView();
        }
    }

    private void updateNewsView() {
        News news = newsList.get(index);
        titleLabel.setText(news.getTitle());
        descriptionLabel.setText(news.getDescription());
        publicationTimeLabel.setText(String.valueOf(news.getPublicationTime()));
    }
}
