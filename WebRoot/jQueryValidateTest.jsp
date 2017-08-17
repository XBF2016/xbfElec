<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"  src="${pageContext.request.contextPath}/script/jquery-1.8.3.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/script/validate/validate.js"></script>
<script type="text/javascript">

  //表单数据检查
  $(document).ready(function(){
	  $('#form1').validate(
		  {
			  rules:{
			      username: 'required',
			      password:{
			          required : true,
		              minlength : 5
			      },
		           repassword:{
			          required : true,
		              minlength :5,
		              equalTo : '#password'
		           },
		           email1:'email'
			  }
		  }
);
  });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

        

</head>
<body>
<form action="xxx"   id="form1">
	用户名:<input type="text"  name="username" /><br/>
	密码:<input type="password"  name="password"  id="password"   /><br/>
	确认密码:<input type="password"  name="repassword" /><br/><br/>
	电子邮件:<input type="text"  name="email1" /><br/><br/>
	年龄:<input type="text" name="age" /><br/>
	<input type="submit" value="提交" />
</form>
</body>
</html>