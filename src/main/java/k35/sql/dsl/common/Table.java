package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SQL Table...
 */
public class Table implements SqlBuilder {

    public static final class FieldExpression implements SqlBuilder {

        private final Table table;
        private final List<String> terms;

        private FieldExpression(Table table) {
            super();
            this.table = table;
            this.terms = new ArrayList<>();
        }

        private FieldExpression(Table table, List<String> terms) {
            super();
            this.table = table;
            this.terms = terms;
        }

        public static FieldExpression of(Table table) {
            return new FieldExpression(table);
        }

        public FieldExpression get() {
            return new FieldExpression(this.table,
                    Stream.concat(this.terms.stream(),
                                    List.of(this.table.sql() + ".*").stream())
                            .toList());
        }

        public FieldExpression get(String field) {
            return new FieldExpression(this.table,
                    Stream.concat(this.terms.stream(),
                                    List.of(this.table.sql() + "." + field).stream())
                            .toList());
        }

        public FieldExpression get(String field, String alias) {
            return new FieldExpression(this.table,
                    Stream.concat(this.terms.stream(),
                                    List.of(this.table.sql() + "." + field + " as " + alias).stream())
                            .toList());
        }

        @Override
        public String sql() {
            return this.terms.stream().collect(Collectors.joining(", "));
        }
    }

    protected final String table;

    protected Table(String table) {
        this.table = table;
    }

    public static Table of(String table) {
        return new Table(table);
    }

    public FieldExpression get(String field) {
        return FieldExpression.of(this).get(field);
    }

    public FieldExpression get(String field, String alias) {
        return FieldExpression.of(this).get(field, alias);
    }

    public FieldExpression get() {
        return FieldExpression.of(this).get();
    }

    public FieldExpression getAll() {
        return FieldExpression.of(this).get();
    }

    @Override
    public String sql() {
        return this.table;
    }

}
