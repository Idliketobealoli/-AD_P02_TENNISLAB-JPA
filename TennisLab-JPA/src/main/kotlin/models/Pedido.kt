package models

import models.enums.PedidoEstado
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PEDIDOS")
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
class Pedido() {
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    lateinit var id: UUID
    @OneToMany(mappedBy = "Pedido", fetch = FetchType.EAGER)
    lateinit var tareas: List<Tarea>
    lateinit var client: User
    @OneToMany(mappedBy = "Pedido", fetch = FetchType.EAGER)
    lateinit var turnos: List<Turno>
    lateinit var state: PedidoEstado
    @Column(name = "fecha_entrada")
    lateinit var fechaEntrada: LocalDate
    @Column(name = "fecha_programada")
    lateinit var fechaProgramada: LocalDate
    @Column(name = "fecha_salida")
    lateinit var fechaSalida: LocalDate
    @Column(name = "fecha_entrega")
    lateinit var fechaEntrega: LocalDate
    var precio: Double = 0.0

    constructor(
        id: UUID?,
        tareas: List<Tarea>,
        client: User,
        turnos: List<Turno>,
        state: PedidoEstado,
        fechaEntrada: LocalDate?,
        fechaProgramada: LocalDate,
        fechaSalida: LocalDate,
        fechaEntrega: LocalDate?
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.tareas = tareas
        this.client = client
        this.turnos = turnos
        this.state = state
        this.fechaEntrada = fechaEntrada ?: LocalDate.now()
        this.fechaProgramada = fechaProgramada
        this.fechaSalida = fechaSalida
        this.fechaEntrega = fechaEntrega ?: fechaSalida
        this.precio = tareas.sumOf { it.precio }
    }
}
