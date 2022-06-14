<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>




<c:if test="${ empty dscate }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty dscate }">

<table class="table table-striped">
  <thead>
			<th>Tên</th>
			<th>Người Thêm</th>
			<th colspan="2">Thao Tác</th>

		</thead>
		<tbody>
			<c:forEach items="${ dscate }" var="user">
				<tr>
					<td>${ user.ten }</td>
					<td>${ user.user.hoTen }</td>
					<td><a class="btn btn-primary"
						href="/ASSM_SOF3011/cate/edit?id=${user.id}"> Sửa </a></td>
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
        <a href="/ASSM_SOF3011/cate/delete?id=${user.id}"><button type="button" class="btn btn-primary"> Xóa</button></a>
      </div>
    </div>
  </div>
</div>
				</tr>
			</c:forEach>
		</tbody>
</table>
</c:if>


