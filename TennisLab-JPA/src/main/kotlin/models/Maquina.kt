package models

import models.enums.TipoMaquina
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m")
@Embeddable
open class Maquina() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column
    @Type(type = "uuid-char")
    open lateinit var id: UUID
    @Column
    lateinit var modelo: String
    @Column
    lateinit var marca: String

    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var fechaAdquisicion: LocalDate
    @Column
    lateinit var numeroSerie: String
//    Para ver el tipo de máquina se puede mirar siguiendo el uuid
    @Embedded
    lateinit var tipoMaquina: TipoMaquina

    constructor(
        id: UUID?,
        modelo: String,
        marca: String,
        fechaAdquisicion:LocalDate?,
        numeroSerie: String,
        tipoMaquina: TipoMaquina
    ) : this(){
        this.id = id ?: UUID.randomUUID()
        this.modelo = modelo
        this.marca = marca
        this.fechaAdquisicion = fechaAdquisicion ?: LocalDate.now()
        this.numeroSerie = numeroSerie
        this.tipoMaquina = tipoMaquina
    }
}
