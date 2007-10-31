<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">

<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>
    <c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login.jsp"/>" class="current"><fmt:message key="login.title"/></a></li></c:if>
    <menu:displayMenu name="MainMenu"/>
<%--    <menu:displayMenu name="UserMenu"/>--%>
<%--    <menu:displayMenu name="AdminMenu"/>--%>
<%----%>
<%--    <menu:displayMenu name="ProductMenu"/>--%>
<%--    <menu:displayMenu name="OrderMenu"/>--%>

    <menu:displayMenu name="MasterDataMenu"/>
    <menu:displayMenu name="SalesMenu"/>
<%--    <menu:displayMenu name="Logout"/>--%>
<%--    <menu:displayMenu name="Logout"/>--%>
<%--    <menu:displayMenu name="MasterDataMenu"/>--%>
    <menu:displayMenu name="Logout"/>
</ul>

</menu:useMenuDisplayer>

