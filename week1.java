
import java.util.Scanner;


class Mobile{
    private  String mobilenumber;

    public Mobile(){}
    public Mobile(String mobilenumber) {
        setMobilenumber(mobilenumber);
    }

    public String getMobilenumber(){return mobilenumber; }
    public void setMobilenumber(String mobilenumber){ this.mobilenumber = mobilenumber;}

    public String toString(){ return getMobilenumber();}
}

class Name {

    private String firstname;
    private String middlename;
    private String lastname;
    private Mobile mobile;

    public Name() {}

    public Name(String firstname, String middlename, String lastname, Mobile mobile) {
        setFirstname(firstname);
        setMiddlename(middlename);
        setLastname(lastname);
        setMobile(mobile);
    }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getMiddlename() { return middlename; }
    public void setMiddlename(String middlename) {this.middlename = middlename;}

    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getMobile() {return mobile.toString();}
    public void setMobile(Mobile mobile){this.mobile = mobile;}

    public String toString() {
        return getFirstname() + ", " + getMiddlename() + ", " + getLastname() + ", "+ getMobile();
    }
    public String wholeName(){
        return getFirstname() + " " + getMiddlename() + " " + getLastname();
    }
}

class ATMCard {
    private String cardnumber;
    private float balance;
    private Name name;

    public ATMCard(){}

    public ATMCard(String cardnumber, Name name, float balance) {
        setCardnumber(cardnumber);;
        setName(name);
        setBalance(balance);
    }

    public String getPhoneNumber(){
        return name.getMobile();
    }

    public String getCardnumber() {return cardnumber; }
    public void setCardnumber(String cardnumber){this.cardnumber = cardnumber;}

    public float getBalance() {return balance; }
    public void setBalance(float balance) {this.balance = balance; }

    public String getName() {return name.wholeName(); }
    public void setName(Name name) {this.name = name;}



    public String toString() {
        return getCardnumber() + ", " + getName() + ", " + getBalance();
    }
    float checkBalance(){return getBalance();}

    float depositMoney(float amount){
        if(amount>=0)
            balance+=amount;
        else
            System.out.println("Invalid amount to deposit. Please Try again!");
            return balance;
    }
    boolean withdrawMoney(float amount){
        if(amount<=balance && amount >=0)
            balance-=amount;
        return amount<=balance && amount >=0;
    }
    float interest(float rate){ float incurred_rate =balance*rate/100/12;  balance+= incurred_rate; return incurred_rate;}
}




public class TestATMCard {

    static void info(ATMCard atm){
        System.out.println("\n\n\nName: "+ atm.getName());
        System.out.println("Mobile: "+ atm.getPhoneNumber());
        System.out.println("Account: " + atm.getCardnumber());
        System.out.printf("Balance: %.2f\n\n\n",atm.checkBalance());
    }

    static void options(){
    System.out.println("DEBIT CARD TRANSACTION");
    System.out.println("[1] Deposit Cash");
    System.out.println("[2] Withdraw Cash");
    System.out.println("[3] Inquire Balance");
    System.out.println("[4] Calculate Interest Rate");
    System.out.println("[5] Exit");
    }


    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String fname,mname,lname,mobileNum,cardNum;
        float balance = 0,amount = 0,rate = 0;
        int choice = 1,valid =0;

        System.out.print("Enter First Name: ");
        fname = in.nextLine();
        System.out.print("Enter Middle Name: ");
        mname = in.nextLine();
        System.out.print("Enter Last Name: ");
        lname = in.nextLine();
        System.out.print("Enter Mobile Number ");
        mobileNum = in.nextLine();
        System.out.print("Enter Card Number ");
        cardNum = in.nextLine();
        while(valid==0){
            try {
                System.out.print("Enter Balance: ");
                balance = in.nextFloat();
                valid = 1;
            }catch(Exception e){
                System.out.println("Invalid input! You have to enter a number");
                in.next();
            }
        }

        Mobile mobile = new Mobile(mobileNum);
        Name name = new Name(fname,mname,lname,mobile);
        ATMCard atm = new ATMCard(cardNum,name,balance);

        while(choice!=5){
            info(atm);
            options();
            System.out.print("\nYour choice:");

            try {
                choice = in.nextInt();

                switch (choice) {
                    case 1:
                        try {
                        System.out.print("\nEnter amount: ");
                        amount = in.nextFloat();
                        atm.depositMoney(amount);
                        }catch (Exception e) {
                            System.out.println("Invalid input! You have to enter a number. Please Try again!");
                            in.next();
                        }

                        break;
                    case 2:
                        try {
                            System.out.print("\nEnter amount: ");
                            amount = in.nextFloat();
                            if (!(atm.withdrawMoney(amount)))
                                System.out.println("Invalid Amount to Withdraw. Try again!");
                        } catch (Exception e) {
                            System.out.println("Invalid input! You have to enter a number. Please Try again!");
                            in.next();
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        try {
                            System.out.println("\nEnter rate: ");
                            rate = in.nextFloat();
                            System.out.printf("\n\nInterest Incurred: %.2f", atm.interest(rate));
                        }catch (Exception e) {
                            System.out.println("Invalid input! You have to enter a number. Please Try again!");
                            in.next();
                        }
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Please try again");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input! You have to enter a number. Please Try again!");
                in.next();
            }
        }
        System.out.print("Press Any key to Continue");
        in.close();
    }
}
