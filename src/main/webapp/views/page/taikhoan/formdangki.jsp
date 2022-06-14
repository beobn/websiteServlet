<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" session="true"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${!empty sessionScope.error}">
	<div class="alert alert-danger" style="padding-left: 10%;padding-right: 10%;" >${sessionScope.error}</div>
	<c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
	<div class="alert alert-success" style="padding-left: 10%;padding-right: 10%;">${sessionScope.message}</div>
	<c:remove var="message" scope="session"/>
</c:if>
 <form method="POST" action="/ASSM_SOF3011//tk/dangki" style="padding-left: 10%;padding-right: 10%;">
        <div class="mb-3">
            <label for="" class="form-label">Họ tên</label>
            <input type="text" name="hoTen" id="" class="form-control" placeholder="" aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">Địa chỉ</label>
            <input type="text" name="diaChi" id="" class="form-control" placeholder="" aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">SĐT</label>
            <input type="text" name="sdt" id="" class="form-control" placeholder="" aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">Email</label>
            <input type="email" name="email" id="" class="form-control" placeholder="" aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">Password</label>
            <input type="password" name="password" id="" class="form-control" placeholder="" aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">Nhập lại Password</label>
            <input type="password" name="ktpass" id="" class="form-control" placeholder="" aria-describedby="helpId">
        </div>
        <div>
            <label>Giới tính :</label>
            <input type="radio" name="gioiTinh" value="1" checked />
            <label>Nam</label>
            <input type="radio" name="gioiTinh" value="0"  />
            <label>Nữ</label>
        </div>
 
        
        

        <div style="margin-top:10px">
            <button class="btn btn-success" >Thêm mới</button>
             <a class="btn btn-danger" href="/ASSM_SOF3011/tk/formlogin" >Quay lại đăng nhập</a>
        </div>
    </form>
