<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" session="true"%>
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

	<form method="POST"
		action="/ASSM_SOF3011/cate/update?id=${catesua.id}" style="padding-left: 10%;padding-right: 10%;">
        <div class="mb-3">
          <label for="" class="form-label">Tên danh mục</label>
          <input type="text" name="ten" value="${catesua.ten}" id="" class="form-control" placeholder="" aria-describedby="helpId">
          
        </div>
		<div class="mb-3">
          <label for="" class="form-label">Người sửa</label>
          
          <select class="form-control" name="user_id" id="">
            <option value="${ userdn.id }">
					${ userdn.hoTen }
				</option>
			
          </select>
        </div>
		
		<div style="margin-top:10px">
            <button class="btn btn-success" >Sửa</button>
            <a class="btn btn-danger" href="/ASSM_SOF3011/cate/index">Hủy</a>
        </div>
	</form>
