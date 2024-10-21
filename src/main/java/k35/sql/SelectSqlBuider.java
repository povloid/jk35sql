package k35.sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SelectSqlBuider {

    public static SelectSqlBuider select(String... fields) {
        return new SelectSqlBuider(fields);
    }

    protected final List<String> fields;

    private SelectSqlBuider(String... fields) {
        this.fields = Arrays.asList(fields);
    }

    private Optional<String> table = Optional.empty();

    public SelectSqlBuider from(String table) {
        this.table = Optional.ofNullable(table);
        return this;
    }

    private Optional<Condition> condition = Optional.empty();

    public SelectSqlBuider where(Condition condition) {
        this.condition = Optional.ofNullable(condition);
        return this;
    }

    private Optional<Integer> limit = Optional.empty();

    public SelectSqlBuider limit(Integer limit) {
        this.limit = Optional.ofNullable(limit);
        return this;
    }

    private Optional<Integer> offset = Optional.empty();

    public SelectSqlBuider offset(Integer offset) {
        this.offset = Optional.ofNullable(offset);
        return this;
    }

    private Optional<String[]> orderByFields = Optional.empty();

    public SelectSqlBuider orderBy(String... fields) {
        this.orderByFields = Optional.ofNullable(fields);
        return this;
    }

    private Optional<String[]> groupByFields = Optional.empty();

    public SelectSqlBuider groupBy(String... fields) {
        this.groupByFields = Optional.ofNullable(fields);
        return this;
    }

    public String build() {

        final String fieldsSqlStr = fields.isEmpty()
                ? "*"
                : fields.stream().collect(Collectors.joining(", "));

        final String tableSqlStr = this.table.map(String::trim).orElse("");

        final String whereSqlStr = this.condition.map(o -> "where " + o.build()).orElse("");

        final String groupBySqlStr = this.groupByFields
                .map(o -> "group by " + Arrays.asList(o)
                        .stream()
                        .collect(Collectors.joining(", ")))
                .orElse("");

        final String orderBySqlStr = this.orderByFields
                .map(o -> "order by " + Arrays.asList(o).stream().collect(Collectors.joining(", ")))
                .orElse("");

        final String limitSqlStr = limit.map(o -> "limit " + o).orElse("");

        final String offsetSqlStr = offset.map(o -> "offset " + o).orElse("");

        return Arrays
                .asList("select", fieldsSqlStr, "from", tableSqlStr,
                        whereSqlStr,
                        groupBySqlStr,
                        orderBySqlStr,
                        limitSqlStr,
                        offsetSqlStr)
                .stream()
                .filter(o -> !o.isBlank())
                .map(String::trim)
                .collect(Collectors.joining(" "));
    }

}
