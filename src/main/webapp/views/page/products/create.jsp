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
 <form action="/ASSM_SOF3011/prd/store" method="post" style="padding-left: 10%;padding-right: 10%;">
  
   <div class="mb-3">
     <label for="" class="form-label">Phân Loại</label>
     <select class="form-control" name="category_id" id="">
      <c:forEach items="${ dscate }" var="cate">
        <option value="${ cate.id }" >
          ${ cate.ten }
        </option>
      </c:forEach>
      
       
     </select>
   </div>
   <div class="mb-3">
     <label for="" class="form-label">Tên Sản Phẩm</label>
     <input type="text" name="ten" id="" class="form-control" placeholder="" aria-describedby="helpId">
   </div>
   <div class="mb-3">
    <label for="" class="form-label">Số Lượng</label>
    <input type="text" name="soLuong" id="" class="form-control" placeholder="" aria-describedby="helpId">
  </div>
  
  <div class="mb-3">
    <label for="" class="form-label">Đơn Giá</label>
    <input type="text" name="donGia" id="" class="form-control" placeholder="" aria-describedby="helpId">
  </div>
  <div class="mb-3">
    <label for="" class="form-label">Màu Sắc</label>
    <input type="text" name="mauSac" id="" class="form-control" placeholder="" aria-describedby="helpId">
  </div>
  <div class="mb-3">
    <label for="" class="form-label">Kích Thước</label>
    <input type="text" name="kichThuoc" id="" class="form-control" placeholder="" aria-describedby="helpId">
  </div>
  <div class="mb-3">
    <label for="" class="form-label">Ảnh</label>
    <input type="file" name="img" id="" class="form-control" placeholder="" aria-describedby="helpId">
  </div>
  <div style="margin-top:10px">
    <button class="btn btn-success" >Thêm mới</button>
    <button class="btn btn-danger"type="reset">Xóa form</button>
</div>

 </form>