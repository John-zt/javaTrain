package shop.db;
import java.util.*;
import java.sql.*;
import java.io.*;
public class connectDb {
	static Connection conn=null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	public static void main(String [] args)
	{
//		conn = getConn();
//		try
//		{
//			ArrayList<String> list=readFile();
//			String sql = "insert into department(dnum,dname,address,dphone) values(?,?,?,?)";
//			for(int i=0;i<list.size();i++)
//			{
//				pstmt = conn.prepareStatement(sql);
//				String str=list.get(i);
//				String [] arr = str.split("\\s+");
//				int k=1;
//			    for(String ss:arr)
//			    {
//			    	System.out.println(ss);
//			    	pstmt.setString(k, ss);
//			    	k++;
//			    }
//			    System.out.println("out");
//			    pstmt.executeUpdate();
//			}
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally {
//			closeDb.addClose(pstmt, conn);
//		}
		conn = getConn();
		
		try 
		{
			ArrayList<String> list=readFile();
			String sql = "insert into selectcourse(studentNum,term,courseNum,workNum,freeGrade,examGrade,finalGrade) values(?,?,?,?,?,?,?)";
			for(int i=0;i<list.size();i++) 
			{
				pstmt = conn.prepareStatement(sql);
				String str=list.get(i);
			    String [] arr = str.split("\\s+");
			    int k=1;
			    for(String ss:arr)
			    {
			    	if(k==5||k==6||k==7)
			    	{
			    		int tmp = Integer.parseInt( ss); 
			    		pstmt.setInt(k, tmp);
			    	}
			    	else{
			    		pstmt.setString(k, ss);
			    	}
			    	k++;
			    	//if(k==7) break;
			    }
			    
			    pstmt.executeUpdate();
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
	 public static ArrayList<String> readFile() {
		    ArrayList<String> line=new ArrayList<String>();
	        String pathname = "C:\\Users\\Administrator\\Desktop\\student.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
	        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
	        //不关闭文件会导致资源的泄露，读写文件都同理
	        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
	        try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
	        ) {
	            String tmp;
	            //网友推荐更加简洁的写法
	            while ((tmp = br.readLine()) != null) 
	            {
	                // 一次读入一行数据
	            	
	                //System.out.println(tmp);
	                line.add(tmp);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return line;
	    }
	
	public static Connection getConn()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("成功加载mysql");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("没有找到mysql驱动");
			e.printStackTrace();
		}
		String url ="jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		Connection conn=null;
		try 
		{
			conn = DriverManager.getConnection(url, "root", "ASDWydc123");
			/*System.out.println("连接db");
			Statement cur = (Statement)conn.createStatement();
			System.out.println("成功连接数据库");
			ResultSet rs= cur.executeQuery("select * from goods");
			System.out.println(rs.toString());
			while(rs.next())
			{
				System.out.println(rs.getString("title"));
			}
			cur.close();
			conn.close();*/
		}
		catch(SQLException e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
		return conn;
	}
}
/*
  create table SALESMAN(
		 sid bigint not null auto_increment,
        sname varchar(20) not null unique,
        spassword varchar(20) not null,
        primary key(sid)
);
create table GSALES(
		 gsid bigint not null auto_increment,
		 gid bigint,
         foreign key(gid) references goods(gid),
         sid bigint,
         foreign key(sid) references salesman(sid),
         primary key(gsid)
);


create table GOODS(
              gid bigint not null auto_increment,
              gname varchar(20) not null unique,
              gprice bigint not null,
              primary key(gid)              
);
*/