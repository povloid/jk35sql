package k35.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SelectSqlBuiderTest {

    final String table = "table1";

    @Test
    public void testBuild() {

        assertEquals("select * from table1", SelectSqlBuider.select().from(table).build());
        assertEquals("select id from table1", SelectSqlBuider.select("id").from(table).build());
        assertEquals("select id, name from table1",
                SelectSqlBuider.select("id", "name").from(table).build());

    }

    @Test
    public void testGroupBy() {
        assertEquals("select id, count(*) from table1 group by id",
                SelectSqlBuider.select("id", "count(*)").from(table).groupBy("id").build());

        assertEquals("select id, name, count(*) from table1 group by id, name",
                SelectSqlBuider.select("id", "name", "count(*)").from(table).groupBy("id", "name")
                        .build());
    }

    @Test
    public void testLimit() {
        assertEquals("select id from table1 limit 10", SelectSqlBuider.select("id").from(table).limit(10).build());
    }

    @Test
    public void testOffset() {
        assertEquals("select id from table1 offset 10", SelectSqlBuider.select("id").from(table).offset(10).build());
    }

    @Test
    public void testOrderBy() {
        assertEquals("select id, count(*) from table1 order by id",
                SelectSqlBuider.select("id", "count(*)").from(table).orderBy("id").build());

        assertEquals("select id, name, count(*) from table1 order by id, name",
                SelectSqlBuider.select("id", "name", "count(*)").from(table).orderBy("id", "name")
                        .build());
    }

    @Test
    public void testWhere() {
        assertEquals("select id, name from table1 where id > 100",
                SelectSqlBuider.select("id", "name").from(table).where(Condition.expression("id > 100"))
                        .build());
    }
}
