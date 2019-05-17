package pl.edu.pw.mini.core.configuration.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@AllArgsConstructor
public class LocalDateDeSerializer extends JsonDeserializer<LocalDate> {
    private String timeZone;

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Long millis = jsonParser.getLongValue();
        return Instant.ofEpochMilli(millis).atZone(ZoneId.of(timeZone)).toLocalDate();
    }
}
