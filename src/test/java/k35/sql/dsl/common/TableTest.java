package k35.sql.dsl.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {

    @Test
    public void test() {

        final var table = Table.of("table");

        assertEquals("table", table.sql());
        assertEquals("table.*", table.get().sql());
        assertEquals("table.*", table.getAll().sql());
        assertEquals("table.a", table.get("a").sql());
        assertEquals("table.a as b", table.get("a", "b").sql());
        assertEquals("table.a as aa, table.b as bb", table.get("a", "aa").get("b", "bb").sql());
        assertEquals("table.*, table.a as aa, table.b as bb", table.get().get("a", "aa").get("b", "bb").sql());
    }

}
