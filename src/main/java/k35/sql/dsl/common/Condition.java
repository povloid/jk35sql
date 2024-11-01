package k35.sql.dsl.common;

import java.util.function.UnaryOperator;

import k35.sql.dsl.interfaces.SqlBuilder;

public final class Condition implements SqlBuilder {

	public static class Value {
		private final String value1;

		private Value(String value1) {
			this.value1 = value1;
		}

		public static Value of(String value1) {
			return new Value(value1);
		}

		/**
		 * = (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String eq(String value2) {
			return equal(value2);
		}

		/**
		 * = (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String equal(String value2) {
			return this.value1 + " = " + value2;
		}

		/**
		 * <> (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String ne(String value2) {
			return notEqual(value2);
		}

		/**
		 * <> (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String notEqual(String value2) {
			return this.value1 + " <> " + value2;
		}

		/**
		 * < (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String lt(String value2) {
			return lessThen(value2);
		}

		/**
		 * < (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String lessThen(String value2) {
			return this.value1 + " < " + value2;
		}

		/**
		 * <= (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String le(String value2) {
			return lessOrEqual(value2);
		}

		/**
		 * <= (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String lessOrEqual(String value2) {
			return this.value1 + " <= " + value2;
		}

		/**
		 * > (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String gt(String value2) {
			return greaterThan(value2);
		}

		/**
		 * > (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String greaterThan(String value2) {
			return this.value1 + " > " + value2;
		}

		/**
		 * >= (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String ge(String value2) {
			return greaterOrEqual(value2);
		}

		/**
		 * >= (some bind value)
		 * 
		 * @param value2
		 * @return
		 */
		public String greaterOrEqual(String value2) {
			return this.value1 + " >= " + value2;
		}

		public String between(String from, String to) {
			return "between " + from + " and " + to;
		}

		public String notBetween(String from, String to) {
			return "not between " + from + " and " + to;
		}

		public String notEq(String value2) {
			return value1 + " <> " + value2;
		}

		public String is(String value2) {
			return value1 + " is " + value2;
		}

		public String isNot(String value2) {
			return value1 + " is not " + value2;
		}

		public String isTrue() {
			return value1 + " is true";
		}

		public String isNotTrue() {
			return value1 + " is not true";
		}

		public String isFalse() {
			return value1 + " is false";
		}

		public String isNotFalse() {
			return value1 + " is not false";
		}

		public String isNull() {
			return value1 + " is null";
		}

		public String isNotNull() {
			return value1 + " is not null";
		}

		public String in(String value2) {
			return value1 + " in ( " + value2 + " )";
		}

		public String isUnknown() {
			return value1 + " is unknown";
		}

		public String isNotUnknown() {
			return value1 + " is not unknown";
		}
	}

	private final String expression;

	private Condition(String expression) {
		this.expression = expression;
	}

	public static Condition expression(String expression) {
		return new Condition(expression);
	}

	public Condition and(Condition condition) {
		return new Condition(this.expression + " and (" + condition.buildSql() + ")");
	}

	public Condition and(String expression) {
		return new Condition(this.expression + " and " + expression);
	}

	public Condition or(Condition condition) {
		return new Condition(this.expression + " or (" + condition.buildSql() + ")");
	}

	public Condition or(String expression) {
		return new Condition(this.expression + " or " + expression);
	}

	public Condition map(UnaryOperator<Condition> fn) {
		final var newCondition = new Condition(this.expression);

		return fn.apply(newCondition);
	}

	@Override
	public String buildSql() {
		return this.expression;
	}
}
