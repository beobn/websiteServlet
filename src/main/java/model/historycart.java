package model;

import java.util.Date;

public class historycart {
	private String tenSp,img,mauSac,kichThuoc,tenUs;
	private int donGia,soLuong,trangThai,id;
	private Date timebuy;
	
	public historycart() {
		
	}

	public historycart(String tenSp, String img, String mauSac, String kichThuoc, String tenUs, int donGia, int soLuong,
			int trangThai, int id, Date timebuy) {
		super();
		this.tenSp = tenSp;
		this.img = img;
		this.mauSac = mauSac;
		this.kichThuoc = kichThuoc;
		this.tenUs = tenUs;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
		this.id = id;
		this.timebuy = timebuy;
	}


	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	public String getKichThuoc() {
		return kichThuoc;
	}
	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Date getTimebuy() {
		return timebuy;
	}

	public void setTimebuy(Date timebuy) {
		this.timebuy = timebuy;
	}

	public String getTenUs() {
		return tenUs;
	}

	public void setTenUs(String tenUs) {
		this.tenUs = tenUs;
	}
	public int tongtien() {
		return soLuong*donGia;
	}
	
	
}
