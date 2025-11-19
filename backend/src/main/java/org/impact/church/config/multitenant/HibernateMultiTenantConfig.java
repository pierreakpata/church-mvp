package org.impact.church.config.multitenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateMultiTenantConfig {

    @Autowired
    private TenantConnectionProvider connectionProvider;

    @Autowired
    private TenantIdentifierResolver tenantResolver;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

        Map<String, Object> props = new HashMap<>();

        props.put("hibernate.multiTenancy", "SCHEMA");
        props.put("hibernate.tenant_identifier_resolver", tenantResolver);
        props.put("hibernate.multi_tenant_connection_provider", connectionProvider);


        return builder
                .dataSource(dataSource)
                .packages("org.impact.church.security.model",
                        "org.impact.church.tenant.model",
                        "org.impact.church.modules.model")
                .properties(props)
                .build();
    }
}
