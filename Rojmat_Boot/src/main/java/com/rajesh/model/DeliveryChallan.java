package com.rajesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="deliverychallan")
public class DeliveryChallan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dcid")
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
	private double sgst;
	@Column
	private double cgst;
	@Column
	private double igst;
	@Column
	private double ptotal;
	
}
