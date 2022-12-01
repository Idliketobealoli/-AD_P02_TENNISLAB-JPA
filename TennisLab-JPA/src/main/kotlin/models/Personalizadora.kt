package models

import javax.persistence.*
import models.enums.TipoMaquina
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.UUID


@Entity
@NamedQuery(name = "Personalizadora.findAll", query = "SELECT p FROM Personalizadora p")
class Personalizadora(): Maquina() {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    override var id = super.id
    @Column
    var measuresManeuverability: Boolean = false
    @Column
    var measuresBalance: Boolean = false
    @Column
    var measuresRigidity: Boolean = false
    constructor(
        id: UUID?,
        modelo: String,
        marca: String,
        fechaAdquisicion: LocalDate?,
        numeroSerie: String,
        measuresRigidity: Boolean,
        measuresManeuverability: Boolean,
        measuresBalance: Boolean
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.modelo = modelo
        this.marca = marca
        this.fechaAdquisicion = fechaAdquisicion ?: LocalDate.now()
        this.numeroSerie = numeroSerie
        this.measuresRigidity = measuresRigidity
        this.measuresBalance = measuresBalance
        this.measuresManeuverability = measuresManeuverability
        this.tipoMaquina = TipoMaquina.PERSONALIZADORA
    }

    constructor(
        id: UUID?,
        measuresRigidity: Boolean,
        measuresManeuverability: Boolean,
        measuresBalance: Boolean
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.measuresRigidity = measuresRigidity
        this.measuresBalance = measuresBalance
        this.measuresManeuverability = measuresManeuverability
        this.tipoMaquina = TipoMaquina.PERSONALIZADORA
    }
}
