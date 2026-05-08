package com.example.hotelbooking.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Envconfig {
    static {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()   // ✅ prevents crash
                .load();

        if (dotenv.get("DB_URL") != null)
            System.setProperty("DB_URL", dotenv.get("DB_URL"));

        if (dotenv.get("DB_USERNAME") != null)
            System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));

        if (dotenv.get("DB_PASSWORD") != null)
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }
}