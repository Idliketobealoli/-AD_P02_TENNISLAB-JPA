package repositories.pedidos

import models.Adquisicion
import models.Pedido
import repositories.ICRUDRepository
import java.util.UUID

interface PedidoRepository: ICRUDRepository<Pedido, UUID> {
}