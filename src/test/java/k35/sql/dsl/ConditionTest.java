package k35.sql.dsl;

import static org.junit.Assert.assertEquals;

import java.util.function.UnaryOperator;

import org.junit.Test;

import k35.sql.dsl.common.Condition;

public class ConditionTest {

	@Test
	public void testExpression() {
		assertEquals("status = 'success'", Condition.expression("status = 'success'").buildSql());
	}

	@Test
	public void testAnd() {
		assertEquals("a < 1 and b > 100", Condition.expression("a < 1").and("b > 100").buildSql());

		final var c1 = Condition.expression("c = 1").and("d = 2");

		assertEquals("a < 1 and b > 100 and (c = 1 and d = 2)",
				Condition.expression("a < 1").and("b > 100").and(c1).buildSql());
	}

	@Test
	public void testOr() {
		assertEquals("a < 1 or b > 100", Condition.expression("a < 1").or("b > 100").buildSql());

		final var c1 = Condition.expression("c = 1").and("d = 2");

		assertEquals("a < 1 or b > 100 or (c = 1 and d = 2)",
				Condition.expression("a < 1").or("b > 100").or(c1).buildSql());
	}

	@Test
	public void testMap() {

		final var c1 = Condition.expression("c = 1").and("d = 2");

		final UnaryOperator<Condition> fn = c -> c.and(c1);

		assertEquals("a < 1 or b > 100 and (c = 1 and d = 2)",
				Condition.expression("a < 1").or("b > 100").map(fn).buildSql());
	}

	@Test
	public void testAll() {
		assertEquals("a < 1 or b > 100 and status = 'success'",
				Condition.expression("a < 1").or("b > 100").and("status = 'success'").buildSql());
	}
}
