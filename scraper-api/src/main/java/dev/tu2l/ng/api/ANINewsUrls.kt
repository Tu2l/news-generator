package dev.tu2l.ng.api

class ANINewsUrls {
    companion object{
        const val BASE = "https://aninews.in"

        /*
         * National News
         */
        const val NATIONAL_BASE = "$BASE/category/national"
        const val NATIONAL_GENERAL = "$NATIONAL_BASE/general-news/"
        const val NATIONAL_POLITICS = "$NATIONAL_BASE/politics/"
        const val NATIONAL_FEATURES = "$NATIONAL_BASE/features/"

        /*
         * World News
         */
        const val WORLD_BASE = "$BASE/category/world"
        const val WORLD_ASIA = "$WORLD_BASE/asia/"
        const val WORLD_US = "$WORLD_BASE/us/"
        const val WORLD_EUROPE = "$WORLD_BASE/europe/"
        const val WORLD_PACIFIC = "$WORLD_BASE/asia/"
        const val WORLD_OTHERS = "$WORLD_BASE/others/"
        const val WORLD_MIDDLE = "$WORLD_BASE/middle-east/"

        /*
         * Video News
         */
        const val VIDEO = "$BASE/videos"
        const val VIDEO_NATIONAL = "$BASE/videos/national"
        const val VIDEO_WORLD = "$BASE/videos/world"
        const val VIDEO_ENTERTAINMENT = "$BASE/videos/entertainment"
        const val VIDEO_SPORTS = "$BASE/videos/sports"
        const val VIDEO_BUSINESS = "$BASE/videos/business"
        const val VIDEO_HEALTH = "$BASE/videos/health"
        const val VIDEO_TECHNOLOGY = "$BASE/videos/technology"
        const val VIDEO_TRAVEL = "$BASE/videos/travel"
    }
}