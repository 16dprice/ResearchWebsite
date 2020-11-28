import entities.Crossing
import entities.Link
import entities.PDCode
import java.io.File

class KnotTextFileReader(
    private val knotTextFile: File
) {
    fun getLinkList(): List<Link> {
        return getPDCodeList().map { pdCode -> Link(pdCode) }
    }

    private fun getPDCodeList(): List<PDCode> {
        val pdCodes = mutableListOf<PDCode>()

        knotTextFile.forEachLine { pdCode ->
            val crossings = getCrossingsFromTextFilePDCode(pdCode).map { crossing ->
                getCrossingFromTextFileCrossing(crossing)
            }
            pdCodes.add(PDCode(crossings))
        }

        return pdCodes
    }

    private fun getCrossingsFromTextFilePDCode(textFilePDCode: String): List<String> {
        // drop the first element because it is just
        // the string "PD[" because of the format of the text files
        return textFilePDCode.split("X").drop(1)
    }

    private fun getCrossingFromTextFileCrossing(textFileCrossing: String): Crossing {
        val removeNonNumericText = Regex("[^\\d]")
        val numericStrands = textFileCrossing.split(",").take(4).map { strand ->
            removeNonNumericText.replace(strand, "").toInt()
        }

        return Crossing(
            numericStrands[0],
            numericStrands[1],
            numericStrands[2],
            numericStrands[3]
        )
    }
}