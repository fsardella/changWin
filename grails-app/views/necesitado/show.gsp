<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'necesitado.label', default: 'Necesitado')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-necesitado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            </section>
            <section class="row">
                <div id="show-necesitado" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="Información personal"/></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:display bean="necesitado" except="contrasenia, problemas"/>
                    <g:if test="${session.usuarioActual != null && params.id.toInteger() == session.usuarioActual.id}">
                        <fieldset class="buttons">
                            <button onclick="location.href='../../necesitado/mostrarProblemasDeNecesitado/${this.necesitado.id}';">Mostrar problemas</button>
                            <button onclick="location.href='../../problema/create';">Crear problema</button>
                            <button onclick="location.href='../logout';">Cerrar sesión</button>
                        </fieldset>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
