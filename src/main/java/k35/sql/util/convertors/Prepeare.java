package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public final class Prepeare {

	private final Map<String, ?> map;

	private Prepeare() {
		this.map = new HashMap<>();
	}

	private Prepeare(Map<String, ?> map) {
		this.map = map;
	}

	public static Prepeare of() {
		return new Prepeare();
	}

	private Prepeare addObject(String param, Object value) {
		final var newMap = new HashMap<String, Object>(map);
		newMap.put(param, value);

		return new Prepeare(newMap);
	}

	public Prepeare add(String param, boolean value) {
		return addObject(param, value);
	}

	public Prepeare add(String param, int value) {
		return addObject(param, value);
	}

	public Prepeare add(String param, long value) {
		return addObject(param, value);
	}

	public Prepeare add(String param, double value) {
		return addObject(param, value);
	}

	public Prepeare add(String param, BigDecimal value) {
		return addObject(param, value);
	}

	public Prepeare add(String param, String value) {
		return addObject(param, value);
	}

	public Prepeare add(String param, Date value) {
		return addObject(param, new java.sql.Timestamp(value.getTime()));
	}

	public Prepeare addTime(String param, Date value) {
		return addObject(param, new java.sql.Time(value.getTime()));
	}

	public Prepeare addDate(String param, Date value) {
		return addObject(param, new java.sql.Date(value.getTime()));
	}

	public Prepeare addOptional(String param, Optional<?> ovalue) {
		return addObject(param, ovalue.orElse(null));
	}

	public Prepeare addOptional(String param, OptionalInt ovalue) {
		return addObject(param, ovalue.isPresent() ? ovalue.getAsInt() : null);
	}

	public Prepeare addOptional(String param, OptionalLong ovalue) {
		return addObject(param, ovalue.isPresent() ? ovalue.getAsLong() : null);
	}

	public Prepeare addOptional(String param, OptionalDouble ovalue) {
		return addObject(param, ovalue.isPresent() ? ovalue.getAsDouble() : null);
	}

	public Prepeare addOptionalTime(String param, Optional<Date> ovalue) {
		return addObject(param, ovalue.map(Date::getTime).map(java.sql.Time::new).orElse(null));
	}

	public Prepeare addOptionalDate(String param, Optional<Date> ovalue) {
		return addObject(param, ovalue.map(Date::getTime).map(java.sql.Date::new).orElse(null));
	}

	public Prepeare addOptionalTimestamp(String param, Optional<Date> ovalue) {
		return addObject(param, ovalue.map(Date::getTime).map(java.sql.Timestamp::new).orElse(null));
	}

	public Map<String, ?> getMap() {
		return map;
	}

}
