package com.rajesh.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="state")
public class State {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long stateid;
	@Column
	private String statename;
	public long getStateid() {
		return stateid;
	}
	public void setStateid(long stateid) {
		this.stateid = stateid;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
}
