package com.example.multitenantapp.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.stereotype.Component

@Component
class TenantIdentifierResolver : CurrentTenantIdentifierResolver<String> {
    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContext.getCurrentTenant()
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}