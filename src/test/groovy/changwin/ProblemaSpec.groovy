package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.LocalDateTime

class ProblemaSpec extends Specification implements DomainUnitTest<Problema> {
    private Problema problema
    private String descripcionDeProblema
    private Rubro rubroDeProblema
    private Necesitado necesitadoDeProblema
    private String ubicacionDeProblema
    private BarrioProblema barrioDeProblema
    private List imagenesDeProblema
    private Experto experto
    private Certificado certificado
    private EnteCertificador ente
    
    def setup() {
        descripcionDeProblema = "Goteo"
        rubroDeProblema =  new Rubro(nombre: "plomeria")
        necesitadoDeProblema = new Necesitado(nombre: "Cristo")
        ubicacionDeProblema = "CalleFalsa 123"
        barrioDeProblema = BarrioProblema.PALERMO
        imagenesDeProblema = ["roto.png", "tuberia.png"]
        problema = new Problema(descripcion: descripcionDeProblema,
                                rubro: rubroDeProblema,
                                necesitado: necesitadoDeProblema,
                                ubicacion: ubicacionDeProblema,
                                barrio: barrioDeProblema,
                                multimedia: imagenesDeProblema)
        experto = new Experto(nombre: "Flor")
        certificado = new Certificado(rubro: rubroDeProblema, numeroMatricula: 123546,
                                      fechaEmision: LocalDateTime.of(2022, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2030, 7, 29, 19, 30, 40),
                                      experto: experto)
        ente = new EnteCertificador()
    }

    def cleanup() {
    }

    void "Problema es creado con todos sus datos necesarios"() {
        expect:
            problema.descripcion == descripcionDeProblema
            problema.rubro == rubroDeProblema
            problema.necesitado == necesitadoDeProblema
            problema.ubicacion == ubicacionDeProblema
            problema.barrio == barrioDeProblema
            problema.getMultimedia() == imagenesDeProblema
    }
    
    void "Problema puede cambiar descripcion"() {
        String nuevaDesc = "Goteo ocasionado por traumatismo en tuberia"
        problema.cambiarDescripcion(nuevaDesc)
        expect:
            problema.descripcion == nuevaDesc
    }
    
    void "Problema puede agregar una nueva imagen"() {
        String nuevaImagen = "tuberiaConZoom.png"
        List imagenesEsperadas = problema.getMultimedia() << nuevaImagen
        problema.agregarMultimedia(nuevaImagen)
        expect:
            problema.getMultimedia() == imagenesEsperadas 
        
    }
    
    void "Problema puede agregar una cotizacion"() {
        experto.agregarRubro(certificado, ente)
        Cotizacion cotizacion = new Cotizacion(costo: 500, experto: experto)
        problema.agregarCotizacion(cotizacion)
        expect:
            problema.getCotizaciones().contains(cotizacion)
    }
}
