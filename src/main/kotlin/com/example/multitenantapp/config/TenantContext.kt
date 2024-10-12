package com.example.multitenantapp.config

object TenantContext {
    private val currentTenant = ThreadLocal<String>()

    fun setCurrentTenant(tenant: String) {
        currentTenant.set(tenant)
    }

    fun getCurrentTenant(): String {
        return currentTenant.get() ?: "public"
    }

    fun clear() {
        currentTenant.remove()
    }
}