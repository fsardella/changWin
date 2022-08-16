<!DOCTYPE html>
<html>
    <head>
        <g:if test="${application.usuarioActual != null}">
            <meta http-equiv="Refresh" content="0; url='http://localhost:8080'" />
        </g:if>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'experto.label', default: 'experto')}" />
        <title><g:message code="Login" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#create-experto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="create-experto" class="col-12 content scaffold-create" role="main">
                    <h1><g:message code="Login" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.experto}">
                    <ul class="errors" role="alert">
                        <li>Login inválido</li>
                    </ul>
                    </g:hasErrors>
                    <g:form resource="${this.experto}" method="POST" controller="experto" action="loginConfirmed">
                        <fieldset class="form">
                            <f:with bean="experto">
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
