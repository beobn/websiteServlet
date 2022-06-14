<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:if test="${!empty sessionScope.error}">
	<div class="alert alert-danger">${sessionScope.error}</div>
	<c:remove var="error" scope="session" />
</c:if>
<c:if test="${!empty sessionScope.message}">
	<div class="alert alert-success">${sessionScope.message}</div>
	<c:remove var="message" scope="session" />
</c:if>




<c:if test="${ empty ds }">
	<p class="alert alert-warning">Giỏ hàng trống</p>
</c:if>

<c:if test="${ !empty ds }">
<h2 class="text-center">Giỏ Hàng</h2>

	
	
	<table class="table">
		<thead>
			<th>Tên Sản Phẩm</th>
			<th>Hình Ảnh</th>
			<th>Màu Sắc</th>
			<th>Kích Thước</th>
			<th>Đơn Giá</th>
			<th>Số Lượng</th>
			<th colspan="2">Thao Tác</th>

		</thead>
		<tbody>
		<c:forEach items="${ ds }" var="user">
		<form action="/ASSM_SOF3011/cart/addcart?id=${user.id}" method="post">
			
				<tr>
					<td>${ user.ten }</td>
					<td><img src="/ASSM_SOF3011/views/img/${user.img }"
						height="100" alt=""></td>
					<td>${ user.mauSac }</td>
					<td>${ user.kichThuoc }</td>
					<td>${ user.donGia }</td>
					<td>
					<input type="number" value="1" min="1" max="${user.soLuong}" name="quantity${user.id }" id=""
						class="form-control" placeholder="" aria-describedby="helpId">
					</td>
					<td>
					<button class="btn btn-primary"
						> Đặt Hàng </button></td>
					<td><a class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${user.id}"> Xóa </a></td>
					<!-- Modal -->
					<div class="modal fade" id="exampleModal${user.id}" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">GẤUU
										BOUTIQUE</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Bạn Có Muốn Xóa</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a href="/ASSM_SOF3011/cart/xoass?id=${user.id}"><button
											type="button" class="btn btn-primary">Xóa</button></a>
								</div>
							</div>
						</div>
					</div>


				</tr>
			
	
	</form>
	</c:forEach>
		</tbody>
	</table>
	
</c:if>
<!-- Button trigger modal -->


