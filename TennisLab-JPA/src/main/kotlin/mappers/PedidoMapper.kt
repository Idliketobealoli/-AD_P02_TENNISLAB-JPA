package mappers

import dto.PedidoDTO
import models.Pedido

class PedidoMapper: BaseMapper<Pedido, PedidoDTO>() {
    override fun fromDTO(item: PedidoDTO): Pedido {
        return Pedido(
            id = item.id,
            tareas = item.tareas,
            client = item.client,
            turnos = item.turnos,
            state = item.state,
            fechaEntrada = item.fechaEntrada,
            fechaProgramada = item.fechaProgramada,
            fechaSalida = item.fechaSalida,
            fechaEntrega = item.fechaEntrega
        )
    }

    override fun toDTO(item: Pedido): PedidoDTO {
        return PedidoDTO(
            id = item.id,
            tareas = item.tareas,
            client = item.client,
            turnos = item.turnos,
            state = item.state,
            fechaEntrada = item.fechaEntrada,
            fechaProgramada = item.fechaProgramada,
            fechaSalida = item.fechaSalida,
            fechaEntrega = item.fechaEntrega
        )
    }
}