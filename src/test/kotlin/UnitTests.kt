import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class UnitTests {

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
    fun testCountWords(input: String, expected: Int) {
        val result = countWords(input)
        assertEquals(expected, result)
    }

    @Test
    fun testCountWordsEmptyString() {
        val result = countWords("")
        val expected = 0
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Test reading file with 3 lines, 1 word each")
    fun testReadFileThreeLines() {
        val result = readFile("threeLines.txt")
        assertEquals("One\r\nTwo\r\nThree", result)
    }

    @Test
    @DisplayName("Test reading non-existent file")
    fun testReadEmptyFile() {
        val result = readFile("nonExistent")
        assertEquals("", result)
    }

}