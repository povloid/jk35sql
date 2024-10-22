package k35.sql;

public final class DSL {

    private DSL() {

    }

    public static Select select(String... fields) {
        return Select.select(fields);
    }

    public static final class InsertBegin {
        public Insert into(String table) {
            return Insert.into(table);
        }
    }

    public static InsertBegin insert() {
        return new InsertBegin();
    }

    public static Update update(String table) {
        return Update.update(table);
    }

    public static final class DeletgeBegin {
        public Delete from(String table) {
            return Delete.from(table);
        }
    }

    public static DeletgeBegin delete() {
        return new DeletgeBegin();
    }

}
