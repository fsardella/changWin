package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.LocalDateTime
import static groovy.test.GroovyAssert.shouldFail

class ExpertoSpec extends Specification implements DomainUnitTest<Experto> {
    Rubro rubro
    Experto experto
    Certificado certificado
    EnteCertificador ente

    def setup() {
        rubro =  new Rubro(nombre: "plomeria")
        experto = new Experto(nombre: "El Juancho")
        certificado = new Certificado(rubro: rubro, numeroMatricula: 123546,
                                      fechaEmision: LocalDateTime.of(2022, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2030, 7, 29, 19, 30, 40),
                                      experto: experto)
        ente = new EnteCertificador()
    }

    def cleanup() {
    }

    void "Agregado de rubro"() {
        experto.agregarRubro(certificado, ente)

        Rubro otroRubro =  new Rubro(nombre: "electricista")
        Certificado otroCertificado = new Certificado(rubro: otroRubro, numeroMatricula: 456789,
                                      fechaEmision: LocalDateTime.of(2021, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2029, 7, 29, 19, 30, 40),
                                      experto: experto)
        experto.agregarRubro(otroCertificado, ente)

        Rubro rubroPayaso = new Rubro(nombre: "payaso")
        Set<Certificado> certificados = experto.certificados
        expect:
            certificados.every{cert -> (cert.rubro == rubro || cert.rubro == otroRubro)
                                        && cert.rubro != rubroPayaso}
    }

    void "Eliminado de rubro"() {
        experto.agregarRubro(certificado, ente)
        Rubro otroRubro =  new Rubro(nombre: "electricista")
        Certificado otroCertificado = new Certificado(rubro: otroRubro, numeroMatricula: 456789,
                                      fechaEmision: LocalDateTime.of(2021, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2029, 7, 29, 19, 30, 40),
                                      experto: experto)
        Rubro rubroPayaso = new Rubro(nombre: "payaso")
        Certificado certificadoPayaso = new Certificado(rubro: rubroPayaso, numeroMatricula: 165248,
                                      fechaEmision: LocalDateTime.of(2021, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2029, 7, 29, 19, 30, 40),
                                      experto: experto)
        experto.agregarRubro(certificadoPayaso, ente)
        experto.eliminarRubro(certificadoPayaso)
        Set<Certificado> certificados = experto.certificados
        expect:
            certificados.every{cert -> (cert.rubro == rubro || cert.rubro == otroRubro)
                                        && cert.rubro != rubroPayaso}
    }

    void "Cotizar un problema"() {
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        Problema problema = necesitado.crearProblema("Goteo", rubro, "CalleFalsa 123", BarrioProblema.PALERMO)
        experto.agregarRubro(certificado, ente)
        BigDecimal costo = 10000
        experto.cotizarProblema(problema, costo)
        expect:
            experto.cotizaciones.size() == 1
    }

    void "Cotizar un problema con rubro invalido"() {
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        Problema problema = necesitado.crearProblema("Goteo", rubro, "CalleFalsa 123", BarrioProblema.PALERMO)
        BigDecimal costo = 10000
        Rubro rubroIncorrecto =  new Rubro(nombre: "payaso")
        Certificado certificadoIncorrecto = new Certificado(rubro: rubroIncorrecto, numeroMatricula: 456789,
                                      fechaEmision: LocalDateTime.of(2021, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2029, 7, 29, 19, 30, 40),
                                      experto: experto)
        experto.agregarRubro(certificadoIncorrecto, ente)
        
        expect:
            shouldFail{experto.cotizarProblema(problema, costo)}
    }

    void "Cotizar un problema con certificado vencido"() {
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        Problema problema = necesitado.crearProblema("Goteo", rubro, "CalleFalsa 123", BarrioProblema.PALERMO)
        BigDecimal costo = 10000
        Certificado certificadoIncorrecto = new Certificado(rubro: rubro, numeroMatricula: 456789,
                                      fechaEmision: LocalDateTime.of(1969, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(1973, 7, 29, 19, 30, 40),
                                      experto: experto)
        experto.agregarRubro(certificadoIncorrecto, ente)
        
        expect:
            shouldFail{experto.cotizarProblema(problema, costo)}
    }

    void "No se puede cotizar un problema ya confirmado"() {
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        Problema problema = necesitado.crearProblema("Goteo", rubro, "CalleFalsa 123", BarrioProblema.PALERMO)
        experto.agregarRubro(certificado, ente)
        BigDecimal costo = 10000
        Cotizacion cotizacion = experto.cotizarProblema(problema, costo)
        necesitado.aceptarCotizacion(problema, cotizacion, LocalDateTime.now())

        expect:
            shouldFail{experto.cotizarProblema(problema, 100)}
    }

    void "Chatear con Necesitado"() {
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        Problema problema = necesitado.crearProblema("Goteo", rubro, "CalleFalsa 123", BarrioProblema.PALERMO)
        experto.agregarRubro(certificado, ente)
        BigDecimal costo = 10000
        Cotizacion cotizacion = experto.cotizarProblema(problema, costo)

        Mensaje mensaje = new Mensaje(usuarioEmisor: experto, mensaje: "Hola")
        experto.chatear(cotizacion, mensaje)
        expect:
            experto.mostrarChat(cotizacion) == cotizacion.mostrarChat()
            experto.mostrarChat(cotizacion) == ["El Juancho: Hola"]
    }
}
