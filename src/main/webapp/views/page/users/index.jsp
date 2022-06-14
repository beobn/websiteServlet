<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>





<c:if test="${ empty ds }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty ds }">
	<table class="table">
		<thead>
			<th>Họ tên</th>
			<th>Giới tính</th>
			<th>SĐT</th>
			<th>Email</th>
			<th>Địa chỉ</th>
			<th>Phân Quyền</th>
			<th colspan="2">Thao Tác</th>

		</thead>
		<tbody>
			<c:forEach items="${ ds }" var="user">
				<tr>
					<td>${ user.hoTen }</td>
					<td><c:choose>
							<c:when test="${ user.gioiTinh == 1 }">Nam</c:when>
							<c:when test="${ user.gioiTinh == 0 }">Nữ</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose></td>
					<td>${ user.sdt }</td>
					<td>${ user.email }</td>
					<td>${ user.diaChi }</td>
					<td><c:choose>
							<c:when test="${ user.roll == 1 }">Người Dùng</c:when>
							<c:when test="${ user.roll == 0 }">Admin</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose></td>
					<td><a class="btn btn-primary" 
						href="/ASSM_SOF3011/users/edit?id=${user.id}"> Sửa </a></td>
					<td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${user.id}"
						> Xóa </a></td>
						<!-- Modal -->
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
        <a href="/ASSM_SOF3011/users/delete?id=${user.id}"><button type="button" class="btn btn-primary"> Xóa</button></a>
      </div>
    </div>
  </div>
</div>
						

				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<!-- Button trigger modal -->


