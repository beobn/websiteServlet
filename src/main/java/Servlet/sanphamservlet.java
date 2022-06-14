package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import model.Category;
import model.Product;

@WebServlet({ "/sp/listsp", "/sp/loclistsp" })
public class sanphamservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDAO prddao;
	CategoryDAO cateDAO;
	

	public sanphamservlet() {
		super();
		prddao = new ProductDAO();
		cateDAO = new CategoryDAO();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		if (uri.contains("listsp")) {
			listsp(request, response);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("loclistsp")) {
			loclistsp(request, response);

		}
		
	}

	private void listsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Product prd = (Product) session.getAttribute("prd");
		List<Category> listcate = cateDAO.all();
		if(prd==null) {
			List<Product> listsp = prddao.all();
			request.setAttribute("listsp", listsp);
			
		}else {
			List<Product> listsp = prddao.all();
			for(int i =0;i<listsp.size();i++) {
				if(listsp.get(i).getId()==prd.getId()) {
					listsp.set(i, prd);
					
				}
				
			}
			request.setAttribute("listsp", listsp);
			
		}
		
		
	
		request.setAttribute("listcate", listcate);
		request.setAttribute("form", "/views/page/sanpham/listsp.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		
	}

	private void loclistsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loc = request.getParameter("loc");

		if (loc.equals("full")) {
			List<Product> listsp = prddao.all();
			request.setAttribute("listsp", listsp);
			request.setAttribute("form", "/views/page/sanpham/listsp.jsp");
		} else {

			List<Product> listsp = prddao.findBycate(Integer.parseInt(loc));
			Category cte= cateDAO.findBy(Integer.parseInt(loc));
			String tencate= cte.getTen();
			request.setAttribute("listsp", listsp);
			request.setAttribute("tencate", tencate);
			request.setAttribute("form", "/views/page/sanpham/listsp.jsp");
		}
		List<Category> listcate = cateDAO.all();

		request.setAttribute("listcate", listcate);
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
//		response.sendRedirect("/ASSM_SOF3011/sp/listsp");

	}

}
