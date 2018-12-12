package ga.eatup.partner.domain;

import lombok.Data;

//@Data
public class OrderVO {

	
	//수정order
	private int sno,uno,mno,quantity;
	private String tid;
	
	
	@Override
	public String toString() {
		return "OrderVO [sno=" + sno + ", uno=" + uno + ", mno=" + mno + ", quantity=" + quantity + ", tid=" + tid
				+ "]";
	}


	public int getSno() {
		return sno;
	}


	public void setSno(int sno) {
		this.sno = 1;
	}


	public int getUno() {
		return uno;
	}


	public void setUno(int uno) {
		this.uno = uno;
	}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = "tid55";
	}
	
	
	
	
	
	
	
	

	


}
