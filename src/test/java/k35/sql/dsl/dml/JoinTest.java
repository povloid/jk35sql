package k35.sql.dsl.dml;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import k35.sql.dsl.common.Predicate;

public class JoinTest {

	@Test
	public void testRightJoin() {
		assertEquals("right join table1", Join.rightJoin("table1").sql());
	}

	@Test
	public void testFullJoin() {
		assertEquals("full join table1", Join.fullJoin("table1").sql());
	}

	@Test
	public void testInnerJoin() {
		assertEquals("inner join table1", Join.innerJoin("table1").sql());
	}

	@Test
	public void testLeftJoin() {
		assertEquals("left join table1", Join.leftJoin("table1").sql());
	}

	@Test
	public void testOn() {
		assertEquals("full join table1 b on a.id = b.a_id",
				Join.fullJoin("table1 b").on(Predicate.of("a.id = b.a_id").sql()).sql());

		assertEquals("full join table1 b on a.id = b.a_id",
				Join.fullJoin("table1 b").on("a.id = b.a_id").sql());
	}

}
