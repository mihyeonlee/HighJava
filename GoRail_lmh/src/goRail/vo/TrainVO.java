package goRail.vo;

public class TrainVO {
	private String train_no;
	private String train_type;
	private String train_chk;
	public String getTrain_no() {
		return train_no;
	}
	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}
	public String getTrain_type() {
		return train_type;
	}
	public void setTrain_type(String train_type) {
		this.train_type = train_type;
	}
	public String getTrain_chk() {
		return train_chk;
	}
	public void setTrain_chk(String train_chk) {
		this.train_chk = train_chk;
	}
	@Override
	public String toString() {
		return "TrainVO [train_no=" + train_no + ", train_type=" + train_type + ", train_chk=" + train_chk + "]";
	}
	
	//오마이 갓!
	

}
