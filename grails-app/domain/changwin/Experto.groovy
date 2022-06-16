package changwin

class Experto {
    String nombre
    List rubros = []

    static constraints = {
    }

    def getNombre() {
        return this.nombre
    }

    def agregarRubro(String nuevoRubro) { // string o clase ?
        // aca iria lo del certificado
        rubros << nuevoRubro
    }
}
