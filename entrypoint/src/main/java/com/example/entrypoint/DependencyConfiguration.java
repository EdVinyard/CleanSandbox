import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class DependencyConfiguration {

    @Bean
    public com.example.domain.Chat chat() {
        return new com.example.domain.Chat();
    }
}
