package helper.operation

import api.operation.OperationApi
import model.operation.OperationData
import service.WaitService

class OperationHelper {

    private val operationApi: OperationApi = OperationApi()


    private fun getOperationStatus(operationId: String): String {
        return operationApi.getOperationResponse(operationId=operationId)
            .getModelFromResponse(OperationData::class.java).status
    }

    fun waitRequiredOperationStatus(operationId: String, status: String){
        WaitService().waitForResponse(waitTime=4500) {
            getOperationStatus(operationId=operationId) == status
        }
    }

}
