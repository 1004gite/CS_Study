fun main() {
    readlnOrNull()?.toLongOrNull()?.let { print("${it*it*it}\n3") }
}