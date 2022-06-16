package changwin

class Necesitado {
    private String nombre
    private String metodoDePago
    List publicaciones = []

    static constraints = {
        nombre ([blank:false, nullable:false])
        metodoDePago ([blank:false, nullable:false])
    }

    def cambiarNombre(String nombre) {
        this.nombre = nombre
    }

    def obtenerNombre() {
        return this.nombre
    }

    def cambiarMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago
    }

    def obtenerMetodoDePago() {
        return this.metodoDePago
    }

    def agregarPublicacion(Publicacion publicacion) {
        publicaciones << publicacion
    }
}
