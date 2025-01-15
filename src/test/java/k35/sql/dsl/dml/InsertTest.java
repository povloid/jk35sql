package k35.sql.dsl.dml;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InsertTest {

	@Test
	public void testValue() {
		assertEquals("insert into table1 ( \"name\", b, c ) values ( :name, :bb, :cc )",
				Insert.into("table1").value("\"name\"", ":name").value("b", ":bb").value("c", ":cc").sql());

	}

	@Test
	public void testReturning() {
		assertEquals("insert into table1 ( a, b, c ) values ( ?, ?, ? ) returning id",
				Insert.into("table1").value("a", "?").value("b", "?").value("c", "?").returning("id").sql());

		assertEquals("insert into table1 ( a, b, c ) values ( ?, ?, ? ) returning *",
				Insert.into("table1").value("a", "?").value("b", "?").value("c", "?").returning("*").sql());
	}
}
