package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

public class Field implements SqlBuilder {

    private final Table table;
    private final String name;

    private Field(Table table, String name) {
        this.table = table;
        this.name = name;
    }

    public static Field of(Table table, String name) {
        return new Field(table, name);
    }

    public SqlBuilder as(String alias) {
        return this.table.get(name, alias);
    }

    @Override
    public String sql() {
        return this.table.get(this.name).sql();
    }
}
