package pl.edu.pw.mini.core.configuration.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.util.TimeZone;

@Configuration
public class ObjectMapperConfiguration {

    @Value("${pl.edu.pw.mini.timeZone}")
    private String timeZone;

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(timeZone));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeSerializer(timeZone));
        objectMapper.getSerializationConfig().getDateFormat().setTimeZone(TimeZone.getTimeZone("GTM"));
        objectMapper.getDeserializationConfig().getDateFormat().setTimeZone(TimeZone.getTimeZone("GTM"));
        objectMapper.registerModule(javaTimeModule);
        objectMapper = objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}
