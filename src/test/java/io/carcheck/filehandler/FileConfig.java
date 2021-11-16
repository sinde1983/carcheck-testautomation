package io.carcheck.filehandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FileConfig {

    @Bean
    @Scope(value = "prototype")
    public FileParser fileParser(String filePath) {
        return new FileParser(filePath);
    }
}
