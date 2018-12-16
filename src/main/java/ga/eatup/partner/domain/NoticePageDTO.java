package ga.eatup.partner.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class NoticePageDTO {
	private int page, skip, display, start, end, total,nno;
	private boolean prev, next;
	
	private String keyword;
	private String type;
	private String[] typeArr;
	
	public NoticePageDTO() {
		this.keyword="";
		this.type="";
		this.prev = true;
		this.next = true;
		this.page = 1;
		this.display = 10;
		this.total = 0;
	}
	
	public int getSkip() {
		
		return (this.page - 1) * this.display;
	}
	
	public void setTotal(int total) {
		this.total = total;
		this.end = (int) (Math.ceil(this.page/10.0) * 10);
		this.start = this.end - 9;
		
		log.info("total: "+ total);

		
		if((this.end * this.display) > this.total) {
			this.end = (int) Math.ceil(this.total/(double)this.display);
			this.next = false;
		}
		
		if(this.start == 1) {
			this.prev = false;
		}
		
		if(this.end == 0) {
			this.end = 1;
		}
		
	}
	
	public String[] getTypeArr() {
		
		this.typeArr = new String[this.type.length()];
		
		for(int i = 0 ; i < this.type.length() ; i++) {
			this.typeArr[i] = this.type.substring(i, i+1);
		}
		
		return this.typeArr;
	}
	
	public String getLink(String path) {
		
		return UriComponentsBuilder.fromPath(path)
				.queryParam("nno", this.nno)
				.queryParam("page", this.page)
				.queryParam("type", this.type)
				.queryParam("display", this.display)
				.queryParam("keyword", this.keyword)
				.toUriString();
	}
	
}
