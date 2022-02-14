package com.gui.coursesystem.GSONSerializable;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Convert
public class LocalDateConverterForDb implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .orElse(null);
    }
}
