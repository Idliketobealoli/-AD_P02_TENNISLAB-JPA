package repositories.pedidos

import db.HibernateManager
import models.*
import java.util.*
import javax.persistence.TypedQuery

class PedidoRepositoryImpl(pedido: Pedido, user: User, maquina: Maquina, producto: Producto, tarea: Tarea) : PedidoRepository {
    override fun readAll(): List<Pedido> {
        var pedidos = mutableListOf<Pedido>()
        HibernateManager.query {
            val query: TypedQuery<Pedido> = HibernateManager.manager.createNamedQuery("Pedido.findAll", Pedido::class.java)
            pedidos = query.resultList
        }
        return pedidos
    }

    override fun findById(id: UUID): Pedido? {
        var pedido: Pedido? = null
        HibernateManager.query {
            pedido = HibernateManager.manager.find(Pedido::class.java, id)
        }
        return pedido
    }

    override fun create(entity: Pedido): Pedido {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Pedido): Boolean {
        var result = false
        HibernateManager.transaction {
            val pedido = HibernateManager.manager.find(Pedido::class.java, entity.id)
            pedido?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}