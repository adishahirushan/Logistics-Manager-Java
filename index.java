import java.util.Scanner;

import java.io.File;                // For file creation, deletion, and metadata
import java.io.FileReader;          // For reading character files
import java.io.FileWriter;          // For writing character files
import java.io.BufferedReader;      // For efficient reading
import java.io.BufferedWriter;      // For efficient writing
import java.io.IOException;         // For handling IO exceptions


public class index {
    
    public static void main(String[] args) {

//............................main menu start from here............................................
        Scanner sc = new Scanner(System.in);
        boolean repeater_1 = true;
        String[][] arr_route = new String[31][31];
        read_route_data_to_array(arr_route);
        //placeTheOder(arr_route);

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
                customer(arr_route);
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

    public static void customer(String[][] arr_route) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to costomer aria");
        System.out.println();
        System.out.print("do you want to delever order Yes/No : ");
        String choice_no_3 = sc.next();
        choice_no_3 = choice_no_3.toUpperCase();
        char choice_no_3_1 = choice_no_3.charAt(0);
        
        if (choice_no_3_1 == 'Y' ){ //we don't use .equal becouse this is char data type variable it work with == with ''
            placeTheOder(arr_route);
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
    public static void placeTheOder(String[][] arr_route) {
        Scanner sc = new Scanner(System.in);
        display_distance_customer(arr_route); 
        System.out.print("Enater source city iD (city id is 1st colluom ) : ");
        int source_city_id = sc.nextInt();
        System.out.print("Enter destination city iD (city id is 1st colluom ) : ");
        int destination_city_id = sc.nextInt();
        System.out.print("Enter package Weight : ");
        float weight = sc.nextFloat();
        
        

        // Vehicle data: {Capacity, Rate/km, Avg Speed, Fuel Efficiency}
        String[] types = {"Van", "Truck", "Lorry"};
        int[][] data = {
            {1000, 30, 60, 12},
            {5000, 40, 50, 6},
            {10000, 80, 45, 4}
        };

        // Print header
        System.out.printf("%-6s %-12s %-15s %-18s %-20s%n",
                "Type", "Capacity(kg)", "Rate/km (LKR)", "Avg Speed (km/h)", "Fuel Efficiency (km/l)");
        System.out.println("--------------------------------------------------------------------------");

        // Filter and print rows where weight <= capacity
        int raw_counter = 0;
        for (int i = 0; i < data.length; i++) {
            if (weight <= data[i][0]) {
                raw_counter ++ ;
                System.out.printf("%-6s %-12d %-15d %-18d %-20d%n",
                        types[i], data[i][0], data[i][1], data[i][2], data[i][3]);
            }
        }
        if (raw_counter == 0) {
            System.out.println("The specified weight exceeds the maximum load capacity of all available vehicles, the highest being 10000 kg");
        }
        else if (source_city_id == destination_city_id) {
            System.out.println("Delivery cannot be processed as the source and destination cities are the same. Our service is designed exclusively for city-to-city deliveries.");
        }
        else{

        System.out.print("Enter package vehical type  : ");
        char v_type = (sc.next()).charAt(0);
        v_type = Character.toUpperCase(v_type);
        System.out.println();
        float W = weight;
            int R = 0;
            int S = 0;
            int E = 0;
            String v_type_name = null;
        if(v_type == 'V'){
            v_type_name = "Van";
            R = data[0][1];
            S = data[0][2];
            E = data[0][3];

        }else if( v_type == 'T' ){
            v_type_name = "Truck";
            R = data[1][1];
            S = data[1][2];
            E = data[1][3];

        }else if( v_type == 'L'){
            v_type_name = "L";
            R = data[2][1];
            S = data[2][2];
            E = data[2][3];

        }
        
        if(v_type != 'V' && v_type != 'T' && v_type != 'L'){
            System.out.println("incorect vehica type. plz try again with valid vehical tye from above table.");
        }
        else{
            float D = least_destence_finder(arr_route,destination_city_id,source_city_id);
            if(D >= 0){
                float F = D / E * 310.00f;

                float cost = delivery_cost(D,R,W);
                float time = estimated_time(D,S);
                float fuel_Used = fuel_consumption(D,E);
                float fuel_Used_cost = fuel_cost(fuel_Used ,F);
                float total_cost = total_operational_cost(cost,fuel_Used_cost);
                float profit = profit_calculation(cost);
                float customercharge =final_charge_to_customer(total_cost,profit);
                System.out.println("==============================================================");
                System.out.println("DELIVERY COST ESTIMATION");
                System.out.println("--------------------------------------------------------------");
                System.out.printf("%-20s: %s%n", "From", source_city_id);
                System.out.printf("%-20s: %s%n", "To", destination_city_id);
                System.out.printf("%-20s: %.0f km%n", "Minimum Distance", D);
                System.out.printf("%-20s: %s%n", "Vehicle", v_type_name);
                System.out.printf("%-20s: %.2f kg%n", "Weight", W);
                System.out.println("--------------------------------------------------------------");
                System.out.printf("%-19s ", "Base Cost");
                System.out.println( ": " +  D + " X " + R + " ( 1 " + W + " / 10000) = " + String.format("%.2f", cost));
                //System.out.printf("%-20s: %d × %.0f × (1 + %d/10000) = %,.2f LKR%n","Base Cost", D, R, W, cost);
                System.out.printf("%-20s: %,.2f L%n", "Fuel Used", fuel_Used);
                System.out.printf("%-20s: %,.2f LKR%n", "Fuel Cost", fuel_Used_cost);
                System.out.printf("%-20s: %,.2f LKR%n", "Operational Cost", total_cost);
                System.out.printf("%-20s: %,.2f LKR%n", "Profit", profit);
                System.out.printf("%-20s: %,.2f LKR%n", "Customer Charge", customercharge);
                System.out.printf("%-20s: %.2f hours%n", "Estimated Time", time);
                System.out.println("==============================================================");
    



            

            }
        }
    }
       
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

            write_route_data_to_file(arr_route);
            

        }
    }
    
    public static void delivery_records() {
        System.out.println("check");
        
    }
    public static void performance_reports() {
        System.out.println();
        
    }
    
//............................end of methods call by employer manu.................................

//............................methods for solve maths part(functions)..............................
    public static float delivery_cost(float D, int R, float W) {
        float cost = D * R * (1 + W / 10000.0f);
        return cost;
    }

    public static float estimated_time(float D, int S) {
        float time = D / S;
        return time;
    }

    public static float fuel_consumption(float D, int E) {
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
                    //System.out.printf("%-5s", ""); // empty cell
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

     public static void display_distance_customer(String[][] arr_route) {   // display route table
        System.out.println();
        System.out.println("______________________");
                            
        // Print rows with row labels and aligned cells
        for (int i = 0; i < 31; i++) {
            
            if (i == 1){
                System.out.println("|_____|______________|");
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
                    
                    //if(arr_route[i + 1][j] == null  ){}
                    //else{System.out.printf("%-5s", j);} // aligned cell
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
                }
                }
            }
            
            System.out.println();
            
        }
        System.out.println("|_____|______________|");
        
    }

    public static void add_new_city(String[][] arr_route) {
        
        Scanner sc = new Scanner(System.in);
        String cityname ;
        String distenceval;
        String cell;
        boolean same_city_catcher = true;
        
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
                
                cityname = sc.next();

               for (int k = 0; k < 31; k++) {
                    if (arr_route[k][0] != null && arr_route[k][0].equalsIgnoreCase(cityname)){
                    same_city_catcher = false;
                    System.out.println(cityname + " is already stored.");

                    break;
                    }
                    
                } 

                if(same_city_catcher){
                arr_route[0][i] = cityname;
                arr_route[i][0] = cityname;
                arr_route[i][i] = "0";

                for (int j = 1; j <= 30 ; j++) {

                    if(arr_route[0][j] != null && !arr_route[0][j].equalsIgnoreCase("null")){
                    System.out.print("enter distence between " + cityname + " and " + arr_route[0][j] + " : ");
                    distenceval = sc.next();

                    arr_route[j][i] = distenceval;
                    arr_route[i][j] = distenceval;

                }}
                i = 33;
            }
            if(same_city_catcher == false){
                i = 33;
            }}
        
            }
             
        }    
       
        
    }

    public static void rename_city(String[][] arr_route) {
        
        Scanner sc =new Scanner(System.in);
        boolean same_city_catcher = true;
        display_distance(arr_route); 
        System.out.print("what city you want to rename, ");
        System.out.println("enter city Id (get city id from above table 1st column) :");
        int city_id = sc.nextInt();
        sc.nextLine();
        System.out.print("enter new name :");
        String city_name = sc.nextLine();
        
        for (int k = 0; k < 31; k++) {
            if (arr_route[k][0] != null && arr_route[k][0].equalsIgnoreCase(city_name)){
            same_city_catcher = false;
            System.out.println(city_name + " is already stored.plz retry using another name");

            break;
            }
        } 

        if(same_city_catcher){
        arr_route[0][city_id] = city_name;
        arr_route[city_id][0] = city_name;
        }

        
    }

    public static void remove_city(String[][] arr_route) {

        

        Scanner sc =new Scanner(System.in);
        display_distance(arr_route);
        display_distance(arr_route); 
        System.out.print("what city you want to remove/delete, ");
        System.out.println("enter city Id (get city id from above table 1st column) :");
        int city_id = sc.nextInt();

        for (int i = 0; i < 31; i++) {
            arr_route[i][city_id] = null;
            arr_route[city_id][i] = null;
 
        }
        
        
    }

    public static float least_destence_finder(String[][] arr_route ,int destination_city_id ,int source_city_id) {

        int r = -1;
        int z = 0;
        int guess_steps_count;
        
        for (int i = 0; i < 31; i++) {
            if(null == arr_route[i][0]) {}
            else{r++;}
        }
        if(r>=1){
        guess_steps_count = r*(((r-1)*(r-1))+1) ;
        }
        else{
        guess_steps_count = 1;
        }

        int loading_1_percent = (int)(guess_steps_count / 100f);

//....................................loader start............................
        for (int q = 0; q < 19; q++) {
            System.out.print("."); 
        }

        System.out.print("Finding for least distence");

        for (int q = 0; q < 19; q++) {
            System.out.print("."); 
        }
        System.out.println();
        System.out.print("0%");
        for (int q = 0; q < 60; q++) {
            System.out.print("_"); 
                  
        }
        System.out.println("100%");     
//..................................loader main end............................
        


        if(destination_city_id <= r && source_city_id <= r && source_city_id > 0 && destination_city_id > 0){
            
            // Level 1 distence checker

            float distance_from_table = Float.parseFloat(arr_route[source_city_id][destination_city_id]);
            

            // level 2 distence checker
            for (int m = 1; m < r+1 ; m++) {

                float distance_by_loop = Float.parseFloat(arr_route[source_city_id][m]) + Float.parseFloat(arr_route[m][destination_city_id]) ;

                z++;
                           

                if(distance_by_loop < distance_from_table){
                    distance_from_table =distance_by_loop ; 
                }                                
            }

            //level 3 distence checker
            for (int m = 1; m < r+1 ; m++) {

                for (int j = 1; j < r; j++) {
               
                    float distance_by_loop = Float.parseFloat(arr_route[source_city_id][m])+ Float.parseFloat(arr_route[m][j]) + Float.parseFloat(arr_route[j][destination_city_id]) ;

                    
                    z++;

                    if(distance_by_loop < distance_from_table){
                        distance_from_table =distance_by_loop ; 
                    }   
                }                                
            }

            //level 4 distence checker
            for (int m = 1; m < r+1 ; m++) {

                for (int j = 1; j < r; j++) {

                    for (int k = 1; k < r-1; k++) {
               
                        float distance_by_loop = Float.parseFloat(arr_route[source_city_id][m])+ Float.parseFloat(arr_route[m][j]) + Float.parseFloat(arr_route[j][k]) + Float.parseFloat(arr_route[k][destination_city_id]) ;

                        
                        z++;
                        if(distance_by_loop < distance_from_table){
                            distance_from_table =distance_by_loop ; 
                        }   
                    }   
                }                             
            }


                
        System.out.println();

        return 100.21f;
        }else{
            System.out.println("Please enter a valid City ID. You can find the correct ID in the first column of the city table displayed during the request process.");
            return -20; 
        }
    }
    

   public static void write_route_data_to_file(String[][] arr_route) {
    String filePath = "routes.txt";

    // Step 1: Write array to file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        for (int row = 0; row < arr_route.length; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < arr_route[row].length; col++) {
                String value = arr_route[row][col];
                line.append(value == null ? "null" : value);
                if (col < arr_route[row].length - 1) {
                    line.append(",");
                }
            }
            bw.write(line.toString());
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing to routes.txt: " + e.getMessage());
        return;
    }
    

}
//..........................end of methods call from city management menu .....................................



}
