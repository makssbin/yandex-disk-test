package client

import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification

class RequestBuilder{

    private val requestSpecification: RequestSpecification = RestAssured.given()

    fun setPathParam(path: String): RequestBuilder{
        requestSpecification.basePath(path)
        return this
    }

    fun setQueryParam(param: String, value: Any): RequestBuilder {
        requestSpecification.queryParam(param, value)
        return this
    }

    fun build(): RequestSpecification {
        return requestSpecification
    }
}
