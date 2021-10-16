package game_snake;

import java.util.Scanner;

public class Game {

    private final GameField gameField;
    private final Snake snake;
    private final Scanner sc;
    private  String command;

    private boolean endGame;
    private boolean commandOk;

    public Game() {
        gameField = new GameField();
        snake = new Snake();
        sc = new Scanner(System.in);
    }

    //====== ОСНОВНОЙ МЕТОД ==================================================================
    public void go(){
        printOnStart();
        do {
            gameField.action(snake);
            printCommand();
            do {
                inputCommand();
                processCommand();
            }while (!commandOk);

        }while(!endGame);

        printOnEnd();
    }
    //======================================================================================

    //список команд
    private void printCommand() {
        System.out.printf("%s,%s,%s,%s - направление   %s - выход \n\n",  Command.UP.getKey(),
                                                                        Command.DOWN.getKey(),
                                                                        Command.LEFT.getKey(),
                                                                        Command.RIGHT.getKey(),
                                                                        Command.END.getKey()
                                                                    );
    }

    //ввод команд
    private void inputCommand() {
        System.out.print("Введите команду: ");
        command = sc.next();
    }

    //обработка введенных команд
    private void processCommand()
    {
        commandOk = true;
        if(command.equalsIgnoreCase(Command.END.getKey())) {
            endGame = true;
        }
        else if(command.equalsIgnoreCase(Command.UP.getKey())) {
            int[] coordinate = Command.UP.getArr();
            moveSnake(coordinate[0], coordinate[1]);
        }
        else if(command.equalsIgnoreCase(Command.DOWN.getKey())) {
            int[] coordinate = Command.DOWN.getArr();
            moveSnake(coordinate[0], coordinate[1]);

        }
        else if(command.equalsIgnoreCase(Command.LEFT.getKey())) {
            int[] coordinate = Command.LEFT.getArr();
            moveSnake(coordinate[0], coordinate[1]);

        }
        else if(command.equalsIgnoreCase(Command.RIGHT.getKey())) {
            int[] coordinate = Command.RIGHT.getArr();
            moveSnake(coordinate[0], coordinate[1]);
        }
        else {
            System.out.println("неизвестная команда");
            System.out.println();
            commandOk = false;
        }
    }

    //двигаем змейку
    private void moveSnake(int addX, int addY)
    {
        int newX = snake.getLastPoint().getX() + addX;
        int newY = snake.getLastPoint().getY() + addY;

        if(snake.isBitMyself(newX, newY)) {
            checkDeath(Const.CODE_EAT_MYSELF);
            return;
        }

        int code = gameField.checkCell(newX, newY);

        if(code == Const.CODE_CRASH || code == Const.CODE_EAT_STONE) {
            checkDeath(code);
            return;
        }

        snake.addPoint(new Point(newX,newY));

        if(code != Const.CODE_EAT_APPLE) {
            snake.delFirstPoint();
        }
    }

    //змея скончалась
    private void checkDeath(int code){
        String message = "";
        if(code == Const.CODE_CRASH) {
            message = "Змейка врезалась в стену!";
        }
        else if(code == Const.CODE_EAT_STONE) {
            message = "Змейка съела камень и подавилась!";
        }
        else if(code == Const.CODE_EAT_MYSELF) {
            message = "Змейка укусила сама себя!";
        }
        Color.printlnColorRed(message);
        endGame = true;
    }

    private void printOnStart() {
        System.out.printf("%s %s \n",Const.NAME_GAME, Const.VERSION);
        System.out.println("~~~~~~o<");
    }

    private void printOnEnd() {
        Color.printlnColorYellow("GAME OVER");
        System.out.println();
        System.out.println(Const.COPYRIGHT);
        System.out.println(Const.AUTHOR);
        System.out.println();
        System.out.println(Const.GIT_URL);
    }
}
