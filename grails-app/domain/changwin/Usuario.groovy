package changwin

abstract class Usuario {
    String nombre

    static constraints = {
        nombre blank: false, nullable: false
    }

    def chatear(Cotizacion cotizacion, Mensaje mensaje) {
        cotizacion.recibirMensaje(mensaje)
    }

    def mostrarChat(Cotizacion cotizacion) {
        cotizacion.mostrarChat()
    }
}
