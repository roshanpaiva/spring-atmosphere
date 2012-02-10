<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<h1>Spring and Atmosphere</h1>
<h2>The time pushed from the server</h2>
<p id="time"></p>
<h:comet url="/comet/time" id="time" />

<%@ include file="/WEB-INF/views/footer.jsp" %>
