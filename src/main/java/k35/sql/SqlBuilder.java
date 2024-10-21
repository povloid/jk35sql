package k35.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class SqlBuilder {

    protected final String table;

    protected SqlBuilder(String table) {
        this.table = table;
    }

    protected record Field(String fieldName, Optional<String> paramName) {
    }

    protected List<Field> fields = new ArrayList<>();

    public SqlBuilder field(String fieldName) {
        this.fields.add(new Field(fieldName, Optional.empty()));
        return this;
    }

    public SqlBuilder field(String fieldName, String paramName) {
        this.fields.add(new Field(fieldName, Optional.of(paramName)));
        return this;
    }

    public abstract String build();
}
