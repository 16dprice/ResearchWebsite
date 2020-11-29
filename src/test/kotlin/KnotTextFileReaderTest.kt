import org.junit.Assert.assertEquals
import org.junit.Test
import org.slf4j.LoggerFactory
import java.io.File
import java.net.URL

class KnotTextFileReaderTest {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val testFileNames = mapOf(
        "knot_6_3.txt" to 2,
        "knot_5_1.txt" to 1
    )

    @Test
    fun testGetLinkList() {
        for((filename, expectedNumberOfLinks) in testFileNames) {
            val testFile = getFileForLinkListTest(filename) ?: continue

            val knotTextFileReader = KnotTextFileReader(testFile)
            assertEquals(expectedNumberOfLinks, knotTextFileReader.getLinkList().size)
        }
    }

    private fun getFileForLinkListTest(filename: String): File? {
        val fileResource: URL? = javaClass.classLoader.getResource(
            filename
        )
        if(fileResource == null) {
            logger.error("File $filename for Link List test not found.")
            return null
        }
        return File(fileResource.file)
    }
}