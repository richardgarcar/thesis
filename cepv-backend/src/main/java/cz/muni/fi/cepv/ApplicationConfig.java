package cz.muni.fi.cepv;

import cz.muni.fi.cepv.dao.HSQLDBQueryExecutionDAO;
import cz.muni.fi.cepv.dao.QueryExecutionDAO;
import cz.muni.fi.cepv.dao.PostgresQueryExecutionDAO;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
* @author xgarcar
*/
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class ApplicationConfig {

    public static final String PACKAGE_WITH_ENTITIES = "cz.muni.fi.cepv.model";

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Configuration
    @Profile("dev")
    public static class DevelopmentConfig {

        @Bean
        public DataSource dataSource() {
            final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            return builder
                    .setType(EmbeddedDatabaseType.HSQL)
                    .addScript("classpath:/sql/inmemorydb.sql").build();
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

            final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setShowSql(true);
            vendorAdapter.setDatabase(Database.HSQL);

            final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setJpaVendorAdapter(vendorAdapter);
            factory.setPersistenceProvider(new HibernatePersistenceProvider());
            factory.setPackagesToScan(PACKAGE_WITH_ENTITIES);
            factory.setDataSource(dataSource());

            return factory;
        }

        @Bean
        public QueryExecutionDAO queryExecutionDAO(){
            return new HSQLDBQueryExecutionDAO();
        }
    }

    @Configuration
    @Profile("!dev")
    @PropertySource(value = "classpath:application.properties")
    public static class DefaultConfig {

        @Autowired
        protected Environment environment;

        @Bean
        public DataSource dataSource() {
            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(environment.getProperty("persistence.production.jdbc.driver"));
            dataSource.setUrl(environment.getProperty("persistence.production.jdbc.url"));
            dataSource.setUsername(environment.getProperty("persistence.production.jdbc.user"));
            dataSource.setPassword(environment.getProperty("persistence.production.jdbc.password"));
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

            final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setDatabase(Database.POSTGRESQL);

            final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setJpaVendorAdapter(vendorAdapter);
            factory.setJpaProperties(additionalJpaProperties());
            factory.setPersistenceProvider(new HibernatePersistenceProvider());
            factory.setPackagesToScan(PACKAGE_WITH_ENTITIES);
            factory.setDataSource(dataSource());

            return factory;
        }

        private Properties additionalJpaProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.default_schema", environment.getProperty("hibernate.default_schema"));

            return properties;
        }

        @Bean
        public QueryExecutionDAO queryExecutionDAO(){
            return new PostgresQueryExecutionDAO();
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return txManager;
    }
}
