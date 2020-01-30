<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel ="stylesheet" type="text/css" href="./css/style.css">
<link rel ="stylesheet" type="text/css" href="./css/inputForm.css">
<title>createUserConfirm画面</title>

</head>
<body>

<!-- ヘッダー画面を常に表示 -->
<jsp:include page="header.jsp" />

<h1>ユーザー情報入力確認画面</h1>

	<div class="input_form">

		<!-- createUser.jspで入力された情報を画面に表示する ここから -->

		<table class="input_table">

			<tr>
				<th scope="row">姓</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_family_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">名</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_first_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">姓ふりがな</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_family_name_kana"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">名ふりがな</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_first_name_kana"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">性別</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_sex"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">メールアドレス</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_email"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">ユーザーID</th>
				<td>
					<div class="input_text">
						<s:property value="session.create_user_id"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">パスワード</th>
				<td>
					<div class="input_text">
						<s:property value="concealedPassword"/>
					</div>
				</td>
			</tr>

		</table>

		<!-- ここまで -->

		<s:form action="CreateUserCompleteAction">
			<div class="submit_btn_box">
				<s:submit value="登録" class="submit_btn"/>
			</div>
		</s:form>

		<!-- 戻るボタンを設計するときは確認画面から入力画面への遷移であることが分かるようにs:hiddenタグでフラッグを立てる -->
		<s:form action="CreateUserAction">
			<div class="submit_btn_box">
				<s:submit value="戻る" class="submit_btn"/>
			</div>
			<s:hidden id="backFlag" name="backFlag" value="1"/>
		</s:form>
	</div>
</body>
</html>