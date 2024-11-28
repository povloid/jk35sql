package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.UUID;

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

	public Prepeare addBoolean(String param, boolean value) {
		return addObject(param, value);
	}

	public Prepeare addInt(String param, int value) {
		return addObject(param, value);
	}

	public Prepeare addLong(String param, long value) {
		return addObject(param, value);
	}

	public Prepeare addDouble(String param, double value) {
		return addObject(param, value);
	}

	public Prepeare addBigDecimal(String param, BigDecimal value) {
		return addObject(param, value);
	}

	public Prepeare addString(String param, String value) {
		return addObject(param, value);
	}

	public Prepeare addUUID(String param, UUID uuid) {
		return addObject(param, uuid);
	}

	public Prepeare addTime(String param, Date value) {
		return addObject(param, new java.sql.Time(value.getTime()));
	}

	public Prepeare addDate(String param, Date value) {
		return addObject(param, new java.sql.Date(value.getTime()));
	}

	public Prepeare addTimestamp(String param, Date value) {
		return addObject(param, new java.sql.Timestamp(value.getTime()));
	}

	public Prepeare addOptional(String param, Optional<?> ovalue) {
		return addObject(param, ovalue.orElse(null));
	}

	public Prepeare addOptionalBoolean(String param, Optional<Boolean> ovalue) {
		return addObject(param, ovalue.orElse(null));
	}

	public Prepeare addOptionalInt(String param, OptionalInt ovalue) {
		return addObject(param, ovalue.isPresent() ? ovalue.getAsInt() : null);
	}

	public Prepeare addOptionalLong(String param, OptionalLong ovalue) {
		return addObject(param, ovalue.isPresent() ? ovalue.getAsLong() : null);
	}

	public Prepeare addOptionalBigDecimal(String param, Optional<BigDecimal> ovalue) {
		return addObject(param, ovalue.orElse(null));
	}

	public Prepeare addOptionalDouble(String param, OptionalDouble ovalue) {
		return addObject(param, ovalue.isPresent() ? ovalue.getAsDouble() : null);
	}

	public Prepeare addOptionalString(String param, Optional<String> ovalue) {
		return addObject(param, ovalue.orElse(null));
	}

	public Prepeare addOptionalUUID(String param, Optional<UUID> ovalue) {
		return addObject(param, ovalue.orElse(null));
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
