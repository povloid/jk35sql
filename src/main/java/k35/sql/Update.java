package k35.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Update implements SqlBuilder {

    private final String table;

    private Update(String table) {
        this.table = table;
    }

    public static Update update(String table) {
        return new Update(table);
    }

    private final record SetValue(String field, String parameter) implements SqlBuilder {

        @Override
        public String buildSql() {
            return field + " = " + parameter;
        }
    }

    private List<SetValue> sets = new ArrayList<>();

    public Update setValue(Parameter parameter) {
        return setValue(parameter.field(), parameter.parameter());
    }

    public Update setValue(String field, String parameter) {
        sets.add(new SetValue(field, parameter));
        return this;
    }

    private Optional<Condition> condition = Optional.empty();

    public Update where(String expression) {
        return where(Condition.expression(expression));
    }

    public Update where(Condition condition) {
        this.condition = Optional.ofNullable(condition);
        return this;
    }

    private Optional<List<String>> returninig = Optional.empty();

    public Update returning(String... fields) {
        this.returninig = Optional.of(Arrays.asList(fields));
        return this;
    }

    @Override
    public String buildSql() {

        final String setSqlStr = "set " + sets.stream().map(SetValue::buildSql).collect(Collectors.joining(", "));

        final String whereSqlStr = this.condition.map(o -> "where " + o.buildSql()).orElse("");

        final String returningSqlStr = returninig
                .map(list -> "returning " + list.stream().collect(Collectors.joining(", ")))
                .orElse("");

        return Arrays.asList("update", table, setSqlStr, whereSqlStr, returningSqlStr)
                .stream()
                .filter(o -> !o.isBlank())
                .map(String::trim)
                .collect(Collectors.joining(" "));
    }

}
