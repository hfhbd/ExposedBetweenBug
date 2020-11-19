
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object Foos: IntIdTable() {
    val validFrom = datetime("validFrom")
    val validUntil = datetime("validUntil")
}
