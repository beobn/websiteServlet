<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${!empty sessionScope.error}">
	<div class="alert alert-danger" style="padding-left: 10%;padding-right: 10%;" >${sessionScope.error}</div>
	<c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
	<div class="alert alert-success" style="padding-left: 10%;padding-right: 10%;">${sessionScope.message}</div>
	<c:remove var="message" scope="session"/>
</c:if>
    
<form action="" method="post" style="padding-left: 10%;padding-right: 10%;">
       <div class="mb-3">
         <label for="" class="form-label">Tài Khoản</label>
         <input type="text" name="email" id="" class="form-control" placeholder="" aria-describedby="helpId">
       </div>
       <div class="mb-3">
        <label for="" class="form-label">Mật Khẩu</label>
        <input type="password" name="password" id="" class="form-control" placeholder="" aria-describedby="helpId">
      </div>
      <div style="margin-top:10px">
        <button class="btn btn-success">Đăng Nhập</button>
    </div>
   </form>