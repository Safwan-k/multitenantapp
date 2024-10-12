package com.example.multitenantapp.filter

import com.example.multitenantapp.config.TenantContext
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import jakarta.servlet.Filter

@Component
@Order(1)
class TenantIdentificationFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val tenantId = (request as HttpServletRequest).getHeader("X-TenantID")
        if (tenantId != null) {
            TenantContext.setCurrentTenant(tenantId)
        }

        try {
            chain.doFilter(request, response)
        } finally {
            TenantContext.clear()
        }
    }
}