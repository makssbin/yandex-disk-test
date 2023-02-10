
import config.OperationStatus.SUCCESS
import helper.operation.OperationHelper
import helper.resources.ResourcesHelper
import io.qameta.allure.Description
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class DeleteFolderTests {

    @Test
    @Description("Асинхронное удаление папки")
    fun asyncFolderDeletion(){
        val resourcesHelper = ResourcesHelper()
        val operationHelper = OperationHelper()
        val path = "some_folder"

        resourcesHelper.createFolder(path=path)
        assertThat(resourcesHelper.checkIfFolderExists(path=path)).isTrue

        val operationId: String = resourcesHelper.deleteFolderAsync(path=path)

        operationHelper.waitRequiredOperationStatus(operationId=operationId, status=SUCCESS)
        assertThat(resourcesHelper.checkIfFolderExists(path=path)).isFalse
    }

}