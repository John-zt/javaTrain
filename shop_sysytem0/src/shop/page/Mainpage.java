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
		System.out.println("欢迎使用超时购物管理系统");
		System.out.println("");
		System.out.println("**********************************");
		System.out.println("");
		System.out.println("");
		System.out.println("             1. 商品维护");
		System.out.println("");
		System.out.println("             2. 前台收银");
		System.out.println("");
		System.out.println("             3. 商品管理");
		System.out.println("");
		System.out.println("             0. 退出 ");
		System.out.println("");
		System.out.println("**********************************");
		System.out.println("请输入选项来确定想要进行的操作");
		System.out.println("");
	}
	public static void Begin()
	{
		do {
			welcomeBegin();
			int Select = cin.nextInt();
			if(Select==1) // 进入商品维护界面
			{
				GoodsMaintencePage();
			}
			else if(Select==2) //进入前台收银界面
			{
				CheckAndPay();
			}
			else if(Select==3) //进入商品管理界面
			{
				
			}
			else if(Select==0) //退出
			{
				System.out.println("感谢使用");
				break;
			}
			else
			{
				System.out.println("输入有误，请重新输入");
			}
			System.out.println("是否继续Y/N");
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
			System.out.println("\t 1.添加商品\n");
			System.out.println("\t 2.更改商品\n");
			System.out.println("\t 3.删除商品\n");
			System.out.println("\t 4.查询商品\n");
			System.out.println("\t 5.显示所有商品\n");
			System.out.println("***************************");
			System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
			int Select = cin.nextInt();
			if(Select==1) //添加商品
			{
				System.out.println("请输入商品名称，价格，数量");
				String name= ScannerChoice.ScannerInfoString();
				int price = ScannerChoice.ScannerPrice();
				int num = ScannerChoice.ScannerNum();
				boolean f=GoodsToDb.addGoods(new Goods(name,price,num));
				if(f==true)
				{
					System.out.println("商品添加成功");
				}
				else
				{
					System.out.println("添加失败,可能该商品已经存在");
				}
			}
			else if(Select==2) //更改商品
			{
				System.out.println("输入要更新商品的名字");
				String nm = ScannerChoice.ScannerInfoString();
				System.out.println("选择1. 更改数量");
				System.out.println("选择2. 更改名字");
				System.out.println("选择3.更改价格");
				int key = cin.nextInt();
				int newNum=0;
				int price=0;
				if(key==1)
				{
					System.out.println("请输入新的数量");
					newNum= cin.nextInt();
				}
				else if(key==3)
				{
					System.out.println("请输入新的价格");
					price = cin.nextInt();
				}
				Goods goods= new Goods(nm,price,newNum);
				GoodsToDb.updateGoods(key,goods);
			}
			else if(Select==3) //删除商品
			{
				System.out.println("输入要删除商品的名字");
				String nm = ScannerChoice.ScannerInfoString();
				boolean  f= GoodsToDb.deleteGoods(nm);
				if(f==true)
				{
					System.out.println("删除成功");
				}
				else
				{
					System.out.println("删除失败，没有找到该物品");
				}
			}
			else if(Select==4) //查询商品
			{
				System.out.println("选择1. 按数量升序");
				System.out.println("选择2. 按价格升序");
				System.out.println("选择3. 关键字查询");
				int key = ScannerChoice.ScannerNum();
				QueryPrint.PrintGoods(GoodsToDb.queryGoods(key));
			}
			else if(Select==5) //显示所有商品
			{
				QueryPrint.PrintGoods(GoodsToDb.displayAllGoods());
			}
			else if(Select==0) //返回上一级
			{
				Begin();
				break;
			}
			System.out.println("是否继续Y/N");
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
			System.out.println("\n*******欢迎使用商超购物管理系统*******\n");
			System.out.println("\t 1.登录系统\n");
			System.out.println("\t 2.退出\n");
			System.out.println("-----------------------------");
			System.out.println("请输入选项,或者按 0 返回上一级菜单.");
			int Select = cin.nextInt();
			if(Select==1) //登录
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
				System.out.println("输入有误，请重新");
			}
		}while(true);
	}
	
	
	public static void Login()
	{
		int LogTime=3;
		cin.nextLine();
		while(LogTime!=0)
		{
			System.out.println("请输入用户名");
			String name = cin.nextLine();
			//name = "dw";
			System.out.println("请输入密码");
			String pd = cin.nextLine();
			//System.out.println("角色选择,1.普通售货员 , 2. 高级管理员");
			//System.out.println(SalesManToDb.FindPassWord("dw").get(0).getPassWord());
			ArrayList<SalesMan> salesman = new SalesManToDb().FindPassWord(name);
			if(salesman==null||salesman.size()<=0)
			{
				System.out.println("1....");
				System.out.println("用户名或密码错误,请重新输入,你还有"+LogTime+"次机会");
			}
			else {
			SalesMan man = salesman.get(0);
			if(pd.equals(man.getPassWord())==true)
			{
				System.out.println("登录成功");
				shoppingSetPage(1);
				break;
			}
			else
			{
				System.out.println("用户名或密码错误,请重新输入,你还有"+LogTime+"次机会");
			}
			}
			LogTime--;
		}
		
		System.out.println("------------------");
		System.err.println("\t！！您已被强制退出系统！！");
		System.exit(1);	
	}
	
	public static void shoppingSetPage(int salesManId)
	{
		System.out.println("购物结算界面");
		System.out.println("按s开始结算，按0返回登录界面");
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
				System.out.println("请输入商品关键字");
				String s = cin.next();
				//System.out.println(s);
				int gid = GoodsToDb.FindGid(s);
				if(gid==-3)
				{
					System.out.println("查无此商品");
				}
				else
				{
					//System.out.println(gid);
					Goods goods = GoodsToDb.FindGoodsByGid(gid);
					System.out.println("请输入想要购买的数量");
					int num = ScannerChoice.ScannerNum();
					System.out.println("ww");
					System.out.println(goods.getGnum());
					if(num > goods.getGnum())
					{
						System.out.println("kk");
						System.out.println("商品数量不足，请重新输入");
						num = ScannerChoice.ScannerNum();
					}
					System.out.println("xx");
					money =money+ num * goods.getGprice();
					goods.setGnum(goods.getGnum()-num);
					System.out.println("yy");
					GoodsToDb.updateGoods(1,goods);//更新数量
					System.out.println("zz");
				}
				System.out.println("是否继续Y/N");
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
			System.out.println("结算结束，此次共花费"+money+"元");
			System.out.println("欢迎下次再来");
		}
	}
    
	public static void manegment()
	{
		
	}
	
}
