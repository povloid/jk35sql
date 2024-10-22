package k35.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InsertTest {

    @Test
    public void testValue() {
        assertEquals("insert into table1 ( \"name\", b, c ) values ( :name, :bb, :cc )",
                Insert.into("table1").value("\"name\"", ":name").value("b", ":bb").value("c", ":cc").buildSql());

        assertEquals("insert into table1 ( \"name\", b, c ) values ( ?, ?, ? )",
                Insert.into("table1")
                        .value(SimpleParameter.byField("\"name\""))
                        .value(SimpleParameter.byField("b"))
                        .value(SimpleParameter.byField("c"))
                        .buildSql());

        assertEquals("insert into table1 ( \"name\", b, c ) values ( :name, :b, :c )",
                Insert.into("table1")
                        .value(NamedParameter.byField("\"name\"").as("name"))
                        .value(NamedParameter.byField("b"))
                        .value(NamedParameter.byField("c"))
                        .buildSql());

    }

    @Test
    public void testReturning() {
        assertEquals("insert into table1 ( a, b, c ) values ( ?, ?, ? ) returning id",
                Insert.into("table1").value("a", "?").value("b", "?").value("c", "?").returning("id").buildSql());

        assertEquals("insert into table1 ( a, b, c ) values ( ?, ?, ? ) returning *",
                Insert.into("table1").value("a", "?").value("b", "?").value("c", "?").returning("*").buildSql());
    }
}
