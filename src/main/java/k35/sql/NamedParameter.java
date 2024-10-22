package k35.sql;

public final class NamedParameter implements Parameter {

    private final String field;
    private String parameter;

    private NamedParameter(String field, String parameter) {
        this.field = field;
        this.parameter = parameter;
    }

    public static NamedParameter byField(String field) {
        return new NamedParameter(field, ":" + field);
    }

    public NamedParameter as(String parameter) {
        this.parameter = ":" + parameter;
        return this;
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
