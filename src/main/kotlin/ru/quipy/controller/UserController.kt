package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.api.UserAggregate
import ru.quipy.api.UserCreatedEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.logic.UserAggregateState
import ru.quipy.logic.create
import ru.quipy.projections.UserProjection
import ru.quipy.repositories.UserProjectionRepository
import ru.quipy.services.ServiceSingleton
import java.util.*

@RestController
@RequestMapping("/users")
class UserController (
    val userEsService: EventSourcingService<UUID, UserAggregate, UserAggregateState>,
    val service: ServiceSingleton,
) {
    @PostMapping("/{nickname}")
    fun createUser(@PathVariable nickname: String, @RequestParam userName: String,@RequestParam password: String): UserCreatedEvent {

        return userEsService.create { it.create(UUID.randomUUID(), nickname, userName,password) }
    }
    @GetMapping("/{name}")
    fun findByName(@PathVariable name: String,): UserProjection? {

        return service.getUserByName(name)
    }
}