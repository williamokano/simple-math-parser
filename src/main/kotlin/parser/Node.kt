package parser

sealed class Node

class NumberNode(val value: Double) : Node() {
    override fun toString(): String {
        return value.toString()
    }
}

class AddNode(val nodeA: Node, val nodeB: Node) : Node() {
    override fun toString(): String {
        return "($nodeA + $nodeB)"
    }
}

class SubtractNode(val nodeA: Node, val nodeB: Node) : Node() {
    override fun toString(): String {
        return "($nodeA - $nodeB)"
    }
}

class MultiplyNode(val nodeA: Node, val nodeB: Node) : Node() {
    override fun toString(): String {
        return "($nodeA * $nodeB)"
    }
}

class DivideNode(val nodeA: Node, val nodeB: Node) : Node() {
    override fun toString(): String {
        return "($nodeA / $nodeB)"
    }
}

class PlusNode(val node: Node) : Node() {
    override fun toString(): String {
        return "(+$node)"
    }
}

class MinusNode(val node: Node) : Node() {
    override fun toString(): String {
        return "(-$node)"
    }
}
