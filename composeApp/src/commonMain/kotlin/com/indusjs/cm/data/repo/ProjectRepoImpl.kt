package com.indusjs.cm.data.repo

import com.indusjs.cm.domain.model.project.Projects
import com.indusjs.cm.domain.repo.IProjectRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class ProjectRepoImpl(
    private val endPoint: String,
    private val httpClient: HttpClient
) : IProjectRepo {

    override suspend fun getProjects(param: Any?): Projects {
        val response: HttpResponse = httpClient.get() {
            url("$endPoint/projects")
        }
        return if (response.status.isSuccess()) {
            response.body<Projects>()
        } else {
            Projects(
                success = false,
                message = response.status.toString(),
            )
        }
    }


}