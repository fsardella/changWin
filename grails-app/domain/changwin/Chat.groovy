package changwin

class Chat {
    Cotizacion cotizacion
    List<Mensaje> mensajes = []

    static constraints = {
        cotizacion blank: false, nullable: false
    }

    def recibirMensaje(Mensaje mensaje) {
        mensajes << mensaje
    }
    
    def mostrarChat() {
        return this.mensajes.collect{mens -> mens.mostrarMensaje()}
    }
}
