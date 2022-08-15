<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'necesitado.label', default: 'Necesitado')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#list-necesitado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="list-necesitado" class="col-12 content scaffold-list" role="main">
                    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:table collection="${problemasDeUsuario}" />
                    <g:if test="cantProblemasUsuario == problemasDeUsuario.size()">
                        <div class="message" role="status">JEJEXD</div>
                    </g:if>
                    <g:if test="cantProblemasUsuario == 0">
                        <div class="message" role="status">LA CONCHA DE TU MADR E E E E E</div>
                    </g:if>
                    <g:if test="${problemasDeUsuario.size() > params.int('max')}">
                    <div class="pagination">
                        <g:paginate total="${problemasDeUsuario.size() ?: 0}" />
                    </div>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
