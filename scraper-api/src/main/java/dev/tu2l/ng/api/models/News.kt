package dev.tu2l.ng.api.models

class News {
    var videoUrl: String? = null
    var imageUrl: String? = null
    var imageTitle: String? = null
    var title: String? = null
    var source: String? = null
    var timestamp: String? = null
    var story: String? = null
    var tags: List<String?>? = null
    var url: String? = null

    override fun toString(): String {
        return "News(videoUrl=$videoUrl, imageUrl=$imageUrl, imageTitle=$imageTitle, title=$title, source=$source, timestamp=$timestamp, story=$story, tags=$tags, url=$url)"
    }
}
