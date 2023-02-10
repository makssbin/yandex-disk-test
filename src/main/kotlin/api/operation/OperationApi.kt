package api.operation

import client.HttpClient
import client.RequestBuilder
import client.Response
import config.Configuration
import io.restassured.http.Method
import report.AllureHelpers

class OperationApi {

    private val httpClient = HttpClient()

    fun getOperationResponse(operationId: String): Response {
        return AllureHelpers.step("Get operation status") {
            Response(httpClient.send(
                method = Method.GET,
                RequestBuilder().setPathParam("${Configuration.OPERATION_ENDPOINT}/$operationId").build()
            ))
        }
    }


}