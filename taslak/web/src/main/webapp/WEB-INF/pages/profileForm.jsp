<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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

<s:form id="profileForm"  action="saveProfile" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="profile.user.id"/>
        <s:hidden key="profile.user.version"/>
        <input type="hidden" name="from" value="${param.from}"/>

        <c:if test="${cookieLogin == 'true'}">
            <s:hidden key="profile.user.password"/>
            <s:hidden key="profile.user.confirmPassword"/>
        </c:if>

        <s:if test="profile.user.version == null">
            <input type="hidden" name="encryptPass" value="true" />
        </s:if>
    </li>
    <li class="buttonBar right">
        <c:set var="buttons">
            <s:submit key="button.save" method="save" onclick="onFormSubmit(this.form)"/>
            
        <c:if test="${param.from == 'list' and not empty profile.user.id}">
            <s:submit key="button.delete" method="delete" onclick="return confirmDelete('profile')"/>
        </c:if>
        
            <s:submit key="button.cancel" method="cancel"/>
        </c:set>
        <c:out value="${buttons}" escapeXml="false"/>
    </li>
    <li class="info">
        <c:choose>
            <c:when test="${param.from == 'list'}">
                <p><fmt:message key="userProfile.admin.message"/></p>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="userProfile.message"/></p>
            </c:otherwise>
        </c:choose>
    </li>

    <s:textfield key="profile.user.username" cssClass="text large" required="true"/>

    <c:if test="${cookieLogin != 'true'}">
    <li>
        <div>
            <div class="left">
                <s:password key="profile.user.password" showPassword="true" theme="xhtml" required="true" 
                    cssClass="text medium" onchange="passwordChanged(this)"/>
            </div>
            <div>
                <s:password key="profile.user.confirmPassword" theme="xhtml" required="true" 
                    showPassword="true" cssClass="text medium" onchange="passwordChanged(this)"/>
            </div>
        </div>
    </li>
    </c:if>

    <s:textfield key="profile.user.passwordHint" required="true" cssClass="text large"/>

    <li>
        <div>
            <div class="left">
                <s:textfield key="profile.user.firstName" theme="xhtml" required="true" cssClass="text medium"/>
            </div>
            <div>
                <s:textfield key="profile.user.lastName" theme="xhtml" required="true" cssClass="text medium"/>
            </div>
        </div>
    </li>

    <li>
        <div>
            <div class="left">
                <s:textfield key="profile.user.email" theme="xhtml" required="true" cssClass="text medium"/>
            </div>
            <div>
                <s:textfield key="profile.user.phoneNumber" theme="xhtml" cssClass="text medium"/>
            </div>
        </div>
    </li>

    <s:textfield key="profile.user.website" required="true" cssClass="text large"/>

    <li>
        
        <div class="group">
            <div class="left">
			<tr>
		    <td class="tdLabel"><label class="desc"><fmt:message key="profile.user.address.address"/></label></td>
		    <td></td>
			</tr>

            <div>
                <s:textfield key="profile.user.address.address" theme="xhtml" cssClass="text large" labelposition="bottom"/>
            </div>
            <div class="left">
                <s:textfield key="profile.user.address.city" theme="xhtml" required="true" cssClass="text medium" 
                    labelposition="bottom"/>
            </div>
            <div>
                <s:textfield key="profile.user.address.province" theme="xhtml" required="true" cssClass="text state" 
                    labelposition="bottom"/>
            </div>
            <div class="left">
                <s:textfield key="profile.user.address.postalCode" theme="xhtml" required="true" cssClass="text medium" 
                    labelposition="bottom"/>
            </div>
            <div>
            	<tr>
    			<td class="tdLabel">
	                <label for="profileForm_profile_user_address_country" class="label">
	                    <fmt:message key="profile.user.address.country"/> <span class="required">*</span>:
	                </label>
                </td>
    			<td>
                <s:set name="country" value="profile.user.address.country" scope="page"/>
                <ida:country name="profile.user.address.country" prompt="" default="${country}"/>
                </td>
				</tr>
            </div>
        </div>
    </li>
<c:choose>
    <c:when test="${param.from == 'list'}">
    <li>
        <fieldset>
            <legend><fmt:message key="userProfile.accountSettings"/></legend>
            <s:checkbox key="profile.user.enabled" id="profile.user.enabled" fieldValue="true" theme="simple"/>
            <label for="profile.user.enabled" class="choice"><fmt:message key="profile.user.enabled"/></label>

            <s:checkbox key="profile.user.accountExpired" id="profile.user.accountExpired" fieldValue="true" theme="simple"/>
            <label for="profile.user.accountExpired" class="choice"><fmt:message key="profile.user.accountExpired"/></label>

            <s:checkbox key="profile.user.accountLocked" id="profile.user.accountLocked" fieldValue="true" theme="simple"/>
            <label for="profile.user.accountLocked" class="choice"><fmt:message key="profile.user.accountLocked"/></label>

            <s:checkbox key="profile.user.credentialsExpired" id="profile.user.credentialsExpired" fieldValue="true" theme="simple"/>
            <label for="profile.user.credentialsExpired" class="choice"><fmt:message key="profile.user.credentialsExpired"/></label>
        </fieldset>
    </li>
    <li>
        <fieldset>
            <legend><fmt:message key="userProfile.assignRoles"/></legend>
            <table class="pickList">
                <tr>
                    <th class="pickLabel">
                        <label class="required"><fmt:message key="profile.user.availableRoles"/></label>
                    </th>
                    <td></td>
                    <th class="pickLabel">
                        <label class="required"><fmt:message key="profile.user.roles"/></label>
                    </th>
                </tr>
                <c:set var="leftList" value="${availableRoles}" scope="request"/>
                <s:set name="rightList" value="profile.user.roleList" scope="request"/>
                <c:import url="/WEB-INF/pages/pickList.jsp">
                    <c:param name="listCount" value="1"/>
                    <c:param name="leftId" value="availableRoles"/>
                    <c:param name="rightId" value="userRoles"/>
                </c:import>
            </table>
        </fieldset>
    </li>
    </c:when>
    <c:otherwise>
    <li>
        <strong><fmt:message key="profile.user.roles"/>:</strong>
        <s:iterator value="profile.user.roleList" status="status">
          <s:property value="label"/><s:if test="!#status.last">,</s:if>
          <input type="hidden" name="userRoles" value="<s:property value="value"/>"/>
        </s:iterator>
        <s:hidden name="profile.user.enabled" value="%{profile.user.enabled}"/>
        <s:hidden name="profile.user.accountExpired" value="%{profile.user.accountExpired}"/>
        <s:hidden name="profile.user.accountLocked" value="%{profile.user.accountLocked}"/>
        <s:hidden name="profile.user.credentialsExpired" value="%{profile.user.credentialsExpired}"/>
    </li>
    </c:otherwise>
</c:choose>
    <li class="buttonBar bottom">
        <c:out value="${buttons}" escapeXml="false"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["profileForm"]);
    highlightFormElements();

    function passwordChanged(passwordField) {
        if (passwordField.name == "profile.user.password") {
            var origPassword = "<s:property value="profile.user.password"/>";
        } else if (passwordField.name == "profile.user.confirmPassword") {
            var origPassword = "<s:property value="profile.user.confirmPassword"/>";
        }
        
        if (passwordField.value != origPassword) {
            createFormElement("input", "hidden",  "encryptPass", "encryptPass",
                              "true", passwordField.form);
        }
    }

<!-- This is here so we can exclude the selectAll call when roles is hidden -->
function onFormSubmit(theForm) {
<c:if test="${param.from == 'list'}">
    selectAll('userRoles');
</c:if>
}
</script>

