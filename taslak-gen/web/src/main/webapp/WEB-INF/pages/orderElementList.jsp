<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="orderElementList.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementList.heading'/>"/>
    <meta name="menu" content="OrderElementMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/OrderElement/editOrderElement.do"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.do"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
    <input type="checkbox" name="allbox" 
           onclick="checkAll(document.getElementById('orderElementsList'), 'deleteId')" />
</c:set>

<s:form id="orderElementsForm" namespace="/OrderElement" action="orderElements">
    <s:textfield key="orderElementSearch.quantityMin" cssClass="text medium" labelposition="left"/>
    <s:textfield key="orderElementSearch.quantityMax" cssClass="text medium" labelposition="left"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="orderElementsList" namespace="/OrderElement" action="orderElements">
    <display:table name="orderElements" class="table" requestURI="" id="orderElementList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="orderElement.id"/>
        <display:column property="quantity" sortable="true" titleKey="orderElement.quantity"/>

        <display:column media="html" titleKey="List.Edit" >
            <a href="/OrderElement/editOrderElement.do?id=<c:out value="${orderElementList.id}"/>"><ida:base-url context="icon" path="edit.gif"/></a>
        </display:column>
        <display:column media="html" titleKey="button.copy">
            <a href="/OrderElement/copyOrderElement.do?idToCopy=<c:out value="${orderElementList.id}"/>"><ida:base-url context="icon" path="save.gif"/></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

        <display:footer>
            <tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
                <td colspan="5">&nbsp;</td>
                <td>
                    <input id="orderElementDeleteButton" name="method:deleteMass" 
                           value="<fmt:message key="button.delete"/>" 
                           onclick="return confirmDelete('OrderElement')" type="submit" />
                </td>
            </tr>
        </display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="orderElementList.orderElement"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="orderElementList.orderElements"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="orderElementList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="orderElementList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="orderElementList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("orderElementList");-->
    <!--</script>-->

</s:form>