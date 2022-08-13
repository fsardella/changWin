package changwin

class BootStrap {

    def init = { servletContext ->
        servletContext["nombreUsuario"] = "pepito"
    }
    def destroy = {
    }
}
