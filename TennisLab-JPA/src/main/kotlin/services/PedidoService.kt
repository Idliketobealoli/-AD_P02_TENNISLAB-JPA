package services

import dto.PedidoDTO
import mappers.PedidoMapper
import models.*
import repositories.pedidos.PedidoRepositoryImpl
import java.util.UUID

class PedidoService : BaseService<Pedido, UUID, PedidoRepositoryImpl>(PedidoRepositoryImpl(
    Pedido, User, Maquina, Producto, Tarea
)) {
    val mapper = PedidoMapper()

    fun getAllPedidos(): List<PedidoDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getPedidoById(id: UUID): PedidoDTO? {
        return this.findById(id)?.let { mapper.toDTO(it) }
    }

    fun createPedido(pedido: PedidoDTO): PedidoDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(pedido)))
    }

    fun deletePedido(pedido: PedidoDTO): Boolean {
        return this.delete(mapper.fromDTO(pedido))
    }


    fun createPedidoInit(pedido: PedidoDTO): PedidoDTO {
        return mapper.toDTO(repository.create(mapper.fromDTO(pedido)))
    }
}