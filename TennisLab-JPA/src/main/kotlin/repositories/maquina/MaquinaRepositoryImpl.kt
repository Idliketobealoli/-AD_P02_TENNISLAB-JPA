package repositories.maquina

import db.HibernateManager
import models.Maquina
import java.util.*
import javax.persistence.TypedQuery

class MaquinaRepositoryImpl: MaquinaRepository {
    override fun readAll(): List<Maquina> {
        var maquinas = mutableListOf<Maquina>()
        HibernateManager.query {
            val query: TypedQuery<Maquina> = HibernateManager.manager.createNamedQuery("Maquina.findAll", Maquina::class.java)
            maquinas = query.resultList
        }
        return maquinas
    }

    override fun findById(id: UUID): Maquina? {
        var maquina: Maquina? = null
        HibernateManager.query {
            maquina = HibernateManager.manager.find(Maquina::class.java, id)
        }
        return maquina
    }

    override fun create(entity: Maquina): Maquina {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Maquina): Boolean {
        var result = false
        HibernateManager.transaction {
            val Maquina = HibernateManager.manager.find(Maquina::class.java, entity.id)
            Maquina?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}