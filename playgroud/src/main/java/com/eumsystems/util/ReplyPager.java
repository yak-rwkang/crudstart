package com.eumsystems.util;



public class ReplyPager {
    // ?éò?ù¥Ïß??ãπ Í≤åÏãúÎ¨? ?àò
    public static final int PAGE_SCALE = 5;
    // ?ôîÎ©¥Îãπ ?éò?ù¥Ïß? ?àò
    public static final int BLOCK_SCALE = 4;
    private int curPage; // ?òÑ?û¨ ?éò?ù¥?àò
    private int prevPage; // ?ù¥?†Ñ ?éò?ù¥Ïß?
    private int nextPage; // ?ã§?ùå ?éò?ù¥Ïß?
    private int totPage; // ?†ÑÏ≤? ?éò?ù¥Ïß? Í∞??àò
    private int totBlock; // ?†ÑÏ≤? ?éò?ù¥Ïß? Î∏îÎ°ù Í∞??àò
    private int curBlock; // ?òÑ?û¨ ?éò?ù¥Ïß? Î∏îÎ°ù 
    private int prevBlock; // ?ù¥?†Ñ ?éò?ù¥Ïß? Î∏îÎ°ù
    private int nextBlock; // ?ã§?ùå ?éò?ù¥Ïß? Î∏îÎ°ù
    // WHERE rn BETWEEN #{start} AND #{end}
    private int pageBegin; // #{start}
    private int pageEnd; // #{end}
    // [?ù¥?†Ñ] blockBegin -> 41 42 43 44 45 46 47 48 49 50 [?ã§?ùå]
    private int blockBegin; // ?òÑ?û¨ ?éò?ù¥Ïß? Î∏îÎ°ù?ùò ?ãú?ûëÎ≤àÌò∏
    // [?ù¥?†Ñ] 41 42 43 44 45 46 47 48 49 50 <- blockEnd [?ã§?ùå]
    private int blockEnd; // ?òÑ?û¨ ?éò?ù¥Ïß? Î∏îÎ°ù?ùò ?ÅùÎ≤àÌò∏
    
    // ?Éù?Ñ±?ûê
    // BoardPager(?†àÏΩîÎìú Í∞??àò, ?òÑ?û¨ ?éò?ù¥Ïß? Î≤àÌò∏)
    public ReplyPager(int count, int curPage){
        curBlock = 1; // ?òÑ?û¨ ?éò?ù¥Ïß? Î∏îÎ°ù Î≤àÌò∏
        this.curPage = curPage; // ?òÑ?û¨ ?éò?ù¥Ïß? ?Ñ§?†ï
        setTotPage(count); // ?†ÑÏ≤? ?éò?ù¥Ïß? Í∞??àò Í≥ÑÏÇ∞
        setPageRange(); // 
        setTotBlock(); // ?†ÑÏ≤? ?éò?ù¥Ïß? Î∏îÎ°ù Í∞??àò Í≥ÑÏÇ∞
        setBlockRange(); // ?éò?ù¥Ïß? Î∏îÎ°ù?ùò ?ãú?ûë, ?Åù Î≤àÌò∏ Í≥ÑÏÇ∞
    }
    
    public void setBlockRange(){
        // *?òÑ?û¨ ?éò?ù¥Ïß?Í∞? Î™áÎ≤àÏß? ?éò?ù¥Ïß? Î∏îÎ°ù?óê ?Üç?ïò?äîÏß? Í≥ÑÏÇ∞
        // (?òÑ?û¨?éò?ù¥Ïß?-1)/?éò?ù¥Ïß? Î∏îÎ°ù?ã®?úÑ+1
        // 1?éò?ù¥Ïß? => 1Î∏îÎ°ù (1-1)/10 + 1 => 1
        // 9?éò?ù¥Ïß? =>     1Î∏îÎ°ù (9-1)/10 + 1 => 1
        // 11?éò?ù¥Ïß? => 2Î∏îÎ°ù (11-1)/10 + 1 => 2
        // 57?éò?ù¥Ïß? => 6Î∏îÎ°ù (57-1)/10 + 1 => 6 
        curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
        // *?òÑ?û¨ ?éò?ù¥Ïß? Î∏îÎ°ù?ùò ?ãú?ûë, ?Åù Î≤àÌò∏ Í≥ÑÏÇ∞
        // ?éò?ù¥Ïß? Î∏îÎ°ù?ùò ?ãú?ûëÎ≤àÌò∏
        // (?òÑ?û¨Î∏îÎ°ù-1)*Î∏îÎ°ù?ã®?úÑ+1
        // 1Î∏îÎ°ù => (1-1)*10 + 1 => 1
        // 2Î∏îÎ°ù => (2-1)*10 + 1 => 11
        // 6Î∏îÎ°ù => (6-1)*10 + 1 => 51
        blockBegin = (curBlock-1)*BLOCK_SCALE+1;
        // ?éò?ù¥Ïß? Î∏îÎ°ù?ùò ?ÅùÎ≤àÌò∏
        // Î∏îÎ°ù?ãú?ûëÎ≤àÌò∏+Î∏îÎ°ù?ã®?úÑ-1;
        // 1Î∏îÎ°ù => 1+10-1 => 10
        // 2Î∏îÎ°ù => 11+10-1 => 20
        // 6Î∏îÎ°ù => 51+10-1 => 60     
        blockEnd = blockBegin+BLOCK_SCALE-1;
        // *ÎßàÏ?Îß? Î∏îÎ°ù?ù¥ Î≤îÏúÑÎ•? Ï¥àÍ≥º?ïòÏß? ?ïä?èÑÎ°? Í≥ÑÏÇ∞
        // [?ù¥?†Ñ] 61 62 => ?ù¥?ü¨?ïú Í≤ΩÏö∞ 70Î≤àÍπåÏß? ?Çò?ò§Ïß? ?ïä?èÑÎ°ùÌïòÍ∏? ?úÑ?ï¥?Ñú
        if(blockEnd > totPage) blockEnd = totPage;
        // *?ù¥?†Ñ?ùÑ ?àå???ùÑ ?ïå ?ù¥?èô?ï† ?éò?ù¥Ïß? Î≤àÌò∏
        prevPage = (curPage == 1)? 1:(curBlock-1)*BLOCK_SCALE;
        // *?ã§?ùå?ùÑ ?àå???ùÑ ?ïå ?ù¥?èô?ï† ?éò?ù¥Ïß? Î≤àÌò∏
        nextPage = curBlock > totBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
        // ÎßàÏ?Îß? ?éò?ù¥Ïß?Í∞? Î≤îÏúÑÎ•? Ï¥àÍ≥º?ïòÏß? ?ïä?èÑÎ°? Ï≤òÎ¶¨
        if(nextPage >= totPage) nextPage = totPage;
    }
    
    public void setPageRange(){
    // WHERE rn BETWEEN #{start} AND #{end}
        // ?ãú?ûëÎ≤àÌò∏ = (?òÑ?û¨?éò?ù¥Ïß?-1)*?éò?ù¥Ïß??ãπ Í≤åÏãúÎ¨ºÏàò +1
        pageBegin = (curPage-1)*PAGE_SCALE+1;
        // ?ÅùÎ≤àÌò∏ = ?ãú?ûëÎ≤àÌò∏+?éò?ù¥Ïß??ãπ Í≤åÏãúÎ¨ºÏàò -1
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
        // 91Í∞úÏùò Í≤åÏãúÎ¨ºÏùÑ 10Í∞úÏî© 9?éò?ù¥Ïß?Î•? Ï≤òÎ¶¨?ïòÍ≥? ?Ç®?? 1Í∞úÏùò Í≤åÏãúÎ¨ºÎèÑ ?éò?ù¥Ïß??óê Ï∂úÎ†•?ïòÍ∏? ?úÑ?ï¥?Ñú?äî
        // ?ï≠?ÉÅ ?ò¨Î¶ºÏúºÎ°? Ï≤òÎ¶¨?ï¥?ïº?ïú?ã§.
        // Math.ceil(?ã§?àò) ?ò¨Î¶? Ï≤òÎ¶¨
        // Î™®Îì† ?éò?ù¥Ïß??äî ?ò¨Î¶ºÏ≤òÎ¶?
        totPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
    }
    public int getTotBlock() {
        return totBlock;
    }
    
    // ?éò?ù¥Ïß? Î∏îÎ°ù?ùò Í∞??àò Í≥ÑÏÇ∞(Ï¥? 100?éò?ù¥Ïß??ùºÎ©? 10Í∞úÏùò Î∏îÎ°ù)
    public void setTotBlock() {
        // ?†ÑÏ≤? ?éò?ù¥Ïß? Í∞??àò / 10
        // 91 / 10 => 9.1 => 10Í∞?
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