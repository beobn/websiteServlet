<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
	    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<link rel="icon" href="/ASSM_SOF3011/views/img/logo.png"
	type="image/x-icon">
<title>GẤUU BOUTIQUE</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS v5.0.2 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<style>
li:hover {
	border-bottom: 3px solid black;
}

.hover {
	display: none;
	padding: 0 15px;
	position: absolute;
	z-index: 100;
	width: 200px;
	float: left;
	left: 0;
}
/* css before */
.quan_li::before {
	content: "";
	width: 39px;
	height: 92%;
	position: absolute;
	margin-top: 9px;
	background-color: #f8f9fa !important;
	margin-left: 123px;
	z-index: 1000;
}

.hover li {
	list-style: none;
	display: inline-block;
}

ul.ao>li {
	position: relative;
	left: 0px;
}

ul.ao>li:hover .hover {
	display: block;
}

/* css sub-hover */
.sub-menu {
	display: none;
	position: absolute;
	left: 125px;
	width: 200px;
	background-color: #f8f9fa !important;
	background-color: red;
	z-index: 1;
	top: -1px;
}

/* css hover  */
ul.quan>li:hover .sub-menu {
	display: block;
	transition: block 2s ease-in-out 0.5s;
}

/* css boder- bottom */
/* .sub-menu>li:nth-child(3) {
                border-bottom: none;
            } */
.quandai {
	display: none;
	padding: 0 15px;
	position: absolute;
	z-index: 100;
	width: 10000px;
	height: 200px;
	float: left;
	left: 0;
}

.quandai li {
	list-style: none;
	display: inline-block;
}

ul.quan>li {
	position: relative;
}

ul.quan>li:hover .hover {
	display: block;
}

.logo {
	padding-left: 0;
	margin-bottom: 0;
}

.imgslide {
	padding-left: 50px;
	padding-right: 50px;
	height: 600px;
}
</style>
</head>

<body>
	<div>
		<header>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">



					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0 col-5 ao"
							style="font-size: 25px;">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Áo Nam</a>
								<ul class="hover bg-light">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="#">Áo sơ mi</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="#">Áo polo</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="#">Áo Thun</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Quần Nam</a>
								<ul class="hover bg-light quan">
									<li class="nav-item quan_li"><a class="nav-link active"
										aria-current="page" href="#">Quần Dài</a>
										<ul class="sub-menu">
											<li class="nav-item"><a class="nav-link active"
												aria-current="page" href="#">Quần jean</a></li>
											<li class="nav-item"><a class="nav-link active"
												aria-current="page" href="#">Quần vải</a></li>
											<li class="nav-item"><a class="nav-link active"
												aria-current="page" href="#">Quần jogger</a></li>
										</ul></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="#">Quần Cộc</a></li>

								</ul></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Phụ Kiện</a></li>
						</ul>
						<ul class="col-2 text-center logo">
							<a href="/ASSM_SOF3011/sp/listsp" style="text-decoration: none; color: black;"> <img
								src="/ASSM_SOF3011/views/img/logo.png" alt="" width="40"
								height="40">
								<h5>GẤUU BOUTIQUE</h5>
							</a>

						</ul>
						<ul class="col-5 navbar-nav justify-content-end ao"
							style="font-size: 25px;">
							<c:if test="${empty userdn }">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Tài Khoản</a>
								<ul class="hover bg-light">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/formlogin">Đăng Nhập</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/formdangki">Đăng Kí</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/formrepass">Quên Mật Khẩu</a></li>
									
								</ul></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Liên Hệ</a></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="/ASSM_SOF3011/cart/index">Giỏ Hàng (${slcart})</a></li>
							</c:if>
							<c:if test="${!empty userdn }">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#"><span>${userdn.hoTen}</span></a>
								<ul class="hover bg-light">
									<c:if test="${userdn.roll ==1}">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/edit?id=${userdn.id}">Thông tin</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/doipass">Đổi mật khẩu</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/cart/index">Giỏ Hàng (${slcart})</a></li>
									<li><hr class="dropdown-divider"></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/logout">Đăng Xuất</a></li>
										</c:if>
										<c:if test="${userdn.roll ==0}">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/edit?id=${userdn.id}">Thông tin</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/doipass">Đổi mật khẩu</a></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/users/index">Quản Lý User</a></li>
										<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/prd/index">Quản Lý ProDuct</a></li>
										<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/cate/index">Quản Lý Category</a></li>
										<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/cart/admincart">Quản Lý Cart</a></li>
									<li><hr class="dropdown-divider"></li>
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="/ASSM_SOF3011/tk/logout">Đăng Xuất</a></li>
										</c:if>
								</ul></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Liên Hệ</a></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="/ASSM_SOF3011/cart/index">Giỏ Hàng (${slcart})</a></li>
							</c:if>
						</ul>
					</div>

				</div>

			</nav>



		</header>
		<div>
			<jsp:include page="${ form }"></jsp:include>
			<hr>
			<jsp:include page="${ table }"></jsp:include>
		</div>
		<footer></footer>
	</div>



	<!-- Bootstrap JavaScript Libraries -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>

</html>