package repositories.clientes

import models.User
import repositories.ICRUDRepository
import java.util.UUID

interface UserRepository: ICRUDRepository<User, UUID> {
    fun findByEmail(email: String): User?
    fun findByPhone(phone: String): User?
}