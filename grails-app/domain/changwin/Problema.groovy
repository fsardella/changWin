package changwin

import java.time.LocalDateTime

class Problema {
    String descripcion
    Rubro rubro
    Necesitado necesitado
    String ubicacion
    Boolean emergencia = false
    private EstadoProblema estado = EstadoProblema.EN_ESPERA
    private List<String> multimedia = []
    private List<Cotizacion> cotizaciones = []

    public enum EstadoProblema {
        EN_ESPERA,
        CONFIRMADO
    }

    static constraints = {
        descripcion blank: false, nullable: false
        rubro blank: false, nullable: false
        necesitado blank: false, nullable: false
        ubicacion blank: false, nullable: false
    }

    def getCotizaciones() {
        return this.cotizaciones.clone()
    }

    def getCotizacionAceptada() {
        List aceptadas = cotizaciones.findAll{cot -> cot.estaConfirmada()}
        if (aceptadas.size() != 1) {
            throw new Exception("No hay una cotizacion confirmada, o hay mas de una")
        }
        return aceptadas.get(0)
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
        return this.emergencia
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
