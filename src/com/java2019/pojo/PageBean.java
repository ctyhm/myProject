package com.java2019.pojo;

public class PageBean {
	private int page;//�ڼ�ҳ
	private int rows;//ÿҳ��¼��
	private int start;//��ʼҳ
    private String pname;//���ڲ�ѯ��ͬ��¼
    private String pidcard;//���ڲ�ѯ��ͬ��¼�����в�ѯ���л����¼
    private String conumber;//���ڲ�ѯ���˴����¼
    private String ayear;//���ڲ�ѯ���˴����¼�����в�ѯ���л����¼
    private String rapplydate;//���в�ѯ���л����¼
    private String coendyear;//���ڲ�ѯ����������Ϣ
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getStart() {
		return (page-1)*rows;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPidcard() {
		return pidcard;
	}

	public void setPidcard(String pidcard) {
		this.pidcard = pidcard;
	}
	
	public String getConumber() {
		return conumber;
	}
	public void setConumber(String conumber) {
		this.conumber = conumber;
	}
	public String getAyear() {
		return ayear;
	}
	public void setAyear(String ayear) {
		this.ayear = ayear;
	}
	public String getRapplydate() {
		return rapplydate;
	}
	public void setRapplydate(String rapplydate) {
		this.rapplydate = rapplydate;
	}
	public String getCoendyear() {
		return coendyear;
	}
	public void setCoendyear(String coendyear) {
		this.coendyear = coendyear;
	}
	
	
}
