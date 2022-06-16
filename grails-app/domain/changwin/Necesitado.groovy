package changwin

class Necesitado {
    private String nombre
    private String apellido

    static constraints = {
        nombre ([blank:false, nullable:false])
    }

    def nombreCompleto() {
        return "${this.nombre} ${this.apellido}"
    }

    def setNombre(String nombre) {
        this.nombre = nombre
    }
}
