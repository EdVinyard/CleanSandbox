import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyConfiguration {

    @Bean
    public com.example.domain.Chat chat() {
        return new com.example.domain.Chat();
    }
}
