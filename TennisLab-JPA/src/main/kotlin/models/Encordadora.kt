package models

import models.enums.TipoMaquina
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.NamedQuery

@Entity
@NamedQuery(name = "Encordadora.findAll", query = "SELECT e FROM Encordadora e")
class Encordadora():Maquina() {
    @Id @GeneratedValue()
    @Type(type = "uuid-char")
    override var id = super.id
    @Column(name = "is_manual")
    var isManual: Boolean = false
    @Column(name = "max_tension")
    var maxTension: Double = 0.0
    @Column(name = "min_tension")
    var minTension: Double = 0.0
    constructor(
        id: UUID?,
        modelo: String,
        marca: String,
        fechaAdquisicion: LocalDate?,
        numeroSerie: String,
        isManual: Boolean,
        maxTension: Double,
        minTension: Double
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.modelo = modelo
        this.marca = marca
        this.fechaAdquisicion = fechaAdquisicion ?: LocalDate.now()
        this.numeroSerie = numeroSerie
        this.isManual = isManual
        this.maxTension = maxTension
        this.minTension = minTension
        this.tipoMaquina = TipoMaquina.ENCORDADORA
    }

    constructor(
        id: UUID?,
        isManual: Boolean,
        maxTension: Double,
        minTension: Double
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.isManual = isManual
        this.maxTension = maxTension
        this.minTension = minTension
        this.tipoMaquina = TipoMaquina.ENCORDADORA
    }
}