package changwin

class Rubro {
    String nombre
    Set<Certificado> certificados = []

    static constraints = {
        nombre blank: false, nullable: false
    }
    
    static hasMany = [certificados: Certificado]
    
    String toString() {
        return nombre
    }
    
    boolean equals(Rubro otherRubro) {
        return this.nombre == otherRubro.nombre
    }
}
