package lexer

class Lexer(text: String) {

    private val textIterator = text.iterator()
    private var currentChar: Char? = textIterator.nextChar()

    fun advance() {
        currentChar = if (textIterator.hasNext())
            textIterator.nextChar()
        else
            null
    }

    fun generateTokens(): Sequence<TokenType> = sequence {
        while (currentChar.isNotNull()) {
            when {
                isWhiteSpace(currentChar!!) -> {

                }
                isDigitOrDecimalPoint(currentChar!!) -> {
                    yield(generateNumber())
                }
                currentChar == '+' -> yield(AddTokenType)
                currentChar == '-' -> yield(SubtractionTokenType)
                currentChar == '*' -> yield(MultiplyTokenType)
                currentChar == '/' -> yield(DivisionTokenType)
                currentChar == '(' -> yield(LeftParenthesesTokenType)
                currentChar == ')' -> yield(RightParenthesesTokenType)
                else -> throw IllegalArgumentException("Invalid character '$currentChar'")
            }

            advance()
        }
    }

    private fun generateNumber(): NumberTokenType {
        var decimalPointCount = 0
        var numberStr: String = currentChar!!.toString()
        advance()

        while (currentChar.isNotNull() && isDigitOrDecimalPoint(currentChar!!)) {
            if (currentChar == '.') {
                decimalPointCount++
            }

            if (decimalPointCount > 1)
                break

            numberStr += currentChar!!.toString()
            advance()
        }

        if (numberStr.startsWith('.')) {
            numberStr = "0$numberStr"
        }

        if (numberStr.endsWith('.')) {
            numberStr = "${numberStr}0"
        }

        return NumberTokenType(numberStr.toDouble())
    }

    private fun <T> T?.isNotNull() = this != null

    private fun isWhiteSpace(char: Char) = WHITEPSACES.contains(char.toString())

    private fun isDigitOrDecimalPoint(char: Char) = DIGITS.contains(char) || char == '.'

    companion object {
        private val WHITEPSACES = " \t\n\r"
        private val DIGITS = "0123456789"
    }
}
