package sample;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;

/**
 * JPAのエンティティでTimestampをLocalDateTimeとして扱うために相互変換を行うクラスです。
 * 
 * @author backpaper0
 *
 */
public class LocalDateTimeAttributeConverter
        implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return Optional.ofNullable(attribute).map(Timestamp::valueOf)
                .orElse(null);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return Optional.ofNullable(dbData).map(Timestamp::toLocalDateTime)
                .orElse(null);
    }
}
