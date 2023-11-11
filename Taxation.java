import java.util.Scanner;
class IncomeTax
{
    private int povertyLine=0;
    private int maxWealth=500000000;
    Scanner sc=new Scanner(System.in);
    void setPovertyLineAndMaxWealth(String key)
    {
        if(key.equals("Password"))
        {
            Scanner sc=new Scanner(System.in);
            System.out.printf("Enter the new poverty line: ");
            povertyLine=sc.nextInt();
            System.out.printf("Enter the new maximum wealth cutoff: ");
            maxWealth=sc.nextInt();
            System.out.printf("The poverty line has been changed to %d.\n",povertyLine);
            System.out.printf("The maximum wealth cutoff has been changed to %d.\n",maxWealth);
        }
        else
        {
            System.out.printf("Access denied. You must be a governor to change the poverty line/maximum wealth cutoff.\n");
        }
    }
    double calculateTax(double income)
    {
        double taxAmount;
        double taxPercentage;
        if(income<=povertyLine)
        {
            return 0.00;
        }
        else
        {
            taxPercentage=calculateTaxPercentage(income);
            taxAmount=(income-povertyLine)*(taxPercentage/100);
        }
        if((income-taxAmount)>=maxWealth)
        {
            taxAmount=income-maxWealth;
        }
        return taxAmount;
    }
    private double calculateTaxPercentage(double income)
    {
        if(income<=1.5*povertyLine)
        {
            return 10.00;
        }
        else if(income<=2*povertyLine)
        {
            return 20.00;
        }
        else
        {
            return 30.00;
        }
    }
}
class PropertyTax
{
    private double cpm=-1;
    void setCompensation(String key)
    {
        if(key.equals("Password"))
        {
            Scanner sc=new Scanner(System.in);
            System.out.printf("Enter the new compensation amount: ");
            cpm=sc.nextInt();
            if(cpm>=0)
            {
                cpm=-cpm;
            }
            System.out.printf("Compensation amount changed!\n");
        }
        else
        {
            System.out.printf("Access denied. You must be a governor to change the compensation amount.\n");
        }
    }
    double calculatePropertyTax(double propertyValue)
    {
        if(propertyValue<=0)
        {
           return cpm;
        }
        else
        {
            double taxPercentage=propertyValue<=100000?0.01:(propertyValue<=500000?0.02:0.03);
            return propertyValue*taxPercentage;
        }
    }
}
class TransportationTax
{
    private double trainPricePerKm=3.0;
    private double busPricePerKm=2.0;
    private double carPricePerKm=5.0;
    void setTranstax(String key)
    {
        if(key.equals("Password"))
        {
            Scanner sc=new Scanner(System.in);
            System.out.printf("Enter the new TrainPrice/km: ");
            double t=sc.nextInt();
            System.out.printf("Enter the new BusPrice/km: ");
            double b=sc.nextInt();
            System.out.printf("Enter the new CarPrice/km: ");
            double c=sc.nextInt();
            if(t<c && b<t)
            {
                trainPricePerKm=t;
                busPricePerKm=b;
                carPricePerKm=c;
                System.out.printf("Price change successful!\n");
            }
            else
            {
                System.out.printf("Price change rejected. Ensure that BusPrice/km < TrainPrice/km < CarPrice/km.\n");
            }
        }
        else
        {
            System.out.printf("Access denied. You must be a governor to change the transportation tax.\n");
        }
    }
    double calculateTax(double trainDistance,double busDistance,double carDistance)
    {
        double trainTax=trainPricePerKm*trainDistance;
        double busTax=busPricePerKm*busDistance;
        double carTax=carPricePerKm*carDistance;
        return trainTax+busTax+carTax;
    }
}
class UtilityTax
{
    private double waterPricePerL=1.0;
    private double electricityPricePerkWh=1.0;
    public void setUtiltax(String key)
    {
        if(key.equals("Password"))
        {
            Scanner sc=new Scanner(System.in);
            System.out.printf("Enter the new water tax per litre: ");
            waterPricePerL=sc.nextInt();
            System.out.printf("Enter the new electricity tax per kWh: ");
            electricityPricePerkWh=sc.nextInt();
            System.out.printf("The tax for a litre of water is %.2f.\n",waterPricePerL);
            System.out.printf("The tax for one kWh of electricity is %.2f.\n",electricityPricePerkWh);
        }
        else
        {
            System.out.printf("Access denied. You must be a governor to change the cost of water and electricity.\n");
        }
    }
    double calculateTax(double wateramount,double electricityamount)
    {
        double waterTax=waterPricePerL*wateramount;
        double electricityTax=electricityPricePerkWh*electricityamount;

        return waterTax+electricityTax;
    }
}
public class Taxation
{
    public static void main(String[] args)
    {
        System.out.printf("\n==CS3A Tax Payment Portal==\n\n");
        while(true)
        {
            int opt;
            Scanner sc=new Scanner(System.in);
            System.out.printf("1. Calculate total tax.\n");
            System.out.printf("2. Calculate specific tax.\n");
            System.out.printf("3. Governor controls.\n");
            System.out.printf("4. Exit.\n\n");
            System.out.printf("Enter a number: ");
            opt=sc.nextInt();
            IncomeTax obj1=new IncomeTax();
            PropertyTax obj2=new PropertyTax();
            TransportationTax obj3=new TransportationTax();
            UtilityTax obj4=new UtilityTax();
            if(opt==1)
            {
                System.out.printf("\nEnter your income: ");
                double a=sc.nextInt();
                System.out.printf("Enter the value of your property: ");
                double b=sc.nextInt();
                System.out.printf("Enter the distance you have travelled by car: ");
                double c=sc.nextInt();
                System.out.printf("Enter the distance you have travelled by bus: ");
                double d=sc.nextInt();
                System.out.printf("Enter the distance you have travelled by train: ");
                double e=sc.nextInt();
                System.out.printf("Enter the amount of litres of water you have used: ");
                double f=sc.nextInt();
                System.out.printf("Enter the amount of kWh of power you have used: ");
                double g=sc.nextInt();
                double Total=obj1.calculateTax(a)+obj2.calculatePropertyTax(b)+obj3.calculateTax(c,d,e)+obj4.calculateTax(f,g);
                System.out.printf("\nTotal tax to be paid: %.2f\n",Total);
            }
            else if(opt==2)
            {
                System.out.printf("\n1. Calculate income tax.\n");
                System.out.printf("2. Calculate property tax.\n");
                System.out.printf("3. Calculate transportation tax.\n");
                System.out.printf("4. Calculate utilities tax.\n\n");
                while(true)
                {
                    System.out.printf("Enter a number: ");
                    int opt1=sc.nextInt();
                    if(opt1==1)
                    {
                        System.out.printf("\nEnter your income: ");
                        double a=sc.nextInt();
                        System.out.printf("Income tax to be paid: %.2f",obj1.calculateTax(a));
                    }
                    else if(opt1==2)
                    {
                        System.out.printf("\nEnter the value of your property: ");
                        int a=sc.nextInt();
                        System.out.printf("Property tax to be paid: %.2f",obj2.calculatePropertyTax(a));
                    }
                    else if(opt1==3)
                    {
                        System.out.printf("\nEnter the distance you have travelled by car: ");
                        double a=sc.nextInt();
                        System.out.printf("Enter the distance you have travelled by bus: ");
                        double b=sc.nextInt();
                        System.out.printf("Enter the distance you have travelled by train: ");
                        double c=sc.nextInt();
                        System.out.printf("Transportation tax to be paid: %.2f",obj3.calculateTax(a,b,c));
                    }
                    else if(opt1==4)
                    {
                        System.out.printf("\nEnter the amount of litres of water you have used: ");
                        double a=sc.nextInt();
                        System.out.printf("Enter the amount of kWh of power you have used: ");
                        double b=sc.nextInt();
                        System.out.printf("Utilities tax to be paid: %.2f",obj4.calculateTax(a,b));
                    }
                    else
                    {
                        System.out.printf("Invalid input. Enter a valid number.\n\n");
                        continue;
                    }
                    System.out.printf("\n");
                    break;
                }
            }
            else if(opt==3)
            {
                System.out.printf("\n1. Change the poverty line and maximum wealth cutoff.\n");
                System.out.printf("2. Change the property compensation amount.\n");
                System.out.printf("3. Change the cost per km of transportation.\n");
                System.out.printf("4. Change the cost of water and electricity.\n\n");
                while(true)
                {
                    System.out.printf("Enter a number: ");
                    int opt1=sc.nextInt();
                    System.out.printf("Enter the password: ");
                    String pswd=sc.next();
                    System.out.printf("\n");
                    if(opt1==1)
                    {
                        obj1.setPovertyLineAndMaxWealth(pswd);
                    }
                    else if(opt1==2)
                    {
                        obj2.setCompensation(pswd);
                    }
                    else if(opt1==3)
                    {
                        obj3.setTranstax(pswd);
                    }
                    else if(opt1==4)
                    {
                        obj4.setUtiltax(pswd);
                    }
                    else
                    {
                        System.out.printf("Invalid input. Enter a valid number.\n\n");
                        continue;
                    }
                    break;
                }
            }
            else if(opt==4)
            {
                System.out.printf("\nThank you for your service.\n\n");
                break;
            }
            System.out.printf("\n");
        }
    }
}
