package com.rajesh.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="dctotals")
public class DcTotals {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dctotid")
	private long dctotid;
	@Column
	private String invoiceno;
	@Column
	private String customername;
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
	private double ptotal;
	@Column
	private double quantitytotal;
	@Column
	private double subtotal;
	@Column
	private double sgsttotal;
	@Column
	private double cgsttotal;
	@Column
	private double igsttotal;
	@Column
	private double taxtotal;
	@Column
	private double grandtotal;
	
	@OneToOne
	@JoinColumn(name="stateid")
	private State state;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="dctotals_dcinvoice", 
    	joinColumns=@JoinColumn(name="dctot_id"), 
    		inverseJoinColumns=@JoinColumn(name="dc_id"))
	private List<DcInvoice> dcTotals = new ArrayList<DcInvoice>(Arrays.asList());
}
