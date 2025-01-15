package k35.sql.dsl.dml;

import k35.sql.dsl.DSL;
import k35.sql.dsl.common.Predicate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectTest {

    final String table = "table1";

    @Test
    public void testGetSql() {
        assertEquals("select * from table1", DSL.select().from(table).sql());
        assertEquals("select id from table1", DSL.select("id").from(table).sql());
        assertEquals("select id, name from table1",
                DSL.select("id, name").from(table).sql());
    }

    @Test
    public void testJoin() {
        assertEquals("select * from table1 a left join table2 b on b.a_id = a.id",
                DSL.select().from("table1 a")
                        .leftJoin("table2 b").on(Predicate.of("b.a_id = a.id").sql())
                        .sql());

        assertEquals("select * from table1 a left join table2 b on b.a_id = a.id",
                DSL.select().from("table1 a")
                        .leftJoin("table2 b").on("b.a_id = a.id")
                        .sql());
    }

    @Test
    public void testGroupBy() {
        assertEquals("select id, count(*) from table1 group by id",
                DSL.select("id, count(*)").from(table).groupBy("id").sql());

        assertEquals("select id, name, count(*) from table1 group by id, name",
                DSL.select("id, name, count(*)").from(table).groupBy("id", "name")
                        .sql());
    }

    @Test
    public void testLimit() {
        assertEquals("select id from table1 limit :limit", DSL.select("id").from(table).limit(":limit").sql());
    }

    @Test
    public void testOffset() {
        assertEquals("select id from table1 offset :offset",
                DSL.select("id").from(table).offset(":offset").sql());
    }

    @Test
    public void testOrderBy() {
        assertEquals("select id, count(*) from table1 order by id",
                DSL.select("id, count(*)").from(table).orderBy("id").sql());

        assertEquals("select id, name, count(*) as cnt from table1 order by id, name",
                DSL.select("id, name, count(*) as cnt").from(table).orderBy("id", "name")
                        .sql());
    }

    @Test
    public void testWhere() {


        assertEquals("select id, name from table1",
                DSL.select("id, name").from(table).where(" ")
                        .sql());

        assertEquals("select id, name from table1",
                DSL.select("id, name").from(table).where(Predicate.of(" "))
                        .sql());

        assertEquals("select id, name from table1 where id > 100",
                DSL.select("id, name").from(table).where("id > 100")
                        .sql());

        assertEquals("select id, name from table1 where id > 100",
                DSL.select("id, name").from(table).where(Predicate.of("id > 100"))
                        .sql());
    }

    @Test
    public void testAsc() {
        assertEquals("select id, count(*) from table1",
                DSL.select("id, count(*)").from(table).asc().sql());
        assertEquals("select id, count(*) from table1 order by id asc",
                DSL.select("id, count(*)").from(table).orderBy("id").asc().sql());

    }

    @Test
    public void testDesc() {
        assertEquals("select id, count(*) from table1",
                DSL.select("id, count(*)").from(table).desc().sql());
        assertEquals("select id, count(*) from table1 order by id desc",
                DSL.select("id, count(*)").from(table).orderBy("id").desc().sql());
    }

    @Test
    public void testMap() {
        assertEquals("select id, count(*) from table1 where id < 5",
                DSL.select("id, count(*)")
                        .from(table)
                        .map(s -> s.where("id < 5"))
                        .sql());
    }
}
