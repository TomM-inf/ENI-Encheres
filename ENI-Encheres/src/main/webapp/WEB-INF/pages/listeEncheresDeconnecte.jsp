<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table, th, td {
	border: 1px solid;
}
</style>
</head>
<body>
	<c:if test="${not empty listArticles}">
		<div hidden>${val = 0}</div>
		<c:forEach items="${listArticles}" var="value">
			<table>
				<tbody>
					<tr>
						<td rowspan="5">IMAGE</td>
					</tr>
					<tr>
						<td><a
							href="${pageContext.request.contextPath }/details?id=${value.noArticle }"><c:out
									value="${value.nomArticle}" /></a></td>
					</tr>
					<tr>
						<td>Prix : <c:out value="${value.prixInitial}" /> points
						</td>
					</tr>
					<tr>
						<td>Fin de l'enchere : <c:out value="${value.dateFin}" /></td>
					</tr>
					<tr>
						<td>Vendeur : <c:if test="${empty sessionScope.utilisateur}">
								<c:out value="${listPseudo[val]}" />
							</c:if> <c:if test="${not empty sessionScope.utilisateur}">
								<a
									href="${pageContext.request.contextPath}/afficherProfil?pseudo=${listPseudo[val]}&etat=${value.etatVente}&id=${value.noArticle}"><c:out
										value="${listPseudo[val]}" /></a>
							</c:if></td>
					</tr>

				</tbody>
			</table>
			<div hidden>${val = val+1}</div>
		</c:forEach>
	</c:if>
</body>
</html>