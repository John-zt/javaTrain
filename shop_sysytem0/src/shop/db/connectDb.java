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
	        String pathname = "C:\\Users\\Administrator\\Desktop\\student.txt"; // ����·�������·�������ԣ�д���ļ�ʱ��ʾ���·��,��ȡ����·����input.txt�ļ�
	        //��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw;
	        //���ر��ļ��ᵼ����Դ��й¶����д�ļ���ͬ��
	        //Java7��try-with-resources�������Źر��ļ����쳣ʱ�Զ��ر��ļ�����ϸ���https://stackoverflow.com/a/12665271
	        try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) // ����һ�����������ļ�����ת�ɼ�����ܶ���������
	        ) {
	            String tmp;
	            //�����Ƽ����Ӽ���д��
	            while ((tmp = br.readLine()) != null) 
	            {
	                // һ�ζ���һ������
	            	
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
			System.out.println("�ɹ�����mysql");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("û���ҵ�mysql����");
			e.printStackTrace();
		}
		String url ="jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		Connection conn=null;
		try 
		{
			conn = DriverManager.getConnection(url, "root", "ASDWydc123");
			/*System.out.println("����db");
			Statement cur = (Statement)conn.createStatement();
			System.out.println("�ɹ��������ݿ�");
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
			System.out.println("����ʧ��");
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