# ExposedBetweenBug
https://github.com/JetBrains/Exposed/issues/1096

Using
```kotlin
Foo.find {
    CurrentDateTime().between(Foos.validFrom, Foos.validUntil)
}
```
with 
```kotlin
object Foos: IntIdTable() {
    val validFrom = datetime("validFrom")
    val validUntil = datetime("validUntil")
}

class Foo(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Foo>(Foos)

    var validFrom by Foos.validFrom
    var validUntil by Foos.validUntil
}
```
results into a `IllegalStateException`: `Unexpected value: Foos.validFrom of org.jetbrains.exposed.sql.Column`.

# Stacktrace

```
Unexpected value: Foos.validFrom of org.jetbrains.exposed.sql.Column
java.lang.IllegalStateException: Unexpected value: Foos.validFrom of org.jetbrains.exposed.sql.Column
	at org.jetbrains.exposed.sql.java-time.JavaLocalDateTimeColumnType.nonNullValueToString(JavaDateColumnType.kt:89)
	at org.jetbrains.exposed.sql.IColumnType$DefaultImpls.valueToString(ColumnType.kt:53)
	at org.jetbrains.exposed.sql.ColumnType.valueToString(ColumnType.kt:71)
	at org.jetbrains.exposed.sql.QueryBuilder$registerArguments$1.invoke(Expression.kt:72)
	at org.jetbrains.exposed.sql.QueryBuilder.registerArguments(Expression.kt:75)
	at org.jetbrains.exposed.sql.QueryBuilder.registerArgument(Expression.kt:66)
	at org.jetbrains.exposed.sql.QueryParameter$toQueryBuilder$1.invoke(Op.kt:496)
	at org.jetbrains.exposed.sql.QueryParameter$toQueryBuilder$1.invoke(Op.kt:490)
	at org.jetbrains.exposed.sql.QueryBuilder.invoke(Expression.kt:18)
	at org.jetbrains.exposed.sql.QueryParameter.toQueryBuilder(Op.kt:496)
	at org.jetbrains.exposed.sql.QueryBuilder.append(Expression.kt:43)
	at org.jetbrains.exposed.sql.ExpressionKt.append(Expression.kt:96)
	at org.jetbrains.exposed.sql.Between$toQueryBuilder$1.invoke(Op.kt:178)
	at org.jetbrains.exposed.sql.Between$toQueryBuilder$1.invoke(Op.kt:170)
	at org.jetbrains.exposed.sql.QueryBuilder.invoke(Expression.kt:18)
	at org.jetbrains.exposed.sql.Between.toQueryBuilder(Op.kt:178)
	at org.jetbrains.exposed.sql.QueryBuilder.append(Expression.kt:43)
	at org.jetbrains.exposed.sql.QueryBuilder.unaryPlus(Expression.kt:53)
	at org.jetbrains.exposed.sql.Query$prepareSQL$1.invoke(Query.kt:107)
	at org.jetbrains.exposed.sql.Query$prepareSQL$1.invoke(Query.kt:15)
	at org.jetbrains.exposed.sql.QueryBuilder.invoke(Expression.kt:18)
	at org.jetbrains.exposed.sql.Query.prepareSQL(Query.kt:90)
	at org.jetbrains.exposed.sql.Query.arguments(Query.kt:83)
	at org.jetbrains.exposed.sql.Query.arguments(Query.kt:15)
	at org.jetbrains.exposed.sql.statements.Statement.executeIn$exposed_core(Statement.kt:32)
	at org.jetbrains.exposed.sql.Transaction.exec(Transaction.kt:129)
	at org.jetbrains.exposed.sql.Transaction.exec(Transaction.kt:115)
	at org.jetbrains.exposed.sql.Query.iterator(Query.kt:218)
	at org.jetbrains.exposed.sql.IterableExKt$mapLazy$1.iterator(IterableEx.kt:129)
	at kotlin.collections.CollectionsKt___CollectionsKt.toCollection(_Collections.kt:1243)
	at kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(_Collections.kt:1276)
	at kotlin.collections.CollectionsKt___CollectionsKt.toList(_Collections.kt:1267)
	at BetweenTest$testBetween$1.invoke(BetweenTest.kt:25)
	at BetweenTest$testBetween$1.invoke(BetweenTest.kt:9)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt$inTopLevelTransaction$1.invoke(ThreadLocalTransactionManager.kt:170)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt$inTopLevelTransaction$2.invoke(ThreadLocalTransactionManager.kt:211)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.keepAndRestoreTransactionRefAfterRun(ThreadLocalTransactionManager.kt:219)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.inTopLevelTransaction(ThreadLocalTransactionManager.kt:210)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt$transaction$1.invoke(ThreadLocalTransactionManager.kt:148)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.keepAndRestoreTransactionRefAfterRun(ThreadLocalTransactionManager.kt:219)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.transaction(ThreadLocalTransactionManager.kt:120)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.transaction(ThreadLocalTransactionManager.kt:118)
	at org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.transaction$default(ThreadLocalTransactionManager.kt:117)
	at BetweenTest.testBetween(BetweenTest.kt:13)

