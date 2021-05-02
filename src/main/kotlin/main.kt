import lexer.Lexer

fun main(args: Array<String>) {
    val lexer = Lexer("1 + 2 * 3 + (4 * 5) / 2 + 1.0 - 0.1 + .1 * 2.")

    lexer.generateTokens().forEach { token ->
        println(token)
    }
}
