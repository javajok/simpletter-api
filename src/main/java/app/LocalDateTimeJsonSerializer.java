package app;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen,
            SerializerProvider provider)
                    throws IOException, JsonProcessingException {
        jgen.writeString(value.toString());
    }
}
