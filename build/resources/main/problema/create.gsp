<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'problema.label', default: 'Problema')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="grails-logo-container-v2"></div>
    <asset:image src="problema.png" class="imagen-contenida"/>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#create-problema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="create-problema" class="col-12 content scaffold-create" role="main">
                    <h1><g:message code="Crear {0}" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.problema}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${this.problema}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                    </g:hasErrors>
                    <g:form resource="${this.problema}" method="POST">
                        <fieldset class="form">
                            <f:with bean="problema">
                                <f:field property="descripcion"/>
                                <f:field property="rubro"/>
                                <f:field property="ubicacion"/>
                                <f:field property="barrio"/>
                                <f:field property="emergencia"/>
                            </f:with>
                        </fieldset>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
