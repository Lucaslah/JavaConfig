package me.lucaslah.javaconfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Main class for the library
 */
public class JavaConfig {
    private final Class<?> clazz;
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Create a new instance of JavaConfig
     * @param clazz the config class
     * @param path the config file path
     */
    public JavaConfig(Class<?> clazz, Path path) {
        this.clazz = clazz;
        this.path = path;
    }

    /**
     * Initiates the config file, creates it if it does not exist and loads the file into memory
     * @throws IOException error
     */
    public void init() throws IOException {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
                        if (ConfigEntry.class.isAssignableFrom(desc.getBeanClass())) {
                            return new ConfigEntrySerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });

        File configFile = new File(path.toUri());
        if (!configFile.exists()) {
            write();
        }

        mapper.readValue(Files.newBufferedReader(path), clazz);
    }

    /**
     * Writes the config class to file
     */
    public void write() {
        try {
            if (!Files.exists(path)) Files.createFile(path);
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
