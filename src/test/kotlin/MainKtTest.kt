import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MainKtTest {
    @Test
    fun testMainFileInput() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        main(arrayOf("mytext.txt"))
        assertTrue(outContent.toString().contains("Number of words: 17"))
    }

}