package k35.sql.dsl.common;

public final class NamedParameter implements Parameter {

	private final String field;
	private final String parameter;

	private NamedParameter(String field, String parameter) {
		this.field = field;
		this.parameter = parameter;
	}

	public static NamedParameter byField(String field) {
		return new NamedParameter(field, ":" + field);
	}

	public NamedParameter as(String parameter) {
		return new NamedParameter(this.field, parameter);
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
