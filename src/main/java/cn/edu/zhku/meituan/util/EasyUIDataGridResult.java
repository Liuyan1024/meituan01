package cn.edu.zhku.meituan.util;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult implements Serializable{

	private long total; //�ܼ�¼��
	private List rows;  //��ҳ��ѯ������
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
