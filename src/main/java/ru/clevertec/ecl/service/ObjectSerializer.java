package ru.clevertec.ecl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.dto.GiftCertificateDto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

@Component
public class ObjectSerializer {
    public static final SimpleDateFormat df =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public String arrayOfObjectsToJson(Object... objects){
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.asList(objects).forEach(o -> stringBuilder.append(objectToJson(o)));
        return stringBuilder.toString();
    }

    public String objectToJson(Object object) {
        try {
            return newMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public GiftCertificateDto getObject(String newJson) {
        try {
            return newMapper().readValue(newJson, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        mapper.setDateFormat(df);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
