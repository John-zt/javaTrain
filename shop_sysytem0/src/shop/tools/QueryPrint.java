package shop.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shop.db.closeDb;
import shop.db.connectDb;
import shop.entity.Goods;
import shop.entity.*;
import shop.operateDb.*;

public class QueryPrint {
	static Connection        conn  = null;
	static PreparedStatement pstmt = null;
	static ResultSet 		rs 	 	 = null;
	
	/**
	 * 模糊查询并陈列查询信息函数小工具
	 * @param oper 调用者
	 * @return 查询到的信息的gid,如果返回值等于-1，则代表查询异常。	  
	 */
	public static  void main(String [] args)
	{
		//PrintGoods(GoodsToDb.displayAllGoods());
		//int gid=query("updateGoods");
	}
	public static void PrintGoods(ArrayList<Goods> goodsList)
	{
		if(goodsList==null||goodsList.size()==0)
		{
			System.out.println("当前仓库为空");
			return;
		}
		System.out.println("\t\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注");
		for(int i=0;i<goodsList.size();i++)
		{
			Goods tmp = goodsList.get(i);
			System.out.print("\t\t"+tmp.getGid()+"\t\t"+tmp.getGname()+"\t\t"+tmp.getGprice()+"\t\t"+tmp.getGnum());
			if(tmp.getGnum()==0)
			{
				System.out.println("\t\t此商品已经售空");
			}
			else
			{
				if(tmp.getGnum()<10)
				{
					System.out.println("\t\t此商品剩余数量小于10件");
				}
				else
				{
					System.out.println("\t\t__");
				}
			}
		}
	}
	public static int query(String oper) //给出操作，返回该商品的编号
	{
		int gid=-1;
		String ShoppingName = ScannerChoice.ScannerInfoString();
		ArrayList<Goods> goodsList = queryGoodsKey(gid,ShoppingName);
		if(goodsList==null||goodsList.size()<=0)
		{
			System.err.println("\t 查无此商品");
			ScannerChoice.changedGoodsNext(oper);
		}
		else
		{
			Goods goods=goodsList.get(0);
			System.out.println("\t\t\t\t\t商品列表\n\n");
			System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
			System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
			if(goods.getGnum()==0)
			{
				System.out.println("该商品已经售完");
			}
			else if(goods.getGnum()<10)
			{
				System.out.println("\t\t该商品已少于10件");
			}
			gid=goods.getGid();
		}
		return gid;
	}
	//根据编号或名字查找出物品
	public static ArrayList<Goods> queryGoodsKey(int gId,String gName) 
	{
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = connectDb.getConn();
		String sql = "select * from goods where gid=? or gname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, gName);
			pstmt.setInt(1, gId);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int gid = rs.getInt(1);
				int gprice =rs.getInt(3);
				int gnum = rs.getInt(4);
				String gname =rs.getString(2);
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
		return goodsList;
	}
	
	public static void PrintSalesMan(ArrayList<SalesMan> list)
	{
		System.out.println("\t\t\t\t售货员列表\t\t\t\t");
		if(list==null||list.size()<=0)
		{
			System.out.println("当前没有任何售货员");
			return;
		}
		System.out.println("\t\t编号\t\t姓名\t\t密码");
		for(int i=0;i<list.size();i++)
		{
			SalesMan sman=list.get(i);
			System.out.println("\t\t"+sman.getId()+"\t\t"+sman.getName()+"\t\t"+sman.getPassWord());
		}
		
	}
	
    public static void PrintGsales(ArrayList<Gsales> list)
    {
    	System.out.println("\t\t\t\t售出商品列表\t\t\t\t");
		if(list==null||list.size()<=0)
		{
			System.out.println("当前还没有售出任何商品");
			return;
		}
		System.out.println("\t\t出售编号\t\t商品编号\t\t售货员编号\t\t售出总量");
		for(int i=0;i<list.size();i++)
		{
			Gsales sman=list.get(i);
			System.out.println("\t\t"+sman.get_gsid()+"\t\t"+sman.get_gid()+"\t\t"+sman.get_sid()+"\t\t"+sman.get_sum());
		}
    }
	
	public static int querySettlement()
	{
		int gid=-1;
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		goodsList = new GoodsToDb().queryGoods(3);//特定查找
		if(goodsList==null||goodsList.size()<=0)
		{
			System.err.println("查无此商品");
			gid=-3;
		}
		else
		{
			System.out.println("\t\t\t\t\t商品列表\n\n");
			System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
			for(int i=0;i<goodsList.size();i++)
			{
				System.out.print("\t"+goodsList.get(i).getGid()+"\t\t"+goodsList.get(i).getGname()+"\t\t"+goodsList.get(i).getGprice()+"\t\t"+goodsList.get(i).getGnum());
				if(goodsList.get(i).getGnum()==0)
				{
					System.out.println("\t\t 此商品已经售空");
				}
				else {
					if(goodsList.get(i).getGnum()<10)
					{
						System.out.println("\t\t此商品已经不足10件");
					}
					else
					{
						System.out.println("\t\t__");
					}
				}
			}
			if(goodsList.size()==1)
			{
				gid=goodsList.get(0).getGid();//如果只有一件，则返回编号
			}
			else {
				gid=-2;//有多个....
			}
		}
		return gid;
	}
}
