<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${!empty sessionScope.error}">
	<div class="alert alert-danger">${sessionScope.error}</div>
	<c:remove var="error" scope="session" />
</c:if>
<c:if test="${!empty sessionScope.message}">
	<div class="alert alert-success">${sessionScope.message}</div>
	<c:remove var="message" scope="session" />
</c:if>
<c:if test="${ empty dsprd }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty dsprd }">
	
	<table class="table table-striped">
	
		<thead>
			<th>Phân Loại</th>
			<th>Tên Sản Phẩm</th>
			<th>Số Lượng</th>
			<th>Đơn Giá</th>
			<th>Màu Sắc</th>
			<th>Kích Thước</th>
			<th>Ảnh</th>
			<th colspan="2">Thao Tác</th>

		</thead>
		<tbody>
			<c:forEach items="${ dsprd }" var="user">
				<tr>
					<td>${ user.categoryByCategoryId.ten }</td>
					<td>${ user.ten }</td>
					<td><c:choose>
							<c:when test="${ user.soLuong == 0 }">Hết Hàng</c:when>
							<c:when test="${ user.soLuong != 0 }">${user.soLuong }</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose></td>
					<td>${ user.donGia }</td>
					<td>${ user.mauSac }</td>
					<td>${ user.kichThuoc }</td>
					<td><img src="/ASSM_SOF3011/views/img/${user.img }"
						height="100" alt=""></td>
					<td><a class="btn btn-primary"
						href="/ASSM_SOF3011/prd/edit?id=${user.id}"> Sửa </a></td>
					<td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${user.id}"
						> Xóa </a></td>
<div class="modal fade" id="exampleModal${user.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">GẤUU BOUTIQUE</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Bạn Có Muốn Xóa
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="/ASSM_SOF3011/prd/delete?id=${user.id}"><button type="button" class="btn btn-primary"> Xóa</button></a>
      </div>
    </div>
  </div>
</div>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>