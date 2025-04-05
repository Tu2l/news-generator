package dev.tu2l.ng.api;

import com.google.gson.Gson;
import dev.tu2l.ng.api.models.News;
import dev.tu2l.ng.api.scrapers.ANINews;
import dev.tu2l.ng.api.scrapers.BaseNewsScraper;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
       new Thread(()->{
           BaseNewsScraper newsScraper = new ANINews();
           List<News> newsList = newsScraper.searchNews("guwahati");
           Gson gson = new Gson();
//           for(News news: newsList)
//                System.err.println(gson.toJson(news));
           System.err.println(gson.toJson(newsList));
       }).start();
    }
}