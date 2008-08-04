<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="orderList.title"/></title>
    <meta name="heading" content="<fmt:message key='orderList.heading'/>"/>
    <meta name="menu" content="OrderMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/Order/editOrder.do"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.do"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
    <input type="checkbox" name="allbox" 
           onclick="checkAll(document.getElementById('ordersList'), 'deleteId')" />
</c:set>

<s:form id="ordersForm" namespace="/Order" action="orders">
    <s:textfield key="orderSearch.name" cssClass="text medium" labelposition="left"/>
    <s:textfield key="orderSearch.priceTotalsMin" cssClass="text medium" labelposition="left"/>
    <s:textfield key="orderSearch.priceTotalsMax" cssClass="text medium" labelposition="left"/>
    <s:textfield key="orderSearch.createDateMin" cssClass="text medium" labelposition="left"/>
    <s:textfield key="orderSearch.createDateMax" cssClass="text medium" labelposition="left"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="ordersList" namespace="/Order" action="orders">
    <display:table name="orders" class="table" requestURI="" id="orderList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="order.id"/>
        <display:column property="name" sortable="true" titleKey="order.name"/>
        <display:column property="priceTotals" sortable="true" titleKey="order.priceTotals"/>
        <display:column property="createDate" sortable="true" titleKey="order.createDate"/>

        <display:column media="html" titleKey="List.Edit" >
            <a href="/Order/editOrder.do?id=<c:out value="${orderList.id}"/>"><ida:base-url context="icon" path="edit.gif"/></a>
        </display:column>
        <display:column media="html" titleKey="button.copy">
            <a href="/Order/copyOrder.do?idToCopy=<c:out value="${orderList.id}"/>"><ida:base-url context="icon" path="save.gif"/></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

        <display:footer>
            <tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
                <td colspan="5">&nbsp;</td>
                <td>
                    <input id="orderDeleteButton" name="method:deleteMass" 
                           value="<fmt:message key="button.delete"/>" 
                           onclick="return confirmDelete('Order')" type="submit" />
                </td>
            </tr>
        </display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="orderList.order"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="orderList.orders"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="orderList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="orderList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="orderList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("orderList");-->
    <!--</script>-->

</s:form>