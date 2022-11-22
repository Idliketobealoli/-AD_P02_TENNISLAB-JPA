package repositories.producto

import db.HibernateManager
import models.Producto
import java.util.*
import javax.persistence.TypedQuery

class ProductoRepositoryImpl: ProductoRepository {
    override fun readAll(): List<Producto> {
        var productos = mutableListOf<Producto>()
        HibernateManager.query {
            val query: TypedQuery<Producto> = HibernateManager.manager.createNamedQuery("Producto.findAll", Producto::class.java)
            productos = query.resultList
        }
        return productos
    }

    override fun findById(id: UUID): Producto? {
        var producto: Producto? = null
        HibernateManager.query {
            producto = HibernateManager.manager.find(Producto::class.java, id)
        }
        return producto
    }

    override fun create(entity: Producto): Producto {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Producto): Boolean {
        var result = false
        HibernateManager.transaction {
            val producto = HibernateManager.manager.find(Producto::class.java, entity.id)
            producto?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}