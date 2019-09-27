package com.rajesh.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="totalsdc")
public class TotalsDC {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tid")
	private long tid;
	@Column
	private double subtotal;
	@Column
	private double sgsttotal;
	@Column
	private double cgsttotal;
	@Column
	private double igsttotal;
	@Column
	private double taxamount;
	@Column
	private double grandtotal;
	
	private List<DeliveryChallan> deliveryChallan = new ArrayList<DeliveryChallan>(Arrays.asList());
}
