<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>





<c:if test="${ empty listhtr }">
	<p class="alert alert-warning">Chưa có đơn hàng</p>
</c:if>

<c:if test="${ !empty listhtr }">
<h2 class="text-center">Hàng đã đặt</h2>
	<table class="table">
		<thead>
				<th>Tên Sản Phẩm</th>
			<th>Hình Ảnh</th>
			<th>Màu Sắc</th>
			<th>Kích Thước</th>
			<th>Đơn Giá</th>
			<th>Số Lượng</th>
			<th>Trạng thái</th>
			<th>Thời Gian Đặt</th>

		</thead>
		<tbody>
		
			<c:forEach items="${ listhtr }" var="user"> 
			
				<tr>
			
					<td>${ user.tenSp }</td>
					<td><img src="/ASSM_SOF3011/views/img/${user.img }"
						height="100" alt=""></td>
					<td>${ user.mauSac }</td>
					<td>${ user.kichThuoc }</td>
					<td>${ user.donGia }</td>
					<td>${ user.soLuong }</td>
					<td>
					<c:if test="${ user.trangThai ==0}">Chờ xác nhận</c:if>
					<c:if test="${ user.trangThai ==1}">Đang Giao Hàng</c:if>
					<c:if test="${ user.trangThai ==3}">Giao Thành Công</c:if>
					<c:if test="${ user.trangThai ==2}">Đã Bị Hủy</c:if>
					</td>
					<td>
					<fmt:formatDate  value="${ user.timebuy }" pattern="dd/MM/yyyy HH:mm:ss"/>
					
					</td>	
					
					
					
				</tr>
				</c:forEach>
				
			
		</tbody>
	</table>
</c:if>
<!-- Button trigger modal -->


