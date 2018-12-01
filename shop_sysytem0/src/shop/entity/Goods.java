package shop.entity;

public class Goods {
	int gid;
	String gname;
	int gprice;
	int gnum;
	
	public Goods(String nm,int gp,int gn)
	{
		this.gname=nm;
		this.gprice=gp;
		this.gnum=gn;
	}
	
	public Goods(String nm,int gp,int gn,int gi)
	{
		this.gname=nm;
		this.gprice=gp;
		this.gnum=gn;
		this.gid=gi;
	}
	
	
	/**
	 * 根据编号更改商品信息
	 * @param gid,gum
	 */
	public Goods(int gid,int gnum)
	{
		this.gid	= gid;
		this.gnum 	= gnum;
	}
	
	/**
	 * 根据编号更改商品信息
	 * @param gid,gname
	 */
	public  Goods(int gid,String gname)
	{
		this.gid	= gid;
		this.gname  = gname;
	}
	public int getGnum()
	{
		return gnum;
	}
	public int getGid()
	{
		return gid;
	}
	public int getGprice()
	{
		return gprice;
	}
	public String getGname()
	{
		return gname;
	}
	public void setGnum(int gn)
	{
		gnum=gn;
	}
	public void setGprice(int gp)
	{
		this.gprice = gp;
	}
	public void setGname(String nm)
	{
		this.gname = nm;
	}
}
