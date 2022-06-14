package Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.cartDAO;
import model.Cart;
import model.Category;
import model.Product;
import model.User;
import utils.EncryptUtil;

@WebServlet({ "/prd/index", "/prd/show", "/prd/create", "/prd/store", "/prd/edit", "/prd/update", "/prd/delete", })
public class productservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductDAO prddao;
	CategoryDAO catedao;
	cartDAO cartdao;

	public productservlet() {
		super();
		prddao = new ProductDAO();
		catedao = new CategoryDAO();
		cartdao= new cartDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("show")) {
			this.show(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("edit")) {
			this.edit(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		} else {
			// 404
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			// 404
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Product prd = (Product) session.getAttribute("prd");
		if(prd==null) {
			List<Product> listsp = prddao.all();
			request.setAttribute("dsprd", listsp);
			
		}else {
			List<Product> listsp = prddao.all();
			for(int i =0;i<listsp.size();i++) {
				if(listsp.get(i).getId()==prd.getId()) {
					listsp.set(i, prd);
					
				}
				
			}
			request.setAttribute("dsprd", listsp);
			
		}
	
		List<Category> dscate = this.catedao.all();
		
		request.setAttribute("dscate", dscate);
		request.setAttribute("form", "/views/page/products/create.jsp");
		request.setAttribute("table", "/views/page/products/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("form", "/views/page/users/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			if (valid(request, response)==true) {
				Product entity = new Product();
				int id = Integer.parseInt(request.getParameter("category_id"));
				Category cate = catedao.findBy(id);
				entity.setCategoryByCategoryId(cate);
				BeanUtils.populate(entity, request.getParameterMap());

				this.prddao.create(entity);
				request.setAttribute("form", "/views/page/products/create.jsp");
				request.setAttribute("table", "/views/page/products/index.jsp");
				session.setAttribute("message", "Thêm mới thành công");

				response.sendRedirect("/ASSM_SOF3011" + "/prd/index");
			}
			else {
				response.sendRedirect("/ASSM_SOF3011" + "/prd/index");
			}
//				
			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới thất bại");
			response.sendRedirect("/ASSM_SOF3011" + "/prd/index");
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> dsprd = this.prddao.all();
		List<Category> dscate = this.catedao.all();
		int id = Integer.parseInt(request.getParameter("id"));
		Product prd = prddao.findBy(id);
		request.setAttribute("prd", prd);
		request.setAttribute("dsprd", dsprd);
		request.setAttribute("dscate", dscate);
		request.setAttribute("form", "/views/page/products/edit.jsp");
		request.setAttribute("table", "/views/page/products/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		HttpSession session = request.getSession();
		int id = Integer.parseInt(idStr);
		List<Cart> listcart= cartdao.all();
		for(int i=0;i<listcart.size();i++) {
			if(listcart.get(i).getProductId()==id) {
				session.setAttribute("error", "Đang có sản phẩm trong giỏ hàng mời bạn xóa trong giỏ hàng trước");
				response.sendRedirect("/ASSM_SOF3011" + "/prd/index");
				return;
			}
		}
		
		for(int i=0;i<listcart.size();i++) {
			if(id==listcart.get(i).getProductId()) {
				session.setAttribute("error",
						"Sản Phẩm này có liên quan đến quản lý Cart, bạn hãy xóa dữ liệu liên quan đến người này trước.");
				response.sendRedirect("/ASSM_SOF3011"
						+ "/users/index");
				return;
			}
		}
		Product entity = this.prddao.findBy(id);
		try {
			this.prddao.delete(entity);
			session.setAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Đang có sản phẩm trong giỏ hàng mời bạn xóa trong giỏ hàng trước");
			response.sendRedirect("/ASSM_SOF3011" + "/prd/index");
			return;
		}

		response.sendRedirect("/ASSM_SOF3011" + "/prd/index");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		try {
			if (valid(request, response)==true) {
				int id = Integer.parseInt(idStr);
				Product oldValue = this.prddao.findBy(id);
				Product newValue = new Product();
				BeanUtils.populate(newValue, request.getParameterMap());
				int idcate = Integer.parseInt(request.getParameter("category_id"));
				Category prdcate = catedao.findBy(idcate);
				newValue.setCategoryByCategoryId(prdcate);
				this.prddao.update(newValue);
				session.setAttribute("message", "Sửa thành công");
				// TODO: Thông báo thành công
				response.sendRedirect("/ASSM_SOF3011/prd/index");
			}
			else {
				response.sendRedirect("/ASSM_SOF3011/prd/edit?id=" + idStr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: Thông báo lỗi
			e.printStackTrace();
			session.setAttribute("error", "Sửa thất bại");
			response.sendRedirect("/ASSM_SOF3011" + "/prd/edit?id=" + idStr);

		}
	}

	private boolean valid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ten, soLuong, donGia, img, mauSac, kichThuoc;
		ten = request.getParameter("ten");
		soLuong = request.getParameter("soLuong");
		donGia = request.getParameter("donGia");
		mauSac = request.getParameter("mauSac");
		kichThuoc = request.getParameter("kichThuoc");
		img = request.getParameter("img");

		if (ten.length() == 0) {
			session.setAttribute("error", "Không được để trống Tên");
			return false;
		} else if (soLuong.length() == 0) {
			session.setAttribute("error", "Không được để trống Số Lượng");
			return false;
		} else if (donGia.length() == 0) {
			session.setAttribute("error", "Không được để trống Đơn Giá");
			return false;
		} else if (mauSac.length() == 0) {
			session.setAttribute("error", "Không được để trống Màu Sắc");
			return false;
		} else if (kichThuoc.length() == 0) {
			session.setAttribute("error", "Không được để trống Kích thước");
			return false;
		} else if (img.length() == 0) {
			session.setAttribute("error", "Không được để trống ảnh");
			return false;
		}
		try {
			int checksl=
			Integer.parseInt(soLuong);
			if (checksl<0) {
				session.setAttribute("error", "Số lượng phải là số dương");
				return false;
			}
		} catch (Exception e) {
			session.setAttribute("error", "Số lượng phải là số");
			return false;
		}
		try {
			int checkdongia=
			Integer.parseInt(donGia);
			if (checkdongia < 0 ) {
				session.setAttribute("error", "Đơn Giá phải lớn hơn 0");
				return false;
			}
		} catch (Exception e) {
			session.setAttribute("error", "Đơn Giá phải là số");
			return false;
		}
		

		return true;

	}

}
