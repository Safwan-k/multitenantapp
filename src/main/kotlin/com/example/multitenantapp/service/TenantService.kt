package com.example.multitenantapp.service

import org.flywaydb.core.Flyway
import org.springframework.stereotype.Service
import javax.sql.DataSource

@Service
class TenantService(private val dataSource: DataSource) {

    fun createTenant(tenantId: String) {
        dataSource.connection.use { connection ->
            val statement = connection.createStatement()
            statement.execute("CREATE SCHEMA IF NOT EXISTS $tenantId")

            val flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(tenantId)
                .load()
            flyway.migrate()
        }
    }

    fun deleteTenant(tenantId: String) {
        dataSource.connection.use { connection ->
            val statement = connection.createStatement()
            statement.execute("DROP SCHEMA IF EXISTS $tenantId CASCADE")
        }
    }
}