<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="orderElementDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementDetail.heading'/>"/>
</head>

<c:set var="deleteChecks">
    <input type="checkbox" name="allbox"
           onclick="checkAll(document.getElementById('orderElementsList'), 'deleteId')" />
</c:set>

<c:if test="${not empty orderElement.order.id}">
    <s:url id="url" namespace="/Order" action="editOrder.html" includeParams="false">
        <s:param name="id">
            <s:property value="orderElement.order.id"/>
        </s:param>
    </s:url>
    <s:a href="%{url}"><fmt:message key="Order.master.link"/></s:a>
    <br>
</c:if>

<!--copy URL-->
<s:url id="copyUrl" namespace="/OrderElement" action="copyOrderElement.html" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="orderElement.id"/>
    </s:param>
</s:url>

<s:form id="orderElementForm" namespace="/OrderElement" action="saveOrderElement" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="orderElement.id"/>
        <s:hidden key="orderElement.order.id"/>
    </li>
    <s:textfield required="true" cssClass="text medium" key="orderElement.quantity" labelposition="left"/>
    <s:select name="orderElement.product.id" list="productList" listKey="id" listValue="id"
              key="orderElement.product" headerKey="<%=""+Integer.MIN_VALUE%>" headerValue="Select.choose"
              labelposition="left"/>
    <li class="buttonBar bottom">
        <c:if test="${empty orderElement.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty orderElement.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                      onclick="return confirmDelete('OrderElement')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
    </li>
</s:form>
<s:form id="orderElementsList" namespace="/OrderElement" action="orderElements" validate="false">
    <li style="display: none">
        <s:hidden key="orderElement.order.id"/>
    </li>

    <display:table name="orderElements" class="table" requestURI="" id="orderElementList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="orderElement.id"/>
        <display:column property="quantity" sortable="true" titleKey="orderElement.quantity"/>
        <display:column media="html" titleKey="orderElement.editProduct">
            <a href="/Product/editProduct.html?id=<c:out value="${orderElementList.product.id}"/>"><img src="/images/common/edit_up.gif"></a>
        </display:column>

        <display:column media="html" titleKey="List.Edit">
            <a href="/OrderElement/editOrderElement.html?id=<c:out value="${orderElementList.id}"/>"><img src="/images/common/edit.gif"></a>
        </display:column>
        <display:column titleKey="button.copy" >
            <a href="/OrderElement/copyOrderElement.html?idToCopy=<c:out value="${orderElementList.id}"/>"><img src="/images/common/save.gif"></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

        <display:footer>
            <tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
                <td colspan="4">&nbsp;</td>
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
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("orderElementForm"));
</script>
