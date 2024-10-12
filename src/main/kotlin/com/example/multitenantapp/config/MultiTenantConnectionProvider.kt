package com.example.multitenantapp.config

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.stereotype.Component
import java.sql.Connection
import javax.sql.DataSource

@Component
class MultiTenantConnectionProvider(private val dataSource: DataSource) : MultiTenantConnectionProvider<String> {

    override fun getAnyConnection(): Connection {
        return dataSource.connection
    }

    override fun releaseAnyConnection(connection: Connection) {
        connection.close()
    }

    override fun getConnection(tenantIdentifier: String): Connection {
        val connection = anyConnection
        connection.createStatement().execute("SET SCHEMA '$tenantIdentifier'")
        return connection
    }

    override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
        connection.createStatement().execute("SET SCHEMA 'public'")
        releaseAnyConnection(connection)
    }

    override fun supportsAggressiveRelease(): Boolean {
        return false
    }

    override fun isUnwrappableAs(unwrapType: Class<*>): Boolean {
        return false
    }

    override fun <T : Any?> unwrap(unwrapType: Class<T>): T? {
        return null
    }
}