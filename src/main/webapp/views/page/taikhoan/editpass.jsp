<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:if test="${!empty sessionScope.error}">
<div class="alert alert-danger">
${sessionScope.error}
</div>
	<c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
<div class="alert alert-success">
${sessionScope.message}
</div>
	<c:remove var="message" scope="session"/>
</c:if>
<form action="/ASSM_SOF3011/tk/doi" method="post" style="padding-left: 10%;padding-right: 10%;" >
       <div class="mb-3">
         <label for="" class="form-label">Tài Khoản</label>
         <input type="text" name="email" value="${userdn.email }" id="" class="form-control" readonly="readonly" placeholder="" aria-describedby="helpId">
       </div>
       <div class="mb-3">
        <label for="" class="form-label">Nhập Mật Khẩu Cũ</label>
        <input type="password" name="passwordcu" id="" class="form-control" placeholder="" aria-describedby="helpId">
      </div>
      <div class="mb-3">
        <label for="" class="form-label">Nhập Mật Khẩu Mới</label>
        <input type="password" name="passwordmoi" id="" class="form-control" placeholder="" aria-describedby="helpId">
      </div>
      <div class="mb-3">
        <label for="" class="form-label">Nhập Lại Mật Khẩu Mới</label>
        <input type="password" name="checkpasswordmoi" id="" class="form-control" placeholder="" aria-describedby="helpId">
      </div>
      <div style="margin-top:10px">
        <button class="btn btn-success">Đổi Mật Khẩu</button>
        
    </div>
   </form>