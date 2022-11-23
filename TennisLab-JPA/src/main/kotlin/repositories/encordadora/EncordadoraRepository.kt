package repositories.encordadora

import models.Encordadora
import repositories.ICRUDRepository
import java.util.UUID

interface EncordadoraRepository: ICRUDRepository<Encordadora, UUID> {
}