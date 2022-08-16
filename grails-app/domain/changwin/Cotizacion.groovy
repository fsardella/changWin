package changwin

import java.time.Duration
import java.time.LocalDateTime

class Cotizacion {
    Dinero costo
    Experto experto
    Problema problema
    EstadoCotizacion estado = EstadoCotizacion.EN_ESPERA
    private LocalDateTime horaDeReunion
    private Integer calificacion = null
    private Chat chat = new Chat(cotizacion: this)

    public enum EstadoCotizacion {
        EN_ESPERA,
        CONFIRMADA,
        RECHAZADA,
        ELIMINADA
    }

    static constraints = {
        costo blank: false, nullable: false
        experto blank: false, nullable: false
        problema blank: false, nullable: false
    }

    def aceptar(LocalDateTime horaReunion) {
        this.horaDeReunion = horaReunion
        this.estado = EstadoCotizacion.CONFIRMADA
    }

    def calificar(Integer calificacion) {
        LocalDateTime horaActual = LocalDateTime.now()
        Duration duration = Duration.between(this.horaDeReunion, horaActual)
        if (this.estaCalificada() || duration.toDays() > 30 || this.horaDeReunion.isAfter(horaActual)) {
            throw new Exception("No es posible calificar")
        }
        this.calificacion = calificacion
    }
    
    def getCalificacion() {
        if (!this.estaCalificada()) {
            throw new Exception("No ha sido calificada")
        }
        return this.calificacion
    }

    def recibirMensaje(Mensaje mensaje) {
        chat.recibirMensaje(mensaje)
    }

    def mostrarChat() {
        chat.mostrarChat()
    }

    def estaConfirmada() {
        return this.estado == EstadoCotizacion.CONFIRMADA
    }

    def estaCalificada() {
        return this.calificacion != null
    }

    def eliminar() {
        problema.eliminarCotizacion(this)
        experto.eliminarCotizacion(this)
    }

    def getRubro() {
        return this.problema.rubro
    }
}
