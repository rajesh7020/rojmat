package com.rajesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="dcinvoice")
public class DcInvoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dcid;
	@Column
	private String productname;
	@Column
	private long quantity;
	@Column
	private double price;
	@Column
	private String hsncode;
	@Column
	private double sgstpercentage;
	@Column
	private double sgstamount;
	@Column
	private double cgstpercentage;
	@Column
	private double cgstamount;
	@Column
	private double igstpercentage;
	@Column
	private double igstamount;
}
