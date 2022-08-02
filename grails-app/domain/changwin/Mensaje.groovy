package changwin

class Mensaje {
    Usuario usuarioEmisor
    String mensaje

    static constraints = {
        usuarioEmisor nullable: false
        mensaje blank: false, nullable: false
    }

    def mostrarMensaje() {
        return "${usuarioEmisor.nombre}: ${mensaje}"
    }
}
