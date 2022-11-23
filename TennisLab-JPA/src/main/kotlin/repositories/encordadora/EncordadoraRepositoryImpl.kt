package repositories.encordadora

import db.HibernateManager
import models.Encordadora
import java.util.*
import javax.persistence.TypedQuery

class EncordadoraRepositoryImpl: EncordadoraRepository {
    override fun readAll(): List<Encordadora> {
        var encordadoras = mutableListOf<Encordadora>()
        HibernateManager.query {
            val query: TypedQuery<Encordadora> = HibernateManager.manager.createNamedQuery("Encordadora.findAll", Encordadora::class.java)
            encordadoras = query.resultList
        }
        return encordadoras
    }

    override fun findById(id: UUID): Encordadora? {
        var encordadora: Encordadora? = null
        HibernateManager.query {
            encordadora = HibernateManager.manager.find(Encordadora::class.java, id)
        }
        return encordadora
    }

    override fun create(entity: Encordadora): Encordadora {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Encordadora): Boolean {
        var result = false
        HibernateManager.transaction {
            val encordadora = HibernateManager.manager.find(Encordadora::class.java, entity.id)
            encordadora?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}