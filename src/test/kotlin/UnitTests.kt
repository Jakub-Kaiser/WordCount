import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.charset.StandardCharsets
import java.util.*


class UnitTests {

    @ParameterizedTest
    @CsvSource(
        "Two words; 2",
        "With double  space??; 3",
        "With one-dash; 3",
        "With one\ttabulator; 3",
        "Process finished with exit code 0; 5",
        "The two words; 2",
        "On and off are ignored; 3",
        "Ju5t two words; 2",
        "On3 word ; 1",
        "First sentence. Second sentence.; 4",
        delimiter = ';'
    )
    fun testCountWords(input: String, expected: Int) {
        val result = countWords(input, "stopwords.txt")
        assertEquals(expected, result)
    }

    @Test
    fun testCountWordsEmptyString() {
        val result = countWords("","stopwords.txt")
        val expected = 0
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test reading file with 3 lines, 1 word each")
    fun testReadFileThreeLines() {
        val result = readFileToString("threeLines.txt")
        assertEquals("One\r\nTwo\r\nThree", result)
    }

    @Test
    @DisplayName("Test reading non-existent file")
    fun testReadEmptyFile() {
        val result = readFileToString("nonExistent")
        assertEquals("", result)
    }

    @Test
    fun countWordsWithoutStopWords() {
        val result = countWords("The three words","non-existent")
        assertEquals(3, result)
    }

}