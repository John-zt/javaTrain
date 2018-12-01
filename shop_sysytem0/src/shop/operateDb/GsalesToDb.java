package shop.operateDb;
import java.util.*;
import java.sql.*;
import shop.entity.*;
import shop.db.closeDb;
import shop.db.connectDb;
import shop.tools.*;
public class GsalesToDb {
      
	static Connection conn = null;
	static PreparedStatement pstmt  = null;
	static ResultSet rs = null;
	public static void main(String [] args)
	{
		//addGsales(new Gsales(3,2,3,4) );
		
		QueryPrint.PrintGsales(getAll_gsales());
	}
	public static ArrayList<Gsales> getAll_gsales()
	{
		ArrayList<Gsales> list = new ArrayList<Gsales>();
		conn = connectDb.getConn();
		String sql = "select * from gsales";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int gid=rs.getInt("gid");
				int sid=rs.getInt("sid");
				int sgid=rs.getInt("gsid");
				int sum=rs.getInt("sum");
				list.add(new Gsales(gid,sid,sgid,sum));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			closeDb.addClose(pstmt, conn);
		}
		return list;
	}
	public static boolean addGsales(Gsales goods)
	{
		boolean bl=false;
		conn = connectDb.getConn();
		String sql = "insert into gsales(gsid,sid,gid,sum) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods.get_gsid());
			pstmt.setInt(2, goods.get_sid());
			pstmt.setInt(3, goods.get_gid());
			pstmt.setInt(4, goods.get_sum());
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
		return bl;
	}
	//存在问题
	public static boolean updateGsales(Gsales goods)
	{
		boolean bl = false;
		conn = connectDb.getConn();
		String sql = "update goods set sum=?";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods.get_sum());
			int f=pstmt.executeUpdate();
			if(f>0)
			{
				bl = true;
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
		return bl;
	}
    //存在问题
	public static boolean deleteGsales(Gsales goods)
	{
		boolean bl = false;
		conn = connectDb.getConn();
		String sql = "delete from goods where gid=?";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods.get_sum());
			int f=pstmt.executeUpdate();
			if(f>0)
			{
				bl = true;
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
		return bl;
	}
}
