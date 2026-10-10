package benedicto.learnspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org. springframework.context.annotation.Configuration;

@Component
class Engine {
    public void start () {
        System.out.println("Mesin menyala");
    }
}

class ExternalPrinter {
    private final String brand;
    public ExternalPrinter(String brand) {
        this.brand = brand;
    }

    public void printLog(String message) {
        System.out.println("[" + brand + " printer] " + message);
    }
}

@Configuration
class Appconfig {
    @Bean
    public ExternalPrinter myPrinter() {
        return new ExternalPrinter ("Epson");
    }
}

@Component
class ServiceCar {
    private final Engine engine;
    private final ExternalPrinter printer;

    public ServiceCar(Engine engine, ExternalPrinter printer) {
        this.engine = engine;
        this.printer = printer;
    }

    public void runDiagnostics() {
        engine.start();
        printer.printLog("Diagnosa mobil selesai tanpa error");
    }
}

@SpringBootApplication
public class LearnSpringApplication {
    public static void main (String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner testBean(ServiceCar serviceCar) {
        return args -> {
            serviceCar.runDiagnostics();
        };
    }
}