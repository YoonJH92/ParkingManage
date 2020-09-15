package com.pms.dto;

public class PmsPageDto {
	
	   private int page =1; 
	    private int totalCount; 
	    private int beginPage;  
	    private int endPage;   
	    private int displayRow;  
	    private int displayPage =10;  
	    boolean prev;
	    boolean next;	    
	    private int startNum;
	    private int endNum;
	    private int NEXT;
	    
	    public int getNEXT() {
			return NEXT;
		}
		public void setNEXT(int nEXT) {
			NEXT = nEXT;
		}
		public int getPREV() {
			return PREV;
		}
		public void setPREV(int pREV) {
			PREV = pREV;
		}
		public int getDisplayPage() {
			return displayPage;
		}

		private int PREV;
	
	    public void setStartNum(int startNum) {
	    	this.startNum = startNum;
	    }
	    public int getStartNum() {
			return startNum=(page-1)*displayRow;
		}
	    public void setEndNum(int endNum) {
	    	this.endNum = endNum;
	    }
		public int getEndNum() {
			return endNum=page*displayRow;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			paging();
		}
		public int getBeginPage() {
			return beginPage;
		}
		public void setBeginPage(int beginPage) {
			this.beginPage = beginPage;
		}
		public int getEndPage() {
			return endPage;
		}
		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}
		public int getDisplayRow() {
			return displayRow;
		}
		public void setDisplayRow(int displayRow) {
			this.displayRow = displayRow;
		}
		public int getdisplayPage() {
			return displayPage;
		}
		public void setDisplayPage(int displayPage) {
			this.displayPage = displayPage;
		}
		public boolean isPrev() {
			return prev;
		}
		public void setPrev(boolean prev) {
			this.prev = prev;
		}
		public boolean isNext() {
			return next;
		}
		public void setNext(boolean next) {
			this.next = next;
		}

		public void paging() {
			  
		    endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
	        System.out.println("endPage : " + endPage);
	        
	        beginPage = endPage - (displayPage - 1);
	        System.out.println("beginPage : " + beginPage);
	      
	        
	        int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
	        
	        this.NEXT=page+1;
	        
	        this.PREV=page-1;
	        
	        if(totalPage<endPage){
	           endPage = totalPage;
	           next = false;
	        }else{
	            next = true;
	        }
	        prev = (beginPage==1)?false:true;
	        System.out.println("endPage : " + endPage);
	        System.out.println("totalPage : " + totalPage);
		
			
			
		}
		
	

}
