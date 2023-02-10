package api.resources

import client.HttpClient
import client.RequestBuilder
import config.Configuration
import io.restassured.http.Method
import io.restassured.response.Response
import report.AllureHelpers

class ResourcesMoveApi {

    private val httpClient = HttpClient(endpoint = Configuration.RESOURCES_MOVE_ENDPOINT)

    fun moveFolderResponse(from: String, newPath: String, overwrite: Boolean = false, forceAsync: Boolean = false): Response {
        return AllureHelpers.step("Move folder one path: $from to another: $newPath") {
            httpClient.send(
                method = Method.POST,
                RequestBuilder()
                    .setQueryParam("from", from)
                    .setQueryParam("path", newPath)
                    .setQueryParam("force_async", forceAsync)
                    .setQueryParam("overwrite", overwrite)
                    .build()
            )
        }
    }
}