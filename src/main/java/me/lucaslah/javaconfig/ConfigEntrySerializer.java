package me.lucaslah.javaconfig;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ConfigEntrySerializer extends JsonSerializer<ConfigEntry> {
    private final JsonSerializer<Object> defaultSerializer;

    public ConfigEntrySerializer(JsonSerializer<Object> serializer) {
        defaultSerializer = serializer;
    }

    @Override
    public void serialize(ConfigEntry value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value.isHidden())
            return;
        defaultSerializer.serialize(value, jsonGenerator, provider);
    }

    @Override
    public boolean isEmpty(ConfigEntry value) {
        return (value == null || value.isHidden());
    }
}
