package pl.edu.pw.mini.core.configuration.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

@AllArgsConstructor
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    private String timeZone;

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(localDate.atStartOfDay(ZoneId.of(timeZone)).toInstant().toEpochMilli());
    }
}
