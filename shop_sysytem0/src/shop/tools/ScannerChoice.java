package shop.tools;
import java.util.*;
public class ScannerChoice 
{
	static Scanner cin = new Scanner(System.in);
	// �����ַ�������
	public static String ScannerInfoString()
	{
		System.out.print("������  ");
		String tmp = cin.nextLine();
		return tmp;
	}
	
	public static int ScannerNum() //��������
	{
		System.out.print("���������� ");
		int num=cin.nextInt();
		return num;
	}
	
	public static int ScannerPrice() //�����ֵ
	{
		System.out.print("������۸� ");
		int num=cin.nextInt();
		return num;
	}
	
	public static void changedGoodsNext(String opr) //��һ��
	{
		System.out.println("�Ƿ����Y/N");
		String Select = cin.next();
		if("Y".equals(Select)||"y".equals(Select))
		{
			if("updateGoods".equals(opr))
			{
				//��ת��updateGoodsҳ��
				
			}
			else if("deleteGoods".equals(opr))
			{
				//��ת��deleteGoodsҳ��
				
			}
			else if("addGoods".equals(opr))
			{
				//��ת��addGoodsҳ��
				
			}
		}
		else
		{
			
		}
	}
	
	
	
	
}
