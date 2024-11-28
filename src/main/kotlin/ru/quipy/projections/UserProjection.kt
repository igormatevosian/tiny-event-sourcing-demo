package ru.quipy.projections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document(collection = "users")
data class UserProjection(
    @Id
    val userId: UUID,
    val nickname: String,
    val password: String,
    val name: String

)