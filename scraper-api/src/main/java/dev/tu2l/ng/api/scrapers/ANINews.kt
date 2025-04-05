package dev.tu2l.ng.api.scrapers

import dev.tu2l.ng.api.ANINewsUrls
import dev.tu2l.ng.api.models.News
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class ANINews:BaseNewsScraper {

    private fun getList(document: Document?, className: String?): List<News> {
        val newsList: MutableList<News> = ArrayList<News>()
        var i = 0
        val size = document?.select("div[class=$className]")?.size!!
        while (i < size) {
            val item = document.select("div[class=$className]")[i]
            val news = News()
            news.imageUrl = item?.select("img")?.attr("data-src")

            var link = item?.select("a[class=read-more]")?.attr("href")
            if (link == null || link == "")
                link = item?.selectFirst("a")?.attr("href")
            news.url = ANINewsUrls.BASE + link

            var timestamp = item?.select("span[class=time-red]")?.text()
            timestamp += " " + item?.select("span[class=first red-ist]")?.text()
            news.timestamp = timestamp

            news.title = item?.select("h6[class=title]")?.text()

            news.story = item?.select("p[class=text small]")?.text()
            if (news.story == "")
                if(item?.select("p")?.size!! > 1)
                    news.story = item.select("p")?.get(2)?.text()

            news.source = "ANI"
            newsList.add(news)
            i++
        }
        return newsList
    }

    override fun getNews(url: String?): News {
        if (url == null)
            throw NullPointerException("news url is empty")

        val document = getDocument(url)
        val card: Element? = document?.selectFirst("div[class=card]")

        val news: News = News()

        //image
        news.imageUrl = card?.selectFirst("img")?.attr("src")
        news.imageTitle = card?.select("i")?.text()

        //article
        val article: Element? = card?.selectFirst("article")
        news.title = article?.select("h1[class=title]")?.text()
//        var timestamp: String? = article?.select("span[class=time-red")?.text()
//        timestamp += article?.select("span[class=first red-ist]")?.text()
        news.timestamp = article?.select("p[class=time]")?.text()
        news.story = article?.select("div[class=content count-br]")?.text()

        val tags: Elements? = card?.select("div[class=box-tag]")
        var i = 0
        val tagsList: MutableList<String?> = ArrayList<String?>()
        while (i < tags?.size!!) {
            tagsList.add(tags[i].select("a").text())
            i++
        }

        news.source = "ANI"
        news.url = url
        news.tags = tagsList.toList()

        return news
    }

    override fun getNewsList(url: String): List<News> {
        val newsList: MutableList<News> = ArrayList<News>()
        val document = getDocument(url)
        val main: Element? = document?.selectFirst("div[class=card]")

        val news: News = News()
        news.imageUrl = main?.select("img")?.attr("data-src")
        news.url = ANINewsUrls.BASE + main?.select("div[class=read-more-block]")?.select("a")?.attr("href")
        var timestamp: String? = main?.select("span[class=time-red]")?.text()
        timestamp += " " + main?.select("span[class=first red-ist]")?.text()
        news.timestamp = timestamp
        news.title = main?.select("h1[class=title]")?.text()
        news.story = main?.select("div[class=content]")?.last()?.select("p")?.text()
        news.source = "ANI"
        newsList.add(news)

        newsList.addAll(getList(document, "col-sm-6 col-xs-12 extra-related-block"))
        return newsList;
    }

    override fun searchNews(keyword: String): List<News> {
        val url = "${ANINewsUrls.BASE}/search/?query=$keyword"
        return getList(getDocument(url), "col-sm-4 col-xs-12")
    }

    override fun getLatestNews(): List<News> {
        val url = "${ANINewsUrls.BASE}/latest-news/"
        return getList(getDocument(url), "col-md-4 col-sm-6 col-xs-12 extra-related-block mb-4")
    }

    override fun getVideoNewsList(url: String): List<News> {
        return getList(getDocument(url), "col-md-3 col-lg-3  col-sm-3 col-xs-12")
    }

    override fun getVideoNews(url: String): News {
        val document = getDocument(url)
        val card: Element? =
                document?.selectFirst("div[class=col-md-8 col-sm-8 col-xs-12 left-block video-left-block \tvideo-page]")

        val news: News = News()
        news.videoUrl = card?.selectFirst("iframe")?.attr("data-src")
        news.title = card?.select("h1 [class=title video-title]")?.text()
        news.timestamp = card?.select("span[class=time-red]")?.text()
        news.story = card?.select("div[class=content]")?.get(1)?.select("p")?.text()
        news.source = "ANI"
        news.url = url

        return news
    }
}