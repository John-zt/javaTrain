package shop.tools;
import java.util.*;
public class ScannerChoice 
{
	static Scanner cin = new Scanner(System.in);
	// 输入字符串函数
	public static String ScannerInfoString()
	{
		System.out.print("请输入  ");
		String tmp = cin.nextLine();
		return tmp;
	}
	
	public static int ScannerNum() //输入数量
	{
		System.out.print("请输入数量 ");
		int num=cin.nextInt();
		return num;
	}
	
	public static int ScannerPrice() //输入价值
	{
		System.out.print("请输入价格 ");
		int num=cin.nextInt();
		return num;
	}
	
	public static void changedGoodsNext(String opr) //下一步
	{
		System.out.println("是否继续Y/N");
		String Select = cin.next();
		if("Y".equals(Select)||"y".equals(Select))
		{
			if("updateGoods".equals(opr))
			{
				//跳转到updateGoods页面
				
			}
			else if("deleteGoods".equals(opr))
			{
				//跳转到deleteGoods页面
				
			}
			else if("addGoods".equals(opr))
			{
				//跳转到addGoods页面
				
			}
		}
		else
		{
			
		}
	}
	
	
	
	
}
