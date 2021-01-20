package test.jpa.data.demofetchjpa.testconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@TestPropertySource("classpath:application-test.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "test.jpa.data.demofetchjpa.data.repository")
@ActiveProfiles("test")
public class ConfigTest {
/*

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                                .driverClassName(environment.getProperty("spring.datasource.driver-class-name"))
                                .url(environment.getProperty("spring.datasource.url"))
                                .username(environment.getProperty("spring.datasource.username"))
                                .password(environment.getProperty("spring.datasource.password"))
                                .type(HikariDataSource.class)
                                .build();

    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setPackagesToScan("test.jpa.data.demofetchjpa.domain.entity");
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emFactory.setDataSource(dataSource());
        return emFactory;
    }


    public Properties additionalProperties(){
    Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        return hibernateProperties;
    }
*/

}
