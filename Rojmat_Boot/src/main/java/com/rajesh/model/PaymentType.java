package com.rajesh.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="paymenttype")
public class PaymentType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long payid;
	@Column
	private String paymentMode;
	@OneToOne
	@JoinColumn(name="user_id")
	private User users;
	
	public long getPayid() {
		return payid;
	}
	public void setPayid(long payid) {
		this.payid = payid;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	
}
