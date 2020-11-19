import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.`java-time`.CurrentDateTime
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class BetweenTest {
    @Test
    fun testBetween() {
        Database.connect("jdbc:h2:mem:test", "org.h2.Driver")
        transaction {
            SchemaUtils.create(Foos)

            val now = LocalDateTime.now()
            val expectedFoo = Foo.new {
                validFrom = now.minusDays(1)
                validUntil = now.plusDays(1)
            }
            // Use SQL (H2)
            // select * from Foos where current_date between Foos.validFrom and Foos.validUntil
            val foundFoo = Foo.find {
                CurrentDateTime().between(Foos.validFrom, Foos.validUntil)
            }.first()

            assertEquals(expectedFoo.id.value, foundFoo.id.value)
            assertEquals(expectedFoo.validFrom, foundFoo.validFrom)
            assertEquals(expectedFoo.validUntil, foundFoo.validUntil)
        }
    }
}
