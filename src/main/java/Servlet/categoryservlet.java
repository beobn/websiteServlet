
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
import DAO.UserDAO;
import model.Category;
import model.User;
import utils.EncryptUtil;


@WebServlet({
	"/cate/index",
	"/cate/show",
	"/cate/create",
	"/cate/store",
	"/cate/edit",
	"/cate/update",
	"/cate/delete",
}

		)
public class categoryservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO usdao;
	CategoryDAO catedao;
    public categoryservlet() {
        super();
        usdao= new UserDAO();
        catedao= new CategoryDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			// 404
		}
	}
	private void index(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
//		File dir = new File(request.getServletContext().getRealPath("/img"));
//		if(!dir.exists()) {
//			dir.mkdirs();
//		}
			Date now = new Date();
//			int idus = Integer.parseInt(request.getParameter("user_id")) ;
//			User usct= usdao.findBy(idus);
			List<User> ds = this.usdao.all();
			List<Category> dscate=this.catedao.all();
			request.setAttribute("ds", ds);
			request.setAttribute("dscate", dscate);
//			request.setAttribute("usct", usct);
			request.setAttribute("now", now);
			request.setAttribute("form", "/views/page/category/create.jsp");
			request.setAttribute("table",
				"/views/page/category/index.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
		}
		
		private void show(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			//
		}
		
		private void create(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			request.setAttribute("form", "/views/page/category/create.jsp");
			request.setAttribute("table",
				"/views/page/category/index.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
			
		}
		
		
		private void store(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			HttpSession session = request.getSession();
			try {
				if(valid(request, response)==true) {
					Category entity = new Category();
					String ten= request.getParameter("ten");
					int id= Integer.parseInt(request.getParameter("user_id")) ;
					User usercate= this.usdao.findBy(id);
					entity.setTen(ten);
					entity.setUser(usercate);
					BeanUtils.populate(entity, request.getParameterMap());			
					this.catedao.create(entity);
					request.setAttribute("form", "/views/page/category/create.jsp");
					request.setAttribute("table",
						"/views/page/category/index.jsp");
					session.setAttribute("message",
						"Thêm mới thành công");
					response.sendRedirect("/ASSM_SOF3011"
						+ "/cate/index");
				}else {
					response.sendRedirect("/ASSM_SOF3011"
							+ "/cate/index");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("error",
						"Thêm mới thất bại");
				response.sendRedirect("/ASSM_SOF3011"
					+ "/cate/index");
			}
		}
		
		private void edit(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			
				String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				Category entity = this.catedao.findBy(id);
				List<User> ds = this.usdao.all();
				List<Category> dscate=this.catedao.all();
				request.setAttribute("catesua", entity);
				request.setAttribute("ds", ds);
				request.setAttribute("dscate", dscate);
				request.setAttribute("form", "/views/page/category/edit.jsp");
				request.setAttribute("table",
					"/views/page/category/index.jsp");
				request.getRequestDispatcher("/views/layout.jsp")
					.forward(request, response);
			
			
		}
		
		private void delete(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Category entity = this.catedao.findBy(id);
			try {
				this.catedao.delete(entity);
				// TODO: Thông báo thành công
				session.setAttribute("message",
						"Xóa thành công");
			} catch (Exception e) {
				// TODO: Thông báo lỗi
				e.printStackTrace();
			}

			response.sendRedirect("/ASSM_SOF3011"
					+ "/cate/index");
		}
		
		private void update(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String idStr = request.getParameter("id");
			try {
				if (valid(request, response)==true) {
					int id = Integer.parseInt(idStr);
					Category oldValue = this.catedao.findBy(id);
					Category newValue = new Category();
					int idus = Integer.parseInt(request.getParameter("user_id")) ;
					User usct= usdao.findBy(idus);
					
					BeanUtils.populate(newValue,
						request.getParameterMap());
					newValue.setId(oldValue.getId());
					newValue.setUser(usct);
					
					this.catedao.update(newValue);
					session.setAttribute("message",
							"Sửa thành công");
					// TODO: Thông báo thành công
					response.sendRedirect("/ASSM_SOF3011"
						+ "/cate/index");
				}else {
					session.setAttribute("error",
							"Sửa thất bại");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: Thông báo lỗi
				response.sendRedirect("/ASSM_SOF3011"
					+ "/cate/edit?id=" + idStr);
				session.setAttribute("error",
						"Sửa thất bại");
			}
		}
		private boolean valid(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession();
			String ten, soLuong, donGia, img, mauSac, kichThuoc;
			ten = request.getParameter("ten");
			
			if (ten.length() == 0) {
				session.setAttribute("error", "Không được để trống Tên");
				return false;
			}

			return true;

		}
	

}
