import java.util.Scanner;

public class MazeRunner {
    //Create a maze object
    public static Maze myMap = new Maze();

    public static void main(String[] args){
        intro();
        String dir = userMove();
        System.out.println(dir);

    }

    public static void intro(){
        System.out.println("Welcome to Maze Runner");
        System.out.println("Here is your current position: ");
        myMap.printMap();
    }

    public static String userMove(){
        Scanner sc = new Scanner(System.in);
        String user_dir = "";
        int moves = 0;
        while (moves < 100){
            moveMessages(moves);
            System.out.println("Where would you like to move? (R, L, U, D)");
            user_dir = sc.next();
            user_dir = user_dir.toUpperCase();
            char ch_dir = user_dir.charAt(0);

            //check if the user can move in that direction
            switch (ch_dir){
                case 'R':
                    if(myMap.canIMoveRight() == true){
                        myMap.moveRight();
                    }
                    else if (myMap.isThereAPit(user_dir)){
                        navigatePit(user_dir);
                    }
                    else {
                        System.out.println("Sorry, you've hit a wall. Please pick a new direction.");
                    }
                    myMap.printMap();
                    break;
                case 'L':
                    if(myMap.canIMoveLeft() == true){
                        myMap.moveLeft();
                    }else if (myMap.isThereAPit(user_dir)){
                        navigatePit(user_dir);
                    }
                    else {
                        System.out.println("Sorry, you've hit a wall. Please pick a new direction.");
                    }
                    myMap.printMap();
                    break;
                case 'U':
                    if (myMap.canIMoveUp() == true){
                        myMap.moveUp();
                    }
                    else if (myMap.isThereAPit(user_dir)){
                        navigatePit(user_dir);
                    }
                    else {
                        System.out.println("Sorry, you've hit a wall. Please pick a new direction.");
                    }
                    myMap.printMap();
                    break;
                case 'D':
                    if (myMap.canIMoveDown() == true){
                        myMap.moveDown();
                    }
                    else if (myMap.isThereAPit(user_dir)){
                        navigatePit(user_dir);
                    }
                    else {
                        System.out.println("Sorry, you've hit a wall. Please pick a new direction.");
                    }
                    myMap.printMap();
                    break;
                default:
                    System.out.println("Good Bye!");
                    return "Good Bye";
            }
            moves++;
            if(myMap.didIWin()){
                System.out.println("Congratulations, you made it out alive!");
                return "";
            }
        }
        if (moves == 100 && !myMap.didIWin()){
            System.out.println("Sorry, but you didn't escape in time- you lose!");
        }
        else System.out.println("Congratulations! You win! You did it in " + moves + " moves.");
        return user_dir;
    }

    public static void moveMessages(int moves){
        if(moves == 50){
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
            return;
        }
        if (moves == 75){
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
            return;
        }
        if (moves == 90){
            System.out.println("\tDANGER! You have made 90 moves, you only have 10 moves left to escape!!");
            return;
        }
        if (moves == 100){
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
        }
    }

    public static void navigatePit(String direction) {
        Scanner sc = new Scanner(System.in);

        if (myMap.isThereAPit(direction)) {
            System.out.println("Watch out! There's a pit ahead, jump it?");
            String jump = sc.next();
            if (jump.startsWith("y") || jump.startsWith("Y")){
                myMap.jumpOverPit(direction);
            }
            else return;
        }
    }

}
