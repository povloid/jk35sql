package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

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

	public short getShort(String field) throws SQLException {
		return rs.getShort(field);
	}

	public long getLong(String field) throws SQLException {
		return rs.getLong(field);
	}

	public String getString(String field) throws SQLException {
		return rs.getString(field);
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

	public Optional<Boolean> getOptionalBoolean(String field) throws SQLException {
		return Optional.ofNullable(rs.getBoolean(field));
	}

	public OptionalInt getOptionalInt(String field) throws SQLException {
		final var i = rs.getObject(field, Integer.class);
		return i == null ? OptionalInt.empty() : OptionalInt.of(i);
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

	public Optional<BigDecimal> getOptionalBigDecimal(String field) throws SQLException {
		return Optional.of(rs.getBigDecimal(field));
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

}