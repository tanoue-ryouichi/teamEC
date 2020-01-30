<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel ="stylesheet" type="text/css" href="./css/style.css">
<link rel ="stylesheet" type="text/css" href="./css/inputForm.css">
<title>resetPassword画面</title>

</head>
<body>

<!-- ヘッダー画面を常に表示 -->
<jsp:include page="header.jsp" />

<h1>パスワード再設定入力画面</h1>

	<!-- 入力情報に誤りがあったとき、パスワード再設定確認画面から送られたメッセージをフォーム上部に出力。 -->

	<!-- ユーザーIDエラーメッセージ -->
	<s:if test="messageList_user_id!=null && messageList_user_id.size() > 0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageList_user_id"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 現在のパスワードエラーメッセージ -->
	<s:if test="messageList_now_password!=null && messageList_now_password.size() > 0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageList_now_password"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 新しいパスワードエラーメッセージ -->
	<s:if test="messageList_new_password!=null && messageList_new_password.size() > 0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageList_new_password"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 新しいパスワード（再確認）エラーメッセージ -->
	<s:if test="messageList_new_password_confirm!=null && messageList_new_password_confirm.size() > 0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageList_new_password_confirm"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 入力された新しいパスワードと新しいパスワード（再確認）が不一致のときに出力されるメッセージ -->
	<s:if test="message_password_match!=null && !message_password_match.isEmpty()">
	<div class="error">
	<div class="error-message">
		<s:property value="message_password_match"/>
		<br>
		</div>
		</div>
	</s:if>

	<!-- ユーザが存在していないときに出力されるメッセージ -->
	<s:if test="message_user_match!=null && !message_user_match.isEmpty()">
	<div class="error">
	<div class="error-message">
		<s:property value="message_user_match"/>
		<br>
		</div>
		</div>
	</s:if>

	<!-- パスワード再設定入力フォーム ここから -->

	<!-- パスワード再設定入力画面で入力した値に不備があったとき、ResetPasswordConfirmにより再びパスワード再設定入力画面 -->
	<!-- に戻される。その際入力した情報が初期化されていると最初から情報を入力しなおすことになり面倒。 -->
	<!-- そのため入力された情報をいったんセッションに格納し、初期値に設定する。 -->
	<!-- もし初期値がなければplaceholder属性により薄い文字が表示される。 -->

	<s:form class="input_form" action="ResetPasswordConfirmAction">

		<table class="input_table">

			<tr>
				<th scope="row">ユーザーID</th>
				<td>
					<s:textfield class="input_text" name="user_id" value="%{#session.reset_user_id}" placeholder="ユーザーID"/>
				</td>
			</tr>
			<tr>
				<th scope="row">現在のパスワード</th>
				<td>
					<s:password class="input_text" name="now_password" value="" placeholder="現在のパスワード"/>
				</td>
			</tr>
			<tr>
				<th scope="row">新しいパスワード</th>
				<td>
					<s:password class="input_text" name="new_password" value="" placeholder="新しいパスワード"/>
				</td>
			</tr>
			<tr>
				<th scope="row">（再確認）</th>
				<td>
					<s:password class="input_text" name="new_password_confirm" value="" placeholder="新しいパスワード（再確認用）"/>
				</td>
			</tr>

		</table>

	<!-- ここまで -->

		<div class="submit_btn_box">
			<s:submit value="確認" class="submit_btn"/>
		</div>
	</s:form>
</body>
</html>