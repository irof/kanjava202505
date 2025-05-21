package dev.irof.kanjava;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KanjavaApp {

    public static void main(String[] args) {
        SpringApplication.run(KanjavaApp.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> debugLogExcludeMeterRegistryCustomizer() {
        // デバッグログを出力するたびにメトリクスを送信して大変なことになるので除外する（実際はやらない）
        return registry -> registry.config().meterFilter(MeterFilter.deny(id ->
                "logback.events".equals(id.getName()) && "debug".equals(id.getTag("level"))));
    }
}
