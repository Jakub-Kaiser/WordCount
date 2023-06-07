import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.*

class MainKtTest {

    @ParameterizedTest
    @CsvSource(
        "../../test/resources/twoWords.txt, 2",
        "../../test/resources/twoWords.txt, 2",
        "../../test/resources/mytext.txt, 16",
        "../../test/resources/non-existent.txt, 0",
        "../../test/resources/threeLines.txt, 3",
        "../../test/resources/emptyFile.txt, 0",
    )
    fun testMainFileInput(fileName: String, expected: Int) {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        main(arrayOf(fileName))
        assertTrue(outContent.toString().contains("Number of words: $expected"))
    }

    @ParameterizedTest
    @CsvSource(
        "Two words; 2",
        "With double  space??; 3",
        "With one-dash; 3",
        "With a-dash; 2",
        "With one\ttabulator; 3",
        "Process finished with exit code 0; 5",
        "The two words; 2",
        "On and off are ignored; 3",
        "Ju5t two words; 2",
        "On3 word ; 1",
        "First sentence. Second sentence.; 4",
        delimiter = ';'
    )
    fun testMainUserInput(input: String, expected: Int) {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val inputStream: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)
        main(emptyArray())
        assertTrue(outContent.toString().contains("Number of words: $expected"))
    }

}