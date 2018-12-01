package shop.entity;

public class SalesMan {
	String name;
	String passWord;
	int id;
	/**
	 * 查询用户、更改用户密码
	 * @param sId,sName,sPassWord
	 */
	public SalesMan(int id_, String nm,String pw)
	{
		id = id_;
		name = nm;
		passWord = pw;
	}
	
	/**
	 * 添加用户
	 * @param NsNameame,sPassWord
	 */
	public SalesMan(String nm,String pw)
	{
		this.name=nm;
		this.passWord=pw;
	}
	
	/**
	 * 验证用户登陆
	 * @param sId,spassWord
	 */
	public SalesMan(int id_,String pw)
	{
		this.id=id_;
		this.passWord=pw;
	}
	
	/**
	 * 公用get,set方法
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
