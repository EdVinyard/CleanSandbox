package com.example.boundedcontext1;

import com.example.boundedcontext1.domain.Greeter;
import com.example.boundedcontext1.domain.GreetingRepository;
import com.example.boundedcontext1.h2.InMemoryGreetingRepository;
import com.example.boundedcontext1.web.GreetingEndpoint;
import com.example.boundedcontext1.web.GsonJsonSerializer;
import com.example.boundedcontext1.web.JsonSerializer;
import com.example.boundedcontext1.web.WebService;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class Dependencies {

    @Bean
    public GreetingRepository greetingRepository() {
        return new InMemoryGreetingRepository();
    }

    @Bean
    public Greeter greeter(final GreetingRepository repository) {
        return new Greeter(repository);
    }

    @Bean
    public GreetingEndpoint greetingEndpoint(
            final Greeter greeter,
            final JsonSerializer jsonSerializer) {
        return new GreetingEndpoint(greeter, jsonSerializer);
    }

    @Bean
    public WebService webService(final GreetingEndpoint greetingEndpoint) {
        return new WebService(greetingEndpoint);
    }

    @Bean
    public JsonSerializer jsonSerializer() {
        return new GsonJsonSerializer();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final DataSource dataSource) {

        final var jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        final var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] { "com.example.boundedcontext1.h2" });

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaProperties(jpaProperties);

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final var s = new DriverManagerDataSource();
        s.setDriverClassName("org.h2.Driver");
        s.setUsername("sa");
        s.setPassword("password");

        // on disk
        //s.setUrl("spring.datasource.url=jdbc:h2:file:/data/demo");

        // in memory
        s.setUrl("jdbc:h2:mem:testdb");

        return s;
    }
}
