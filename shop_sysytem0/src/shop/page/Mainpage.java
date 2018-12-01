package shop.page;
import java.util.*;
import shop.entity.*;
import shop.operateDb.*;
import shop.tools.QueryPrint;
import shop.tools.ScannerChoice;

public class Mainpage {
	static Scanner cin = new Scanner(System.in);
	public static void main(String [] args)
	{
		Begin();
	}
	public static void welcomeBegin()
	{
		System.out.println("��ӭʹ�ó�ʱ�������ϵͳ");
		System.out.println("");
		System.out.println("**********************************");
		System.out.println("");
		System.out.println("");
		System.out.println("             1. ��Ʒά��");
		System.out.println("");
		System.out.println("             2. ǰ̨����");
		System.out.println("");
		System.out.println("             3. ��Ʒ����");
		System.out.println("");
		System.out.println("             0. �˳� ");
		System.out.println("");
		System.out.println("**********************************");
		System.out.println("������ѡ����ȷ����Ҫ���еĲ���");
		System.out.println("");
	}
	public static void Begin()
	{
		do {
			welcomeBegin();
			int Select = cin.nextInt();
			if(Select==1) // ������Ʒά������
			{
				GoodsMaintencePage();
			}
			else if(Select==2) //����ǰ̨��������
			{
				CheckAndPay();
			}
			else if(Select==3) //������Ʒ�������
			{
				
			}
			else if(Select==0) //�˳�
			{
				System.out.println("��лʹ��");
				break;
			}
			else
			{
				System.out.println("������������������");
			}
			System.out.println("�Ƿ����Y/N");
			String s = cin.next();
			if("Y".equals(s)||"y".equals(s))
			{
				
			}
			else
			{
				break;
			}
		}while(true);
		
	}
	
	public static void GoodsMaintencePage()
	{
		do {
			System.out.println("***************************\n");
			System.out.println("\t 1.�����Ʒ\n");
			System.out.println("\t 2.������Ʒ\n");
			System.out.println("\t 3.ɾ����Ʒ\n");
			System.out.println("\t 4.��ѯ��Ʒ\n");
			System.out.println("\t 5.��ʾ������Ʒ\n");
			System.out.println("***************************");
			System.out.println("\n������ѡ��,���߰� 0 ������һ���˵�.");
			int Select = cin.nextInt();
			if(Select==1) //�����Ʒ
			{
				System.out.println("��������Ʒ���ƣ��۸�����");
				String name= ScannerChoice.ScannerInfoString();
				int price = ScannerChoice.ScannerPrice();
				int num = ScannerChoice.ScannerNum();
				boolean f=GoodsToDb.addGoods(new Goods(name,price,num));
				if(f==true)
				{
					System.out.println("��Ʒ��ӳɹ�");
				}
				else
				{
					System.out.println("���ʧ��,���ܸ���Ʒ�Ѿ�����");
				}
			}
			else if(Select==2) //������Ʒ
			{
				System.out.println("����Ҫ������Ʒ������");
				String nm = ScannerChoice.ScannerInfoString();
				System.out.println("ѡ��1. ��������");
				System.out.println("ѡ��2. ��������");
				System.out.println("ѡ��3.���ļ۸�");
				int key = cin.nextInt();
				int newNum=0;
				int price=0;
				if(key==1)
				{
					System.out.println("�������µ�����");
					newNum= cin.nextInt();
				}
				else if(key==3)
				{
					System.out.println("�������µļ۸�");
					price = cin.nextInt();
				}
				Goods goods= new Goods(nm,price,newNum);
				GoodsToDb.updateGoods(key,goods);
			}
			else if(Select==3) //ɾ����Ʒ
			{
				System.out.println("����Ҫɾ����Ʒ������");
				String nm = ScannerChoice.ScannerInfoString();
				boolean  f= GoodsToDb.deleteGoods(nm);
				if(f==true)
				{
					System.out.println("ɾ���ɹ�");
				}
				else
				{
					System.out.println("ɾ��ʧ�ܣ�û���ҵ�����Ʒ");
				}
			}
			else if(Select==4) //��ѯ��Ʒ
			{
				System.out.println("ѡ��1. ����������");
				System.out.println("ѡ��2. ���۸�����");
				System.out.println("ѡ��3. �ؼ��ֲ�ѯ");
				int key = ScannerChoice.ScannerNum();
				QueryPrint.PrintGoods(GoodsToDb.queryGoods(key));
			}
			else if(Select==5) //��ʾ������Ʒ
			{
				QueryPrint.PrintGoods(GoodsToDb.displayAllGoods());
			}
			else if(Select==0) //������һ��
			{
				Begin();
				break;
			}
			System.out.println("�Ƿ����Y/N");
			String s = cin.next();
			if("Y".equals(s)||"y".equals(s))
			{
				
			}
			else
			{
				break;
			}
		}while(true);
	}
	
	public static void CheckAndPay()
	{
		do {
			System.out.println("\n*******��ӭʹ���̳��������ϵͳ*******\n");
			System.out.println("\t 1.��¼ϵͳ\n");
			System.out.println("\t 2.�˳�\n");
			System.out.println("-----------------------------");
			System.out.println("������ѡ��,���߰� 0 ������һ���˵�.");
			int Select = cin.nextInt();
			if(Select==1) //��¼
			{
				Login();
			}
			else if(Select==2) 
			{
				Begin();
				break;
			}
			else
			{
				System.out.println("��������������");
			}
		}while(true);
	}
	
	
	public static void Login()
	{
		int LogTime=3;
		cin.nextLine();
		while(LogTime!=0)
		{
			System.out.println("�������û���");
			String name = cin.nextLine();
			//name = "dw";
			System.out.println("����������");
			String pd = cin.nextLine();
			//System.out.println("��ɫѡ��,1.��ͨ�ۻ�Ա , 2. �߼�����Ա");
			//System.out.println(SalesManToDb.FindPassWord("dw").get(0).getPassWord());
			ArrayList<SalesMan> salesman = new SalesManToDb().FindPassWord(name);
			if(salesman==null||salesman.size()<=0)
			{
				System.out.println("1....");
				System.out.println("�û������������,����������,�㻹��"+LogTime+"�λ���");
			}
			else {
			SalesMan man = salesman.get(0);
			if(pd.equals(man.getPassWord())==true)
			{
				System.out.println("��¼�ɹ�");
				shoppingSetPage(1);
				break;
			}
			else
			{
				System.out.println("�û������������,����������,�㻹��"+LogTime+"�λ���");
			}
			}
			LogTime--;
		}
		
		System.out.println("------------------");
		System.err.println("\t�������ѱ�ǿ���˳�ϵͳ����");
		System.exit(1);	
	}
	
	public static void shoppingSetPage(int salesManId)
	{
		System.out.println("����������");
		System.out.println("��s��ʼ���㣬��0���ص�¼����");
		String Select = cin.nextLine();
		if("0".equals(Select)==true)
		{
			CheckAndPay();
		}
		else if("S".equals(Select)||"s".equals(Select))
		{
			int money = 0;
			while(true) 
			{
				System.out.println("��������Ʒ�ؼ���");
				String s = cin.next();
				//System.out.println(s);
				int gid = GoodsToDb.FindGid(s);
				if(gid==-3)
				{
					System.out.println("���޴���Ʒ");
				}
				else
				{
					//System.out.println(gid);
					Goods goods = GoodsToDb.FindGoodsByGid(gid);
					System.out.println("��������Ҫ���������");
					int num = ScannerChoice.ScannerNum();
					System.out.println("ww");
					System.out.println(goods.getGnum());
					if(num > goods.getGnum())
					{
						System.out.println("kk");
						System.out.println("��Ʒ�������㣬����������");
						num = ScannerChoice.ScannerNum();
					}
					System.out.println("xx");
					money =money+ num * goods.getGprice();
					goods.setGnum(goods.getGnum()-num);
					System.out.println("yy");
					GoodsToDb.updateGoods(1,goods);//��������
					System.out.println("zz");
				}
				System.out.println("�Ƿ����Y/N");
				cin.nextLine();
				String sr =cin.nextLine();
				if("Y".equals(sr)||"y".equals(sr))
				{
					
				}
				else
				{
					break;
				}
			}
			System.out.println("����������˴ι�����"+money+"Ԫ");
			System.out.println("��ӭ�´�����");
		}
	}
    
	public static void manegment()
	{
		
	}
	
}
