package report

import io.qameta.allure.Allure
import io.restassured.response.Response


object AllureHelpers {

    @JvmStatic
    fun <T> step(name: String, block: () -> T): T = Allure.step(name, block)

    fun attachResponse(response: Response){
        val attachment = StringBuilder()
            .append("Body:").append("\n")
            .append(response.body().asString()).append("\n")
            .append("Cookies:").append("\n")
            .append(response.cookies).append("\n")
            .append("Headers:").append("\n")
            .append(response.headers).append("\n")
            .append("StatusCode:").append("\n")
            .append(response.statusCode).append("\n")
            .toString()
        Allure.addAttachment("Response", attachment)
    }
}