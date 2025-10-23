import java.util.Scanner;
public class index {
    public static void main(String[] args) {

//............................main menu start from here............................................
        Scanner sc = new Scanner(System.in);
        boolean repeater_1 = true;

        while (repeater_1) {  
            System.out.println(" ");  
            System.out.println("Welcome to delever System");       
            System.out.println(" ");
            System.out.println(".....main menu..... ");
            System.out.println("1. I'm customer");
            System.out.println("2. I'm employer");
            System.out.println("3. exit");
            System.out.print("enter choice number : ");
            int choice_no_1 = sc.nextInt(); 

            if (choice_no_1 == 1) {
                customer();
            }else if (choice_no_1 == 2) {
                employer();
            }else if (choice_no_1 == 3) {
                repeater_1 = false;
            }else{
                System.out.println("invalid choice please double check and try anain");
                repeater_1 = true;
            }
        }

    }
//............................main menu end from here..............................................


//............................this methods call from main menu.....................................

    public static void customer() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to costomer aria");
        System.out.println();
        System.out.print("do you want to delever order Yes/No : ");
        String choice_no_3 = sc.next();
        choice_no_3 = choice_no_3.toUpperCase();
        char choice_no_3_1 = choice_no_3.charAt(0);
        
        if (choice_no_3_1 == 'Y' ){ //we don't use .equal becouse this is char data type variable it work with == with ''
            placeTheOder();
        }
        else{
            System.out.println("Sorry costormers only can delever order from us. If you want to delever oder from us plz try again. Thank you.");
        }
    
        
    }

    public static void employer() {

        Scanner sc = new Scanner(System.in);
        boolean repeater_2 = true;

        while (repeater_2) {
            System.out.println(" ");
            System.out.println(".....employer menu..... ");
            System.out.println("1. City Management");
            System.out.println("2. Delivery Records");
            System.out.println("3. Performance Reports");
            System.out.println("4. exit from employer menu");
            System.out.print("enter choice number : ");

            int choice_no_2 = sc.nextInt();

            if (choice_no_2 == 1) {
                city_management();
            }else if (choice_no_2 == 2) {
                delivery_records();
            }else if (choice_no_2 == 3) {
                performance_reports();
            }else if (choice_no_2 == 4) {
                repeater_2 = false;
            }else{
                System.out.println("invalid choice please double check and try anain");
            }    
        }
    }

//............................end of methods call by main manu.....................................

//............................this methods call from costomer menu.................................
    public static void placeTheOder() {
        System.out.println("check");
        
    }

//............................end of methods call by costomer manu.................................

//............................this methods call from employer menu.................................
    public static void city_management() {
        System.out.println("check");
        
    }
    public static void delivery_records() {
        System.out.println("check");
        
    }
    public static void performance_reports() {
        System.out.println("check");
        
    }



//............................end of methods call by employer manu.................................

//............................methods for solve maths part(functions)..............................
    public static float delivery_cost(int D, int R, int W) {
        float cost = D * R * (1 + W / 10000.0f);
        return cost;
    }



//............................end of the methods for solve maths part..............................
}
