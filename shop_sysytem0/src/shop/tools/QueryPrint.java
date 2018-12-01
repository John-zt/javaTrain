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
	 * ģ����ѯ�����в�ѯ��Ϣ����С����
	 * @param oper ������
	 * @return ��ѯ������Ϣ��gid,�������ֵ����-1��������ѯ�쳣��	  
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
			System.out.println("��ǰ�ֿ�Ϊ��");
			return;
		}
		System.out.println("\t\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע");
		for(int i=0;i<goodsList.size();i++)
		{
			Goods tmp = goodsList.get(i);
			System.out.print("\t\t"+tmp.getGid()+"\t\t"+tmp.getGname()+"\t\t"+tmp.getGprice()+"\t\t"+tmp.getGnum());
			if(tmp.getGnum()==0)
			{
				System.out.println("\t\t����Ʒ�Ѿ��ۿ�");
			}
			else
			{
				if(tmp.getGnum()<10)
				{
					System.out.println("\t\t����Ʒʣ������С��10��");
				}
				else
				{
					System.out.println("\t\t__");
				}
			}
		}
	}
	public static int query(String oper) //�������������ظ���Ʒ�ı��
	{
		int gid=-1;
		String ShoppingName = ScannerChoice.ScannerInfoString();
		ArrayList<Goods> goodsList = queryGoodsKey(gid,ShoppingName);
		if(goodsList==null||goodsList.size()<=0)
		{
			System.err.println("\t ���޴���Ʒ");
			ScannerChoice.changedGoodsNext(oper);
		}
		else
		{
			Goods goods=goodsList.get(0);
			System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
			System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
			System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
			if(goods.getGnum()==0)
			{
				System.out.println("����Ʒ�Ѿ�����");
			}
			else if(goods.getGnum()<10)
			{
				System.out.println("\t\t����Ʒ������10��");
			}
			gid=goods.getGid();
		}
		return gid;
	}
	//���ݱ�Ż����ֲ��ҳ���Ʒ
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
		System.out.println("\t\t\t\t�ۻ�Ա�б�\t\t\t\t");
		if(list==null||list.size()<=0)
		{
			System.out.println("��ǰû���κ��ۻ�Ա");
			return;
		}
		System.out.println("\t\t���\t\t����\t\t����");
		for(int i=0;i<list.size();i++)
		{
			SalesMan sman=list.get(i);
			System.out.println("\t\t"+sman.getId()+"\t\t"+sman.getName()+"\t\t"+sman.getPassWord());
		}
		
	}
	
    public static void PrintGsales(ArrayList<Gsales> list)
    {
    	System.out.println("\t\t\t\t�۳���Ʒ�б�\t\t\t\t");
		if(list==null||list.size()<=0)
		{
			System.out.println("��ǰ��û���۳��κ���Ʒ");
			return;
		}
		System.out.println("\t\t���۱��\t\t��Ʒ���\t\t�ۻ�Ա���\t\t�۳�����");
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
		goodsList = new GoodsToDb().queryGoods(3);//�ض�����
		if(goodsList==null||goodsList.size()<=0)
		{
			System.err.println("���޴���Ʒ");
			gid=-3;
		}
		else
		{
			System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
			System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
			for(int i=0;i<goodsList.size();i++)
			{
				System.out.print("\t"+goodsList.get(i).getGid()+"\t\t"+goodsList.get(i).getGname()+"\t\t"+goodsList.get(i).getGprice()+"\t\t"+goodsList.get(i).getGnum());
				if(goodsList.get(i).getGnum()==0)
				{
					System.out.println("\t\t ����Ʒ�Ѿ��ۿ�");
				}
				else {
					if(goodsList.get(i).getGnum()<10)
					{
						System.out.println("\t\t����Ʒ�Ѿ�����10��");
					}
					else
					{
						System.out.println("\t\t__");
					}
				}
			}
			if(goodsList.size()==1)
			{
				gid=goodsList.get(0).getGid();//���ֻ��һ�����򷵻ر��
			}
			else {
				gid=-2;//�ж��....
			}
		}
		return gid;
	}
}
