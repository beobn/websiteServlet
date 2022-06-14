package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;



	public Category getCategoryByCategoryId() {
		return categoryByCategoryId;
	}

	public void setCategoryByCategoryId(Category categoryByCategoryId) {
		this.categoryByCategoryId = categoryByCategoryId;
	}

	@Column(name = "don_gia")
	private int donGia;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id")
	private Category categoryByCategoryId;

	private String img;

	@Column(name = "kich_thuoc")
	private String kichThuoc;

	@Column(name = "mau_sac")
	private String mauSac;

	@Column(name = "so_luong")
	private int soLuong;
	

	
	private String ten;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getDonGia() {
		return this.donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getKichThuoc() {
		return this.kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public String getMauSac() {
		return this.mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

}