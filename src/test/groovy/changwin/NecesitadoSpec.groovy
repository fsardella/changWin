package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.LocalDateTime
import static groovy.test.GroovyAssert.shouldFail

class NecesitadoSpec extends Specification implements DomainUnitTest<Necesitado> {
    private String descripcionDeProblema
    private Rubro rubroDeProblema
    private Necesitado necesitado
    private String ubicacionDeProblema
    private List imagenesDeProblema
    private Experto experto
    private Certificado certificado
    private EnteCertificador ente
    
    def setup() {
        descripcionDeProblema = "Goteo"
        rubroDeProblema =  new Rubro(nombre: "plomeria")
        necesitado = new Necesitado(nombre: "Cristo")
        ubicacionDeProblema = "CalleFalsa 123"
        imagenesDeProblema = ["roto.png", "tuberia.png"]
        experto = new Experto(nombre: "Mario")
        certificado = new Certificado(rubro: rubroDeProblema, numeroMatricula: 123546,
                                      fechaEmision: LocalDateTime.of(2022, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2030, 7, 29, 19, 30, 40),
                                      experto: experto)
        ente = new EnteCertificador()
        experto.agregarRubro(certificado, ente)
    }

    def cleanup() {
    }

    void "Crear problema"() {
        Problema problemaEsperado = new Problema(descripcion:descripcionDeProblema, rubro:rubroDeProblema,
                                                necesitado:necesitado, ubicacion:ubicacionDeProblema,
                                                multimedia:imagenesDeProblema)
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema, imagenesDeProblema)
        expect:
            necesitado.obtenerProblemas().size() == 1
            Problema problemaObtenido = necesitado.obtenerProblemas().get(0)
            problemaObtenido.descripcion == problemaEsperado.descripcion
            problemaObtenido.rubro == problemaEsperado.rubro
            problemaObtenido.necesitado == problemaEsperado.necesitado
            problemaObtenido.ubicacion == problemaEsperado.ubicacion
            problemaObtenido.getMultimedia() == problemaEsperado.getMultimedia()
    }

    void "Eliminar problema"() {
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema, imagenesDeProblema)
        Problema problemaAEliminar = necesitado.obtenerProblemas().get(0)
        necesitado.eliminarProblema(problemaAEliminar)
        expect:
            necesitado.obtenerProblemas().size() == 0
    }

    void "Eliminar problema elimina sus cotizaciones"() {
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema, imagenesDeProblema)
        Problema problemaAEliminar = necesitado.obtenerProblemas().get(0)

        experto.cotizarProblema(problemaAEliminar, 10000)
        experto.cotizarProblema(problemaAEliminar, 25000)
        experto.cotizarProblema(problemaAEliminar, 100)

        necesitado.eliminarProblema(problemaAEliminar)
        expect:
            necesitado.obtenerProblemas().size() == 0
            problemaAEliminar.getCotizaciones().size() == 0
            experto.obtenerCotizaciones().size() == 0
    }

    void "No se puede eliminar un problema que fue confirmado"() {
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema, imagenesDeProblema)
        Problema problemaAEliminar = necesitado.obtenerProblemas().get(0)

        experto.cotizarProblema(problemaAEliminar, 10000)
        Cotizacion cotizacion = problemaAEliminar.getCotizaciones().get(0)
        LocalDateTime dia = LocalDateTime.now()
        necesitado.aceptarCotizacion(problemaAEliminar, cotizacion, dia)
        expect:
            shouldFail{necesitado.eliminarProblema(problemaAEliminar)}
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
            necesitado.obtenerProblemas().get(0).esUrgente()
            !necesitado.obtenerProblemas().get(1).esUrgente()
    }

    void "Cambiar descripcion de problema"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        necesitado.cambiarDescripcionProblema(problema, "Goteo de tuberia")
        expect:
            necesitado.obtenerProblemas().get(0).getDescripcion() == "Goteo de tuberia"
    }

    void "Un problema cotizado no puede cambiar su descripción"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        experto.cotizarProblema(problema, 10000)
        expect:
            shouldFail{necesitado.cambiarDescripcionProblema(problema, "Fiesta de cumpleaños")}
    }

    void "Subir nueva imagen al problema"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        necesitado.agregarImagenProblema(problema, "imagen.png")
        expect:
            necesitado.obtenerProblemas().get(0).getMultimedia() == ["roto.png", "tuberia.png", "imagen.png"]
    }

    void "Un problema no confirmado no puede ser calificado"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        expect:
            shouldFail{necesitado.calificar(problema, 10)}
    }

    void "Aceptar una cotizacion"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        BigDecimal costo = 100000
        experto.cotizarProblema(problema, costo)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)
        LocalDateTime dia = LocalDateTime.now()
        necesitado.aceptarCotizacion(problema, cotizacion, dia)
        expect:
            problema.estaConfirmado()
            problema.getCotizacionAceptada() == cotizacion
    }

    void "No se pueden aceptar dos cotizaciones distintas"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        experto.cotizarProblema(problema, 100000)
        experto.cotizarProblema(problema, 250000)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)
        Cotizacion otraCotizacion = problema.getCotizaciones().get(1)
        LocalDateTime dia = LocalDateTime.now()
        necesitado.aceptarCotizacion(problema, cotizacion, dia)
        expect:
            shouldFail{necesitado.aceptarCotizacion(problema, otraCotizacion, dia)}
    }

    void "Calificar un problema confirmado"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        BigDecimal costo = 100000
        experto.cotizarProblema(problema, costo)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)
        LocalDateTime dia = LocalDateTime.now() // dia acordado por chat
        necesitado.aceptarCotizacion(problema, cotizacion, dia)
        Integer calificacion = 10
        necesitado.calificar(problema, calificacion)
        expect:
            problema.getCalificacion() == 10
    }

    void "No se pueden calificar problemas fuera del limite temporal"() {
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                imagenesDeProblema)
        Problema problema1 = necesitado.obtenerProblemas().get(0)
        Problema problema2 = necesitado.obtenerProblemas().get(1)
        experto.cotizarProblema(problema1, 1000)
        experto.cotizarProblema(problema2, 1000)
        Cotizacion cotizacion1 = problema1.getCotizaciones().get(0)
        Cotizacion cotizacion2 = problema2.getCotizaciones().get(0)
        LocalDateTime diaDespues = LocalDateTime.now().plusDays(5)
        LocalDateTime diaMuchoAntes = LocalDateTime.now().minusDays(31)
        necesitado.aceptarCotizacion(problema1, cotizacion1, diaDespues)
        necesitado.aceptarCotizacion(problema2, cotizacion2, diaMuchoAntes)
        expect:
            shouldFail{necesitado.calificar(problema1, 10)}
            shouldFail{necesitado.calificar(problema2, 10)}
    }
    
    void "Chatear con Experto"() {
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema,
                                 ubicacionDeProblema, imagenesDeProblema)
        Problema problema = necesitado.obtenerProblemas().get(0)
        BigDecimal costo = 100000
        experto.cotizarProblema(problema, costo)
        Cotizacion cotizacion = problema.getCotizaciones().get(0)

        Mensaje mensaje = new Mensaje(usuarioEmisor: necesitado, mensaje: "Chau")
        necesitado.chatear(cotizacion, mensaje)
        expect:
            necesitado.mostrarChat(cotizacion) == cotizacion.mostrarChat()
            necesitado.mostrarChat(cotizacion) == ["Cristo: Chau"]
    }
}
