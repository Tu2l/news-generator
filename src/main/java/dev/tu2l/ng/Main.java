package dev.tu2l.ng;

import com.google.gson.Gson;
import dev.tu2l.ng.api.ANINewsUrls;
import dev.tu2l.ng.api.models.News;
import dev.tu2l.ng.api.scrapers.ANINews;
import dev.tu2l.ng.api.scrapers.BaseNewsScraper;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BaseNewsScraper newsScraper = new ANINews();
        Gson gson = new Gson();
        List<News> newsList = newsScraper.getNewsList(ANINewsUrls.NATIONAL_POLITICS);
        new Thread(() -> {
            saveToFile("news.json", String.format("%s/", getCurrentDirectory()), gson.toJson(newsList));
        }).start();

        for (News news : newsList) {
            System.out.println(gson.toJson(news));
        }
    }

    static void saveToFile(String fileName, String path, String data) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path + File.separator + fileName))) {
            out.print(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

}