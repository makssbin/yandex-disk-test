package client

import config.Configuration.AUTH_TOKEN
import config.Configuration.MAIN_API_URL
import io.restassured.http.Method
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import report.AllureHelpers
import report.AllureHelpers.step


class HttpClient(private val endpoint: String = "") {

    private val baseUrl: String = MAIN_API_URL

    fun send(method: Method, request: RequestSpecification): Response {
        val response = step("Send $method request to $baseUrl"){
            request.baseUri(baseUrl).header("Authorization", "OAuth $AUTH_TOKEN").request(method, endpoint)
        }
        AllureHelpers.attachResponse(response=response)
        return response
    }
}
