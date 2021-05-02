package interpreter

import parser.AddNode
import parser.DivideNode
import parser.MinusNode
import parser.MultiplyNode
import parser.Node
import parser.NumberNode
import parser.PlusNode
import parser.SubtractNode

class Interpreter {
    fun visit(node: Node): NumberValue {
        return when (node) {
            is NumberNode -> visit(node)
            is AddNode -> visit(node)
            is DivideNode -> visit(node)
            is MinusNode -> visit(node)
            is MultiplyNode -> visit(node)
            is PlusNode -> visit(node)
            is SubtractNode -> visit(node)
        }
    }

    private fun visit(node: NumberNode): NumberValue {
        return NumberValue(node.value)
    }

    private fun visit(node: AddNode): NumberValue {
        return NumberValue(visit(node.nodeA).value + visit(node.nodeB).value)
    }

    private fun visit(node: SubtractNode): NumberValue {
        return NumberValue(visit(node.nodeA).value - visit(node.nodeB).value)
    }

    private fun visit(node: MultiplyNode): NumberValue {
        return NumberValue(visit(node.nodeA).value * visit(node.nodeB).value)
    }

    private fun visit(node: DivideNode): NumberValue {
        try {
            return NumberValue(visit(node.nodeA).value / visit(node.nodeB).value)
        } catch (throwable: Throwable) {
            throw RuntimeException("Thou shall not divide by 0", throwable)
        }
    }

    private fun visit(node: PlusNode): NumberValue {
        return NumberValue(visit(node.node).value)
    }

    private fun visit(node: MinusNode): NumberValue {
        return NumberValue(visit(node.node).value * -1)
    }
}
