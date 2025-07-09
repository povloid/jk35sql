package k35.sql.dsl.common;

import junit.framework.TestCase;

public class PredicateTest extends TestCase {

    final Table table1 = Table.of("table1");
    final Table table2 = Table.of("table2");
    final Table table3 = Table.of("table3");

    public void testOf() {
        assertEquals(Predicate.of().sql(), "");
    }

    public void testTestOf() {
        assertEquals(Predicate.of("true").sql(), "true");
    }

    public void testTestOf1() {
        assertEquals(Predicate.of(table1.get("id")).sql(), "table1.id");
    }

    public void testMap() {
        assertEquals(Predicate.of(table1.get("id")).map(o -> o.equal(":id")).sql(), "table1.id = :id");
    }

    public void testEqual() {
        assertEquals(Predicate.of(table1.get("id")).equal(":id").sql(), "table1.id = :id");
    }

    public void testTestEqualB() {
        assertEquals(Predicate.of(table1.get("id")).equal(table2.get("table1_id")).sql(), "table1.id = table2.table1_id");
    }

    public void testNotEqual() {
        assertEquals(Predicate.of(table1.get("id")).notEqual(":id").sql(), "table1.id <> :id");
    }

    public void testNotEqualB() {
        assertEquals(Predicate.of(table1.get("id")).notEqual(table2.get("table1_id")).sql(), "table1.id <> table2.table1_id");
    }

    public void testLessThen() {
        assertEquals(Predicate.of(table1.get("id")).lessThen(":id").sql(), "table1.id < :id");
    }

    public void testLessThenB() {
        assertEquals(Predicate.of(table1.get("id")).lessThen(table2.get("table1_id")).sql(), "table1.id < table2.table1_id");
    }

    public void testLessOrEqual() {
        assertEquals(Predicate.of(table1.get("id")).lessOrEqual(":id").sql(), "table1.id <= :id");
    }

    public void testLessOrEqualB() {
        assertEquals(Predicate.of(table1.get("id")).lessOrEqual(table2.get("table1_id")).sql(), "table1.id <= table2.table1_id");
    }

    public void testGreaterThan() {
        assertEquals(Predicate.of(table1.get("id")).greaterThan(":id").sql(), "table1.id > :id");
    }

    public void testGreaterThanB() {
        assertEquals(Predicate.of(table1.get("id")).greaterThan(table2.get("table1_id")).sql(), "table1.id > table2.table1_id");
    }

    public void testGreaterOrEqual() {
        assertEquals(Predicate.of(table1.get("id")).greaterOrEqual(":id").sql(), "table1.id >= :id");
    }

    public void testGreaterOrEqualB() {
        assertEquals(Predicate.of(table1.get("id")).greaterOrEqual(table2.get("table1_id")).sql(), "table1.id >= table2.table1_id");
    }

    public void testBetween() {
        assertEquals(Predicate.of(table1.get("id")).between(":a", ":b").sql(), "table1.id between :a and :b");
    }

    public void testBetweenB() {
        assertEquals(Predicate.of(table1.get("id")).between(table2.get("begin"), table2.get("end")).sql(), "table1.id between table2.begin and table2.end");
    }

    public void testIs() {
        assertEquals(Predicate.of(table1.get("id")).is(":a").sql(), "table1.id is :a");
    }

    public void testIsB() {
        assertEquals(Predicate.of(table1.get("id")).is(table2.get("a")).sql(), "table1.id is table2.a");
    }

    public void testIsNot() {
        assertEquals(Predicate.of(table1.get("id")).isNot(":a").sql(), "table1.id is not :a");
    }

    public void testTestIsNot() {
        assertEquals(Predicate.of(table1.get("id")).isNot(table2.get("a")).sql(), "table1.id is not table2.a");
    }

    public void testIsTrue() {
        assertEquals(Predicate.of(table1.get("id")).isTrue().sql(), "table1.id is true");
    }

    public void testIsNotTrue() {
        assertEquals(Predicate.of(table1.get("id")).isNotTrue().sql(), "table1.id is not true");
    }

    public void testIsFalse() {
        assertEquals(Predicate.of(table1.get("id")).isFalse().sql(), "table1.id is false");
    }

    public void testIsNotFalse() {
        assertEquals(Predicate.of(table1.get("id")).isNotFalse().sql(), "table1.id is not false");
    }

    public void testIsNull() {
        assertEquals(Predicate.of(table1.get("id")).isNull().sql(), "table1.id is null");
    }

    public void testIsNotNull() {
        assertEquals(Predicate.of(table1.get("id")).isNotNull().sql(), "table1.id is not null");
    }

    public void testIn() {
        assertEquals(Predicate.of(table1.get("id")).in(":ids").sql(), "table1.id in (:ids)");
    }

    public void testInB() {
        assertEquals(Predicate.of(table1.get("id")).in(table2.get("id").sql()).sql(), "table1.id in (table2.id)");
    }

    public void testNotIn() {
        assertEquals(Predicate.of(table1.get("id")).notIn(":ids").sql(), "table1.id not in (:ids)");
    }

    public void testTestNotIn() {
        assertEquals(Predicate.of(table1.get("id")).notIn(table2.get("id").sql()).sql(), "table1.id not in (table2.id)");
    }

    public void testIsUnknown() {
        assertEquals(Predicate.of(table1.get("id")).isUnknown().sql(), "table1.id is unknown");
    }

    public void testIsNotUnknown() {
        assertEquals(Predicate.of(table1.get("id")).isNotUnknown().sql(), "table1.id is not unknown");
    }

    public void testAnd() {
        assertEquals(Predicate.of(table1.get("id")).and().sql(), "table1.id and");
    }

    public void testTestAnd() {
        assertEquals(Predicate.of(table1.get("id")).and("1=1").sql(), "table1.id and 1=1");
    }

    public void testTestAnd1() {
        assertEquals(Predicate.of(table1.get("id")).equal(":id").and(table2.get("id")).equal(":id2").sql(), "table1.id = :id and table2.id = :id2");
        assertEquals(Predicate.of(table1.get("id")).equal(":id").and(Predicate.of(table2.get("id")).equal(":id2")).sql(), "table1.id = :id and table2.id = :id2");
    }

    public void testOr() {
        assertEquals(Predicate.of(table1.get("id")).or().sql(), "table1.id or");
    }

    public void testTestOr() {
        assertEquals(Predicate.of(table1.get("id")).or("1=1").sql(), "table1.id or 1=1");
    }

    public void testTestOr1() {
        assertEquals(Predicate.of(table1.get("id")).equal(":id").or(Predicate.of(table2.get("id")).equal(":id2")).sql(), "table1.id = :id or table2.id = :id2");
    }

    public void testNot() {
        assertEquals(Predicate.of(table1.get("active")).not().sql(), "table1.active not");
    }

    public void testTestNot() {
        assertEquals(Predicate.of(table1.get("active")).not("true").sql(), "table1.active not true");
    }

    public void testTestNot1() {
        assertEquals(Predicate.of(table1.get("active")).not(table2.get("active")).sql(), "table1.active not table2.active");
    }

    public void testLike() {
        assertEquals(Predicate.of(table1.get("name")).like(":name").sql(), "table1.name like :name");
    }

    public void testIlike() {
        assertEquals(Predicate.of(table1.get("name")).ilike(":name").sql(), "table1.name ilike :name");
    }

}