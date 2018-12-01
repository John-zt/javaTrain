package shop.operateDb;
import java.sql.*;
import java.util.ArrayList;

import shop.db.closeDb;
import shop.db.connectDb;
import shop.entity.Goods;
import shop.tools.ScannerChoice;
import shop.tools.*;

public class GoodsToDb {
	static Connection conn=null;
	static PreparedStatement pstmt=null;
	static ResultSet rs = null;
	public static void main(String [] args)
	{
		System.out.println(FindGid("second"));
	}
	// 向数据库添加商品，添加成功返回true，否则返回false
	public static  boolean addGoods(Goods goods)
	{
		boolean bl = false;
		conn = connectDb.getConn();
		String sql = "insert into goods(gname,gprice,gnum) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGname());
			pstmt.setInt(2, goods.getGprice());
			pstmt.setInt(3, goods.getGnum());
			int f = pstmt.executeUpdate();
			if(f>0)
			{
				bl=true;
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			closeDb.addClose(pstmt, conn);
		}
		return bl;
	}
	/**
	 *4.查询商品信息
	 * @param key 查询方式
	 * @return ArrayList<Goods>
	 */
	public static int FindGid(String nm) //根据名字找出gid
	{
		int ans=-1;
		conn = connectDb.getConn();
		String sql = "select gid from goods where gname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nm);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ans =rs.getInt("gid");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			closeDb.queryClose(pstmt, rs, conn);
		}
		//System.out.println(ans);
		return ans;
	}
	public static ArrayList<Goods> queryGoods(int key) 
	{
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = connectDb.getConn();
		if(key==1)
		{
			//按数量升序排列
			String sql = "select * from goods order by gnum asc";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					int gid = rs.getInt(1);
					int gnum = rs.getInt(4);
					int gprice = rs.getInt(3);
					String gname = rs.getString(2);
					goodsList.add(new Goods(gname,gprice,gnum,gid));
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally {
				closeDb.queryClose(pstmt, rs, conn);
			}
		}
		else if(key==2)
		{
			//按价格升
			String sql = "select * from goods order by gprice asc";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					int gid = rs.getInt(1);
					int gnum = rs.getInt(4);
					int gprice = rs.getInt(3);
					String gname = rs.getString(2);
					goodsList.add(new Goods(gname,gprice,gnum,gid));
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				closeDb.queryClose(pstmt, rs, conn);
			}
		}
		else if(key==3)
		{
			//关键字查询
			String Select = ScannerChoice.ScannerInfoString();
			String sql = "select * from goods where gname like '%?%'";
			try
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Select);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					int gid = rs.getInt(1);
					int gnum = rs.getInt(4);
					int gprice = rs.getInt(3);
					String gname = rs.getString(2);
					goodsList.add(new Goods(gname,gprice,gnum,gid));
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally {
				closeDb.queryClose(pstmt, rs, conn);
			}
		}
		return goodsList;
	}
	
	public static boolean updateGoods(int key,Goods goods)
	{
		boolean bl=false;
		if(key==1) //更改数量
		{
			conn = connectDb.getConn();
			String sql = "update goods set gnum=? where gname=?";
			try 
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, goods.getGnum());
				pstmt.setString(2, goods.getGname());
				int f=pstmt.executeUpdate();
				if(f>0)
				{
					bl=true;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				closeDb.addClose(pstmt, conn);
			}
		}
		else if(key==2) //更改名字
		{
			System.out.println("请输入新的名字");
			String newName = ScannerChoice.ScannerInfoString();
			int gid=FindGid(goods.getGname());
			String sql = "update goods set gname=? where gid=?";
			try {
				conn = connectDb.getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newName);
				pstmt.setInt(2, gid);
				int f=pstmt.executeUpdate();
				if(f>0)
				{
					bl=true;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				closeDb.addClose(pstmt, conn);
			}
		}
		else if(key==3) //更新价格
		{
			conn = connectDb.getConn();
			String sql = "update goods set gprice=? where gname=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, goods.getGprice());
				pstmt.setString(2, goods.getGname());
				int f=pstmt.executeUpdate();
				if(f>0)
				{
					bl=true;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally {
				closeDb.addClose(pstmt, conn);
			}
		}
		if(bl==true)
		{
			System.out.println("111");
		}
		return bl;
	}
	
	public static Goods FindGoodsByGid(int gid)
	{
		Goods goods=null;
		conn = connectDb.getConn();
		String sql = "select * from goods where gid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String gname = rs.getString("gname");
				int price  = rs.getInt("gprice");
				int num = rs.getInt("gnum");
				goods = new Goods(gname,price,num,gid);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			closeDb.queryClose(pstmt, rs, conn);
		}
		return goods;
	}
	
	//删除
	public static boolean deleteGoods(int gid)
	{
		boolean bl=false;
		conn = connectDb.getConn();
		String sql = "delete from goods where gid=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			int f= pstmt.executeUpdate();
			if(f>0)
			{
				bl = true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			closeDb.addClose(pstmt, conn);
		}
		return bl;
	}
	public static boolean deleteGoods(String nm) //重载删除函数，根据商品名称来删除
	{
		boolean f=false;
		conn = connectDb.getConn();
		String sql = "delete from goods where gname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nm);
			int tmp=pstmt.executeUpdate();
			if(tmp>0)
			{
				f=true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeDb.addClose(pstmt, conn);
		}
		return f;
	}
	
	public static ArrayList<Goods> displayAllGoods()
	{
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = connectDb.getConn();
		String sql = "select * from goods";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int gid=rs.getInt(1);
				int gnum= rs.getInt(4);
				int gprice = rs.getInt(3);
				String gName = rs.getString(2);
				goodsList.add(new Goods(gName,gprice,gnum,gid));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			closeDb.queryClose(pstmt, rs, conn);
		}
		return goodsList;
	}
}
