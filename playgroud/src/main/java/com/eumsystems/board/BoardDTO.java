package com.eumsystems.board;

public class BoardDTO {
	
    private int num ; //�Խñ۹�ȣ
	private String  name; //�ۼ��� id
	private String  pwd; // �� ��й�ȣ
	private String  email; //�̸����ּ�
	private String  subject; //�� ����
	private String  content; // �� ����
	private String  ipaddr; // �ۼ��� ip�ּ�
	private String  hitcount;// ��ȸ��
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getHitcount() {
		return hitcount;
	}
	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}
	
	
	
}
