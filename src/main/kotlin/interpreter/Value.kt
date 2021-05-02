package interpreter

sealed class Value

data class NumberValue(val value: Double) : Value() {
    override fun toString(): String {
        return value.toString()
    }
}
