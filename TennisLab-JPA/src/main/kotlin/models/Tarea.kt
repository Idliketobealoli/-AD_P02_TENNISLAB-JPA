package models

import models.enums.TipoTarea
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 * @author Iván Azagra Troya
 * Clase abstracta de la entidad tarea con un identificador
 * y el producto que se pasará
 */
// TODO revisar el funcionamiento de esta clase
@Entity
@Table(name = "TAREAS")
@NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")
data class Tarea(
    @Id @GeneratedValue
    // @UuidGenerator // Hibernate 6
    // Hibernate 5
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    val id: UUID = UUID.randomUUID(),
    @OneToMany(mappedBy = "tarea", orphanRemoval = true, fetch = FetchType.EAGER)
    val producto: Producto,
    var precio: Double = 0.0,
//    TODO revisar el ManyToOne
    @ManyToOne()
    var user: User,
    var tipo: TipoTarea
){
/*    open lateinit var id: UUID
    lateinit var raqueta: Producto
    open var precio: Double = 0.0
    lateinit var user: User
    lateinit var tipoTarea: TipoTarea

    constructor(
        id: UUID?,
        raqueta: Producto,
        precio: Double?,
        user: User,
        tipoTarea: TipoTarea
    ) : this () {
        this.id = id ?: UUID.randomUUID()
        this.raqueta = raqueta
        this.precio = precio ?: 0.0
        this.user = user
        this.tipoTarea = tipoTarea
    }*/
}
