package api.resources

import client.HttpClient
import client.RequestBuilder
import config.Configuration
import io.restassured.http.Method
import io.restassured.response.Response
import report.AllureHelpers.step

class ResourcesApi {

    private val httpClient = HttpClient(endpoint = Configuration.RESOURCES_ENDPOINT)

    fun createFolderResponse(path: String): Response {
        return step("Create new folder in $path") {
            httpClient.send(
                method = Method.PUT,
                RequestBuilder().setQueryParam("path", path).build()
            )
        }
    }

    fun getFolderInfoResponse(path: String): Response{
        return step("Get folder $path info") {
            httpClient.send(
                method = Method.GET,
                RequestBuilder().setQueryParam("path", path).build()
            )
        }
    }

    fun deleteFolderResponse(path: String, isAsync: Boolean = false, isPermanent: Boolean = true): Response {
        return step("Delete folder in $path") {
            httpClient.send(
                method = Method.DELETE,
                RequestBuilder()
                    .setQueryParam("path", path)
                    .setQueryParam("force_async", isAsync)
                    .setQueryParam("permanently", isPermanent)
                    .build()
            )
        }
    }



}