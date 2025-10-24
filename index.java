import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.File;                // For file creation, deletion, and metadata
import java.io.FileReader;          // For reading character files
import java.io.FileWriter;          // For writing character files
import java.io.BufferedReader;      // For efficient reading
import java.io.BufferedWriter;      // For efficient writing
import java.io.FileInputStream;     // For reading binary files
import java.io.FileOutputStream;    // For writing binary files
import java.io.IOException;         // For handling IO exceptions

public class index {
    
    public static void main(String[] args) {

//............................main menu start from here............................................
        Scanner sc = new Scanner(System.in);
        boolean repeater_1 = true;
        String[][] arr_route = new String[31][31];
        read_route_data_to_array(arr_route);

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
                employer(arr_route);
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

    public static void employer(String[][] arr_route) {

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
                city_management(arr_route);
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
    public static void city_management(String[][] arr_route) {
        boolean repeater_3 = true ; 

        Scanner sc =new Scanner(System.in);
        int choice_no_4;
        while(repeater_3){
            System.out.println("");
            System.out.println(".....city management menu.....");
            System.out.println("1. View city table");
            System.out.println("2. Add new city");
            System.out.println("3. Rename city");
            System.out.println("4. Remove city");
            System.out.print("5. exit from city management : ");

            choice_no_4 = sc.nextInt();

            if(choice_no_4 == 1){
                display_distance(arr_route);
            }
            else if(choice_no_4 == 2){
                add_new_city(arr_route);
            }
            else if(choice_no_4 == 3){
                rename_city(arr_route);
            }
            else if(choice_no_4 == 4){
                remove_city(arr_route);
            }
            else if(choice_no_4 == 5){
                repeater_3 = false;
            }
            else{
                System.out.println("invalid choice please double check and try anain");
            }

            
            

        }
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

    public static float estimated_time(int D, int S) {
        float time = D / S;
        return time;
    }

    public static float fuel_consumption(int D, int E) {
        float fuel_Used = D / E;
        return fuel_Used;
    }

    public static float fuel_cost(float fuel_Used, float F) {
        float fuel_Used_cost = fuel_Used * F;
        return fuel_Used_cost;
    }

    public static float total_operational_cost(float cost, float fuel_Used_cost) {
        float total_cost = cost + fuel_Used_cost ;
        return total_cost;
    }

    public static float profit_calculation(float cost ) {
        float profit = cost * 25/100;
        return profit;        
    }

    public static float final_charge_to_customer(float total_cost , float profit) {
        float customercharge = total_cost + profit;
        return customercharge;
    }


//............................end of the methods for solve maths part..............................

//............................Load array to programe fron this method..............................
    public static void read_route_data_to_array(String[][] arr_route) { // load routs text to array
        try (BufferedReader br = new BufferedReader(new FileReader("routes.txt"))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < 31) {
                String[] values = line.split(",");
                for (int col = 0; col < 31 && col < values.length; col++) {
                    String value = values[col].trim().replace("\"", "");
                    arr_route[row][col] = value.equalsIgnoreCase("null") ? null : value;
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error reading routes.txt: " + e.getMessage());
        }

        // Optional: Print the array to verify
        
    }
//..........................end of the array loading ...............................................

//..........................this methods call from city management menu.............................

    public static void display_distance(String[][] arr_route) {   // display route table
        System.out.println();
        System.out.println("________________________________________________________________________________________________________________________________________________");
        
        // Print rows with row labels and aligned cells
        for (int i = 0; i < 31; i++) {
            
            if (i == 1){
                System.out.println("|_____|______________|_____________________________________________________________________________________________________________________________");
            }
            
            if (arr_route[i][0] == null || arr_route[i][0].equalsIgnoreCase("null")) {
                continue; // skip row if first cell is "null"
            }
            System.out.print("|");
            System.out.printf("%3d) ", i); // row label

            for (int j = 0; j < 31; j++) {
                
                String cell = arr_route[i][j];
                if(i == 0){
                if(j == 0){
                if (cell == null || cell.equalsIgnoreCase("null")) {
                    System.out.printf("%-14s", ""); // empty cell
                } else {
                    System.out.print("|");
                    System.out.printf("%-14s", cell); // aligned cell
                    System.out.printf("%-4s", "|"); 
                }}
                else{
                if (cell == null || cell.equalsIgnoreCase("null")) {
                    System.out.printf("%-5s", ""); // empty cell
                } else {
                    
                    if(arr_route[i + 1][j] == null  ){}
                    else{System.out.printf("%-5s", j);} // aligned cell
                }}
                }else{
                    if(j == 0){
                System.out.print("|");
                if (cell == null || cell.equalsIgnoreCase("null")) {
                    System.out.printf("%-14s", ""); // empty cell
                } else {
                    System.out.printf("%-14s", cell); // aligned cell
                    System.out.printf("%-4s", "|"); 
                }}
                else{
                if (cell == null || cell.equalsIgnoreCase("null")) {
                    System.out.printf(""); // empty cell
                } else {
                    System.out.printf("%-5s", cell); // aligned cell
                }}
                }
            }
            if(i != 0) {
            System.out.print("|"); }
            System.out.println();
            
        }
        System.out.println("|_____|______________|__________________________________________________________________________________________________________________________");
        
    }


    public static void add_new_city(String[][] arr_route) {
        
        Scanner sc = new Scanner(System.in);
        String cityname ;
        String distenceval;
        String cell;
        System.out.println(arr_route[28][28]);
        for (int i = 0; i < 32; i++) {

            if(i == 31){
                System.out.println("Database has reached its maximum storage limit.delete unwanted things and try again.");
                i = 31;
            }
            else{
                cell = arr_route[i][i];
            if (cell == null || cell.equalsIgnoreCase("null")) {

           // if (arr_route[i][i].equalsIgnoreCase("-1") ){
                System.out.println("enter city name :");
                System.out.print(arr_route[i][i]);
                cityname = sc.next();
                arr_route[0][i] = cityname;
                arr_route[i][0] = cityname;
                arr_route[i][i] = "0";

                for (int j = 1; j <= i ; j++) {

                    System.out.print("enter distence between " + cityname + " and " + arr_route[0][j] + " : ");
                    distenceval = sc.next();

                    arr_route[j][i] = distenceval;
                    arr_route[i][j] = distenceval;

                }
                i = 33;
            }}
               
                        
           
        }    

        display_distance(arr_route);
        
    }

    public static void rename_city(String[][] arr_route) {
        
    }

    public static void remove_city(String[][] arr_route) {
        
    }

//..........................end of methods call from city management menu .....................................



}
