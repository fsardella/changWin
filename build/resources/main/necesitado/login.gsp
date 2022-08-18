<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'necesitado.label', default: 'Necesitado')}" />
        <title><g:message code="Login" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#create-necesitado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="create-necesitado" class="col-12 content scaffold-create" role="main">
                    <h1><g:message code="Login como {0}" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.necesitado}">
                    <ul class="errors" role="alert">
                        <li>Login inválido</li>
                    </ul>
                    </g:hasErrors>
                    <g:form resource="${this.necesitado}" method="POST" controller="Necesitado" action="loginConfirmed">
                        <fieldset class="form">
                            <f:with bean="necesitado">
                                <f:field property="nombre"/>
                                <f:field property="contrasenia" label="Contraseña"/>
                            </f:with>
                        </fieldset>
                        <fieldset class="buttons">
                            <g:submitButton name="login" class="login" value="Login" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
