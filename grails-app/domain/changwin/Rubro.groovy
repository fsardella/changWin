package changwin

class Rubro {
    String nombre
    private List<Certificado> certificados = []

    static constraints = {
        nombre blank: false, nullable: false
    }

    String toString() {
        return nombre
    }
}
