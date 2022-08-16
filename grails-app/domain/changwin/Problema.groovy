package changwin

import java.time.LocalDateTime
import org.apache.commons.lang3.StringUtils

public enum BarrioProblema {
    PALERMO,
    SAN_TELMO,
    RECOLETA
}

public enum EstadoEmergencia {
    URGENTE,
    REGULAR
}

class Problema {
    String descripcion
    Rubro rubro
    Necesitado necesitado
    String ubicacion
    BarrioProblema barrio
    EstadoEmergencia emergencia = EstadoEmergencia.REGULAR
    private EstadoProblema estado = EstadoProblema.EN_ESPERA
    private List<String> multimedia = []
    Set<Cotizacion> cotizaciones = []

    public enum EstadoProblema {
        EN_ESPERA,
        CONFIRMADO
    }
    
    static hasMany = [
        cotizaciones: Cotizacion
    ]

    static constraints = {
        descripcion blank: false, nullable: false
        rubro blank: false, nullable: false
        necesitado blank: false, nullable: false
        ubicacion blank: false, nullable: false
        barrio blank: false, nullable: false
    }

    static belongsTo = [
        necesitado: Necesitado
    ]

    String toString() {
        String ret = "${rubro}: ${descripcion}"
        return ret.size() > 20 ? StringUtils.abbreviate(ret, 20) : ret
    }

    def getCotizacionAceptada() {
        Set<Cotizacion> aceptadas = cotizaciones.findAll{cot -> cot.estaConfirmada()}
        if (aceptadas.size() != 1) {
            throw new Exception("No hay una cotizacion confirmada, o hay mas de una")
        }
        return aceptadas.iterator().next()
    }
    
    def agregarCotizacion(Cotizacion cotizacion) {
        this.cotizaciones << cotizacion
    }
    
    def cambiarDescripcion(String newDesc) {
        if (!this.cotizaciones.isEmpty()) {
            throw new Exception("Esta problema ya fue cotizado")
        }
        this.descripcion = newDesc
    }
    
    def getMultimedia() {
        return this.multimedia.clone()
    }
    
    def agregarMultimedia(String newMultimedia) {
        this.multimedia << newMultimedia
    }

    def esUrgente() {
        return this.emergencia == EstadoEmergencia.URGENTE
    }

    def aceptarCotizacion(Cotizacion cotizacion, LocalDateTime fechaDeReunion) {
        if (this.estaConfirmado()) {
            throw new Exception("Una cotizacion ya fue aceptada")
        }
        this.estado = EstadoProblema.CONFIRMADO
        cotizacion.aceptar(fechaDeReunion)
    }
    
    def calificar(Integer calificacion) {
        getCotizacionAceptada().calificar(calificacion)
    }

    def estaConfirmado() {
        return this.estado == EstadoProblema.CONFIRMADO
    }

    def getCalificacion() {
        return getCotizacionAceptada().getCalificacion()
    }

    def eliminarCotizacion(Cotizacion cotizacion) {
        if (!this.cotizaciones.contains(cotizacion)) {
            return
        }
        this.cotizaciones.removeElement(cotizacion)
        cotizacion.eliminar()
    }

    def eliminar() {
        this.cotizaciones.clone().forEach{cot -> cot.eliminar()}
    }
}
