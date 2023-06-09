import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

class MainKtTest {
    @ParameterizedTest
    @CsvSource(
        "mytext.txt, 17",
        "non-existent.txt, 0",
        "threeLines.txt, 3",
        "emptyFile.txt, 0",

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
        "With double  space?; 3",
        "With a-dash; 2",
        "With one\ttabulator; 3",
        "Process finished with exit code 0; 6",
        "The two words; 2",
        "On and off are ignored; 3",
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