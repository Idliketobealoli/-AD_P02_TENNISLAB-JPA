package repositories.personalizacion

import models.Adquisicion
import models.Personalizacion
import repositories.ICRUDRepository
import java.util.UUID

interface PersonalizacionRepository: ICRUDRepository<Personalizacion, UUID> {
}