<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel ="stylesheet" type="text/css" href="./css/style.css">
<link rel ="stylesheet" type="text/css" href="./css/inputForm.css">
<title>createUser画面</title>

</head>
<body>

<!-- ヘッダー画面を常に表示 -->
<jsp:include page="header.jsp" />

<h1>ユーザー情報入力画面</h1>

	<!-- 入力情報に誤りがあったとき、ユーザー情報入力確認画面から送られたメッセージをフォーム上部に出力。 -->

	<!-- 姓エラーメッセージ -->
	<s:if test="messageListFamilyName!=null && messageListFamilyName.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListFamilyName"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 名エラーメッセージ -->
	<s:if test="messageListFirstName!=null && messageListFirstName.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListFirstName"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 姓ふりがなエラーメッセージ -->
	<s:if test="messageListFamilyNameKana!=null && messageListFamilyNameKana.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListFamilyNameKana"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 名ふりがなエラーメッセージ -->
	<s:if test="messageListFirstNameKana!=null && messageListFirstNameKana.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListFirstNameKana"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- ユーザーIDエラーメッセージ -->
	<s:if test="messageListUserId!=null && messageListUserId.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListUserId"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- パスワードエラーメッセージ -->
	<s:if test="messageListPassword!=null && messageListPassword.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListPassword"><s:property />
		<br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- メールアドレスエラーメッセージ -->
	<s:if test="messageListEmail!=null && messageListEmail.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListEmail"><s:property /><br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- 入力されたユーザーIDが使用されていたときに出力されるエラーメッセージ -->
	<s:if test="messageListUserCheck!=null && !messageListUserCheck.isEmpty()">
	<div class="error">
	<div class="error-message">
		<s:iterator value="messageListUserCheck"><s:property /><br></s:iterator>
		</div>
		</div>
	</s:if>

	<!-- ユーザー情報登録フォーム ここから -->

	<!-- ユーザー情報入力画面で入力した値に不備があったとき、CreateUserConfirmActionにより再びユーザー情報入力画面 -->
	<!-- に戻される。その際入力した情報が初期化されていると最初から情報を入力しなおすことになり面倒。 -->
	<!-- そのため入力された情報をいったんセッションに格納し、初期値に設定する。 -->
	<!-- もし初期値がなければplaceholder属性により薄い文字が表示される。 -->

	<s:form class="input_form" action="CreateUserConfirmAction">

		<table class="input_table">

			<tr>
				<th scope="row">姓</th>
				<td>
					<s:textfield class="input_text" name="family_name" placeholder="姓" value="%{#session.create_family_name}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">名</th>
				<td>
					<s:textfield class="input_text" name="first_name" placeholder="名" value="%{#session.create_first_name}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">姓ふりがな</th>
				<td>
					<s:textfield class="input_text" name="family_name_kana" placeholder="姓ふりがな" value="%{#session.create_family_name_kana}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">名ふりがな</th>
				<td>
					<s:textfield class="input_text" name="first_name_kana" placeholder="名ふりがな" value="%{#session.create_first_name_kana}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">性別</th>
				<td>
					<div class="input_text">
						<s:radio name="sex" list="%{#session.sexList}" value="%{#session.create_sex}" placeholder="性別"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">メールアドレス</th>
				<td>
					<s:textfield class="input_text" name="email" placeholder="メールアドレス" value="%{#session.create_email}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">ユーザーID</th>
				<td>
					<s:textfield class="input_text" name="user_id" placeholder="ユーザID" value="%{#session.create_user_id}"/>
				</td>
			</tr>
			<tr>
				<th scope="row">パスワード</th>
				<td>
					<s:password class="input_text" name="password" placeholder="パスワード"/>
				</td>
			</tr>

		</table>

	<!-- ここまで -->

		<div class="submit_btn_box">
			<s:submit value="確認" class="submit_btn" />
		</div>
	</s:form>
</body>
</html>