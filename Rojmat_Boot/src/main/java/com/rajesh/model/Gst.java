package com.rajesh.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
@Table(name="gst")
public class Gst {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gstid")
	private long gstid;
	@Column
	private double percentage;
	public long getGstid() {
		return gstid;
	}
	public void setGstid(long gstid) {
		this.gstid = gstid;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
}
