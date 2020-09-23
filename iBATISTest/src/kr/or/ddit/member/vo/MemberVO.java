package kr.or.ddit.member.vo;

/**
 * DB테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.
 * @author PC-03
 * <p>
 * DB테이블의 '컬럼'이 클래스의 '멤버변수'가 된다.<br>
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.<br>
 * </p>
 */
public class MemberVO {
	private String mem_id;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	
	
	
	/*
	 * 생성자를 따로 만들경우 디폴트생성자가 없어지기 때문에 명시적으로 기본생성자를 만들어줘야함. 안그럼 아이바이스사용불가
	 */
	public MemberVO(String mem_id, String mem_name, String mem_tel, String mem_addr) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_addr = mem_addr;
	}
	
	//명시적 기본생성자
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	
	
}
