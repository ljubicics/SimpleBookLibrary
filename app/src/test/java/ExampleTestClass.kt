import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleTestClass {

    @Test
    fun testExample() {
        val expected = 4
        val actual = 2 + 2
        assertEquals(expected, actual)
    }
}
