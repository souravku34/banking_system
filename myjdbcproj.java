package banking_system;
import java.sql.*;
import java.io.*;
import java.util.*;
public class myjdbcproj {

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Connection con=null;
		Statement stmt=null;
		ResultSet result=null;
		int n,ch,ch1;
		int c=0;
		String sql;
		try {
			String url="jdbc:mysql://localhost:3306/org";
			String uid="root";
			String pwd="";
			
				con=DriverManager.getConnection(url,uid,pwd);
				stmt=con.createStatement();
				System.out.println("\n***** Banking Management System*****\n");
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				do 
				{
					System.out.println("1: Display the customer details");
					System.out.println("2: Insert customer's details");
					System.out.println("3: Delete customer's details");
					System.out.println("4: Update customer's details");
					System.out.println("5: Show account details of a customer");
					System.out.println("6: Show loan details of a customer");
					System.out.println("7: Deposit Money to an Account");
					System.out.println("8: Withdraw Money from an Account");
					System.out.println("9: EXIT\n");
					System.out.println("Enter Your Choice");
					ch=Integer.parseInt(br.readLine());
					
					switch(ch)
					{
						//DISPLAY RECORD
						case 1:
							 c=0;
							 sql="select * from customer";
							 result=stmt.executeQuery(sql);
							while(result.next())
							{
								System.out.print(result.getString("cust_no")+"\t\t");
								System.out.print(result.getString("name")+"\t\t");
								System.out.print(result.getString("phone_no")+"\t\t");
								System.out.print(result.getString("city")+"\t\t\n");
								c++;
							}
							System.out.println(" ");
							System.out.println(c+" rows selected\n\n");
							break;
						//INSERT RECORD	
						case 2:
							c=0;
							System.out.println("Enter the customer number");
							String cno=br.readLine();
							System.out.println("Enter the customer name");
							String name=br.readLine();
							System.out.println("Enter the phone_no ");
							Long phone=Long.parseLong(br.readLine());
							System.out.println("Enter the city ");
							String city=br.readLine();
							
							sql="insert into customer (cust_no,name,phone_no,city) values('"+cno+"','"+name+"','"+phone+"','"+city+"')";
							stmt.executeUpdate(sql);
							c++;
							System.out.println(c+" Row has been inserted\n");
							System.out.println("Data Inserted\n");
							System.out.println();
							break;
						//DELETE RECORD
						case 3:
							c=0;
							System.out.println("Enetr the customer number");
							String del=br.readLine();
							sql="DELETE FROM customer WHERE cust_no='"+del+"'";
							stmt.executeUpdate(sql);
							c++;
							System.out.println(c+" Row has been affected\n");
							System.out.println("Data Deleted\n");
							System.out.println();
							break;
						//UPDATE RECORD
						case 4:
							
							System.out.println("1: For name");
							System.out.println("2: For number");
							System.out.println("3: For city");
							System.out.println("Enter Your Choice");
							ch1=Integer.parseInt(br.readLine());
							System.out.println("Enter the customer number");
							String c_no=br.readLine();
							System.out.println();
							switch(ch1)
							{
							case 1:
								c=0;
								System.out.println("Enter the customer name");
								String Name=br.readLine();
								sql="update customer set name='"+Name+"' where cust_no='"+c_no+"' ";
								stmt.executeUpdate(sql);
								c++;
								System.out.println(c+" Row has been affected\n");
								System.out.println("Data Updated\n");
								System.out.println();
								break;
							case 2:
								c=0;
								System.out.println("Enter the phone number");
								String Num=br.readLine();
								sql="update customer set phone_no='"+Num+"' where cust_no='"+c_no+"' "; 
								stmt.executeUpdate(sql);
								c++;
								System.out.println(c+" Row has been affected\n");
								System.out.println("Data Updated\n");
								System.out.println();
								break;
							case 3:
								c=0;
								System.out.println("Enter the City");
								String City=br.readLine();
								sql="update customer set city='"+City+"' where cust_no='"+c_no+"' ";
								stmt.executeUpdate(sql);
								c++;
								System.out.println(c+" Row has been affected\n");
								System.out.println("Data Updated\n");
								System.out.println();
								break;
							default :
								System.out.println("Invalid Input");
							}
						//SHOW ACCOUNT DETAIL
						case 5:
							c=0;
							System.out.println("Enter the customer number");
							String cnum=br.readLine();
							sql="select cust_no,name,phone_no,city, account_no, type,balance, branch_code, branch_name,branch_city from customer natural join depositor natural join account natural join branch where cust_no='"+cnum+"'";
							result=stmt.executeQuery(sql);
							while(result.next())
							{
								for(int i=1;i<=10;i++)
								{
									System.out.print(result.getString(i)+"\t\t");
									
								}
								System.out.print("\n");
							}
							System.out.println("\n");
							c++;
							System.out.println(c+" Row has been affected\n");
							
							break;
						//SHOW LOAN DETAIL
						case 6:
							c=0;
							System.out.println("Enter the customer number");
							String cnu=br.readLine();
							sql="select cust_no,name,phone_no,city, loan_no,amount,branch_code, branch_name,branch_city from customer natural join loan natural join  branch where cust_no='"+cnu+"' ";
							result=stmt.executeQuery(sql);
							while(result.next())
							{
								for(int i=1;i<=9;i++)
								{
									System.out.print(result.getString(i)+"\t\t");
									
								}
								System.out.print("\n");
							}
							System.out.println("\n");
							c++;
							System.out.println(c+" Row has been affected\n");
							
							break;
						//DEPOSIT AMOUNT
						case 7:
							c=0;
							System.out.println("Enter the account number");
							String ano=br.readLine();
							System.out.println("Enter the amount to deposit");
							int amt=sc.nextInt();
							System.out.println();
							sql="update account set balance=balance+'"+amt+"' where account_no='"+ano+"'";
							stmt.executeUpdate(sql);
							System.out.print("CURRENT BALANCE:-");
							sql="select balance from account where account_no='"+ano+"'";
							result=stmt.executeQuery(sql);
							while(result.next())
							{
								System.out.print(result.getString("balance")+"\t\t\n");
							}
							System.out.println();
							c++;
							System.out.println(c+" Row has been affected\n");
							
							break;
						//WITHDRAW AMOUNT
						case 8:

							System.out.println("Enter the account_no");
							String accno1=br.readLine();
							System.out.println("Enter the amount you want to add");
							int amount1=Integer.parseInt(br.readLine());
							int bal=0;
							String cbal="select balance from account where account_no='"+accno1+"'";
							ResultSet res3=stmt.executeQuery(cbal);
							while(res3.next()) {
								System.out.println("previous balance:"+res3.getString("balance")+"\n");
								bal=Integer.parseInt(res3.getString("balance"));
							}
							if(bal>=amount1) {
								String sub="update account set balance=balance-'"+amount1+"' where account_no='"+accno1+"'";
								stmt.executeUpdate(sub);
								System.out.println("Account balanced updated");
								String balance1="select balance from account where account_no='"+accno1+"'";
								ResultSet res4=stmt.executeQuery(balance1);
								while(res4.next()) {
									System.out.println("Updated Balance:"+res4.getString("balance")+"\n");
								}
							}
							else {
								System.out.println("Insufficient Balance!!!\n");
							}
							break;
						//EXIT
						case 9:
							stmt.close();
							con.close();
							System.out.println("Exit Successful");
							System.exit(0);
							
						default :
							System.out.println("Invalid Input");
					}
				}
				while(ch!=9);
					
		}
		catch(Exception e) 
		{
			System.out.println("Exception found");
		}
		
	}

}
