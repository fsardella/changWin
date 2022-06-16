package changwin

class Necesitado {
    private String nombre
    List publicaciones = []

    static constraints = {
        nombre ([blank:false, nullable:false])
    }

    def cambiarNombre(String nombre) {
        this.nombre = nombre
    }

    def obtenerNombre() {
        return this.nombre
    }

    def agregarPublicacion(Problema problema) {
        publicaciones << problema
    }
}
