<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
        <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${!empty sessionScope.error}">
	<div class="alert alert-danger" style="padding-left: 10%;padding-right: 10%;" >${sessionScope.error}</div>
	<c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
	<div class="alert alert-success" style="padding-left: 10%;padding-right: 10%;">${sessionScope.message}</div>
	<c:remove var="message" scope="session"/>
</c:if>

<form method="POST" action="/ASSM_SOF3011/users/update?id=${user.id}" style="padding-left: 10%;padding-right: 10%;">
        <div class="mb-3">
            <label for="" class="form-label">Họ Tên</label>
            <input type="text" name="hoTen" id="" value="${user.hoTen}" class="form-control" placeholder=""
                aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">Địa chỉ</label>
            <input type="text" name="diaChi" id="" value="${user.diaChi}" class="form-control" placeholder=""
                aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">SĐT</label>
            <input type="text" name="sdt" id="" value="${user.sdt}" class="form-control" placeholder=""
                aria-describedby="helpId">
        </div>
        <div class="mb-3">
            <label for="" class="form-label">Email</label>
            <input type="email" name="email" id="" value="${user.email}" class="form-control" placeholder=""
                aria-describedby="helpId">
        </div>
        
        <div>
            <label>Giới tính</label>
            <input type="radio" name="gioiTinh" value="1" ${user.gioiTinh==1 ? "checked" :""} />
            <label>Nam</label>
            <input type="radio" name="gioiTinh" value="0" ${user.gioiTinh==0 ? "checked" :""} />
            <label>Nữ</label>
        </div>
        
        <div style="margin-top:10px">
            <label>Phân Quyền</label>
            <input type="radio" name="roll" value="1" ${user.roll==1 ? "checked" :""} />
            <label>Người Dùng</label>
            <input type="radio" name="roll" value="0" ${user.roll==0 ? "checked" :""} />
            <label>Admin</label>
        </div>
        <div style="margin-top:10px">
            <button class="btn btn-success">Sửa</button>
            <a class="btn btn-danger" href="/ASSM_SOF3011/users/index">Hủy</a>
        </div>
    </form>