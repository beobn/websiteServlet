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




<c:if test="${ empty listhtradmin }">
	<p class="alert alert-warning">Không có đơn đặt hàng nào</p>
</c:if>

<c:if test="${ !empty listhtradmin }">
	<h2 class="text-center">Giỏ Hàng</h2>



	<table class="table">
		<thead>
			<th>Người Đặt</th>
			<th>Tên Sản Phẩm</th>
			<th>Hình Ảnh</th>
			<th>Màu Sắc</th>
			<th>Kích Thước</th>
			<th>Đơn Giá</th>
			<th>Số Lượng</th>
			<th>Tổng Tiền</th>
			<th>Trạng Thái</th>
			<th>Thao Tác</th>

		</thead>
		<tbody>
			<c:forEach items="${ listhtradmin }" var="user">
				<form action="/ASSM_SOF3011/cart/updatestt?id=${user.id}"
					method="post">

					<tr>
						<td>${ user.tenUs }</td>
						<td>${ user.tenSp }</td>
						<td><img src="/ASSM_SOF3011/views/img/${user.img }"
							height="100" alt=""></td>
						<td>${ user.mauSac }</td>
						<td>${ user.kichThuoc }</td>
						<td>${ user.donGia }</td>
						<td>${ user.soLuong }</td>
						<td>${user.tongtien()}$</td>
						<td><select class="form-select"
							aria-label="Default select example" name="status">
								<option value=""
									<c:if test="${ user.trangThai ==0}">selected</c:if>>
									Chờ Xác Nhận</option>


								
								<option value="1"
									<c:if test="${ user.trangThai ==1}">selected</c:if>>
									Xác Nhận Giao Hàng</option>
									<option value="3"
									<c:if test="${ user.trangThai ==3}">selected</c:if>>
									Hoàn Thành Đơn</option>
								<option value="2"
									<c:if test="${ user.trangThai ==2}">selected</c:if>>
									Hủy Đơn Hàng</option>

						</select></td>
						<td>
							<button class="btn btn-primary">Cập nhập trạng thái</button>
						</td>


					</tr>


				</form>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<!-- Button trigger modal -->


