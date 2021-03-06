package Servlet;

import java.io.File;
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
import DAO.cartDAO;
import model.Cart;
import model.Category;
import model.User;
import utils.EncryptUtil;


@WebServlet({
	"/users/index",
	"/users/show",
	"/users/create",
	"/users/store",
	"/users/edit",
	"/users/update",
	"/users/delete",
})
public class userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO usdao;
	CategoryDAO catedao;
	cartDAO cartdao;
       
    
    public userservlet() {
        super();
        usdao= new UserDAO();
        catedao = new CategoryDAO();
        cartdao= new cartDAO();
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
		// TODO Auto-generated method stub
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
			Date now = new Date();
			List<User> ds = this.usdao.all();
			request.setAttribute("ds", ds);
			request.setAttribute("now", now);
			request.setAttribute("form", "/views/page/users/create.jsp");
			request.setAttribute("table",
				"/views/page/users/index.jsp");
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
			request.setAttribute("form",
				"/views/page/users/create.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		}
		
		private void store(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			HttpSession session = request.getSession();
			try {
				if (valid(request, response)==true) {
					User entity = new User();
					BeanUtils.populate(entity,
						request.getParameterMap());
					String encrypted = EncryptUtil.encrypt(
						request.getParameter("password")
					);
					entity.setPassword(encrypted);
					
					this.usdao.create(entity);
					
					session.setAttribute("message",
						"Th??m m???i th??nh c??ng");
					response.sendRedirect("/ASSM_SOF3011"
						+ "/users/index");
				}else {
					response.sendRedirect("/ASSM_SOF3011"
							+ "/users/index");
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("error",
						"Th??m m???i th???t b???i");
				response.sendRedirect("/ASSM_SOF3011"
					+ "/users/index");
			}
		}
		
		private void edit(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			User entity = this.usdao.findBy(id);
			List<User> ds = this.usdao.all();
		   
			request.setAttribute("ds", ds);
			request.setAttribute("user", entity);
			request.setAttribute("form", "/views/page/users/edit.jsp");
			request.setAttribute("table",
				"/views/page/users/index.jsp");
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
			User us= (User) session.getAttribute("userdn");
			List<Category> listcate = catedao.all();
			
			if(us.getId()==id) {
				session.setAttribute("error",
						"B???n Kh??ng th??? x??a ch??nh b???n");
				response.sendRedirect("/ASSM_SOF3011"
						+ "/users/index");
				return;
			}
			for(int i=0;i<listcate.size();i++) {
				if(id==listcate.get(i).getUser().getId()) {
					session.setAttribute("error",
							"Ng?????i n??y c?? li??n quan ?????n qu???n l?? category, b???n h??y x??a d??? li???u li??n quan ?????n ng?????i n??y tr?????c.");
					response.sendRedirect("/ASSM_SOF3011"
							+ "/users/index");
					return;
				}
			}
			List<Cart> listcart= cartdao.all();
			for(int i=0;i<listcart.size();i++) {
				if(id==listcart.get(i).getUserId()) {
					session.setAttribute("error",
							"Ng?????i n??y c?? li??n quan ?????n qu???n l?? Cart, b???n h??y x??a d??? li???u li??n quan ?????n ng?????i n??y tr?????c.");
					response.sendRedirect("/ASSM_SOF3011"
							+ "/users/index");
					return;
				}
			}
			
			User entity = this.usdao.findBy(id);
			try {
				this.usdao.delete(entity);
				session.setAttribute("message","x??a th??nh c??ng");
				response.sendRedirect("/ASSM_SOF3011"
						+ "/users/index");
				// TODO: Th??ng b??o th??nh c??ng
			} catch (Exception e) {
				// TODO: Th??ng b??o l???i
				e.printStackTrace();
				session.setAttribute("error",
						"X??a L???i");
				response.sendRedirect("/ASSM_SOF3011"
						+ "/users/index");
			}

			
		}
		
		private void update(
			HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String idStr = request.getParameter("id");
			try {
				if(validupdate(request, response)==true) {
					int id = Integer.parseInt(idStr);
					User oldValue = this.usdao.findBy(id);
					User newValue = new User();
					BeanUtils.populate(newValue,
						request.getParameterMap());	
					newValue.setId(oldValue.getId());
					String pass= oldValue.getPassword();
					newValue.setPassword(pass);
					this.usdao.update(newValue);
					session.setAttribute("message",
							"S???a th??nh c??ng");
					// TODO: Th??ng b??o th??nh c??ng
					response.sendRedirect("/ASSM_SOF3011"
						+ "/users/index");
				}
				else {
					response.sendRedirect("/ASSM_SOF3011"
							+ "/users/edit?id=" + idStr);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: Th??ng b??o l???i
				e.printStackTrace();
				session.setAttribute("error",
						"S???a th???t b???i");
				response.sendRedirect("/ASSM_SOF3011"
					+ "/users/edit?id=" + idStr);
				
			}
		}
		private boolean valid(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession();
			
			List<User> listuss = usdao.all();
			String hoTen,sdt, diaChi, email , password;
			hoTen = request.getParameter("hoTen");
			sdt= request.getParameter("sdt");
			diaChi= request.getParameter("diaChi");
			email= request.getParameter("email");
			password= request.getParameter("password");

			String checksdt="^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
			 String checkemai = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if(hoTen.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng H??? T??n");
				 return false;
			 }
			 else if(diaChi.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng ?????a Ch???");
				 return false;
			 }
			 else if(sdt.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng S??T");
				 return false;
			 }
			 else if(email.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng Email");
				 return false;
			 }else if(password.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng PassWord");
				 return false;
			 }
			 else if(!sdt.matches(checksdt)) {
				 session.setAttribute("error",
							"Sai ?????nh D???ng S??T");
				 return false;
			 }
			 else if(!email.matches(checkemai)) {
				 session.setAttribute("error",
							"Sai ?????nh D???ng email");
				 return false;
			 }
			for(int i=0;i<listuss.size();i++) {
				if(listuss.get(i).getEmail().equals(email)) {
					session.setAttribute("error",
							"Email ???? c?? ng?????i ????ng k?? m???i b???n ch???n qu??n m???t kh???u ho???c t???o t??i kho???n m???i");
					return false;
				}
			}
			
			 
			
			 
			 return true;
		 }
		private boolean validupdate(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession();
			
			String hoTen,sdt, diaChi, email , password;
			hoTen = request.getParameter("hoTen");
			sdt= request.getParameter("sdt");
			diaChi= request.getParameter("diaChi");
			email= request.getParameter("email");
			
			
			
			String checksdt="^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
			 String checkemai = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if(hoTen.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng H??? T??n");
				 return false;
			 }
			 else if(diaChi.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng ?????a Ch???");
				 return false;
			 }
			 else if(sdt.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng S??T");
				 return false;
			 }
			 else if(email.length()==0) {
				 session.setAttribute("error",
							"Kh??ng ???????c ????? tr???ng Email");
				 return false;
			 }
			 else if(!sdt.matches(checksdt)) {
				 session.setAttribute("error",
							"Sai ?????nh D???ng S??T");
				 return false;
			 }
			 else if(!email.matches(checkemai)) {
				 session.setAttribute("error",
							"Sai ?????nh D???ng email");
				 return false;
			 }
			
			
			 
			
			 
			 return true;
			
		}


}
