
<%@ page
	import="java.sql.*,com.epam.lab.controller.dao.dbconnect.ConnectionManager;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ConnectionManager connManager = ConnectionManager.getInstance();
		String name = request.getParameter("userEmail").toString();
		String id = request.getParameter("id");
		System.out.println(name);
		String data = "";
		Connection con = null;
		try {
			con = connManager.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from users where email='"
							+ name + "' AND id!=" + id);
			int count = 0;
			while (rs.next()) {

				count++;
			}

			if (count > 0) {
				data = "Email-ID already exists!";
			} else {
				data = "You can register now!!!!";
			}
			out.println(data);
			System.out.println(data);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			connManager.closeQuality(con);
		}
	%>
</body>
</html>




