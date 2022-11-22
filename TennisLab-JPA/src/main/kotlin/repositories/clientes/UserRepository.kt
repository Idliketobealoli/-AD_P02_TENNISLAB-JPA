package repositories.clientes

import models.User
import repositories.ICRUDRepository
import java.util.UUID

interface UserRepository: ICRUDRepository<User, UUID> {
}