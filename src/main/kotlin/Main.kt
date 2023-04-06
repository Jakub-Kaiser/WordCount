fun main(args: Array<String>) {
    val text = if (args.isNotEmpty()) {
        readFileToString(args[0])
    } else {
        print("Enter text: ")
        readln()
    }
    println("Number of words: ${countWords(text, "stopwords.txt")}")
}

fun countWords(text: String, stopWordsFile: String): Int {
    if (text == "") return 0
    val words = "\\b[a-zA-Z]+\\b".toRegex().findAll(text).map { it.value }
    val stopWords = object {}.javaClass.getResourceAsStream(stopWordsFile)?.bufferedReader()?.readLines()
    return stopWords?.let {
        words.filter { word -> !it.contains(word.lowercase()) }.count()
    } ?: words.count()
}

fun readFileToString(fileName: String): String {
    return object {}.javaClass.getResource("/$fileName")?.readText() ?: ""
}