package com.example.multitenantapp.config
import org.hibernate.cfg.Environment
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource



@Configuration
class HibernateConfig(
    private val dataSource: DataSource,
    private val multiTenantConnectionProvider: MultiTenantConnectionProvider,
    private val currentTenantIdentifierResolver: CurrentTenantIdentifierResolver<String>
) {

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.setPackagesToScan("com.example.multitenantapp.model")
        em.jpaVendorAdapter = jpaVendorAdapter()
        val properties: Map<String, Any> = mapOf(
            Environment.MULTI_TENANT_CONNECTION_PROVIDER to multiTenantConnectionProvider,
            Environment.MULTI_TENANT_IDENTIFIER_RESOLVER to currentTenantIdentifierResolver,
            Environment.FORMAT_SQL to true,
            Environment.SHOW_SQL to true
        )
        em.setJpaPropertyMap(properties)
        return em
    }

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        return HibernateJpaVendorAdapter()
    }
}