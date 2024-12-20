package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.UUID;

public final class Transform {

	private final ResultSet rs;

	private Transform(ResultSet rs) {
		this.rs = rs;
	}

	@FunctionalInterface
	public static interface TransformFunction<T> {
		T apply(ResultSet rs) throws SQLException;
	}

	@FunctionalInterface
	public static interface ExtractFunction<T> {
		T apply(Transform ts) throws SQLException;
	}

	public static <T> TransformFunction<T> of(ExtractFunction<T> extractor) {
		return (ResultSet rs) -> extractor.apply(new Transform(rs));
	}

	public boolean getBoolean(String field) throws SQLException {
		return rs.getBoolean(field);
	}

	public int getInt(String field) throws SQLException {
		return rs.getInt(field);
	}

	public int getIntFromNumeric(String field) throws SQLException {
		return rs.getBigDecimal(field).intValue();
	}

	public short getShort(String field) throws SQLException {
		return rs.getShort(field);
	}

	public long getLong(String field) throws SQLException {
		return rs.getLong(field);
	}

	public String getString(String field) throws SQLException {
		return rs.getString(field);
	}

	public UUID getUUID(String field) throws SQLException {
		return UUID.fromString(rs.getString(field));
	}

	public BigDecimal getBigDecimal(String field) throws SQLException {
		return rs.getBigDecimal(field);
	}

	public Date getTime(String field) throws SQLException {
		return new Date(rs.getTime(field).getTime());
	}

	public Date getDate(String field) throws SQLException {
		return new Date(rs.getDate(field).getTime());
	}

	public Date getTimestamp(String field) throws SQLException {
		return new Date(rs.getTimestamp(field).getTime());
	}

	@SuppressWarnings("unchecked")
	public <T> T[] getArray(String field, Class<T> type) throws SQLException {
		final var sqlArray = rs.getArray(field);
		return (T[]) sqlArray.getArray();
	}

	public <T> Optional<T> getOptional(String field, Class<T> type) throws SQLException {
		return Optional.ofNullable(rs.getObject(field, type));
	}

	public Optional<Boolean> getOptionalBoolean(String field) throws SQLException {
		return Optional.ofNullable(rs.getBoolean(field));
	}

	public OptionalInt getOptionalInt(String field) throws SQLException {
		final var i = rs.getObject(field, Integer.class);
		return i == null ? OptionalInt.empty() : OptionalInt.of(i);
	}

	public OptionalInt getOptionalIntFromNumeric(String field) throws SQLException {
		final var i = rs.getBigDecimal(field);
		return i == null ? OptionalInt.empty() : OptionalInt.of(i.intValue());
	}

	public OptionalLong getOptionalLong(String field) throws SQLException {
		final var i = rs.getObject(field, Long.class);
		return i == null ? OptionalLong.empty() : OptionalLong.of(i);
	}

	public OptionalDouble getOptionalDouble(String field) throws SQLException {
		final var i = rs.getObject(field, Long.class);
		return i == null ? OptionalDouble.empty() : OptionalDouble.of(i);
	}

	public Optional<String> getOptionalString(String field) throws SQLException {
		return Optional.ofNullable(rs.getString(field));
	}

	public Optional<UUID> getOptionalUUID(String field) throws SQLException {
		return Optional.ofNullable(rs.getString(field)).map(UUID::fromString);
	}

	public Optional<BigDecimal> getOptionalBigDecimal(String field) throws SQLException {
		return Optional.ofNullable(rs.getBigDecimal(field));
	}

	public Optional<Date> getOptionalTime(String field) throws SQLException {
		return Optional.ofNullable(rs.getTime(field)).map(java.sql.Time::getTime).map(Date::new);
	}

	public Optional<Date> getOptionalDate(String field) throws SQLException {
		return Optional.ofNullable(rs.getDate(field)).map(java.sql.Date::getTime).map(Date::new);
	}

	public Optional<Date> getOptionalTimestamp(String field) throws SQLException {
		return Optional.ofNullable(rs.getTimestamp(field)).map(java.sql.Timestamp::getTime).map(Date::new);
	}

	@SuppressWarnings("unchecked")
	public <T> Optional<T[]> getOptionalArray(String field, Class<T> type) throws SQLException {
		final var sqlArray = rs.getArray(field);

		if (Objects.isNull(sqlArray)) {
			return Optional.empty();
		}

		return Optional.of((T[]) sqlArray.getArray());
	}

}
