<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>
    <c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login.jsp"/>" class="current"><fmt:message key="login.title"/></a></li></c:if>
    <menu:displayMenu name="MainMenu"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    <!--Product-START-->
    <menu:displayMenu name="ProductMenu"/>
    <!--Product-END-->
    <!--Order-START-->
    <menu:displayMenu name="OrderMenu"/>
    <!--Order-END-->
    <!--OrderElement-START-->
    <menu:displayMenu name="OrderElementMenu"/>
    <!--OrderElement-END-->
</ul>
</menu:useMenuDisplayer>