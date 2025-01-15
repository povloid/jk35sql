package k35.sql.dsl.dml;

import k35.sql.dsl.common.Predicate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteTest {
    @Test
    public void testFrom() {
        assertEquals("delete from table1", Delete.from("table1").sql());
    }

    @Test
    public void testWhere() {
        assertEquals("delete from table1 where status = :deleted",
                Delete.from("table1").where("status = :deleted").sql());

        assertEquals("delete from table1",
                Delete.from("table1").where(" ").sql());

        assertEquals("delete from table1 where status = :deleted",
                Delete.from("table1").where(Predicate.of("status = :deleted").sql()).sql());
    }

    @Test
    public void testReturning() {
        assertEquals("delete from table1 returning *", Delete.from("table1").returning("*").sql());
    }
}
