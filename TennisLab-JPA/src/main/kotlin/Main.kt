import db.HibernateManager

fun main(args: Array<String>) {
    initDataBase()
}
fun initDataBase() {
    // Probamos la conexión a la base de datos e inicamos los datos!!
    HibernateManager.open()
    HibernateManager.close()
}