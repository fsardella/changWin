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
    private BarrioProblema barrioDeProblema
    private List imagenesDeProblema
    private Experto experto
    private Certificado certificado
    private EnteCertificador ente
    
    def setup() {
        descripcionDeProblema = "Goteo"
        rubroDeProblema =  new Rubro(nombre: "plomeria")
        necesitado = new Necesitado(nombre: "Cristo")
        ubicacionDeProblema = "CalleFalsa 123"
        barrioDeProblema = BarrioProblema.PALERMO
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
                                                barrio: barrioDeProblema,
                                                multimedia:imagenesDeProblema)
        necesitado.crearProblema(descripcionDeProblema, rubroDeProblema, ubicacionDeProblema,
                                 barrioDeProblema, imagenesDeProblema)
        expect:
            necesitado.problemas.size() == 1
            necesitado.problemas.forEach {
                problemaObtenido -> 
                problemaObtenido.descripcion == problemaEsperado.descripcion
                problemaObtenido.rubro == problemaEsperado.rubro
                problemaObtenido.necesitado == problemaEsperado.necesitado
                problemaObtenido.ubicacion == problemaEsperado.ubicacion
                problemaObtenido.barrio == problemaEsperado.barrio
                problemaObtenido.getMultimedia() == problemaEsperado.getMultimedia()
            }
    }

    void "Eliminar problema"() {
        Problema problemaAEliminar = necesitado.crearProblema(descripcionDeProblema,
                                                              rubroDeProblema,
                                                              ubicacionDeProblema,
                                                              barrioDeProblema,
                                                              imagenesDeProblema)
        necesitado.eliminarProblema(problemaAEliminar)
        expect:
            necesitado.problemas.size() == 0
    }

    void "Eliminar problema elimina sus cotizaciones"() {
        Problema problemaAEliminar = necesitado.crearProblema(descripcionDeProblema,
                                                              rubroDeProblema,
                                                              ubicacionDeProblema,
                                                              barrioDeProblema,
                                                              imagenesDeProblema)
        experto.cotizarProblema(problemaAEliminar, 10000)
        experto.cotizarProblema(problemaAEliminar, 25000)
        experto.cotizarProblema(problemaAEliminar, 100)

        necesitado.eliminarProblema(problemaAEliminar)
        expect:
            necesitado.problemas.size() == 0
            problemaAEliminar.cotizaciones.size() == 0
            experto.cotizaciones.size() == 0
    }

    void "No se puede eliminar un problema que fue confirmado"() {
        Problema problemaAEliminar = necesitado.crearProblema(descripcionDeProblema,
                                                              rubroDeProblema,
                                                              ubicacionDeProblema,
                                                              barrioDeProblema,
                                                              imagenesDeProblema)

        Cotizacion cotizacion = experto.cotizarProblema(problemaAEliminar, 10000)
        LocalDateTime dia = LocalDateTime.now()
        necesitado.aceptarCotizacion(problemaAEliminar, cotizacion, dia)
        expect:
            shouldFail{necesitado.eliminarProblema(problemaAEliminar)}
    }

    void "Crear un problema con emergencia"() {
        Problema problemaUrgente = necesitado.crearProblema(descripcionDeProblema,
                                                      rubroDeProblema,
                                                      ubicacionDeProblema,
                                                      barrioDeProblema,
                                                      imagenesDeProblema, 
                                                      EstadoEmergencia.URGENTE)
        Problema problemaRegular = necesitado.crearProblema(descripcionDeProblema,
                                                      rubroDeProblema,
                                                      ubicacionDeProblema,
                                                     barrioDeProblema,
                                                     imagenesDeProblema)
        expect:
            problemaUrgente.esUrgente()
            !problemaRegular.esUrgente()
            necesitado.problemas.size() == 2
    }

    void "Cambiar descripcion de problema"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                                     rubroDeProblema,
                                                     ubicacionDeProblema,
                                                     barrioDeProblema,
                                                     imagenesDeProblema)
        necesitado.cambiarDescripcionProblema(problema, "Goteo de tuberia")
        expect:
            problema.getDescripcion() == "Goteo de tuberia"
    }

    void "Un problema cotizado no puede cambiar su descripción"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                                     rubroDeProblema,
                                                     ubicacionDeProblema,
                                                     barrioDeProblema,
                                                     imagenesDeProblema)
        experto.cotizarProblema(problema, 10000)
        expect:
            shouldFail{necesitado.cambiarDescripcionProblema(problema, "Fiesta de cumpleaños")}
    }

    void "Subir nueva imagen al problema"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                barrioDeProblema,
                                imagenesDeProblema)
        necesitado.agregarImagenProblema(problema, "imagen.png")
        expect:
            problema.getMultimedia() == ["roto.png", "tuberia.png", "imagen.png"]
    }

    void "Un problema no confirmado no puede ser calificado"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                barrioDeProblema,
                                imagenesDeProblema)
        expect:
            shouldFail{necesitado.calificar(problema, 10)}
    }

    void "Aceptar una cotizacion"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                                     rubroDeProblema,
                                                     ubicacionDeProblema,
                                                     barrioDeProblema,
                                                     imagenesDeProblema)
        BigDecimal costo = 100000
        Cotizacion cotizacion = experto.cotizarProblema(problema, costo)
        LocalDateTime dia = LocalDateTime.now()
        necesitado.aceptarCotizacion(problema, cotizacion, dia)
        expect:
            problema.estaConfirmado()
            problema.getCotizacionAceptada() == cotizacion
    }

    void "No se pueden aceptar dos cotizaciones distintas"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                barrioDeProblema,
                                imagenesDeProblema)
        Cotizacion cotizacion = experto.cotizarProblema(problema, 100000)
        Cotizacion otraCotizacion = experto.cotizarProblema(problema, 250000)
        LocalDateTime dia = LocalDateTime.now()
        necesitado.aceptarCotizacion(problema, cotizacion, dia)
        expect:
            shouldFail{necesitado.aceptarCotizacion(problema, otraCotizacion, dia)}
    }

    void "Calificar un problema confirmado"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                barrioDeProblema,
                                imagenesDeProblema)
        BigDecimal costo = 100000
        Cotizacion cotizacion = experto.cotizarProblema(problema, costo)
        LocalDateTime dia = LocalDateTime.now() // dia acordado por chat
        necesitado.aceptarCotizacion(problema, cotizacion, dia)
        Integer calificacion = 10
        necesitado.calificar(problema, calificacion)
        expect:
            problema.conseguirCalificacion() == 10
    }

    void "No se pueden calificar problemas fuera del limite temporal"() {
        Problema problema1 = necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                barrioDeProblema,
                                imagenesDeProblema)
        Problema problema2 = necesitado.crearProblema(descripcionDeProblema,
                                rubroDeProblema, ubicacionDeProblema,
                                barrioDeProblema,
                                imagenesDeProblema)
        Cotizacion cotizacion1 = experto.cotizarProblema(problema1, 1000)
        Cotizacion cotizacion2 = experto.cotizarProblema(problema2, 1000)
        LocalDateTime diaDespues = LocalDateTime.now().plusDays(5)
        LocalDateTime diaMuchoAntes = LocalDateTime.now().minusDays(31)
        necesitado.aceptarCotizacion(problema1, cotizacion1, diaDespues)
        necesitado.aceptarCotizacion(problema2, cotizacion2, diaMuchoAntes)
        expect:
            shouldFail{necesitado.calificar(problema1, 10)}
            shouldFail{necesitado.calificar(problema2, 10)}
    }
    
    void "Chatear con Experto"() {
        Problema problema = necesitado.crearProblema(descripcionDeProblema, rubroDeProblema,
                                 ubicacionDeProblema, barrioDeProblema,
                                 imagenesDeProblema)
        BigDecimal costo = 100000
        Cotizacion cotizacion = experto.cotizarProblema(problema, costo)

        Mensaje mensaje = new Mensaje(usuarioEmisor: necesitado, mensaje: "Chau")
        necesitado.chatear(cotizacion, mensaje)
        expect:
            necesitado.mostrarChat(cotizacion) == cotizacion.mostrarChat()
            necesitado.mostrarChat(cotizacion) == ["Cristo: Chau"]
    }
}
