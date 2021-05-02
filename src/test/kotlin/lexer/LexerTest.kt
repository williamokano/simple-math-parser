package lexer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LexerTest {

    @Test
    fun `should return empty sequence for empty text`() {
        val tokens = Lexer("").generateTokens()

        assertThat(tokens.toList()).isEqualTo(emptyList<TokenType>())
    }

    @Test
    fun `should parse numbers`() {
        val lexer = Lexer("1 2 3 4 0 1.0 0.1 .1 1. .").generateTokens()

        assertThat(lexer.toList()).isEqualTo(
            listOf(
                NumberTokenType(1.0),
                NumberTokenType(2.0),
                NumberTokenType(3.0),
                NumberTokenType(4.0),
                NumberTokenType(0.0),
                NumberTokenType(1.0),
                NumberTokenType(0.1),
                NumberTokenType(0.1),
                NumberTokenType(1.0),
                NumberTokenType(0.0)
            )
        )
    }

    @Test
    fun `should parse operators`() {
        val lexer = Lexer("+-*/").generateTokens()

        assertThat(lexer.toList()).isEqualTo(
            listOf(
                AddTokenType,
                SubtractionTokenType,
                MultiplyTokenType,
                DivisionTokenType
            )
        )
    }

    @Test
    fun `should parse parentheses`() {
        val lexer = Lexer("()").generateTokens()

        assertThat(lexer.toList()).isEqualTo(
            listOf(
                LeftParenthesesTokenType,
                RightParenthesesTokenType
            )
        )
    }

    @Test
    fun `should parse a complete expression`() {
        val lexer = Lexer("-(1 + 2) * 3 / 4 * .5").generateTokens()

        assertThat(lexer.toList()).isEqualTo(
            listOf(
                SubtractionTokenType,
                LeftParenthesesTokenType,
                NumberTokenType(1.0),
                AddTokenType,
                NumberTokenType(2.0),
                RightParenthesesTokenType,
                MultiplyTokenType,
                NumberTokenType(3.0),
                DivisionTokenType,
                NumberTokenType(4.0),
                MultiplyTokenType,
                NumberTokenType(0.5)
            )
        )
    }
}
