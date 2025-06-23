package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SQL TableSimple...
 */
public final class TableSimple implements Table {

    public static final class Expression implements SqlBuilder {

        private record Field(String name, Optional<String> as) {
        }

        private final TableSimple table;

        private final List<Field> fields;

        private Expression(TableSimple table, List<Field> fields) {
            super();
            this.table = table;
            this.fields = fields;
        }

        public static Expression of(TableSimple table) {
            return new Expression(table, new ArrayList<>());
        }

        public Expression get(String field, Optional<String> as) {
            final var fields = new ArrayList<>(this.fields);
            fields.add(new Field(field, as));
            return new Expression(this.table, fields);
        }

        public Expression getAll() {
            return get("*", Optional.empty());
        }

        public Expression get(String field) {
            return get(field, Optional.empty());
        }

        public Expression get(String field, String as) {
            return get(field, Optional.ofNullable(as));
        }

        @Override
        public String sql() {
            return this.fields.stream()
                    .map(v -> this.table.sql() + "." + v.name() + v.as().map(a -> " as " + a).orElse(""))
                    .collect(Collectors.joining(", "));
        }

    }

    private final String table;

    private TableSimple(String table) {
        this.table = table;
    }

    public static TableSimple of(String table) {
        return new TableSimple(table);
    }

    public Expression get(String field, Optional<String> as) {
        return Expression.of(this).get(field, as);
    }

    public Expression getAll() {
        return Expression.of(this).get("*", Optional.empty());
    }

    public Expression get(String field) {
        return Expression.of(this).get(field, Optional.empty());
    }

    public Expression get(String field, String as) {
        return Expression.of(this).get(field, Optional.ofNullable(as));
    }

    @Override
    public String sql() {
        return this.table;
    }

}
