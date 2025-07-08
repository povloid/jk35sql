package k35.sql.dsl;

import k35.sql.dsl.common.Table;
import k35.sql.dsl.dml.Delete;
import k35.sql.dsl.dml.Insert;
import k35.sql.dsl.dml.Select;
import k35.sql.dsl.dml.Update;
import k35.sql.dsl.interfaces.SqlBuilder;

public final class DSL {

    private DSL() {

    }

    public static Select select() {
        return Select.all();
    }

    public static Select select(String expression) {
        return Select.select(expression);
    }

    public static Select select(SqlBuilder... builders) {
        return Select.select(builders);
    }

    public static Select selectCount() {
        return Select.count();
    }

    public static final class InsertBegin {
        public Insert into(String table) {
            return Insert.into(table);
        }

        public Insert into(Table table) {
            return Insert.into(table.sql());
        }
    }

    public static InsertBegin insert() {
        return new InsertBegin();
    }

    public static Update update(String table) {
        return Update.update(table);
    }

    public static Update update(Table table) {
        return Update.update(table.sql());
    }

    public static final class DeletgeBegin {
        public Delete from(String table) {
            return Delete.from(table);
        }

        public Delete from(Table table) {
            return Delete.from(table.sql());
        }
    }

    public static DeletgeBegin delete() {
        return new DeletgeBegin();
    }

}
