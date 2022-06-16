package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProblemaSpec extends Specification implements DomainUnitTest<Problema> {
    private Problema problema
    private String descripcionDeProblema
    private String rubroDeProblema
    private Necesitado necesitadoDeProblema
    private String ubicacionDeProblema
    private List imagenesDeProblema
    
    def setup() {
        descripcionDeProblema = "Goteo"
        rubroDeProblema =  "plomeria"
        necesitadoDeProblema = new Necesitado(nombre: "Cristo")
        ubicacionDeProblema = "CalleFalsa 123"
        imagenesDeProblema = ["roto.png", "tuberia.png"]
        problema = new Problema(descripcion: descripcionDeProblema,
                                rubro: rubroDeProblema,
                                necesitado: necesitadoDeProblema,
                                ubicacion: ubicacionDeProblema,
                                multimedia: imagenesDeProblema)
    }

    def cleanup() {
    }

    void "Problema es creado con todos sus datos necesarios"() {
        expect:
            problema.getDescripcion() == descripcionDeProblema
            problema.getRubro() == rubroDeProblema
            problema.getNecesitado() == necesitadoDeProblema
            problema.getUbicacion() == ubicacionDeProblema
            problema.getMultimedia() == imagenesDeProblema
    }
    
    void "Problema puede cambiar descripcion"() {
        String nuevaDesc = "Goteo ocacionado por traumatismo en tuberia"
        problema.cambiarDescripcion(nuevaDesc)
        expect:
            problema.getDescripcion() == nuevaDesc
    }
    
    void "Problema puede agregar una nueva imagen"() {
        String nuevaImagen = "tuberiaConZoom.png"
        List imagenesEsperadas = problema.getMultimedia() << nuevaImagen
        problema.agregarMultimedia(nuevaImagen)
        expect:
            problema.getMultimedia() == imagenesEsperadas 
        
    }
    
    void "Problema puede agregar una cotizacion"() {
        Experto tipoExperto = new Experto(nombre: "Hola")
        tipoExperto.agregarRubro(rubroDeProblema)
        Cotizacion cotizacion = new Cotizacion(costo: 500, experto: tipoExperto)
        problema.agregarCotizacion(cotizacion)
        expect:
            problema.getCotizaciones().contains(cotizacion)
    }
    
}
