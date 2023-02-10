
import config.Errors.FOLDER_ALREADY_EXISTS_ERROR
import helper.resources.ResourcesHelper
import io.qameta.allure.Description
import model.resource.FailureResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.AfterTest

class FolderMoveTests {

    private val initialPath = "folder"
    private val existingPath = "new_folder"

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    @Description("Перемещение каталога в папку с уже существующим таким же ресурсом. С перезаписью и без.")
    fun moveFolderToExistingDestination(overwrite: Boolean){

        val resourcesHelper = ResourcesHelper()

        resourcesHelper.createFolder(initialPath)
        assertThat(resourcesHelper.checkIfFolderExists(initialPath)).isTrue

        resourcesHelper.createFolder(existingPath)
        assertThat(resourcesHelper.checkIfFolderExists(existingPath)).isTrue

        val moveFolderResponse = resourcesHelper.moveFolderToNewPath(
            from=initialPath, newPath=existingPath, overwrite=overwrite
        )

        if(overwrite){
            moveFolderResponse.hasStatusCode(201)
            assertThat(resourcesHelper.checkIfFolderExists(initialPath)).isFalse
        }
        if(!overwrite){
            val model = moveFolderResponse.hasStatusCode(409)
                .getModelFromResponse(FailureResult::class.java)
            assertThat(model.error).isEqualTo(FOLDER_ALREADY_EXISTS_ERROR)
            assertThat(resourcesHelper.checkIfFolderExists(initialPath)).isTrue
        }

    }

    @AfterTest
    @Description("Teardown")
    fun cleanUpFolders(){
        val resourcesHelper = ResourcesHelper()
        if(resourcesHelper.checkIfFolderExists(path=initialPath)){
            resourcesHelper.deleteFolderAsync(path=initialPath)
        }
        if(resourcesHelper.checkIfFolderExists(path=existingPath)){
            resourcesHelper.deleteFolderAsync(path=existingPath)
        }
    }

}