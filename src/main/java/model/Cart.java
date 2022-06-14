package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cart database table.
 * 
 */
@Entity
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="product_id")
	private int productId;

	private int soluong;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timebuy;

	@Column(name="user_id")
	private int userId;

	public Cart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSoluong() {
		return this.soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimebuy() {
		return this.timebuy;
	}

	public void setTimebuy(Date timebuy) {
		this.timebuy = timebuy;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}