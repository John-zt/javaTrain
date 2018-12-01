package shop.entity;

public class Gsales {
    int gid;
    int sid;
    
    int gsid;
    int sum;//销售总量
    
    public Gsales(int gid,int sid,int gsid,int sum)
    {
    	this.gid=gid;
    	this.sid=sid;
    	this.gsid=gsid;
    	this.sum=sum;
    }
    public Gsales(int gid,int sid,int sum)
    {
    	this.gid=gid;
    	this.sid=sid;
    	this.sum=sum;
    }
    
    public int get_gid()
    {
    	return this.gid;
    }
    public int get_sid()
    {
    	return this.sid;
    }
    public int get_gsid()
    {
    	return this.gsid;
    }
    public int get_sum()
    {
    	return sum;
    }
}
