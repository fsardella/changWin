package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class NecesitadoSpec extends Specification implements DomainUnitTest<Necesitado> {
    private Necesitado necesitado

    def setup() {
        necesitado = new Necesitado(nombre: "Florencia Sardella")
        experto = new Experto(nombre: "El Juancho")
    }

    def cleanup() {
    }

    void "Creacion de Problema"() {
    }

}
