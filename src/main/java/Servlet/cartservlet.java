package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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
import DAO.cartDAO;
import model.Cart;
import model.Product;
import model.User;
import model.historycart;

@WebServlet({ "/cart/index","/cart/updatestt","/cart/xoass","/cart/admincart", "/cart/store", "/cart/create", "/cart/addcart" })
public class cartservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	cartDAO cartdao;
	ProductDAO prddao;
	UserDAO usdao;

	public cartservlet() {
		super();
		cartdao = new cartDAO();
		prddao = new ProductDAO();
		usdao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		}else if (uri.contains("xoass")) {
			this.xoass(request, response);
		} 
		else if (uri.contains("admincart")) {
			this.admincart(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();	 
		if (uri.contains("addcart")) {
			this.addcart(request, response);
		} else if (uri.contains("updatestt")) {
			this.updatestt(request, response);
		}

	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("userdn");
		session.getAttribute("ds");
		request.setAttribute("form", "/views/page/giohang/index.jsp");
		
		List<historycart> listhtr=new ArrayList<historycart>();
		List<Cart> listscart = cartdao.all();
		String tenSp,img,mauSac,kichThuoc,tenUs;
		int donGia,soLuong,trangThai,id;
		 Date timebuy;
		for (int i = 0; i < listscart.size(); i++) {
			if (listscart.get(i).getUserId() == us.getId()) {
				Product sp = prddao.findBy(listscart.get(i).getProductId());
				tenSp=sp.getTen();
				img=sp.getImg();
				mauSac=sp.getMauSac();
				kichThuoc=sp.getKichThuoc();
				donGia=sp.getDonGia();
				soLuong=listscart.get(i).getSoluong();
				trangThai = listscart.get(i).getStatus();
				id= listscart.get(i).getId();
				timebuy=listscart.get(i).getTimebuy();
				tenUs=us.getHoTen();
				historycart htr = new historycart(tenSp, img, mauSac, kichThuoc, tenUs, donGia, soLuong, trangThai, id, timebuy);
				listhtr.add(htr);
				
			} 
		}
		session.setAttribute("listhtr", listhtr);
		request.setAttribute("table", "/views/page/giohang/history.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Product spcart = prddao.findBy(id);
			List<Product> listcart = (List<Product>) session.getAttribute("listcart");
			for (int i = 0; i < listcart.size(); i++) {
				if (spcart.equals(listcart.get(i))) {
					session.setAttribute("error", "Hàng của bạn đã có trong giỏ hàng");
					response.sendRedirect("/ASSM_SOF3011/cart/index");
					return;
				}
				
				
			}
			if (spcart.getSoLuong()==0) {
				session.setAttribute("error", "Hàng bạn muốn mua hiện tại đang hết");
				response.sendRedirect("/ASSM_SOF3011/cart/index");
				return;
			}
			
			listcart.add(spcart);
			int slcart;
			if(listcart.size()==0) {
				slcart=0;
				session.setAttribute("slcart", slcart);
			}else {
				slcart=listcart.size();
				session.setAttribute("slcart", slcart);
			}
			
			session.setAttribute("ds", listcart);
			session.setAttribute("message", "Thêm vào giỏi hàng thành công ");
			response.sendRedirect("/ASSM_SOF3011/cart/index");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi");

		}
	}
	private void xoass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("id"));
			List<Product> listcart = (List<Product>) session.getAttribute("listcart");
			for(int i=0;i<listcart.size();i++) {
				if(listcart.get(i).getId()==id) {
					listcart.remove(i);
				}
			}
			int slcart;
			if(listcart.size()==0) {
				slcart=0;
				session.setAttribute("slcart", slcart);
			}else {
				slcart=listcart.size();
				session.setAttribute("slcart", slcart);
			}
			session.setAttribute("ds", listcart);
			session.setAttribute("message", "Xóa khỏi giỏi hàng thành công ");
			response.sendRedirect("/ASSM_SOF3011/cart/index");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi");

		}
	}

	private void addcart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String idStr = request.getParameter("id");
			int idprd = Integer.parseInt(idStr);
			Product prd= prddao.findBy(idprd);
			User us = (User) session.getAttribute("userdn");
			int idus = us.getId();
			Date now = new Date();
			Cart cart = new Cart();
			String quantity = request.getParameter("quantity" + idStr);
			cart.setProductId(idprd);
			cart.setUserId(idus);
			cart.setStatus(0);
			cart.setTimebuy(now);
			cart.setSoluong(Integer.parseInt(quantity));
			List<Product> listcart = (List<Product>) session.getAttribute("listcart");
			prd.setSoLuong(prd.getSoLuong()-cart.getSoluong());
			cartdao.create(cart);
			prddao.update(prd);
			session.setAttribute("prd", prd);
			
			
			for(int i=0;i<listcart.size();i++) {
				if(listcart.get(i).getId()==idprd) {
					listcart.remove(i);
				}
			}
			int slcart;
			if(listcart.size()==0) {
				slcart=0;
				session.setAttribute("slcart", slcart);
			}else {
				slcart=listcart.size();
				session.setAttribute("slcart", slcart);
			}
			List<Product>  listsp = prddao.all();
			request.setAttribute("listsp", listsp);
			session.setAttribute("message", "Đặt hàng thành công");
			
			response.sendRedirect("/ASSM_SOF3011/cart/index");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	private void admincart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Cart> listcart = cartdao.all();
			List<historycart> listhtr = new ArrayList<historycart>();
			String tenSp,img,mauSac,kichThuoc,tenUs;
			int donGia,soLuong,trangThai,id;
			 Date timebuy;
			for(int i=0;i<listcart.size();i++) {
				Product sp = prddao.findBy(listcart.get(i).getProductId());
				User us = usdao.findBy(listcart.get(i).getUserId());
				tenSp = sp.getTen();
				img=sp.getImg();
				mauSac=sp.getMauSac();
				kichThuoc=sp.getKichThuoc();
				tenUs = us.getHoTen();
				donGia=sp.getDonGia();
				soLuong=listcart.get(i).getSoluong();
				trangThai=listcart.get(i).getStatus();
				id=listcart.get(i).getId();
				timebuy = listcart.get(i).getTimebuy();
				historycart htr = new historycart(tenSp, img, mauSac, kichThuoc, tenUs, donGia, soLuong, trangThai, id, timebuy);
				listhtr.add(htr);
			}
			request.setAttribute("listhtradmin", listhtr);
			request.setAttribute("table", "/views/page/giohang/admincart.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	private void updatestt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int stt= Integer.parseInt(request.getParameter("status"));
			System.out.println(stt);
			Cart cart= cartdao.findBy(id);
			Cart entity = new Cart();
			entity.setId(id);
			entity.setProductId(cart.getProductId());
			entity.setSoluong(cart.getSoluong());
			entity.setStatus(stt);
			entity.setTimebuy(cart.getTimebuy());
			entity.setUserId(cart.getUserId());
			cartdao.update(entity);
			
			
			session.setAttribute("message", "Cập Nhập thành công");

			response.sendRedirect("/ASSM_SOF3011" + "/cart/admincart");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
