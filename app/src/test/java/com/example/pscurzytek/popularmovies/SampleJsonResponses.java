package com.example.pscurzytek.popularmovies;

class SampleJsonResponses {
    final static String InvalidIdSingleMovieResponse = "{\n" +
            "            \"id\": 0\n" +
            "        }";

    final static String SingleMovieResponse = "{\n" +
            "            \"vote_count\": 10,\n" +
            "            \"id\": 1,\n" +
            "            \"video\": false,\n" +
            "            \"vote_average\": 10.5,\n" +
            "            \"title\": \"movie title\",\n" +
            "            \"popularity\": 15.8,\n" +
            "            \"poster_path\": \"/path.jpg\",\n" +
            "            \"original_language\": \"hi\",\n" +
            "            \"original_title\": \"title in spanish\",\n" +
            "            \"genre_ids\": [\n" +
            "                2,\n" +
            "                test,\n" +
            "                4\n" +
            "            ],\n" +
            "            \"backdrop_path\": \"/backdrop.jpg\",\n" +
            "            \"adult\": false,\n" +
            "            \"overview\": \"movie overview\",\n" +
            "            \"release_date\": \"1995-10-20\"\n" +
            "        }";

    final static String MoviesPageResponse = "{\n" +
            "    \"page\": 1,\n" +
            "    \"total_results\": 100,\n" +
            "    \"total_pages\": 10,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"id\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"vote_count\": 10,\n" +
            "            \"id\": 1,\n" +
            "            \"video\": false,\n" +
            "            \"vote_average\": 10.5,\n" +
            "            \"title\": \"movie title\",\n" +
            "            \"popularity\": 15.8,\n" +
            "            \"poster_path\": \"/path.jpg\",\n" +
            "            \"original_language\": \"hi\",\n" +
            "            \"original_title\": \"title in spanish\",\n" +
            "            \"genre_ids\": [\n" +
            "                1,\n" +
            "                2\n" +
            "            ],\n" +
            "            \"backdrop_path\": \"/backdrop.jpg\",\n" +
            "            \"adult\": false,\n" +
            "            \"overview\": \"movie overview\",\n" +
            "            \"release_date\": \"1995-10-20\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"vote_count\": 20,\n" +
            "            \"id\": 2,\n" +
            "            \"video\": false,\n" +
            "            \"vote_average\": 20.5,\n" +
            "            \"title\": \"movie title 2\",\n" +
            "            \"popularity\": 15.8,\n" +
            "            \"poster_path\": \"/path2.jpg\",\n" +
            "            \"original_language\": \"en\",\n" +
            "            \"original_title\": \"movie title 2\",\n" +
            "            \"genre_ids\": [\n" +
            "                2,\n" +
            "                4\n" +
            "            ],\n" +
            "            \"backdrop_path\": \"/backdrop2.jpg\",\n" +
            "            \"adult\": false,\n" +
            "            \"overview\": \"movie overview 2\",\n" +
            "            \"release_date\": \"2000-10-20\"\n" +
            "        }" +
            "       ],\n" +
            " }";
}
