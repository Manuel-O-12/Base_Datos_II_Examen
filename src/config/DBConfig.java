package config;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private Properties properties;

    public DBConfig() {
        properties = new Properties();
        // Intentamos cargar el archivo desde el classpath
        // La ruta es relativa al directorio de clases (classpath root)
        // Si está en un paquete, usa: /paquete/archivo.properties
        // Pero como está en la carpeta config, que está en el classpath, usamos "config/db.properties"
        try (InputStream input = getClass().getResourceAsStream("/config/db.properties")) {
            if (input == null) {
                // Si no lo encuentra, intentamos cargarlo desde el filesystem
                System.err.println("No se pudo encontrar /config/db.properties en el classpath. Intentando con ruta absoluta...");
                // También podrías intentar con una ruta absoluta, pero no es lo recomendado
                // input = new FileInputStream("path/absoluto/db.properties");
            } else {
                properties.load(input);
                System.out.println("db.properties cargado desde classpath.");
            }
        } catch (Exception e) {
            System.err.println("Error cargando db.properties: " + e.getMessage());
        }
    }

    public String getUrl() {
        return properties.getProperty("db.url");
    }

    public String getUsername() {
        return properties.getProperty("db.username");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }
}