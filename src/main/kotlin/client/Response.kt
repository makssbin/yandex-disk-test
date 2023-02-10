package client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat


class Response(private val response: Response) {

    fun hasStatusCode(statusCode: Int): client.Response {
        assertThat(response.statusCode.toString()).isEqualTo(statusCode.toString())
        return this
    }

    fun getStatusCode(): Int{
        return response.statusCode
    }

    fun <T> getModelFromResponse(modelClass: Class<T>): T {
        val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return objectMapper.readValue(response.body().asString(), modelClass)
    }
}
