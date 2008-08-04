<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="profileList.title"/></title>
    <meta name="heading" content="<fmt:message key='profileList.heading'/>"/>
    <meta name="menu" content="ProfileMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/Profile/editProfile.do"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.do"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
    <input type="checkbox" name="allbox" 
           onclick="checkAll(document.getElementById('profilesList'), 'deleteId')" />
</c:set>

<s:form id="profilesForm" namespace="/Profile" action="profiles">
    <s:textfield key="profileSearch.privatePhone" cssClass="text medium" labelposition="left"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="profilesList" namespace="/Profile" action="profiles">
    <display:table name="profiles" class="table" requestURI="" id="profileList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="profile.id"/>
        <display:column property="privatePhone" sortable="true" titleKey="profile.privatePhone"/>

        <display:column media="html" titleKey="List.Edit" >
            <a href="/Profile/editProfile.do?id=<c:out value="${profileList.id}"/>"><ida:base-url context="icon" path="edit.gif"/></a>
        </display:column>
        <display:column media="html" titleKey="button.copy">
            <a href="/Profile/copyProfile.do?idToCopy=<c:out value="${profileList.id}"/>"><ida:base-url context="icon" path="save.gif"/></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

        <display:footer>
            <tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
                <td colspan="5">&nbsp;</td>
                <td>
                    <input id="profileDeleteButton" name="method:deleteMass" 
                           value="<fmt:message key="button.delete"/>" 
                           onclick="return confirmDelete('Profile')" type="submit" />
                </td>
            </tr>
        </display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="profileList.profile"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="profileList.profiles"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="profileList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="profileList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="profileList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("profileList");-->
    <!--</script>-->

</s:form>