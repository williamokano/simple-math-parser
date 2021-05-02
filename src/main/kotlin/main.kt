import interpreter.Interpreter
import lexer.Lexer
import parser.Parser

fun main(args: Array<String>) {
    val lexer = Lexer("1")
    val tokens = lexer.generateTokens()

    val parser = Parser(tokens)
    val tree = parser.parse()

    val interpreter = Interpreter()
    val value = interpreter.visit(tree)

    println(value)
}
