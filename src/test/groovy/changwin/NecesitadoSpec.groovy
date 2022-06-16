package changwin

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class NecesitadoSpec extends Specification implements DomainUnitTest<Necesitado> {
    private Necesitado necesitado

    def setup() {
        necesitado = new Necesitado(nombre: "florencia", apellido:"sardella")
    }

    def cleanup() {
    }

    void "test necesitado"() {
        expect:"fix me"
            println "${necesitado.nombre} ${necesitado.apellido}"
            necesitado.nombreCompleto() == "florencia sardella"
            // true == false
    }
}
