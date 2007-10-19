<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderElementList.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementList.heading'/>"/>
    <meta name="menu" content="OrderElementMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
        onclick="location.href='<c:url value="/editOrderElement.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
    <input type="checkbox" name="allbox" 
           onclick="checkAll(document.getElementById('orderElementsList'), 'deleteId')" />
</c:set>

<s:form id="orderElementsForm" action="orderElements">
    <s:textfield key="orderElementSearch.fromQuantity" cssClass="text medium" labelposition="left"/>
    <s:textfield key="orderElementSearch.toQuantity" cssClass="text medium" labelposition="left"/>
    <s:select name="orderElementSearch.order.id" list="orderList" listKey="id" listValue="id"
        key="orderElement.order" headerKey="<%=""+Integer.MIN_VALUE%>" headerValue="*" labelposition="left"/>
    <s:select name="orderElementSearch.product.id" list="productList" listKey="id" listValue="id"
        key="orderElement.product" headerKey="<%=""+Integer.MIN_VALUE%>" headerValue="*" labelposition="left"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="orderElementsList" action="orderElements">

    <display:table name="orderElements" class="table" requestURI="" id="orderElementList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="orderElement.id"/>
        <display:column property="quantity" sortable="true" titleKey="orderElement.quantity"/>
        <display:column media="html" titleKey="orderElement.editOrder">
            <a href="/editOrder.html?id=<c:out value="${orderElementList.order.id}"/>"><img src="/images/common/edit_up.gif"></a>
        </display:column>
        <display:column media="html" titleKey="orderElement.editProduct">
            <a href="/editProduct.html?id=<c:out value="${orderElementList.product.id}"/>"><img src="/images/common/edit_up.gif"></a>
        </display:column>

        <display:column media="html" titleKey="List.Edit">
            <a href="/editOrderElement.html?id=<c:out value="${orderElementList.id}"/>"><img src="/images/common/edit.gif"></a>
        </display:column>
        <display:column titleKey="button.copy" >
            <a href="/copyOrderElement.html?idToCopy=<c:out value="${orderElementList.id}"/>"><img src="/images/common/save.gif"></a>
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
    
    <c:out value="${buttons}" escapeXml="false" />
    
    <!--<script type="text/javascript">-->
        <!--highlightTableRows("orderElementList");-->
    <!--</script>-->
</s:form>
