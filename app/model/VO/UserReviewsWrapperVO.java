package model.VO;

import java.util.List;

public class UserReviewsWrapperVO {

	private int rowCount;
	
	private List<UserReviewsVO> userReviewVOs;

	public UserReviewsWrapperVO(){
		
	}
	
	public UserReviewsWrapperVO(int rowCount,List<UserReviewsVO> userReviewVOs){
		this.rowCount = rowCount;
		this.userReviewVOs = userReviewVOs;
	}
	
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<UserReviewsVO> getUserReviewVOs() {
		return userReviewVOs;
	}

	public void setUserReviewVOs(List<UserReviewsVO> userReviewVOs) {
		this.userReviewVOs = userReviewVOs;
	}
}
