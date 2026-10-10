package benedicto.learnspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Radio {
    public void playMusic() {
        System.out.println("Music is now playing");
    }
}

@Component
class Mobil {
    private final Radio radio;
    public Mobil(Radio radio){
        this.radio = radio;
    }

    public void jalan() {
        radio.playMusic();
        System.out.println("Mobil siap jalan");
    }
}

@SpringBootApplication
public class LearnSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }

    @Component
    class TesJalan implements CommandLineRunner{
        private final Mobil mobil;

        public TesJalan(Mobil mobil){
            this.mobil = mobil;
        }

        @Override
        public void run(String... args) {
            mobil.jalan();
        }
    }
}