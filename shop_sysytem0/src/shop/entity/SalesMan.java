package shop.entity;

public class SalesMan {
	String name;
	String passWord;
	int id;
	/**
	 * ��ѯ�û��������û�����
	 * @param sId,sName,sPassWord
	 */
	public SalesMan(int id_, String nm,String pw)
	{
		id = id_;
		name = nm;
		passWord = pw;
	}
	
	/**
	 * ����û�
	 * @param NsNameame,sPassWord
	 */
	public SalesMan(String nm,String pw)
	{
		this.name=nm;
		this.passWord=pw;
	}
	
	/**
	 * ��֤�û���½
	 * @param sId,spassWord
	 */
	public SalesMan(int id_,String pw)
	{
		this.id=id_;
		this.passWord=pw;
	}
	
	/**
	 * ����get,set����
	 * @param Name,PassWord ,id
	 */
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getPassWord()
	{
		return passWord;
	}
	public void setId(int iid)
	{
		this.id=iid;
	}
	public void setName(String nm)
	{
		this.name=nm;
	}
	public void setPassWord(String pw)
	{
		this.passWord=pw;
	}
}
