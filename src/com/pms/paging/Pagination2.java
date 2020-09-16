package com.pms.paging;

public class Pagination2 {
	
    /** 한 페이지당 게시글 수 **/
    private int pageSize=10;
    
    /** 한 블럭(range)당 페이지 수 **/
    private int rangeSize = 10;
    
    /** 현재 페이지 **/
    private int curPage = 1;
    
    /** 총 게시글 수 **/
    private int listCnt;
    
    /** 총 페이지 수 **/
    private int pageCnt;

    /** 시작 페이지 **/
    private int startPage = 1;
    
    /** 끝 페이지 **/
    private int endPage = 1;
    
    /** 시작 index **/
    private int startIndex = 0;
    
    /** 이전 페이지 **/
    private int prevPage;
    
    /** 다음 페이지 **/
    private int nextPage;
 
    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRangeSize() {
		return rangeSize;
	}

	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageCnt() {
		return pageCnt;
	}
	public int getStartIndex() {
		return startIndex;
	}

	public Pagination2(int listCnt, int curPage){
        /** 현재페이지 **/
        setCurPage(curPage);
        /** 총 게시물 수 **/
        setListCnt(listCnt);
        /** 1. 총 페이지 수 **/
        setPageCnt(listCnt);
        /** 3. 블럭(range) setting **/
        rangeSetting(curPage);
        /** DB 질의를 위한 startIndex 설정 **/
        setStartIndex(curPage);
    }
	
	public Pagination2(int listCnt, int curPage, int pageSize){
		/** 페이지 개수 설정**/
		setPageSize(pageSize);
        /** 현재페이지 **/
        setCurPage(curPage);
        /** 총 게시물 수 **/
        setListCnt(listCnt);
        /** 1. 총 페이지 수 **/
        setPageCnt(listCnt);
        /** 3. 블럭(range) setting **/
        rangeSetting(curPage);
        /** DB 질의를 위한 startIndex 설정 **/
        setStartIndex(curPage);
    }
	
    public void setPageCnt(int listCnt) {
        this.pageCnt = (int) Math.ceil(listCnt/(double)pageSize);
    }
    
    public void rangeSetting(int curPage){
    	if( curPage < 6) {
    		this.startPage = 1;
    		this.endPage = startPage + rangeSize - 1;
    	}else {
    		this.startPage = curPage - 5;
    		this.endPage = curPage + 4;
    	}
    	
        if(endPage > pageCnt){
            this.endPage = pageCnt;
        }
        
        this.prevPage = curPage - 1;
        this.nextPage = curPage + 1;
    }

    public void setStartIndex(int curPage) {
        this.startIndex = (curPage-1) * pageSize;
    }
 
    
}
