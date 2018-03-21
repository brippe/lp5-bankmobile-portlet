<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib prefix="aui" uri="http://alloy.liferay.com/tld/aui" %>
<%@ page session="true" %>

<%--Defines a number of theme objects, etc --%>
<liferay-theme:defineObjects />
<%--Defines renderRequest, renderResponse, etc--%>
<portlet:defineObjects />

<div class="container">
    <div class="row" id="bankMobile">
        <div class="col-6 col-md-4">
            <div><a href="${bankMobileURL}" target="_blank" border="0">
                <img src="${renderRequest.getContextPath()}/images/BMdisbursements-175x61.png"
                alt="BankMobile Student Refund Disbursement: click to signin to BankMobile" width="175" height="61"/></a></div>
        </div>
        <div class="col-12 col-md-8">
            <a href="${bankMobileURL}" target="_blank" border="0">BankMobile</a> allows students to select how they will get their refund.
        </div>

    </div>
</div>


