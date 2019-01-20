package coe528.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; 

/**
 *
 * This class will read the file for the accounts and also write the updated 
 * contents into the file accs. This is a mutable class.
 */
public class Users  {
    String username,password,role;
    Users accounts[] = new Users[1];
    int balance,acclength;
    String status;
    //PrintWriter AccFile = new PrintWriter("accs", true);

    public Users(String un, String pass, String roler, int balance, String status) {
        username = un;
        password = pass; 
        role = roler;
        this.balance = balance;
        this.status = status;
    }
   
    /**
     * //Requires: 
    *Modifies: accounts[]
    *Effects: if file does not exist it creates one 
     * @throws FileNotFoundException
     * @
     */
    public void startup() throws FileNotFoundException  {
        FileReader fin = new FileReader("accs");
        Scanner src = new Scanner(fin);
        
        String temp[] = new String[1000]; 
        int checker = 0;
        while (src.hasNext()) {
        temp[0] = src.nextLine();
        String[] split = temp[0].split(" ");
        int intb = Integer.parseInt(split[3]);			
        Users temperory;
        temperory = new Users(split[0],split[1],split[2],intb,split[4] );
        createAccount(temperory);
        //System.out.println(checker);
        }
        //removeAccount(accounts.length -2 );
        //System.out.println( 20);
        StatusUpdate();
        
    }
    public Users[] getAccounts(){
        return accounts;
    }
    /**
     * Requires: Users newbie
     *Modifies:accounts[], accs.txt
     *Effects:accounts[]
     * @param newbie
     * @
     */
    public void newuser(Users newbie) throws FileNotFoundException {
        String newuser =  newbie.username + " " + newbie.password + " "+ newbie.role + " " +  newbie.balance +" " +newbie.status+  "\n";
        PrintWriter AccFile;
        AccFile = new PrintWriter("accs");
        AccFile.write(newuser);
        AccFile.close();
    }
  

    /**
     *Requires: Users adder
    * Modifies: accounts[]
    * Effects: acc.txt
    * @param adder
    * @
     */
    public void createAccount(Users adder)  {
        int accsize = accounts.length;
        Users accountsplus[] = new Users[accsize+1];
       
        for (int i=0; accounts.length>i; i++){
            accountsplus[i] = accounts[i];
        }
        accountsplus[accsize] = adder;
        accounts = accountsplus;
        
        acclength = accsize+1;
    }
     
    /**
     *Requires: accnum
    * Modifies: accounts
    * Effects: accs.txt
     * @param accnum
     * @throws java.io.FileNotFoundException
     * @
     */
    public void removeAccount(int accnum) throws FileNotFoundException {
        int accsize = accounts.length;
        
        Users accountsminus[] = new Users[accsize-1];
        for (int i = accnum + 2 ;i < accsize; i++){
        //System.out.println(" we want " + i + " to be " + (i+1) + " " +accounts[21].balance );            
        accounts[i-1] = accounts[i]; 
        }
        for (int i=0; accountsminus.length > i; i++){
            accountsminus[i] = accounts[i];
    }
        accounts = accountsminus;
        WritetoFile();
       
    }
    
    public void displayAccounts(){
       for (int i=0; accounts.length-1>i; i++){
       System.out.println("Account " + (i+1) + " is a " + accounts[i+1].role
       + " has a balance of " + accounts[i+1].balance + " and is a " + 
       accounts[i+1].status + " Member" );
       }
    }

    /**
     * Requires: 
     * Modifies:acc.txt
     * Effects: updusers
     * @throws java.io.FileNotFoundException
     * @
     */
    public void WritetoFile() throws FileNotFoundException  {
       PrintWriter AccFile;
       AccFile = new PrintWriter("accs");
       String updusers = "";
       for (int i=0; accounts.length-1>i; i++){
       updusers = updusers + accounts[i+1].username + " " + accounts[i+1].password + " "+ accounts[i+1].role + " " +  accounts[i+1].balance +" " +accounts[i+1].status+  "\n";
       }   
       AccFile.write(updusers);
       AccFile.close();
    }
   
    /**
     * Requires: 
     * Modifies: accounts[i] status
     * Effects: accounts[]
     */
    public void StatusUpdate(){
       for (int i=0; acclength-1>i;i++){
           if (accounts[i+1].balance > 20000){
               accounts[i+1].status =  "Platinum";
           }
           else if (accounts[i+1].balance < 10000) {
                accounts[i+1].status =  "Silver";
       } 
           else{
               accounts[i+1].status = "Gold";
           }
        }
   }
    

    /**
     * Requires: int amount
     * Modifies: accounts[i] balance
     * Effects: accounts
     * @param amount
     */
    public void deposit(int amount){
        balance = balance + amount;
        StatusUpdate();
    }
   /**
     * Requires: int amount
     * Modifies: accounts[i] balance
     * Effects: accounts
     * @param amount
     */
       public void withdrawl(int amount){
        balance = balance - amount;
        StatusUpdate();
    }
        /**
     * Requires: int amount
     * Modifies: accounts[i] balance
     * Effects: accounts
     * @param amount
     */
        public void Purchase(int amount){
           if ("Platinum".equals(status)){
               balance = balance - amount;               
           }
           if ("Silver".equals(status)){
               balance = balance - amount - 20;
           }
        if ("Gold".equals(status)){
           balance = balance - amount -10;
           }
        StatusUpdate();
       }


    /**
     * Requires: 
     * Modifies: accounts[]
     * Effects:accs.txt
     * @
     */
        public void Login() throws FileNotFoundException {
            System.out.println("Hello and welcome to the bank");
            System.out.println("Please enter your username");
            Scanner src = new Scanner(System.in);
            String usernam; 
            usernam = src.nextLine();
            if ("admin".equals(usernam)){
                String passie;
                System.out.println("Please enter password");
                passie = src.nextLine();
                if ("admin".equals(passie)){
                    managerinterface();
                }
            }
            else{
                for (int i =0; accounts.length -1 >i; i++){
                    if (accounts[i+1].username.equals(usernam)){
                        String paassiieee;
                        System.out.println("Please enter password");
                        paassiieee = src.nextLine();
                        if (paassiieee.equals(accounts[i+1].password)){
                            customerinterface(i);
                }               
            }
            }  }
            WritetoFile();
          
            
        }
   /**
     * Requires: 
     * Modifies: accounts[]
     * Effects:accs.txt
     * @throws java.io.FileNotFoundException
     * @
     */
        public void managerinterface() throws FileNotFoundException {
            System.out.println("\nEnter 1 to create an account, \nEnter 2 to delete an account\nEnter 3 to dilplaty accounts \nEnter 4 to log out");
            int input;
            Scanner src = new Scanner(System.in);
            input = src.nextInt();
            if (input == 1){
                System.out.println("Please enter username");
                String usernamer = src.next();
                System.out.println("Please enter password");
                String pass = src.next();
                Users Temp;
                Temp = new Users(usernamer,pass,"customer",100,"Silver");
                createAccount(Temp);
                managerinterface();

            }
            if (input == 2){
                System.out.println("What is the account number you wish to delete");
                int accnum  = src.nextInt();
                removeAccount(accnum);
                managerinterface();

            }
            if (input==3){
                displayAccounts();
                managerinterface();
            }
            if (input == 4){
                System.out.println("Goodbye manager");
            }
                }
     /**
     * Requires: 
     * Modifies: accounts[]
     * Effects:accs.txt
     * @
     */
        public void customerinterface(int index){
            StatusUpdate();
            System.out.println("\nEnter 1 for balance \nEnter 2 for withdrawl \nEnter 3 for deposit \nEnter 4 to make an online purchase  \nEnter 5 to logout");
            Scanner src = new Scanner(System.in);
            int input;
            input = src.nextInt();
            if (input == 1){
                System.out.println("Your balance is " + accounts[index+1].balance
                + " You are a " +accounts[index+1].status+" Account holder" );
                customerinterface(index);
            }
            if (input ==2){
                System.out.println("How much do you wanna withdraw");
                int amount = src.nextInt();
                accounts[index+1].withdrawl(amount);
                customerinterface(index);

            }
             if (input ==3){
                System.out.println("How much do you wanna deposit");
                int amount = src.nextInt();
                accounts[index+1].deposit(amount);
                customerinterface(index);

        }
         if (input ==4){
                System.out.println("How much does your purchase cost");
                int amount = src.nextInt();
                accounts[index+1].Purchase(amount);
                customerinterface(index);
         }
         if (input==5){
             System.out.println("Goodbye and come back soon");
         }
        }
        public boolean  repOk(){
        for (int i=0; accounts.length-1>i; i++){
            if ( accounts[i].balance == (int)(accounts[i].balance) ) {
                return true;
            }
        }
              return false;
        }               
        
@Override
  /**
     * c is a an account in accounts[] 
     * AF.c is the abstract function
     * Af.c[0,1,2,4] = String
     * Af.c[3] = int
     * 
     * Rep Invariant()
     * Af.c != null
     * Af.c = Users
     * 
*/
    public String toString() {
       String returner = "";
      for (int i=0; accounts.length-1>i; i++){
       returner= returner + ("Account " + (i+1) + " is a " + accounts[i+1].role
       + " has a balance of " + accounts[i+1].balance + " and is a " + 
       accounts[i+1].status + " Member\n" );
       }
        return returner ;
    }
    /*public static void main(String[] args) throws IOException  {
        Users Jamal,acc1;
        Jamal = new Users("admin","admin","manager",100000,"Silver");
        Jamal.startup();
        Jamal.Login();
    */
} 
          

         

 
