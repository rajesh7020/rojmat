package com.rajesh.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="debit")
public class Debit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long did;
	@Column
	private long amount;
	@Column
	private String description;	
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public Debit() {
		
	}
	public Debit(long amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
	        return "Debit [did=" + did + ", amount =" + amount + ", description=" + description + "]";
    }
}
