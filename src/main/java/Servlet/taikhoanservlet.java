package Servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.ProductDAO;
import DAO.UserDAO;
import model.Product;
import model.User;
import utils.EncryptUtil;

@WebServlet({ "/tk/home", "/tk/formlogin", "/tk/login", "/tk/dangki", "/tk/formdangki",

		"/tk/logout", "/tk/edit", "/tk/doipass", "/tk/doi", "/tk/update"

})
public class taikhoanservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO usdao;
	ProductDAO prddao;

	public taikhoanservlet() {
		super();
		usdao = new UserDAO();
		prddao = new ProductDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("formlogin")) {
			this.formlogin(request, response);
		} else if (uri.contains("edit")) {
			this.edit(request, response);
		} else if (uri.contains("formdangki")) {
			this.formdangki(request, response);
		} else if (uri.contains("pass")) {
			this.editpass(request, response);
		} else if (uri.contains("home")) {
			this.home(request, response);
		} else if (uri.contains("logout")) {
			this.logout(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("login")) {
			this.login(request, response);
		} else if (uri.contains("dangki")) {
			this.dangki(request, response);
		} else if (uri.contains("repass")) {

		} else if (uri.contains("update")) {
			update(request, response);

		} else if (uri.contains("doi")) {
			updatepass(request, response);
		}

	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("form", "/views/page/silde.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	private void formlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("form", "/views/page/taikhoan/formlogin.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	private void formdangki(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("form", "/views/page/taikhoan/formdangki.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void editpass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("form", "/views/page/taikhoan/editpass.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("form", "/views/page/taikhoan/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		try {
			if (validupdate(request, response) == true) {
				int id = Integer.parseInt(idStr);
				User oldValue = this.usdao.findBy(id);
				User newValue = new User();
				BeanUtils.populate(newValue, request.getParameterMap());
				newValue.setId(oldValue.getId());
				newValue.setPassword(oldValue.getPassword());
				this.usdao.update(newValue);
				session.setAttribute("message", "Sửa thành công");
				response.sendRedirect("/ASSM_SOF3011/tk/edit");
			} else {
				response.sendRedirect("/ASSM_SOF3011/tk/edit");
			}

			// TODO: Thông báo thành công

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: Thông báo lỗi
			e.printStackTrace();
			session.setAttribute("error", "Sửa thất bại");
			response.sendRedirect("/ASSM_SOF3011" + "/tk/edit?id=" + idStr);

		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email"), pwd = request.getParameter("password");
		try {
			String tk,mk;
			tk= request.getParameter("email");
			mk=request.getParameter("password");
			if(tk.length()==0) {
				session.setAttribute("error", "Tài khoản không được bỏ trống");
				response.sendRedirect("/ASSM_SOF3011/tk/formlogin");
				return;
			}else if(mk.length()==0) {
				session.setAttribute("error", "Mật Khẩu không được bỏ trống");
				response.sendRedirect("/ASSM_SOF3011/tk/formlogin");
				return;
			}else {
				User user = this.usdao.findByEmail(email);
				List<Product> listcart = new ArrayList<Product>();
				
				int slcart = 0;
				boolean check = EncryptUtil.check(pwd, user.getPassword());
				if (check == true) {
					// Đăng nhập thành công
					
					session.setAttribute("listcart", listcart);
					session.setAttribute("slcart", slcart);
					session.setAttribute("userdn", user);
					response.sendRedirect("/ASSM_SOF3011/tk/home");
				} else {
					session.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");
					response.sendRedirect("/ASSM_SOF3011/tk/formlogin");
				}
			}
			
		} catch (Exception e) {
			session.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");
			response.sendRedirect("/ASSM_SOF3011/tk/formlogin");

		}

	}

	private void dangki(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User entity = new User();
		try {
			if (valid(request, response) == true) {
				BeanUtils.populate(entity, request.getParameterMap());
				String encrypted = EncryptUtil.encrypt(request.getParameter("password"));
				entity.setPassword(encrypted);
				
				entity.setRoll(1);
				this.usdao.create(entity);
				session.setAttribute("message", "Thêm mới thành công mời bạn đăng nhập");
				response.sendRedirect("/ASSM_SOF3011/tk/formlogin");
			} else {
				
				response.sendRedirect("/ASSM_SOF3011/tk/formdangki");
				return;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute("error", "Lỗi Mời Bạn Nhập Lại");
			response.sendRedirect("/ASSM_SOF3011/tk/formdangki");
			return;
		}

	}

	private void updatepass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			User doipass = (User) session.getAttribute("userdn");

			String passcu = request.getParameter("passwordcu");
			System.out.println(EncryptUtil.check(passcu, doipass.getPassword()));
			String passmoi = request.getParameter("passwordmoi");
			String chekpassmoi = request.getParameter("checkpasswordmoi");
			if (EncryptUtil.check(passcu, doipass.getPassword()) == true) {

				if (passmoi.equals(chekpassmoi)) {

					try {

						BeanUtils.populate(doipass, request.getParameterMap());
						String pass = EncryptUtil.encrypt(passmoi);
						doipass.setPassword(pass);
						usdao.update(doipass);
						session.setAttribute("message", "Sửa thành công. Mời bạn đăng nhập lại");
						session.setAttribute("userdn", null);
						response.sendRedirect("/ASSM_SOF3011/tk/formlogin");
					} catch (Exception e) {
						session.setAttribute("error", "Sửa thất bại");
						response.sendRedirect("/ASSM_SOF3011/tk/doipass");
						e.printStackTrace();
					}
				} else {
					session.setAttribute("error", "Nhập lại mật khẩu không giống với mật khẩu bạn muốn đổi");
					response.sendRedirect("/ASSM_SOF3011/tk/doipass");
					return;
				}

			} else {
				session.setAttribute("error", "Mật Khẩu cũ sai bạn hãy thử lại");
				response.sendRedirect("/ASSM_SOF3011/tk/doipass");
				return;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("out");
		List<Product> ls= (List<Product>) session.getAttribute("listcart");
		ls.clear();
		session.setAttribute("listcart", ls);
		session.setAttribute("userdn", null);
		session.setAttribute("slcart", 0);
		

		response.sendRedirect("/ASSM_SOF3011/tk/home");

	}

	private boolean valid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<User> listuss = usdao.all();
		String hoTen,sdt, diaChi, email , password,ktpass;
		hoTen = request.getParameter("hoTen");
		sdt= request.getParameter("sdt");
		diaChi= request.getParameter("diaChi");
		email= request.getParameter("email");
		password= request.getParameter("password");
		ktpass= request.getParameter("ktpass");
		

		String checksdt="^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
		 String checkemai = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(hoTen.length()==0) {
			 session.setAttribute("error",
						"Không được để trống Họ Tên");
			 return false;
		 }
		 else if(diaChi.length()==0) {
			 session.setAttribute("error",
						"Không được để trống Địa Chỉ");
			 return false;
		 }
		 else if(sdt.length()==0) {
			 session.setAttribute("error",
						"Không được để trống SĐT");
			 return false;
		 }
		 else if(email.length()==0) {
			 session.setAttribute("error",
						"Không được để trống Email");
			 return false;
		 }else if(password.length()==0) {
			 session.setAttribute("error",
						"Không được để trống PassWord");
			 return false;
		 }
		 else if(!sdt.matches(checksdt)) {
			 session.setAttribute("error",
						"Sai Định Dạng SĐT");
			 return false;
		 }
		 else if(!email.matches(checkemai)) {
			 session.setAttribute("error",
						"Sai Định Dạng email");
			 return false;
		 }else if(!(password.equals(ktpass))) {
			 session.setAttribute("error",
						"Nhập Lại PassWord Sai");
			 return false;
		 }
		for(int i=0;i<listuss.size();i++) {
			if(listuss.get(i).getEmail().equals(email)) {
				session.setAttribute("error",
						"Email đã có người đăng kí mời bạn chọn quên mật khẩu hoặc tạo tài khoản mới");
				return false;
			}
		}
		
		 
		
		 
		 return true;
	 }

	private boolean validupdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String hoTen, sdt, diaChi, email, password;
		hoTen = request.getParameter("hoTen");
		sdt = request.getParameter("sdt");
		diaChi = request.getParameter("diaChi");
		email = request.getParameter("email");

		String checksdt = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
		String checkemai = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (hoTen.length() == 0) {
			session.setAttribute("error", "Không được để trống Họ Tên");
			return false;
		} else if (diaChi.length() == 0) {
			session.setAttribute("error", "Không được để trống Địa Chỉ");
			return false;
		} else if (sdt.length() == 0) {
			session.setAttribute("error", "Không được để trống SĐT");
			return false;
		} else if (email.length() == 0) {
			session.setAttribute("error", "Không được để trống Email");
			return false;
		} else if (!sdt.matches(checksdt)) {
			session.setAttribute("error", "Sai Định Dạng SĐT");
			return false;
		} else if (!email.matches(checkemai)) {
			session.setAttribute("error", "Sai Định Dạng email");
			return false;
		}
		
		return true;

	}
}
