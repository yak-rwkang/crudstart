package com.eumsystems.util;



public class ReplyPager {
    // ?��?���??�� 게시�? ?��
    public static final int PAGE_SCALE = 5;
    // ?��면당 ?��?���? ?��
    public static final int BLOCK_SCALE = 4;
    private int curPage; // ?��?�� ?��?��?��
    private int prevPage; // ?��?�� ?��?���?
    private int nextPage; // ?��?�� ?��?���?
    private int totPage; // ?���? ?��?���? �??��
    private int totBlock; // ?���? ?��?���? 블록 �??��
    private int curBlock; // ?��?�� ?��?���? 블록 
    private int prevBlock; // ?��?�� ?��?���? 블록
    private int nextBlock; // ?��?�� ?��?���? 블록
    // WHERE rn BETWEEN #{start} AND #{end}
    private int pageBegin; // #{start}
    private int pageEnd; // #{end}
    // [?��?��] blockBegin -> 41 42 43 44 45 46 47 48 49 50 [?��?��]
    private int blockBegin; // ?��?�� ?��?���? 블록?�� ?��?��번호
    // [?��?��] 41 42 43 44 45 46 47 48 49 50 <- blockEnd [?��?��]
    private int blockEnd; // ?��?�� ?��?���? 블록?�� ?��번호
    
    // ?��?��?��
    // BoardPager(?��코드 �??��, ?��?�� ?��?���? 번호)
    public ReplyPager(int count, int curPage){
        curBlock = 1; // ?��?�� ?��?���? 블록 번호
        this.curPage = curPage; // ?��?�� ?��?���? ?��?��
        setTotPage(count); // ?���? ?��?���? �??�� 계산
        setPageRange(); // 
        setTotBlock(); // ?���? ?��?���? 블록 �??�� 계산
        setBlockRange(); // ?��?���? 블록?�� ?��?��, ?�� 번호 계산
    }
    
    public void setBlockRange(){
        // *?��?�� ?��?���?�? 몇번�? ?��?���? 블록?�� ?��?��?���? 계산
        // (?��?��?��?���?-1)/?��?���? 블록?��?��+1
        // 1?��?���? => 1블록 (1-1)/10 + 1 => 1
        // 9?��?���? =>     1블록 (9-1)/10 + 1 => 1
        // 11?��?���? => 2블록 (11-1)/10 + 1 => 2
        // 57?��?���? => 6블록 (57-1)/10 + 1 => 6 
        curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
        // *?��?�� ?��?���? 블록?�� ?��?��, ?�� 번호 계산
        // ?��?���? 블록?�� ?��?��번호
        // (?��?��블록-1)*블록?��?��+1
        // 1블록 => (1-1)*10 + 1 => 1
        // 2블록 => (2-1)*10 + 1 => 11
        // 6블록 => (6-1)*10 + 1 => 51
        blockBegin = (curBlock-1)*BLOCK_SCALE+1;
        // ?��?���? 블록?�� ?��번호
        // 블록?��?��번호+블록?��?��-1;
        // 1블록 => 1+10-1 => 10
        // 2블록 => 11+10-1 => 20
        // 6블록 => 51+10-1 => 60     
        blockEnd = blockBegin+BLOCK_SCALE-1;
        // *마�?�? 블록?�� 범위�? 초과?���? ?��?���? 계산
        // [?��?��] 61 62 => ?��?��?�� 경우 70번까�? ?��?���? ?��?��록하�? ?��?��?��
        if(blockEnd > totPage) blockEnd = totPage;
        // *?��?��?�� ?��???�� ?�� ?��?��?�� ?��?���? 번호
        prevPage = (curPage == 1)? 1:(curBlock-1)*BLOCK_SCALE;
        // *?��?��?�� ?��???�� ?�� ?��?��?�� ?��?���? 번호
        nextPage = curBlock > totBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
        // 마�?�? ?��?���?�? 범위�? 초과?���? ?��?���? 처리
        if(nextPage >= totPage) nextPage = totPage;
    }
    
    public void setPageRange(){
    // WHERE rn BETWEEN #{start} AND #{end}
        // ?��?��번호 = (?��?��?��?���?-1)*?��?���??�� 게시물수 +1
        pageBegin = (curPage-1)*PAGE_SCALE+1;
        // ?��번호 = ?��?��번호+?��?���??�� 게시물수 -1
        pageEnd = pageBegin+PAGE_SCALE-1;
    }
    
    // Getter/Setter
    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
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
    public int getTotPage() {
        return totPage;
    }
    public void setTotPage(int count) {
        // 91개의 게시물을 10개씩 9?��?���?�? 처리?���? ?��?? 1개의 게시물도 ?��?���??�� 출력?���? ?��?��?��?��
        // ?��?�� ?��림으�? 처리?��?��?��?��.
        // Math.ceil(?��?��) ?���? 처리
        // 모든 ?��?���??�� ?��림처�?
        totPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
    }
    public int getTotBlock() {
        return totBlock;
    }
    
    // ?��?���? 블록?�� �??�� 계산(�? 100?��?���??���? 10개의 블록)
    public void setTotBlock() {
        // ?���? ?��?���? �??�� / 10
        // 91 / 10 => 9.1 => 10�?
        totBlock = (int)Math.ceil(totPage / BLOCK_SCALE);
    }
    
    public int getCurBlock() {
        return curBlock;
    }
    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }
    public int getPrevBlock() {
        return prevBlock;
    }
    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }
    public int getNextBlock() {
        return nextBlock;
    }
    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }
    public int getPageBegin() {
        return pageBegin;
    }
    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }
    public int getPageEnd() {
        return pageEnd;
    }
    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    public int getBlockBegin() {
        return blockBegin;
    }
    public void setBlockBegin(int blockBegin) {
        this.blockBegin = blockBegin;
    }
    public int getBlockEnd() {
        return blockEnd;
    }
    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }
    
    
}