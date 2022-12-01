package models

import models.enums.PedidoEstado
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
class Pedido() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column(updatable = false, nullable = false)
    @Type(type = "uuid-char")
    lateinit var id: UUID

    //todo arreglar esta relación
    /*@OneToMany(mappedBy = "Pedido", fetch = FetchType.EAGER)
    lateinit var tareas: List<Tarea>*/

    @OneToOne
    lateinit var tarea1: Tarea

    @OneToOne
    var tarea2: Tarea? = null


    @OneToOne
    lateinit var client: User

    @OneToMany(mappedBy = "Pedido", fetch = FetchType.EAGER)
    lateinit var turnos: List<Turno>

    @Column @Embedded // todo mirar cómo pasar el estado del pedido
    lateinit var state: PedidoEstado
    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var fechaEntrada: LocalDate
    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var fechaProgramada: LocalDate
    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var fechaSalida: LocalDate
    @Column
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    lateinit var fechaEntrega: LocalDate
    @Column
    var precio: Double = 0.0

    constructor(
        id: UUID?,
//        tareas: List<Tarea>,
        tarea1: Tarea,
        tarea2: Tarea?,
        client: User,
        turnos: List<Turno>,
        state: PedidoEstado,
        fechaEntrada: LocalDate?,
        fechaProgramada: LocalDate,
        fechaSalida: LocalDate,
        fechaEntrega: LocalDate?
    ): this() {
        this.id = id ?: UUID.randomUUID()
//        this.tareas = tareas
        this.tarea1 = tarea1
        this.tarea2 = tarea2

        this.client = client
        this.turnos = turnos
        this.state = state
        this.fechaEntrada = fechaEntrada ?: LocalDate.now()
        this.fechaProgramada = fechaProgramada
        this.fechaSalida = fechaSalida
        this.fechaEntrega = fechaEntrega ?: fechaSalida

//        this.precio = tareas.sumOf { it.precio }
        if (tarea2 != null) {
            this.precio = tarea1.precio+tarea2.precio
        }else this.precio = tarea1.precio
    }
}
