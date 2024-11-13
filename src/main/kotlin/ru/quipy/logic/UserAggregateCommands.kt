package ru.quipy.logic

import ru.quipy.api.UserCreatedEvent
import java.util.*

fun UserAggregateState.create(id : UUID,nickname : String,password : String,name : String) :  UserCreatedEvent{
    return UserCreatedEvent(
        userId = id,
        nickname = nickname,
        username = name,
        password = password,
    )
}