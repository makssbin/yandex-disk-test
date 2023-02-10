package helper.resources

import api.resources.ResourcesApi
import api.resources.ResourcesMoveApi
import client.Response
import model.resource.SuccessfulResult


class ResourcesHelper {

    private val resourcesApi: ResourcesApi = ResourcesApi()
    private val resourcesMoveApi: ResourcesMoveApi = ResourcesMoveApi()

    fun createFolder(path: String) {
        Response(resourcesApi.createFolderResponse(path=path)).hasStatusCode(201)
    }

    fun deleteFolderAsync(path: String): String{
        return Response(resourcesApi.deleteFolderResponse(path=path, isAsync=true))
            .hasStatusCode(202)
            .getModelFromResponse(SuccessfulResult::class.java)
            .operationLink
            .substringAfterLast("operations/")
    }

    fun checkIfFolderExists(path: String): Boolean{
        val response = Response(resourcesApi.getFolderInfoResponse(path=path))
        if(response.getStatusCode() == 200){
            return true
        }
        return false
    }

    fun moveFolderToNewPath(from: String, newPath: String, overwrite: Boolean): Response{
        return Response(resourcesMoveApi.moveFolderResponse(from=from, newPath=newPath, overwrite=overwrite))
    }
}