import java.util.Scanner;
public class index {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean repeater_1 = true;

        // 1st menu.... start from here....
        while (repeater_1) {           
        
        System.out.println(" ");
        System.out.println(".....main menu..... ");
        System.out.println("1. I'm customer");
        System.out.println("2. I'm employer");
        System.out.println("enter choice number : ");
        int choice_no_1 = sc.nextInt(); 

        if (choice_no_1 == 1) {
            customer();
        }else if (choice_no_1 == 2) {
            employer();
        }else{
            System.out.println("invalid choice please double check and try anain");
            repeater_1 = true;
        }
        }

    }
    public static void customer() {
        System.out.println("costomer check");
    }
    public static void employer() {
        System.out.println("employer check");
    }
}
