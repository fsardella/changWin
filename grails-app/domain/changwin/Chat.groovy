package changwin

class Chat {
    List mensajes = []

    static constraints = {
    }

    def enviarMensaje(String nombre, String mensaje) {
        mensajes << "${nombre}: ${mensaje}"
    }
    
    def getMensajes() {
        return this.mensajes
    }
}
