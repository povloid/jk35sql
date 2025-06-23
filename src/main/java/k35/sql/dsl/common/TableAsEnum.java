package k35.sql.dsl.common;

public interface TableAsEnum extends Table {

    String table();

    String field();

    default String all() {
        return table() + ".*";
    }

    default String as(String alias) {
        return table() + "." + field() + " as " + alias;
    }

}
