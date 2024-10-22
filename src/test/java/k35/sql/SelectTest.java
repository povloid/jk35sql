package k35.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SelectTest {

    final String table = "table1";

    @Test
    public void testGetSql() {
        assertEquals("select * from table1", Select.select().from(table).buildSql());
        assertEquals("select id from table1", Select.select("id").from(table).buildSql());
        assertEquals("select id, name from table1",
                Select.select("id", "name").from(table).buildSql());

    }

    @Test
    public void testJoin() {
        assertEquals("select * from table1 a left join table2 b on b.a_id = a.id",
                Select.select().from("table1 a")
                        .leftJoin("table2 b").on(Condition.expression("b.a_id = a.id"))
                        .buildSql());
    }

    @Test
    public void testGroupBy() {
        assertEquals("select id, count(*) from table1 group by id",
                Select.select("id", "count(*)").from(table).groupBy("id").buildSql());

        assertEquals("select id, name, count(*) from table1 group by id, name",
                Select.select("id", "name", "count(*)").from(table).groupBy("id", "name")
                        .buildSql());
    }

    @Test
    public void testLimit() {
        assertEquals("select id from table1 limit :limit", Select.select("id").from(table).limit(":limit").buildSql());
    }

    @Test
    public void testOffset() {
        assertEquals("select id from table1 offset :offset",
                Select.select("id").from(table).offset(":offset").buildSql());
    }

    @Test
    public void testOrderBy() {
        assertEquals("select id, count(*) from table1 order by id",
                Select.select("id", "count(*)").from(table).orderBy("id").buildSql());

        assertEquals("select id, name, count(*) as cnt from table1 order by id, name",
                Select.select("id", "name", "count(*) as cnt").from(table).orderBy("id", "name")
                        .buildSql());
    }

    @Test
    public void testWhere() {
        assertEquals("select id, name from table1 where id > 100",
                Select.select("id", "name").from(table).where(Condition.expression("id > 100"))
                        .buildSql());

        assertEquals("select id, name from table1 where id > 100",
                Select.select("id", "name").from(table).where("id > 100")
                        .buildSql());
    }

    @Test
    public void testAsc() {
        assertEquals("select id, count(*) from table1",
                Select.select("id", "count(*)").from(table).asc().buildSql());
        assertEquals("select id, count(*) from table1 order by id asc",
                Select.select("id", "count(*)").from(table).orderBy("id").asc().buildSql());

    }

    @Test
    public void testDesc() {
        assertEquals("select id, count(*) from table1",
                Select.select("id", "count(*)").from(table).desc().buildSql());
        assertEquals("select id, count(*) from table1 order by id desc",
                Select.select("id", "count(*)").from(table).orderBy("id").desc().buildSql());
    }
}
