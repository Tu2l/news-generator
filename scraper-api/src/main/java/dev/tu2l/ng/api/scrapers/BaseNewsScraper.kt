package dev.tu2l.ng.api.scrapers

import dev.tu2l.ng.api.models.News
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

interface BaseNewsScraper {
     fun getDocument(url: String): Document? {
        var response: Connection.Response? = null
        try {
            response = Jsoup.connect(url).execute()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
        return response?.parse()
    }

    fun getNews(url:String?):News
    fun getNewsList(url:String):List<News>
    fun searchNews(keyword:String):List<News>
    fun getLatestNews():List<News>
    fun getVideoNewsList(url: String): List<News>
    fun getVideoNews(url: String): News
}