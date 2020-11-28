package entities

class Link(
    private val PDCode: PDCode
) {
    fun getPDCode(): PDCode { return PDCode }
}