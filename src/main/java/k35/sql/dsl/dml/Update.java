package k35.sql.dsl.dml;

import k35.sql.dsl.common.Predicate;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public final class Update implements SqlBuilder, Map<Update> {

    private final String table;
    private final List<SetValue> sets;
    private final Optional<String> condition;
    private final List<String> returning;

    private Update(
            String table,
            List<SetValue> sets,
            Optional<String> condition,
            List<String> returning) {
        super();
        this.table = table;
        this.sets = sets;
        this.condition = condition;
        this.returning = returning;
    }

    public static Update update(String table) {
        return new Update(table, new ArrayList<>(), Optional.empty(), List.of());
    }

    private record SetValue(String field, String parameter) implements SqlBuilder {
        @Override
        public String sql() {
            return field + " = " + parameter;
        }
    }

    public Update setValue(String field) {
        return setValue(field, ":" + field);
    }

    public Update setValue(String field, String parameter) {
        final var newSets = new ArrayList<>(this.sets);
        newSets.add(new SetValue(field, parameter));

        return new Update(table, newSets, condition, returning);
    }

    public Update where(Predicate predicate) {
        return where(predicate.sql());
    }

    public Update where(String condition) {
        return new Update(
                table,
                sets,
                Optional.ofNullable(condition).filter(s -> !s.isBlank()),
                returning);
    }

    public Update returning(String... fields) {
        return new Update(
                table,
                sets,
                condition,
                List.of(fields)
        );
    }

    public Update map(UnaryOperator<Update> fn) {
        final var newUpdate = new Update(table, sets, condition, returning);
        return fn.apply(newUpdate);
    }

    @Override
    public String sql() {

        final String setSqlStr = "set " + sets.stream().map(SetValue::sql).collect(Collectors.joining(", "));

        final String whereSqlStr = this.condition.map(o -> "where " + o).orElse("");

        final String returningSqlStr = Optional.of(returning)
                .filter(o -> o.size() > 0)
                .map(list -> "returning " + list.stream().collect(Collectors.joining(", "))).orElse("");

        return Arrays.asList("update", table, setSqlStr, whereSqlStr, returningSqlStr).stream()
                .filter(o -> !o.isBlank()).map(String::trim).collect(Collectors.joining(" "));
    }

}
