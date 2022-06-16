package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class NecesitadoSpec extends Specification implements DomainUnitTest<Necesitado> {
    private Necesitado necesitado

    def setup() {
        necesitado = new Necesitado(nombre: "Florencia Sardella", metodoDePago: "efectivo")
    }

    def cleanup() {
    }

    void "test necesitado"() {
        expect:"fix me"
            necesitado.cambiarMetodoDePago("tarjeta de credito")
            necesitado.obtenerMetodoDePago() == "tarjeta de credito"
    }
}
