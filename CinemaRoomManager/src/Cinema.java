import java.util.Scanner;

/**
 * @author : Sergiu Ivanov
 * @project : Cinema Room Manager
 */
public class Cinema {

    static void printMenu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    static void showTheSeats(String[][] arr){
        System.out.println("\nCinema:");
        for (int i = 0; i < arr.length; i++) { //matrix' row
            for (int j = 0; j < arr[i].length; j++) { //column of each row
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); //new line when reach the end of nested loop
        }
        System.out.println();
    }

    static void buyTicket(String[][] arr, int chosenRow, int chosenSeat, int ticketPrice){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == chosenRow && j == chosenSeat ){
                    if (arr[chosenRow ][chosenSeat ] == "B"){
                        System.out.println("That ticket has already been purchased!\n");
                    }else {
                        System.out.println("\nTicket price: $" + ticketPrice);
                        arr[chosenRow ][chosenSeat ] = "B";
                    }
                }
            }
        }
    }

    static int purchasedTickets(String[][] arr){
        int numOfTickets = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == "B"){
                    numOfTickets ++;
                }
            }
        }
        return numOfTickets;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        System.out.println();
        String[][] arr = new String[rows +1][seats + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length ; j++) {
                if ((i == 0) && (j == 0)){
                    arr[i][j] = " ";
                }else if (i == 0){
                    arr[i][j] = String.valueOf(j);
                }else if (j == 0){
                    arr[i][j] = String.valueOf(i);
                }else{
                    arr[i][j] = "S";
                }
            }
        }

        printMenu();

        boolean askAgain = true;
        while (askAgain){
            int option = scanner.nextInt();
            switch (option){
                case 0:
                    askAgain = false;
                    break;
                case 1:
                    showTheSeats(arr);
                    printMenu();
                    break;
                case 2:
                    int total = rows * seats;
                    System.out.println("\nEnter a row number:");
                    int chosenRow = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int chosenSeat = scanner.nextInt();
                    int ticketPrice = 0;
                    if (total <= 60){
                        ticketPrice = 10;
                    }else {
                        ticketPrice = chosenRow <= rows/2 ? 10 : 8;
                    }
                    buyTicket(arr, chosenRow, chosenSeat, ticketPrice);
                    printMenu();
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: " + purchasedTickets(arr) + "\n");
                    printMenu();
                    break;
                default:
                    askAgain = false;
                    System.out.println("Wrong input!\n");
                    break;
            }
        }
    }
}