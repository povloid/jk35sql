package k35.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeleteTest {
    @Test
    public void testFrom() {
        assertEquals("delete from table1", Delete.from("table1").buildSql());
    }

    @Test
    public void testWhere() {
        assertEquals("delete from table1 where status = :deleted",
                Delete.from("table1").where("status = :deleted").buildSql());

        assertEquals("delete from table1 where status = :deleted",
                Delete.from("table1").where(Condition.expression("status = :deleted")).buildSql());
    }

    @Test
    public void testReturning() {
        assertEquals("delete from table1 returning *", Delete.from("table1").returning("*").buildSql());
    }
}
