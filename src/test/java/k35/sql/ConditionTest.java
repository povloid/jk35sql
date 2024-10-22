package k35.sql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionTest {

    @Test
    public void testExpression() {
        assertEquals("status = 'success'",
                Condition.expression("status = 'success'").buildSql());
    }

    @Test
    public void testAnd() {
        assertEquals("( a < 1 and b > 100 )", Condition.and(
                Condition.expression("a < 1"),
                Condition.expression("b > 100")).buildSql());
    }

    @Test
    public void testOr() {
        assertEquals("( a < 1 or b > 100 )", Condition.or(
                Condition.expression("a < 1"),
                Condition.expression("b > 100")).buildSql());
    }

    @Test
    public void testAll() {
        assertEquals("( a < 1 or ( b > 100 and status = 'success' ) )", Condition.or(
                Condition.expression("a < 1"),
                Condition.and(
                        Condition.expression("b > 100"),
                        Condition.expression("status = 'success'")))
                .buildSql());
    }
}
