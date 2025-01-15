package k35.sql.dsl.dml;

import k35.sql.dsl.common.Predicate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateTest {
    @Test
    public void testReturning() {
        assertEquals("update table1 set a = :a, b = :b, c = :c returning id",
                Update.update("table1").setValue("a", ":a").setValue("b", ":b").setValue("c", ":c").returning("id")
                        .sql());
    }

    @Test
    public void testSetValue() {
        assertEquals("update table1 set a = :a, b = :b, c = :c",
                Update.update("table1")
                        .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .sql());

        assertEquals("update table1 set a = ?, b = ?, c = ?",
                Update.update("table1")
                        .setValue("a", "?").setValue("b", "?").setValue("c", "?")
                        .sql());

    }

    @Test
    public void testWhere() {
        assertEquals("update table1 set a = :a, b = :b, c = :c where id > :line",
                Update.update("table1")
                        .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .where(Predicate.of("id > :line"))
                        .sql());

        assertEquals("update table1 set a = :a, b = :b, c = :c where id > :line",
                Update.update("table1")
                        .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .where("id > :line")
                        .sql());

        assertEquals("update table1 set a = :a, b = :b, c = :c",
                Update.update("table1")
                        .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .where(" ")
                        .sql());
    }
}
