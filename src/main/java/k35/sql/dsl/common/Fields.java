package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class Fields implements SqlBuilder {

    private final Field[] fields;

    private Fields(Field[] fields) {
        this.fields = fields;
    }

    public static Fields of(Field... fields) {
        return new Fields(fields);
    }

    public static Fields of(Table table) {
        final var fields = Arrays.stream(table.getClass().getDeclaredFields())
                .filter(o -> o.getGenericType().equals(Field.class))
                .map(o -> {
                    try {
                        return o.get(table);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray((i) -> new Field[i]);

        return of(fields);
    }

    @Override
    public String sql() {
        return Arrays.stream(this.fields).map(SqlBuilder::sql).collect(Collectors.joining(", "));
    }
}
