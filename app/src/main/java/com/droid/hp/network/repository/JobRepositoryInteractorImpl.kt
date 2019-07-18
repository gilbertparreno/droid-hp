package com.droid.hp.network.repository

import com.droid.hp.network.model.Job
import com.google.gson.Gson
import io.reactivex.Single

class JobRepositoryInteractorImpl :
    JobRepositoryInteractor {

    val json = "{\n" +
            "  \"jobs\": [\n" +
            "    {\n" +
            "      \"jobId\": 423421,\n" +
            "      \"category\": \"Electricians\",\n" +
            "      \"postedDate\": \"2017-04-13\",\n" +
            "      \"status\": \"In Progress\",\n" +
            "      \"connectedBusinesses\": [\n" +
            "        {\n" +
            "          \"businessId\": 203213,\n" +
            "          \"thumbnail\": \"https://assets.homeimprovementpages.com.au/images/hui/avatars/a.png\",\n" +
            "          \"isHired\": false\n" +
            "        },\n" +
            "        {\n" +
            "          \"businessId\": 205434,\n" +
            "          \"thumbnail\": \"https://assets.homeimprovementpages.com.au/images/hui/avatars/p.png\",\n" +
            "          \"isHired\": true\n" +
            "        },\n" +
            "        {\n" +
            "          \"businessId\": 414324,\n" +
            "          \"thumbnail\": \"https://assets.homeimprovementpages.com.au/images/hui/avatars/i.png\",\n" +
            "          \"isHired\": false\n" +
            "        },\n" +
            "        {\n" +
            "          \"businessId\": 324353,\n" +
            "          \"thumbnail\": \"https://assets.homeimprovementpages.com.au/images/hui/avatars/g.png\",\n" +
            "          \"isHired\": true\n" +
            "        }\n" +
            "      ],\n" +
            "      \"detailsLink\": \"/jobs/423421\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"jobId\": 426484,\n" +
            "      \"category\": \"Carpet Cleaning\",\n" +
            "      \"postedDate\": \"2017-03-20\",\n" +
            "      \"status\": \"Closed\",\n" +
            "      \"connectedBusinesses\": [\n" +
            "        {\n" +
            "          \"businessId\": 381453,\n" +
            "          \"thumbnail\": \"https://assets.homeimprovementpages.com.au/images/hui/avatars/d.png\",\n" +
            "          \"isHired\": true\n" +
            "        }\n" +
            "      ],\n" +
            "      \"detailsLink\": \"/jobs/426484\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"jobId\": 4265031,\n" +
            "      \"category\": \"Splashbacks\",\n" +
            "      \"postedDate\": \"2017-05-03\",\n" +
            "      \"status\": \"In progress\",\n" +
            "      \"connectedBusinesses\": [\n" +
            "        {\n" +
            "          \"businessId\": 381453,\n" +
            "          \"thumbnail\": \"https://assets.homeimprovementpages.com.au/images/hui/avatars/e.png\",\n" +
            "          \"isHired\": false\n" +
            "        }\n" +
            "      ],\n" +
            "      \"detailsLink\": \"/jobs/4265031\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"jobId\": 564211,\n" +
            "      \"category\": \"Painting\",\n" +
            "      \"postedDate\": \"2017-05-08\",\n" +
            "      \"status\": \"In progress\",\n" +
            "      \"connectedBusinesses\": null,\n" +
            "      \"detailsLink\": \"/jobs/564211\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    override fun getProjectList(): Single<List<Job.JobsItem>> {
        val job = Gson().fromJson(json, Job::class.java)
        return Single.just(job.jobs)
    }
}