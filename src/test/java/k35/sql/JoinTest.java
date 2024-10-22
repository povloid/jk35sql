package k35.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JoinTest {

    @Test
    public void testRightJoin() {
        assertEquals("right join table1", Join.rightJoin("table1").buildSql());
    }

    @Test
    public void testFullJoin() {
        assertEquals("full join table1", Join.fullJoin("table1").buildSql());
    }

    @Test
    public void testInnerJoin() {
        assertEquals("inner join table1", Join.innerJoin("table1").buildSql());
    }

    @Test
    public void testLeftJoin() {
        assertEquals("left join table1", Join.leftJoin("table1").buildSql());
    }

    @Test
    public void testOn() {
        assertEquals("full join table1 b on a.id = b.a_id",
                Join.fullJoin("table1 b").on(Condition.expression("a.id = b.a_id")).buildSql());

        assertEquals("full join table1 b on a.id = b.a_id",
                Join.fullJoin("table1 b").on("a.id = b.a_id").buildSql());
    }

}
