package changwin

abstract class Usuario {
    String nombre
    String mail
    String contrasenia

    static constraints = {
        nombre blank: false, nullable: false, unique: true
        mail email: true, blank: false, nullable: false
        contrasenia password: true, blank: false, nullable: false
    }

    def chatear(Cotizacion cotizacion, Mensaje mensaje) {
        cotizacion.recibirMensaje(mensaje)
    }

    def mostrarChat(Cotizacion cotizacion) {
        cotizacion.mostrarChat()
    }
}
