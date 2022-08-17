package changwin

import java.time.Duration
import java.time.LocalDateTime

public enum EstadoCotizacion {
    EN_ESPERA,
    CONFIRMADA,
    RECHAZADA,
    ELIMINADA
}

class Cotizacion {
    BigDecimal costo
    Experto experto
    Problema problema
    EstadoCotizacion estado = EstadoCotizacion.EN_ESPERA
    LocalDateTime horaDeReunion
    private Integer calificacion = null
    private Chat chat = new Chat(cotizacion: this)

    static belongsTo = [
        experto: Experto,
        problema: Problema
    ]

    static constraints = {
        costo blank: false, nullable: true
        experto blank: false, nullable: false
        problema blank: false, nullable: false
        horaDeReunion nullable: true
        calificacion nullable: true
        chat nullable: true
    }

    def aceptar(LocalDateTime horaReunion) {
        this.setHoraDeReunion(horaReunion)
        this.setEstado(EstadoCotizacion.CONFIRMADA)
    }

    def rechazar() {
        this.setEstado(EstadoCotizacion.RECHAZADA)
    }

    def calificar(Integer calificacion) {
        LocalDateTime horaActual = LocalDateTime.now()
        Duration duration = Duration.between(this.horaDeReunion, horaActual)
        if (this.estaCalificada() || duration.toDays() > 30 || this.horaDeReunion.isAfter(horaActual)) {
            throw new Exception("No es posible calificar")
        }
        this.calificacion = calificacion
    }
    
    def conseguirCalificacion() {
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
    
    String toString() {
        return 'Cotizacion: $' << costo.setScale(2)
    }
}
