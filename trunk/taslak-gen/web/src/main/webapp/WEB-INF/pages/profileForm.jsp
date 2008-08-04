<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="profileDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='profileDetail.heading'/>"/>
</head>

<!--copy URL-->
<s:url id="copyUrl" namespace="/Profile" action="copyProfile.do" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="profile.id"/>
    </s:param>
</s:url>

<s:form id="profileForm" namespace="/Profile" action="saveProfile" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="profile.id"/>
    </li>
        <s:textfield key="profile.privatePhone" required="false" cssClass="text medium" labelposition="left"/>

    <li class="buttonBar bottom">
        <c:if test="${empty profile.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty profile.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('Profile')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("profileForm"));
</script>
