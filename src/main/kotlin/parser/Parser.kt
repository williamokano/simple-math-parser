package parser

import lexer.AddTokenType
import lexer.DivisionTokenType
import lexer.LeftParenthesesTokenType
import lexer.MultiplyTokenType
import lexer.NumberTokenType
import lexer.RightParenthesesTokenType
import lexer.SubtractionTokenType
import lexer.TokenType

class Parser(tokens: Sequence<TokenType>) {

    private val tokenIterator = tokens.iterator()
    private var currentToken: TokenType? = tokenIterator.next()

    fun parse(): Node {
        checkNotNull(currentToken) { "Not input provided" }

        val result = expression()

        check(currentToken == null) { "Invalid syntax" }

        return result
    }

    private fun expression(): Node {
        var result = term()

        while (currentToken != null && (currentToken is AddTokenType || currentToken is SubtractionTokenType)) {
            if (currentToken is AddTokenType) {
                advance()
                result = AddNode(result, term())
            } else if (currentToken is SubtractionTokenType) {
                advance()
                result = SubtractNode(result, term())
            }
        }

        return result
    }

    private fun term(): Node {
        var result = factor()

        while (currentToken != null && (currentToken is MultiplyTokenType || currentToken is DivisionTokenType)) {
            if (currentToken is MultiplyTokenType) {
                advance()
                result = MultiplyNode(result, factor())
            } else if (currentToken is DivisionTokenType) {
                advance()
                result = DivideNode(result, factor())
            }
        }

        return result
    }

    private fun factor(): Node {
        var token = currentToken!!

        when (token) {
            is LeftParenthesesTokenType -> {
                advance()
                val result = expression()

                if (currentToken !is RightParenthesesTokenType) {
                    throw IllegalArgumentException("Invalid syntax. Expecting right parentheses")
                }

                advance()

                return result

            }
            is NumberTokenType -> {
                advance()
                return NumberNode(token.value)
            }
            is AddTokenType -> {
                advance()
                return PlusNode(factor())
            }
            is SubtractionTokenType -> {
                advance()
                return MinusNode(factor())
            }
            else -> throw IllegalArgumentException("Invalid syntax")
        }

    }

    private fun advance() {
        currentToken = if (tokenIterator.hasNext())
            tokenIterator.next()
        else
            null
    }
}
