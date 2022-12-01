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
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")
class Tarea(
){
    @Id @GeneratedValue
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column
    @Type(type = "uuid-char")
    lateinit var id: UUID
    @ManyToOne()
    lateinit var raqueta: Producto
    @Column
    var precio: Double = 0.0
    @ManyToOne()
    lateinit var user: User
    @Column
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
    }
}
