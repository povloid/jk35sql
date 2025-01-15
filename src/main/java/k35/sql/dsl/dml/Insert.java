package k35.sql.dsl.dml;

import k35.sql.dsl.common.Table;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * SQL Insert...
 */
public final class Insert implements SqlBuilder, Map<Insert> {

    private final String table;
    private final List<Value> values;
    private final List<String> returning;

    private Insert(String table, List<Value> values, List<String> returning) {
        super();
        this.table = table;
        this.values = values;
        this.returning = returning;
    }

    public static Insert into(String table) {
        return new Insert(table, List.of(), List.of());
    }

    private record Value(String field, String parameter) {

    }

    public Insert value(String field) {
        return value(field, ":" + field);
    }

    public Insert value(String field, String parameter) {
        final var newValues = new ArrayList<>(this.values);
        newValues.add(new Value(field, parameter));

        return new Insert(table, newValues, returning);
    }

    public Insert returning(String fields) {
        return new Insert(table, values, List.of(fields));
    }

    public Insert returning(Table... sqlBuilders) {
        return returning(List.of(sqlBuilders).stream().map(SqlBuilder::sql).collect(Collectors.joining(", ")));
    }

    @Override
    public Insert map(UnaryOperator<Insert> fn) {
        final var newInsert = new Insert(table, values, returning);
        return fn.apply(newInsert);
    }

    @Override
    public String sql() {

        final String fieldsSqlStr = this.values.isEmpty() ? ""
                : "( " + this.values.stream().map(Value::field).collect(Collectors.joining(", ")) + " )";

        final String valuesSqlStr = this.values.isEmpty() ? ""
                : "values ( " + this.values.stream().map(Value::parameter).collect(Collectors.joining(", ")) + " )";

        final String returningSqlStr = Optional.of(returning)
                .filter(o -> o.size() > 0)
                .map(list -> "returning " + list.stream().collect(Collectors.joining(", "))).orElse("");

        return Arrays.asList("insert into", table, fieldsSqlStr, valuesSqlStr, returningSqlStr).stream()
                .filter(o -> !o.isBlank()).map(String::trim).collect(Collectors.joining(" "));
    }

}
