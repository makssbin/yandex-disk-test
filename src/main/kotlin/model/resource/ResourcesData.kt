package model.resource

import com.fasterxml.jackson.annotation.JsonProperty

open class SuccessfulResult{
    @JsonProperty("href")
    val operationLink: String = ""
    @JsonProperty("method")
    val method: String = ""
    @JsonProperty("templated")
    val templated: Boolean = true
}


class FailureResult{

    @JsonProperty("message")
    val message: String = ""
    @JsonProperty("description")
    val description: String = ""
    @JsonProperty("error")
    val error: String = ""

}