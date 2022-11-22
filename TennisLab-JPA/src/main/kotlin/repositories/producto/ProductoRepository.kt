package repositories.producto

import models.Adquisicion
import models.Producto
import repositories.ICRUDRepository
import java.util.UUID

interface ProductoRepository: ICRUDRepository<Producto, UUID> {
}