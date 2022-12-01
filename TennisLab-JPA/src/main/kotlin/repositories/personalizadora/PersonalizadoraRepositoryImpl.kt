package repositories.personalizadora

import db.HibernateManager
import models.Maquina
import models.Personalizadora
import java.util.*
import javax.persistence.TypedQuery

class PersonalizadoraRepositoryImpl(personalizadora: Personalizadora, maquina: Maquina) :PersonalizadoraRepository {
    override fun readAll(): List<Personalizadora> {
        var personalizadoras = mutableListOf<Personalizadora>()
        HibernateManager.query {
            val query: TypedQuery<Personalizadora> = HibernateManager.manager.createNamedQuery("Personalizadora.findAll", Personalizadora::class.java)
            personalizadoras = query.resultList
        }
        return personalizadoras
    }

    override fun findById(id: UUID): Personalizadora? {
        var personalizadora: Personalizadora? = null
        HibernateManager.query {
            personalizadora = HibernateManager.manager.find(Personalizadora::class.java, id)
        }
        return personalizadora
    }

    override fun create(entity: Personalizadora): Personalizadora {
        HibernateManager.transaction {
            HibernateManager.manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Personalizadora): Boolean {
        var result = false
        HibernateManager.transaction {
            val personalizadora = HibernateManager.manager.find(Personalizadora::class.java, entity.id)
            personalizadora?.let {
                HibernateManager.manager.remove(it)
                result = true
            }
        }
        return result
    }
}