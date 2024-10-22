package k35.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Insert implements SqlBuilder {

    private final String table;

    private Insert(String table) {
        this.table = table;
    }

    public static Insert into(String table) {
        return new Insert(table);
    }

    private record Value(String field, String parameter) {

    }

    private List<Value> values = new ArrayList<>();

    public Insert value(Parameter value) {
        return value(value.field(), value.parameter());
    }

    public Insert value(String field, String parameter) {
        values.add(new Value(field, parameter));
        return this;
    }

    private Optional<List<String>> returninig = Optional.empty();

    public Insert returning(String... fields) {
        this.returninig = Optional.of(Arrays.asList(fields));
        return this;
    }

    @Override
    public String buildSql() {

        final String fieldsSqlStr = this.values.isEmpty()
                ? ""
                : "( " + this.values.stream().map(Value::field).collect(Collectors.joining(", ")) + " )";

        final String valuesSqlStr = this.values.isEmpty()
                ? ""
                : "values ( " + this.values.stream().map(Value::parameter).collect(Collectors.joining(", ")) + " )";

        final String returningSqlStr = returninig
                .map(list -> "returning " + list.stream().collect(Collectors.joining(", ")))
                .orElse("");

        return Arrays.asList("insert into", table, fieldsSqlStr, valuesSqlStr, returningSqlStr)
                .stream()
                .filter(o -> !o.isBlank())
                .map(String::trim)
                .collect(Collectors.joining(" "));
    }

}
