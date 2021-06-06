package com.bulatsa.demo.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json,
                                                 Type typeOfT,
                                                 JsonDeserializationContext context) throws JsonParseException {
                        return LocalDate.parse(json.getAsString(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json,
                                                     Type typeOfT,
                                                     JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    }
                })
                .create();
    }
}
