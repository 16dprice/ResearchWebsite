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

        knotTextFile.forEachLine { pdCodeTextFileString ->
            if(isPDCodeTextFileStringValid(pdCodeTextFileString)) {
                pdCodes.add(getPDCodeFromValidTextFileString(pdCodeTextFileString))
            }
        }

        return pdCodes
    }

    private fun getPDCodeFromValidTextFileString(textFileString: String): PDCode {
        val crossings = getCrossingsFromTextFilePDCode(textFileString).map { crossing ->
            getCrossingFromTextFileCrossing(crossing)
        }
        return PDCode(crossings)
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

    private fun isPDCodeTextFileStringValid(pdCodeTextFileString: String): Boolean {
        if(pdCodeTextFileString.isBlank()) return false
        return true
    }
}