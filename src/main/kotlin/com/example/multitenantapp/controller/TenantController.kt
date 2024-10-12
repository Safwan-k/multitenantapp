package com.example.multitenantapp.controller

import com.example.multitenantapp.service.TenantService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tenants")
class TenantController(private val tenantService: TenantService) {

    @PostMapping
    fun createTenant(@RequestParam tenantId: String): ResponseEntity<String> {
        tenantService.createTenant(tenantId)
        return ResponseEntity.ok("Tenant $tenantId created successfully")
    }

    @DeleteMapping
    fun deleteTenant(@RequestParam tenantId: String): ResponseEntity<String> {
        tenantService.deleteTenant(tenantId)
        return ResponseEntity.ok("Tenant $tenantId deleted successfully")
    }
}