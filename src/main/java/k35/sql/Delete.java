package k35.sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Delete implements SqlBuilder {

    private final String table;

    private Delete(String table) {
        this.table = table;
    }

    public static Delete from(String table) {
        return new Delete(table);
    }

    private Optional<Condition> condition = Optional.empty();

    public Delete where(String expression) {
        return where(Condition.expression(expression));
    }

    public Delete where(Condition condition) {
        this.condition = Optional.ofNullable(condition);
        return this;
    }

    private Optional<List<String>> returninig = Optional.empty();

    public Delete returning(String... fields) {
        this.returninig = Optional.of(Arrays.asList(fields));
        return this;
    }

    @Override
    public String buildSql() {
        final String whereSqlStr = this.condition.map(o -> "where " + o.buildSql()).orElse("");

        final String returningSqlStr = returninig
                .map(list -> "returning " + list.stream().collect(Collectors.joining(", ")))
                .orElse("");

        return Arrays.asList("delete from", table, whereSqlStr, returningSqlStr)
                .stream()
                .filter(o -> !o.isBlank())
                .map(String::trim)
                .collect(Collectors.joining(" "));

    }

}
