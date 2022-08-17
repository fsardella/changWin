package changwin

import java.time.LocalDateTime

class Certificado {
    Rubro rubro
    EstadoCertificacion estado = EstadoCertificacion.EN_ESPERA
    Integer numeroMatricula
    LocalDateTime fechaEmision
    LocalDateTime fechaVencimiento
    Experto experto

    public enum EstadoCertificacion {
        CERTIFICADO,
        EN_ESPERA,
        RECHAZADO
    }

    static hasOne = [rubro: Rubro]

    static belongsTo = [experto: Experto]

    static constraints = {
        numeroMatricula blank: false, nullable: false
        fechaEmision blank: false, nullable: false
        fechaVencimiento blank: false, nullable: false
    }

    String toString() {
        return rubro
    }

    def aceptar() {
        this.estado = EstadoCertificacion.CERTIFICADO
        this.experto.actualizarCertificado(this)
    }

    def esValido() {
        return this.estado == EstadoCertificacion.CERTIFICADO &&
               LocalDateTime.now().isBefore(fechaVencimiento)
    }
}
