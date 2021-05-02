package lexer

sealed class TokenType {
    override fun toString(): String {
        return javaClass.simpleName
    }
}

data class NumberTokenType(val value: Double) : TokenType() {
    override fun toString(): String {
        return "${javaClass.simpleName}:$value"
    }
}

object AddTokenType : TokenType()
object SubtractionTokenType : TokenType()
object MultiplyTokenType : TokenType()
object DivisionTokenType : TokenType()
object LeftParenthesesTokenType : TokenType()
object RightParenthesesTokenType : TokenType()
