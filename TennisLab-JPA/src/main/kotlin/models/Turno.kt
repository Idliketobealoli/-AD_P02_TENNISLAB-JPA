package models

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t")
class Turno() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column
    @Type(type = "uuid-char")
    lateinit var id: UUID
    @OneToOne
    lateinit var worker: User
    @OneToOne
    lateinit var maquina: Maquina
    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var horaInicio: LocalDateTime
    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var horaFin: LocalDateTime
    var numPedidosActivos: Int = 0
    @OneToOne
    var tarea1: Tarea? = null
    @OneToOne
    var tarea2: Tarea? = null

    constructor(
        id: UUID?,
        worker: User,
        maquina: Maquina,
        horaInicio: LocalDateTime,
        horaFin: LocalDateTime?,
        tarea1: Tarea?,
        tarea2: Tarea?
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.worker = worker
        this.maquina = maquina
        this.horaInicio = horaInicio
        this.horaFin = horaFin ?: this.horaInicio.plusHours(4L)
        this.tarea1 = tarea1
        this.tarea2 = tarea2

        if (this.tarea1 != null) {
            numPedidosActivos++
        }
        if (this.tarea2 != null) {
            numPedidosActivos++
        }
    }

}