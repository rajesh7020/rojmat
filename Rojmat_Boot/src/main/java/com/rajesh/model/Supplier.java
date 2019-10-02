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
@Table(name="supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long supid;
	@Column
	private String suppliername;
	@Column
	private String gstinno;
	@Column
	private String panno;
	@Column
	private String address;
	@Column
	private int pincode;
	@Column
	private String city;
	@Column
	private long mobileno;
	@Column
	private String email;
	@Column
	private String bankname;
	@Column
	private String branchname;
	@Column
	private String accountno;
	@Column
	private String ifscode;
	@OneToOne
	@JoinColumn(name="stateid")
	private State state;
	public long getSupid() {
		return supid;
	}
	public void setSupid(long supid) {
		this.supid = supid;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getGstinno() {
		return gstinno;
	}
	public void setGstinno(String gstinno) {
		this.gstinno = gstinno;
	}
	public String getPanno() {
		return panno;
	}
	public void setPanno(String panno) {
		this.panno = panno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getIfscode() {
		return ifscode;
	}
	public void setIfscode(String ifscode) {
		this.ifscode = ifscode;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
