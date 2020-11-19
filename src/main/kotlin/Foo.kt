import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Foo(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Foo>(Foos)

    var validFrom by Foos.validFrom
    var validUntil by Foos.validUntil
}
