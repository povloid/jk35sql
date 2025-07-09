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

    class Table1 extends Table {

        private Table1() {
            super("table1");
        }

        public Field id = Field.of(this, "id");
        public Field name = Field.of(this, "name");
        public Field description = Field.of(this, "description");

        public Field[] fields = {id, name, description};

    }

    final Table1 table1 = new Table1();

    @Test
    public void testTable1() {

        assertEquals(table1.sql(), "table1");
        assertEquals(table1.id.sql(), "table1.id");
        assertEquals(table1.name.sql(), "table1.name");
        assertEquals(table1.description.sql(), "table1.description");

        assertEquals(table1.name.as("name_1").sql(), "table1.name as name_1");
    }

}
