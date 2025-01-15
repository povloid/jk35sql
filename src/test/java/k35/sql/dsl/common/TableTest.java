package k35.sql.dsl.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TableTest {

	@Test
	public void test() {

		final var table1 = Table.of("table");

		assertEquals("table", table1.sql());

		assertEquals("table.*", table1.getAll().sql());
		assertEquals("table.a", table1.get("a").sql());
		assertEquals("table.a, table.b", table1.get("a").get("b").sql());
		assertEquals("table.a, table.b as c", table1.get("a").get("b", "c").sql());

	}

}
