import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.slf4j.LoggerFactory
import java.io.File
import java.net.URL

class KnotTextFileReaderTest {

    private data class GetLinkListTestCase(
        private val testFileName: String,
        val expectedResult: Int
    ) {
        val testFile: File?

        init {
            val testFileResource: URL? = javaClass.classLoader.getResource(
                testFileName
            )

            testFile = if(testFileResource == null) null else File(testFileResource.file)
        }
    }

    private val logger = LoggerFactory.getLogger(javaClass)

    @Test
    fun testGetLinkList() {
        for(testCase in getTestCasesForGetLinkList()) {
            testCase.testFile ?: continue
            val knotTextFileReader = KnotTextFileReader(testCase.testFile)
            assertEquals(knotTextFileReader.getLinkList().size, testCase.expectedResult)
        }
    }

    private fun getTestCasesForGetLinkList(): List<GetLinkListTestCase> {
        val testCasesResource: URL? = javaClass.classLoader.getResource(
            "KnotTextFileReader_GetLinkList_TestCases.json"
        )
        if(testCasesResource == null) {
            logger.error("KnotTextFileReader JSON test file not found.")
            return listOf()
        }

        val testCasesFile = File(testCasesResource.file)
        return jacksonObjectMapper().readValue(
            testCasesFile.readText()
        )
    }
}