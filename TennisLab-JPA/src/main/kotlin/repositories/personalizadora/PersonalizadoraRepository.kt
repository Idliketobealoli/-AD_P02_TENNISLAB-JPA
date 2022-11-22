package repositories.personalizadora

import models.Adquisicion
import models.Personalizadora
import repositories.ICRUDRepository
import java.util.UUID

interface PersonalizadoraRepository: ICRUDRepository<Personalizadora, UUID> {
}