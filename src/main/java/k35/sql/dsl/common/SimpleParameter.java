package k35.sql.dsl.common;

public final class SimpleParameter implements Parameter {

    private final String field;
    private final String parameter;

    private SimpleParameter(String field) {
        this.field = field;
        this.parameter = "?";
    }

    public static SimpleParameter byField(String field) {
        return new SimpleParameter(field);
    }

    @Override
    public String field() {
        return this.field;
    }

    @Override
    public String parameter() {
        return this.parameter;
    }

}
