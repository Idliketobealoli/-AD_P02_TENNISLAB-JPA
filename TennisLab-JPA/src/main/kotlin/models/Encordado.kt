package models

import models.enums.TipoTarea
import org.hibernate.annotations.Type
import sun.jvm.hotspot.gc.shared.Generation
import java.util.*
import javax.persistence.*

@Entity
@NamedQuery(name = "Encordado.findAll", query = "SELECT e FROM Encordado e")
class Encordado():Tarea() {
    @Id @GeneratedValue()
    @Type(type = "uuid-char")
    override var id: UUID = super.id
    @Column(name = "tension_horizontal")
    var tensionHorizontal: Double = 0.0
    @Column(name = "cordaje_horizontal") @Embedded
    lateinit var cordajeHorizontal: Producto
    @Column(name = "tension_vertical")
    var tensionVertical: Double = 0.0
    @Column(name = "cordaje_vertical") @Embedded
    lateinit var cordajeVertical: Producto
    @Column(name = "dos_nudos")
    var dosNudos: Boolean = false

    constructor(
        id: UUID?,
        raqueta: Producto,
        user: User,
        tensionHorizontal: Double,
        cordajeHorizontal: Producto,
        tensionVertical: Double,
        cordajeVertical: Producto,
        dosNudos: Boolean
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.raqueta = raqueta
        this.user = user
        this.tensionHorizontal = tensionHorizontal
        this.tensionVertical = tensionVertical
        this.cordajeHorizontal = cordajeHorizontal
        this.cordajeVertical = cordajeVertical
        this.dosNudos = dosNudos
        this.precio = (15.0+cordajeHorizontal.precio+cordajeVertical.precio)
        this.tipoTarea = TipoTarea.ENCORDADO
    }

    constructor(
        id: UUID?,
        tensionHorizontal: Double,
        cordajeHorizontal: Producto,
        tensionVertical: Double,
        cordajeVertical: Producto,
        dosNudos: Boolean
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.tensionHorizontal = tensionHorizontal
        this.tensionVertical = tensionVertical
        this.cordajeHorizontal = cordajeHorizontal
        this.cordajeVertical = cordajeVertical
        this.dosNudos = dosNudos
        this.precio = (15.0+cordajeHorizontal.precio+cordajeVertical.precio)
        this.tipoTarea = TipoTarea.ENCORDADO
    }
}
