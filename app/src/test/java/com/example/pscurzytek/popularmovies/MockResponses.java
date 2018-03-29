package com.example.pscurzytek.popularmovies;

class MockResponses {
    final static String SingleReviewResponse = "{\n" +
            "            \"id\": \"12345\", \n" +
            "            \"author\": \"review author\", \n" +
            "            \"content\": \"review content\", \n" +
            "            \"url\": \"review url\", \n" +
            "        }";

    final static String ReviewsPageResponse = "{\n" +
            "    \"page\": 1,\n" +
            "    \"total_results\": 100,\n" +
            "    \"total_pages\": 10,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"id\": \"1\",\n" +
            "            \"author\": \"review 1 author\", \n" +
            "            \"content\": \"review 1 content\", \n" +
            "            \"url\": \"review 1 url\", \n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"2\",\n" +
            "            \"author\": \"review 2 author\", \n" +
            "            \"content\": \"review 2 content\", \n" +
            "            \"url\": \"review 2 url\", \n" +
            "        }\n" +
            "       ],\n" +
            " }";

    final static String SingleTrailerResponse = "{\n" +
            "            \"id\": \"12345\", \n" +
            "            \"iso_639_1\": \"en\", \n" +
            "            \"iso_3166_1\": \"US\", \n" +
            "            \"key\": \"key\",\n" +
            "            \"name\": \"trailer name\", \n" +
            "            \"site\": \"YouTube\", \n" +
            "            \"size\": 100, \n" +
            "            \"type\": \"Trailer\", \n" +
            "        }";

    final static String TrailersListResponse = "{\n" +
            "    \"id\": 1,\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"id\": \"1\", \n" +
            "            \"iso_639_1\": \"en\", \n" +
            "            \"iso_3166_1\": \"US\", \n" +
            "            \"key\": \"key\"," +
            "            \"name\": \"trailer 1\", \n" +
            "            \"site\": \"YouTube\", \n" +
            "            \"size\": 100, \n" +
            "            \"type\": \"Trailer\", \n" +
            "        }, \n" +
            "        {\n" +
            "            \"id\": \"2\", \n" +
            "            \"iso_639_1\": \"en\", \n" +
            "            \"iso_3166_1\": \"US\", \n" +
            "            \"key\": \"key\"," +
            "            \"name\": \"trailer 2\", \n" +
            "            \"site\": \"YouTube\", \n" +
            "            \"size\": 200, \n" +
            "            \"type\": \"Trailer\", \n" +
            "        } \n" +
            "       ],\n" +
            " }";

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
            "        }\n" +
            "       ],\n" +
            " }";
}
