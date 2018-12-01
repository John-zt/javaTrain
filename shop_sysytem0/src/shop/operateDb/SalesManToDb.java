package shop.operateDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import shop.db.closeDb;
import shop.db.connectDb;
import shop.entity.SalesMan;
import shop.tools.QueryPrint;

public class SalesManToDb {
	 static Connection        conn  = null;
	 static PreparedStatement pstmt = null;
	 static ResultSet 		rs 	 	 = null;
	 //�����û��������ݿ����ҳ�
	 public static void main(String [] args)
	 {
		 //addSalesMan(new SalesMan("ydc","123"));
		 //addSalesMan(new SalesMan("dw","1234"));
		 //deleteSalesMan("ydc");
		 System.out.println(FindPassWord("dw").get(0).getPassWord());
		 //updateSalesMan(new SalesMan("dw","123"));
		 //QueryPrint.PrintSalesMan(FindAll());
	 }
	 public static ArrayList<SalesMan> FindPassWord(String nm)
	 {
		 ArrayList<SalesMan> SalesManInf = new ArrayList<SalesMan>();
		 conn = connectDb.getConn();
		 String sql = "select * from salesman where sname=?";
		 try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,nm);
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				 String pw= rs.getString("spassword");
				 int sid = rs.getInt("sid");
				 String nme = rs.getString("sname");
				 SalesMan salesMan = new SalesMan(sid,nme,pw);
				 SalesManInf.add(salesMan);
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
		 return SalesManInf;
	 }
	 public static boolean addSalesMan(SalesMan man)
	 {
		 boolean bl = false;
		 ArrayList<SalesMan> List = FindPassWord(man.getName());
		 if(List.size()>0)
		 {
			 System.out.println("���û��Ѵ��ڣ�������ѡ��Ҫ������ۻ�Ա");
			 return false;
		 }
		 conn = connectDb.getConn();
		 String sql = "insert into salesman(sname,spassword) values(?,?)";
		 try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, man.getName());
			 pstmt.setString(2, man.getPassWord());
			 int f = pstmt.executeUpdate();
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
	 
	 public static boolean deleteSalesMan(String snm)//�����û���ɾ���ۻ�Ա
	 {
		 boolean bl =false;
		 conn = connectDb.getConn();
		 String sql = "delete from salesman where sname=?";
		 try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, snm);
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
	 
	 public static boolean updateSalesMan(SalesMan man) //�����µ����룬���и���
	 {
		 boolean bl =false;
		 conn = connectDb.getConn();
		 String sql= "update salesman set spassword=? where sname=?";
		 try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, man.getPassWord());
			 pstmt.setString(2 ,man.getName());
			 int f = pstmt.executeUpdate();
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
	 
	 private static ArrayList<SalesMan> FindAll()
	 {
		 ArrayList<SalesMan> list = new ArrayList<SalesMan>();
		 conn = connectDb.getConn();
		 String sql = "select * from salesman";
		 try {
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				 int sid= rs.getInt("sid");
				 String sname = rs.getString("sname");
				 String spw = rs.getString("spassword");
				 list.add(new SalesMan(sid,sname,spw));
			 }
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		 finally {
			 closeDb.queryClose(pstmt, rs, conn);
		 }
		 return list;
	 }
	 
	 
}


