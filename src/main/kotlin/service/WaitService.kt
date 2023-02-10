package service

class WaitService {

    fun waitForResponse(waitTime: Int, block: () -> Boolean) {
        val pause = 500
        val maxRetries: Int = waitTime / pause
        var retryCount = 0

        while (retryCount < maxRetries && !block()) {
            retryCount++
            Thread.sleep(pause.toLong())
        }
        if (retryCount >= maxRetries) {
            throw Exception("Failed to get desired value after $waitTime microseconds")
        }
    }

}