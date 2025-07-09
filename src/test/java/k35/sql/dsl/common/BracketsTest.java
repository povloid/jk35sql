package k35.sql.dsl.common;

import junit.framework.TestCase;

public class BracketsTest extends TestCase {

    private final Table table1 = Table.of("table1");

    public void testAround() {
        assertEquals(Brackets.around().sql(), "()");
    }

    public void testTestAround() {
        assertEquals(Brackets.around(":a").sql(), "(:a)");
    }

    public void testTestAround1() {
        assertEquals(Brackets.around(":a", ":b", ":c").sql(), "(:a, :b, :c)");
    }

    public void testTestAround2() {
        assertEquals(Brackets.around(table1.get("id")).sql(), "(table1.id)");
    }

    public void testTestAround3() {
        assertEquals(Brackets.around(table1.get("parent_id"), table1.get("id")).sql(), "(table1.parent_id, table1.id)");
    }
}