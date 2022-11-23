package services

import dto.ProductoDTO
import mappers.ProductoMapper
import models.Producto
import repositories.producto.ProductoRepositoryImpl
import java.util.*

class ProductoService: BaseService<Producto, UUID, ProductoRepositoryImpl>(ProductoRepositoryImpl(Producto)) {
    val mapper = ProductoMapper()

    fun getAllProductos(): List<ProductoDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getProductoById(id: UUID): ProductoDTO? {
        return this.findById(id)?.let { mapper.toDTO(it) }
    }

    fun createProducto(user: ProductoDTO): ProductoDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(user)))
    }

    fun deleteProducto(user: ProductoDTO): Boolean {
        return this.delete(mapper.fromDTO(user))
    }

    fun createProductoInit(user: ProductoDTO): ProductoDTO {
        return mapper.toDTO(repository.create(mapper.fromDTO(user)))
    }
}