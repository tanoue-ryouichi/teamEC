<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel ="stylesheet" type="text/css" href="./css/style.css">
<title>resetPasswordComplete画面</title>

	<!-- ３秒後にHomeActionに遷移。 -->
<META http-equiv="refresh" content="3; url=HomeAction">

</head>
<body>

<!-- ヘッダー画面を常に表示 -->
<jsp:include page="header.jsp" />

<div>
<h1>パスワード再設定完了画面</h1>
<div class="success">
パスワード再設定が完了しました。
</div>
</div>
</body>
</html>