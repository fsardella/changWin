package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.LocalDate

class NecesitadoSpec extends Specification implements DomainUnitTest<Necesitado> {
    private String descripcionDeProblema
    private String rubroDeProblema
    private Necesitado necesitado
    private String ubicacionDeProblema
    private List imagenesDeProblema
    
    def setup() {
        descripcionDeProblema = "Goteo"
        rubroDeProblema =  "plomeria"
        necesitado = new Necesitado(nombre: "Cristo")
        ubicacionDeProblema = "CalleFalsa 123"
        imagenesDeProblema = ["roto.png", "tuberia.png"]
    }

    def cleanup() {
    }

    void "Crear problema"() {
        Problema problemaEsperado = new Problema(descripcion:descripcionDeProblema, rubro:rubroDeProblema,
                                                necesitado:necesitado, ubicacion:ubicacionDeProblema,
                                                multimedia:imagenesDeProblema)
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema, imagenesDeProblema)
        expect:
            necesitado.getProblemas().size() == 1
            Problema problemaObtenido = necesitado.getProblemas().get(0)
            problemaObtenido.getDescripcion() == problemaEsperado.getDescripcion()
            problemaObtenido.getRubro() == problemaEsperado.getRubro()
            problemaObtenido.getNecesitado() == problemaEsperado.getNecesitado()
            problemaObtenido.getUbicacion() == problemaEsperado.getUbicacion()
            problemaObtenido.getMultimedia() == problemaEsperado.getMultimedia()
    }

    void "Eliminar problema"() {
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema, imagenesDeProblema)
        Problema problemaAEliminar = necesitado.getProblemas().get(0)
        necesitado.eliminarProblema(problemaAEliminar)
        expect:
            necesitado.getProblemas().size() == 0
    }

    void "Crear un problema con emergencia"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema, 
                                true)
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        expect:
            necesitado.getProblemas().get(0).esUrgente()
            !necesitado.getProblemas().get(1).esUrgente()
    }

    void "Cambiar descripcion de problema"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.getProblemas().get(0)
        necesitado.cambiarDescripcionProblema(problema, "Goteo de tuberia")
        expect:
            necesitado.getProblemas().get(0).getDescripcion() == "Goteo de tuberia"
    }

    void "Subir nueva imagen al problema"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.getProblemas().get(0)
        necesitado.agregarImagenProblema(problema, "imagen.png")
        expect:
            necesitado.getProblemas().get(0).getMultimedia() == ["roto.png", "tuberia.png", "imagen.png"]
    }

    void "Aceptar una cotizacion"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.getProblemas().get(0)
        Experto experto = new Experto(nombre:"Mario")
        experto.agregarRubro("plomeria")
        BigDecimal costo = 100000
        experto.cotizarProblema(problema, costo)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)
        LocalDate dia = LocalDate.now()
        problema.aceptarCotizacion(cotizacion, dia)
        expect:
            problema.estaConfirmada()
            problema.getLaburo() != null
            problema.getLaburo().getExperto() == experto
            problema.getLaburo().getHoraDeReunion() == dia
    }

    void "Calificar un laburo"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.getProblemas().get(0)
        Experto experto = new Experto(nombre:"Mario")
        experto.agregarRubro("plomeria")
        BigDecimal costo = 100000
        experto.cotizarProblema(problema, costo)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)
        LocalDate dia = LocalDate.now()
        problema.aceptarCotizacion(cotizacion, dia)
        Integer calificacion = 10
        problema.calificar(calificacion)
        Laburo laburo = problema.getLaburo()
        expect:
            laburo.getCalificacion() == 10
    }
    
    void "Chatear con Experto"() {
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema,
                                 ubicacionDeProblema, imagenesDeProblema)
        Problema problema = necesitado.getProblemas().get(0)
        Experto experto = new Experto(nombre:"Mario")
        experto.agregarRubro("plomeria")
        BigDecimal costo = 100000
        experto.cotizarProblema(problema, costo)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)
        
        necesitado.iniciarChat(cotizacion)
        necesitado.chatear(cotizacion, "Chau")
        expect:
            necesitado.seeChat(cotizacion) == problema.seeChat(experto.getNombre())
            necesitado.seeChat(cotizacion) == ["Cristo: Chau"]
    }
}
