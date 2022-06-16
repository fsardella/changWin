package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ExpertoSpec extends Specification implements DomainUnitTest<Experto> {
    Experto experto

    def setup() {
        experto = new Experto(nombre: "El Juancho")
    }

    def cleanup() {
    }

    void "Agregado de rubro"() {
        experto.agregarRubro("plomero")
        experto.agregarRubro("electricista")
        expect:
            List rubros = experto.getRubros()
            rubros.contains("plomero")
            rubros.contains("electricista")
            !rubros.contains("payaso")
    }

    void "Eliminado de rubro"() {
        experto.agregarRubro("plomero")
        experto.agregarRubro("electricista")
        experto.agregarRubro("payaso")
        experto.eliminarRubro("payaso")
        expect:
            List rubros = experto.getRubros()
            rubros.contains("plomero")
            rubros.contains("electricista")
            !rubros.contains("payaso")
    }

    void "Cotizar un problema"() {
        String descripcionDeProblema = "Goteo"
        String rubroDeProblema =  "plomeria"
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        String ubicacionDeProblema = "CalleFalsa 123"
        List imagenesDeProblema = ["roto.png", "tuberia.png"]
        Problema problema = new Problema(descripcion:descripcionDeProblema, rubro:rubroDeProblema,
                                                necesitado:necesitado, ubicacion:ubicacionDeProblema,
                                                multimedia:imagenesDeProblema)
        BigDecimal costo = 10000
        experto.agregarRubro("plomeria")
        experto.cotizarProblema(problema, costo)
        expect:
            experto.getProblemasInteractuados().size() == 1
    }

    // void "Cotizar un problema"() {
    //     String descripcionDeProblema = "Goteo"
    //     String rubroDeProblema =  "plomeria"
    //     Necesitado necesitado = new Necesitado(nombre: "Cristo")
    //     String ubicacionDeProblema = "CalleFalsa 123"
    //     List imagenesDeProblema = ["roto.png", "tuberia.png"]
    //     Problema problema = new Problema(descripcion:descripcionDeProblema, rubro:rubroDeProblema,
    //                                             necesitado:necesitado, ubicacion:ubicacionDeProblema,
    //                                             multimedia:imagenesDeProblema)
    //     BigDecimal costo = 10000
    //     experto.agregarRubro("electricidad")
    //     expect:
    //         shouldFail {
    //             experto.cotizarProblema(problema, costo)
    //         }
    // }
    // NO ENCONTRAMOS MANERA DE HACER FUNCIONAR EL SHOULDFAIL

    void "Chatear con Necesitado"() {
        String descripcionDeProblema = "Goteo"
        String rubroDeProblema =  "plomeria"
        Necesitado necesitado = new Necesitado(nombre: "Cristo")
        String ubicacionDeProblema = "CalleFalsa 123"
        List imagenesDeProblema = ["roto.png", "tuberia.png"]
        Problema problema = new Problema(descripcion:descripcionDeProblema, rubro:rubroDeProblema,
                                                necesitado:necesitado, ubicacion:ubicacionDeProblema,
                                                multimedia:imagenesDeProblema)
        experto.iniciarChat(problema)
        experto.chatear(problema, "Hola")
        expect:
            experto.seeChat(problema) == problema.seeChat(experto.getNombre())
            experto.seeChat(problema) == ["El Juancho: Hola"]
    }
}
