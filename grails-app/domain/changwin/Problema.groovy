package changwin

import java.time.LocalDate

class Problema {
    private String descripcion
    private String rubro
    private Necesitado necesitado
    private String ubicacion
    private String estado = "stand by"
    private Laburo laburo = null
    private Boolean emergencia = false
    private List multimedia = []
    private List cotizaciones = []

    static constraints = {
        descripcion ([blank:false, nullable:false])
        rubro ([blank:false, nullable:false])
        necesitado ([blank:false, nullable:false])
        ubicacion ([blank:false, nullable:false])
        ubicacion ([blank:true, nullable:false])
        multimedia ([blank:true, nullable:false])
    }

    def cotizar(Cotizacion cotizacion) {
        cotizaciones << cotizacion
    }

    def getCotizaciones() {
        return this.cotizaciones.clone() // esto esta ok?
    }
    
    def agregarCotizacion(Cotizacion newCotizacion) {
        this.cotizaciones << newCotizacion
    }
    
    def getDescripcion() {
        return this.descripcion
    }
    
    def cambiarDescripcion(String newDesc) {
        this.descripcion = newDesc
    }
    
    def getRubro() {
        return this.rubro    
    }
    
    def getNecesitado() {
        return this.necesitado    
    }
    
    def getUbicacion() {
        return this.ubicacion
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

    def aceptarCotizacion(Cotizacion cotizacion, LocalDate fechaDeReunion) {
        this.estado = "confirmado"
        this.laburo = new Laburo(horaDeReunion:fechaDeReunion, experto:cotizacion.getExperto())
    }

    def getLaburo() {
        return this.laburo
    }

    def estaConfirmada() {
        return this.estado == "confirmado"
    }

    def calificar(Integer calificacion) {
        laburo.calificar(calificacion)
    }


}
