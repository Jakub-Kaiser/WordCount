fun main(args: Array<String>) {
        val text = if (args.isNotEmpty()) {
            readFile(args[0])
        } else {
            print("Enter text: ")
            readln()
        }
        println("Number of words: ${countWords(text)}")
}

fun countWords(text: String): Int {
    if (text == "") return 0
    val words = text.split("\\s+".toRegex())
    val stopWords = object {}.javaClass.getResourceAsStream("stopwords.txt")?.bufferedReader()?.readLines()
    return stopWords?.let {
        words.filter { word -> !it.contains(word.lowercase()) }.size
    } ?: words.size
}

fun readFile(fileName: String): String {
    return object {}.javaClass.getResource("/$fileName")?.readText() ?: ""
}