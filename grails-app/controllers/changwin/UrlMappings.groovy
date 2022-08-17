package changwin

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{}

        "/frontPage"(view:'/frontPage')
        "/"(view:'/index', controller:'redirect', action:'redirectFrontPage')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
