package k35.sql.dsl.common;

public class Table1 extends Table {

    public Field id = Field.of(this, "id");
    public Field name = Field.of(this, "name");
    public Field description = Field.of(this, "description");

    private Table1() {
        super("table1");
    }

    Table of() {
        return new Table1();
    }

}
