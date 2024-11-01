package k35.sql.dsl.dml;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import k35.sql.dsl.common.Condition;
import k35.sql.dsl.common.NamedParameter;
import k35.sql.dsl.common.SimpleParameter;

public class UpdateTest {
    @Test
    public void testReturning() {
        assertEquals("update table1 set a = :a, b = :b, c = :c returning id",
                Update.update("table1").setValue("a", ":a").setValue("b", ":b").setValue("c", ":c").returning("id")
                        .buildSql());
    }
      
    @Test
    public void testSetValue() {
        assertEquals("update table1 set a = :a, b = :b, c = :c",
                Update.update("table1")
                         .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .buildSql());
 
        assertEquals("update table1 set a = ?, b = ?, c = ?",
                Update.update("table1")
                        .setValue("a", "?").setValue("b", "?").setValue("c", "?")
                        .buildSql());

        assertEquals("update table1 set a = ?, b = ?, c = ?",
                Update.update("table1")
                        .setValue(SimpleParameter.byField("a"))
                        .setValue(SimpleParameter.byField("b"))
                        .setValue(SimpleParameter.byField("c"))
                        .buildSql());

        assertEquals("update table1 set a = :a, b = :b, c = :cc",
                Update.update("table1")
                        .setValue(NamedParameter.byField("a"))
                        .setValue(NamedParameter.byField("b"))
                        .setValue(NamedParameter.byField("c").as(":cc"))
                        .buildSql());
    }

    @Test
    public void testWhere() {
        assertEquals("update table1 set a = :a, b = :b, c = :c where id > :line",
                Update.update("table1")
                        .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .where(Condition.expression("id > :line"))
                        .buildSql());

        assertEquals("update table1 set a = :a, b = :b, c = :c where id > :line",
                Update.update("table1")
                        .setValue("a", ":a").setValue("b", ":b").setValue("c", ":c")
                        .where("id > :line")
                        .buildSql());
    }
}
