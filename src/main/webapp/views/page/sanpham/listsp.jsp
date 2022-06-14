<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="col-12 row">
	<div class="col-1"></div>
	<div class="col-10 bg-white text-center row" style="opacity: 0.90;">
	<form action="/ASSM_SOF3011/sp/loclistsp" method="post">
		<div class=" mb-3">
			<label for="" class="form-label"></label> <select
				class="form-control" name="loc" id="">
				<option value="full">Tất Cả Sản Phẩm</option>
				<c:forEach items="${listcate}" var="cate">

					<option value="${cate.id}">${cate.ten}</option>

				</c:forEach>
			</select>

		</div>
		<button class="btn btn-primary col-3"
			style="margin-top: 24px; padding: 3px; height: 39px;"
			>Lọc</button>
			</form>
		<div class="container text-lg-left  shadow mt-5  p-4">
			<h3 class="fw-bold mb-4 text-center pb-2">Tất Cả Sản Phẩm ${tencate}</h3>
	
			<div class="row">
				<c:forEach items="${listsp}" var="sp">
					<div class="col-5 col-md-6 mb-4" style="min-height: 500px;">

						<div
							class="view overlay rounded z-depth-1 d-flex justify-content-center white">
							<img src="/ASSM_SOF3011/views/img/${sp.img }"
								class="img-fluid w-50" alt="Sample project image"> <a>
								<div class="mask rgba-white-slight"></div>
							</a>
						</div>

						<div class="px-3 pt-3 mx-1 mt-1 pb-0 text-center">
							<a class="" style="text-decoration: none;"> </a>
							<h4 class="font-weight-bold mb-3">${sp.ten}</h4>
							<p class="text-muted">
							<c:if test="${sp.soLuong==0}">Hết Hàng</c:if>
							<c:if test="${sp.soLuong!=0}">Còn: ${sp.soLuong} sản phẩm</c:if>
							</p>
							<p class="text-muted">Màu: ${sp.mauSac}</p>
							<p class="text-muted">Giá: ${sp.donGia}$</p>
							<a class="btn  btn-rounded btn-md btn-success" href="/ASSM_SOF3011/cart/create?id=${sp.id}"> <b>Thêm
									Vào Giỏ Hàng</b>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>

	</div>
	<div class="col-1"></div>