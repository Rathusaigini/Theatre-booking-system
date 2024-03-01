
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Theatre {   //Define the class method
    static Scanner input = new Scanner(System.in);
    //initialize 3 arrays, one for each row
    static int[] row1 = new int[12]; //row 1 has 12 seats
    static int[] row2 = new int[16];//row 2 has 16 seats
    static int[] row3 = new int[20];//row 3 has 20 seats
    static ArrayList<Ticket>tickets=new ArrayList<>();
    //main method is executed when the program is run
    public static void main(String[] args) {
            while (true) {//creating while loop
                try {
                    System.out.println("\n");
                    System.out.println("Welcome to Our New Theatre");//Display welcome message
                    System.out.println("MENU");                       //adding menu in main method part A-Task 2
                    System.out.println("________________________________________________________________________");
                    System.out.println("1) Buy a ticket");
                    System.out.println("2) Print seating area");
                    System.out.println("3) Cancel ticket");
                    System.out.println("4) List available seats");
                    System.out.println("5) Save to file");
                    System.out.println("6) Load from file");
                    System.out.println("7) Print ticket information and total price");
                    System.out.println("8) Sort tickets by price");
                    System.out.println("0) Quit");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Enter option: "); //prompt the user to enter an option
                    int option = input.nextInt();            //get the user's input as an integer
                    switch (option) {                 //use a switch statement to determine which function to call based on the user's input
                        case 1:
                            buy_ticket(); // call method call for buy ticket,if the user choose option 1
                            break;
                        case 2: {
                            System.out.println("           ***********");
                            System.out.println("           *  STAGE  *");
                            System.out.println("           ***********");
                            print_seating_area();      //call method call for print seating area,if the user choose option 2
                        }
                        break;
                        case 3:
                            cancel_ticket();                          //call method call for cancel ticket,if the user choose option 3
                            break;
                        case 4:
                            show_available();                          //call method call for show available,if the user choose option 4
                            break;
                        case 5: {
                            Save_to_file();                            //call method call for save to file,if the user choose option 5
                            System.out.println("Successfully Done!!!");
                        }
                        break;
                        case 6:
                            Load_from_file();                           ///call method call for load from file,if the user choose option 6
                            break;
                        case 7:
                            show_tickets_info();                      //call method call for show ticket info,if the user choose option 7
                        case 8:
                            sort_tickets(tickets);                  //call method call for sort ticket,if the user choose option 8
                            break;
                        case 0: {
                            System.out.println("Quit");            //call method call for quit,if the user choose option 0
                        }
                        System.exit(0);
                        break;
                        default:  //if the user input an invalid option,print an error message
                            System.out.print("Please enter a valid option.");
                    }
                }catch (Exception e){//catch any exception thrown by the code in the try block and print "The input is invalid"
                    System.out.println("The input is Invalid!!!");
                    input.nextLine();
            }

        }
    }
    public static void buy_ticket() {
            System.out.print("please enter your name: ");  //prompt for the ticket name
            String name = input.next();
            System.out.print("please enter your surname: ");//prompt for the ticket surname
            String surname = input.next();
            System.out.print("please enter your email: ");//prompt for the ticket gmail
            String email = input.next();
            //initialize variable for the ticket's price,row num,and seat num
            int price = 0;
            int row= 0;
            int seat = 0;
        while (true) {
            try {
                System.out.print("Enter Row number:");
                row = input.nextInt();

                if (0 < row && row < 4) {
                    System.out.println("Enter the seat no: ");
                    seat = input.nextInt();
                }
                if (row == 1) {     //if the user input row 1
                    if (0 < seat && seat <= 12) {        //seat number is within valid range for row 1
                        if (row1[seat - 1] == 1) {       //if seat is already booked,print message and exit loop
                            System.out.println("Sorry......Seat was already booked!!!");
                            break;                  //exit loop
                        } else {                   // booking the seat and set the price
                            row1[seat - 1] = 1;
                            System.out.println("Seat successfully booked!!!");
                            price = 100; //ticket price is 10 for row 1
                            break;              //exit loop
                        }
                    } else {
                        System.out.println("Invalid Seat No !!!");
                    }
                } else if (row == 2) { //if the user input row 2
                    if (0 < seat && seat <= 16) { //seat number is within valid range for row 2
                        if (row2[seat - 1] == 1) {//if seat is already booked,print message and exit loop
                            System.out.println("Sorry.....Seat was  booked already!!!");
                            break;
                        } else { // booking the seat and set the price
                            row2[seat - 1] = 1;
                            System.out.println("Seat  successfully booked!!!");
                            price = 200; //ticket price is 10 for row 2
                            break;
                        }
                    } else {
                        System.out.println("### Invalid Seat No ###");
                    }
                } else if (row == 3) { //if the user input row 3
                    if (0 < seat && seat <= 20) {  //seat number is within valid range for row 3
                        if (row3[seat - 1] == 1) {//if seat is already booked,print message and exit loop
                            System.out.println("Sorry.....Seat was already booked!!!");
                            break;
                        } else { // booking the seat and set the price
                            row3[seat - 1] = 1;
                            System.out.println("Seat successfully booked!!!");
                            price = 300; //ticket price is 10 for row 3
                            break;
                        }
                    }
                } else {
                    System.out.println(" Invalid Row Number!!!,Please select 1-3.");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input");
                input.nextLine();
            }
        }
        Person person = new Person(name, surname, email); // create a new person with name,surname,email
        Ticket ticket = new Ticket(person, row, seat, price);  //using that person object to create a new ticket object with row num,seat num and price
        tickets.add(ticket);//add the ticket to a list of tickets
    }
    public static void print_seating_area() {
        System.out.print("          ");
        for (int i = 0; i < row1.length; i++) {
            if (row1.length / 2 == i) {
                System.out.print(" "); //add a space in the middle of the row for better visualization
            }
            if (row1[i] == 0) {
                System.out.print("O"); //print  0 to represent an available seats,if the current element in the array is "0"
            } else {
                System.out.print("X");//print  0 to represent a booked seats,if the current element in the array is "x"
            }
        }
        System.out.println("\n");
        System.out.print("        ");
        for (int i = 0; i < row2.length; i++) {
            if ( row2.length / 2 == i) {
                System.out.print(" "); //add a space in the middle of the row for better visualization
            }
            if (row2[i] == 0) {
                System.out.print("O");//print  0 to represent an available seats,if the current element in the array is "0"
            } else {
                System.out.print("X");//print  0 to represent a booked seats,if the current element in the array is "x"
            }
        }
        System.out.println("\n ");
        System.out.print("      ");
        for (int i = 0; i < row3.length; i++) {
            if (row3.length / 2 == i) {
                System.out.print(" "); //add a space in the middle of the row for better visualization
            }
            if (row3[i] == 0) {
                System.out.print("O");//print  0 to represent an available seats,if the current element in the array is "0"
            } else {
                System.out.print("X");//print  0 to represent a booked seats,if the current element in the array is "x"
            }
        }
        System.out.println("\n");
    }
    public static void cancel_ticket() {
        while (true) {
            try{
                System.out.print("Enter the row number you want to cancel your reservation :"); //ask the user to enter the seat number of the seat they want to cancel
                int row = input.nextInt();
                if (0 < row && row < 4) {
                    System.out.println("Enter the seat number you want to cancel your reservation :");
                    int seat = input.nextInt();
                    if (row == 1) {
                        if (0 < seat && seat < 12) {  //check if seat num is valid
                            if (row1[seat - 1] == 1) { //check if cheat is already booked,cancel the reservation and remove it from the ticket
                                row1[seat - 1] = 0;  //if the user already booked,cancel the reservation and remove it from the ticket
                                System.out.println("Seat Reservation Cancelled!!!");
                                for(Ticket x : tickets){ //loop through the ticket list to find the ticket with the specified row and seat num
                                    if(x.row == row && x.seat == seat){
                                        tickets.remove(x);//remove the ticket from the list
                                        break;
                                    }
                                }
                                break;
                            } else {   //if the seat is not already booked,inform the user and exit the while loop
                                System.out.println("There was no reservation for this seat!!!");
                                break;
                            }
                        } else {
                            System.out.println("Invalid seat number!!!"); //if the seat num is invalid,inform
                        }
                    } else if (row == 2) {
                        if (0 < seat && seat < 16) {//check if seat num is valid
                            if (row2[seat - 1] == 1) { //check if cheat is already booked,cancel the reservation and remove it from the ticket
                                row2[seat - 1] = 0;  //if the user already booked,cancel the reservation and remove it from the ticket
                                System.out.println("Seat Reservation Cancelled!!!");
                                for(Ticket x : tickets) { //loop through the ticket list to find the ticket with the specified row and seat num
                                    if (x.row == row && x.seat == seat) {
                                        tickets.remove(x); //remove the ticket from the list
                                        break;
                                    }
                                }
                                break;
                            } else {
                                System.out.println("There was no reservation for this seat!!!");
                                break;
                            }
                        } else {
                            System.out.println("Invalid Seat number!!!");
                        }
                    } else if (row == 3) {
                        if (0 < seat && seat < 20) { //check if seat num is valid
                            if (row3[seat - 1] == 1) { //check if cheat is already booked,cancel the reservation and remove it from the ticket
                                row3[seat - 1] = 0;  //check if cheat is already booked,cancel the reservation and remove it from the ticket
                                System.out.println("Seat Reservation Cancelled!!!");
                                for(Ticket x : tickets) { //loop through the ticket list to find the ticket with the specified row and seat num
                                    if (x.row == row && x.seat == seat) {
                                        tickets.remove(x); //remove the ticket from the list
                                        break;
                                    }
                                }
                                break;
                            } else {
                                System.out.println("There was no reservation for this seat!!!");
                                break;
                            }
                        } else {
                            System.out.println("Invalid Seat Number!!!");
                        }
                    }
                }
                else{
                    System.out.println("invalid row number!!!");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input");
                input.nextLine();
            }
        }
        System.out.println("\n ");
    }
    public static void show_available() {
        System.out.print("Seats available in a row 1: ");
        for (int i = 0; i < row1.length; i++) {   //loop through seat  in row 1
            if (row1[i] == 1) {  //if the seat is already taken,skip to the next iteration
                continue;
            } else {  //print the seat num(i+1)and a comma
                System.out.print(i + 1 + ",");
            }
        }
        System.out.println();

        System.out.print("Seats available in a row 2: ");
        for (int i = 0; i < row2.length; i++) { //loop through seat in row 2
            if (row2[i] == 1) { //if the seat is already taken,skip to the next iteration
                continue;
            } else { //print the seat num(i+1)and a comma
                System.out.print(i + 1 + ",");
            }
        }
        System.out.println();

        System.out.print("Seats available in a row 3: ");
        for (int i = 0; i < row2.length; i++) { //loop through seat  in row 3
            if (row3[i] == 1) { //if the seat is already taken,skip to the next iteration
                continue;
            } else { //print the seat num(i+1)and a comma
                System.out.print(i + 1 + ",");
            }
        }
        System.out.println();
    }
    public static void Save_to_file() {  //this method saves the current seat status to a file
        try {
            FileWriter writer = new FileWriter("seats.txt");//create a new file writer to write to the file
;            for (int i = 0; i < row1.length; i++) {  //iterate over each seat in the  row1
                if (row1[i] == 1) {
                    writer.append("1");  //if seat is reserved append a "1" to the file
                } else {
                    writer.append("0");//if seat is available append a "0" to the file
                }
            }
            writer.append("\n");
            for (int i = 0; i < row2.length; i++) { //iterate over each seat in the  row2
                if (row2[i] == 1) {
                    writer.append("1"); //if seat is reserved append a "1" to the file
                } else {
                    writer.append("0"); //if seat is available append a "0" to the file
                }
            }

            writer.append("\n");
            for (int i = 0; i < row3.length; i++) { //iterate over each seat in the  row3
                if (row3[i] == 1) {
                    writer.append("1"); //if seat is reserved append a "1" to the file
                } else {
                    writer.append("0"); //if seat is available append a "0" to the file
                }
            }
            writer.close();
        } catch (IOException e) { //if an exception occur ,print the error message
            throw new RuntimeException(e);
        }
    }
    public static void Load_from_file() {    //the method loads seats data from a text file
        File read= new File("seats.txt");
        try {
            Scanner f_read = new Scanner(read);  //create a new scanner to read from the text file
            while (f_read.hasNext()) {      //read the next line from the file
                String seat=f_read.nextLine();   //loops through each line of the file until there is no more data
                if (seat.length()==12) {   //if the line 12 characters long,it represents thr row1 of seats
                    for (int i = 0; i < row1.length ; i++) {  //iterate through each character in the line and seat the corresponding seat status
                        row1[i] = Character.getNumericValue(seat.charAt(1));
                    }
                } else if (seat.length()==16) { //if the line 16 characters long,it represents thr row1 of seats
                    for (int i = 0; i < row2.length ; i++) { //iterate through each character in the line and seat the corresponding seat status
                        row2[i]= Character.getNumericValue(seat.charAt(1));
                    }
                } else if (seat.length()==20) { //if the line 20 characters long,it represents thr row1 of seats
                    for (int i = 0; i < row3.length ; i++) { //iterate through each character in the line and seat the corresponding seat status
                        row3[i] = Character.getNumericValue(seat.charAt(1));
                    }
                }
            }
            f_read.close();
            System.out.println("Load From File:");
            System.out.println("Successfully file loaded....!!!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void show_tickets_info() {
        int total=0; //initialize variable
        for (Ticket i : tickets) {
            i.print();
            total += i.price;
        }
        System.out.println("Total Price:"+total);
        System.out.println("\n");
    }
    public static void sort_tickets(ArrayList<Ticket> j){
         int r=1; //initialize variable
         while (r<=tickets.size()){
            for (int i=1;i<=tickets.size() - 1;i++){
                //compare the elements
                    Ticket first =(Ticket) tickets.get(i-1);
                    Ticket second=(Ticket) tickets.get(i);
                    if (first.price>second.price){
                        //swap the elements
                    tickets.set( i-1,second);
                    tickets.set(1,first);
                }
            }
            r++;
        }
         show_tickets_info();
        }
}
