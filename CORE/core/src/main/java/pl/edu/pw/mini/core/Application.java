package pl.edu.pw.mini.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import pl.edu.pw.mini.BasePackageIndicator;

@ComponentScan(basePackageClasses = {BasePackageIndicator.class})
@EntityScan(basePackageClasses = BasePackageIndicator.class)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
