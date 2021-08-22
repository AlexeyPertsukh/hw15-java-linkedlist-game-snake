package prog02;

import java.util.Scanner;

public class Prog {
    private final static String FIND_MAIL1 = "@mail.ru";
    private final static String FIND_MAIL2 = "@gmail.com";
    private final static String[] MAILS = {FIND_MAIL1, FIND_MAIL2};
    public static final int PAUSE = 2500;

    private Storage<User> arr;
    private final Scanner sc;
    boolean endProg;

    public Prog() {
        initArr();
        sc = new Scanner(System.in);
    }

    public void go() {
        printPeople();
        do {
            printCommands();
            inputCommand();
        } while(!endProg);
    }

    public void printAverageAge() {
        System.out.println();
        System.out.println("Средний возраст: " + averageAge());
        System.out.println("Средний возраст несовершеннолетних: " + averageAge(18));
    }


    public void initArr() {
        arr = new Storage();
        arr.add(new User("Андрей Ярмоленко", 8, Gender.MAN, "Украина", "andrey@mail.ru"));
        arr.add(new User("Ирина Королёва", 15, Gender.WOMAN, "Россия", "lapochka@rambler.ru"));
        arr.add(new User("Дарио Сарна", 22, Gender.MAN, "Хорватия", "dario@rambler.ru"));
        arr.add(new User("Маргарита Курило", 17, Gender.WOMAN, "Белоруссия", "amargo@rambler.ru"));
        arr.add(new User("СамантаВирджис", 23, Gender.WOMAN, "США", "sexySamanta@gmail.com"));

        arr.add(new Employee("Владислав Калитвинцев", 34, Gender.MAN, "Украина", "vlad@mail.ru", "IT", "сисадмин", 17750));
        arr.add(new Employee("Александр Караваев", 27, Gender.MAN, "Узбекистан", "alex@toop.ru", "SEO", "менеджер", 13000));
        arr.add(new Employee("Анна Лазарева", 19, Gender.WOMAN, "Украина", "anna@yandex.ru", "Бухгалтерия", "главбух", 14500));
        arr.add(new Employee("Жуниор Мораес", 26, Gender.MAN, "Бразилия", "brasilio@gmail.com", "Маргетинг", "маркетолог", 15200));
        arr.add(new Employee("Света Красавина", 18, Gender.WOMAN, "Украина", "sweta@pochta.ru", "Бухгалтерия", "бухгалтер", 11800));
    }

    public void printPeople() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%4s %-40s %-11s %-10s %-17s %-30s %-20s %-20s %s \n", "","ФИО", "ПОЛ","ВОЗРАСТ","СТРАНА","EMAIL","ОТДЕЛ","ДОЛЖНОСТЬ","ОКЛАД, ГРН");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < arr.size(); i++) {
            String str = (i + 1) + ".";
            System.out.printf("%-4s %s ", str, arr.get(i));
            boolean employee = arr.get(i) instanceof  Employee;
            if(!employee) {
                System.out.printf("%-20s %-20s %s", "-","-","-");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public int averageAge(int max) {
        int sum = 0;
        int num = 0;

        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getAge() < max) {
                sum += arr.get(i).getAge();
                num++;
            }

        }
        if (num == 0) {
            return 0;
        }
        return (sum / num);
    }

    public int averageAge() {
        return averageAge(Integer.MAX_VALUE);
    }

    public void printSpecifiedMail() {
        String str = "";
        System.out.println();
        System.out.print("Люди с почтой" );
        for (String mail : MAILS) {
            str += " " + mail +",";
        }
        str = str.substring(0, str.length()-1) + ":";
        System.out.println(str);

        for (int i = 0; i < arr.size(); i++) {
            User user = arr.get(i);
            String mail = lastPartMail(user.getMail());
            if (mail == null) {
                continue;
            }
            for (String tmp : MAILS) {
                if(My.cmpStr(tmp, mail)) {
                    System.out.println("• " + user.getName()  + ", " + user.getMail());
                }
            }
        }
    }

    public void printWomansAgeFrom18to30() {
        System.out.println();
        System.out.println("Сотрудницы, проживающие в Украине, в возрасте от 18 до 30 лет включительно:" );

        for (int i = 0; i < arr.size(); i++) {
            User user = arr.get(i);
            if(user instanceof  Employee) {
                if((user.getAge() >= 18 && user.getAge() <= 30) && My.cmpStr(user.getCountry(), "Украина")) {
                    System.out.println("• " + user.getName() + ", " + user.getMail());
                }
            }

        }
    }

    private String lastPartMail(String mail) {
        int num = -1;
        for (int i = 0; i < mail.length(); i++) {
            if(mail.charAt(i) == '@') {
                num = i;
                break;
            }
        }
        if(num == -1) {
            return null;
        }
        else {
            return mail.substring(num);
        }
    }


    public void printCommands() {
        //варианты команд
        System.out.println();
        for (Command cmd : Command.values()) {
            String addition = "";
            if(cmd == Command.PRINT_MAILS) {
                for (String str :MAILS) {
                    addition += " " + str +",";
                }
                if(addition.length() > 0) {
                    addition = addition.substring(0, addition.length() - 1);
                }
            }
            System.out.printf("%s - %s%s \n",cmd.getKey(), cmd.getNameRus(), addition);
        }
        System.out.println("-----------------------");
        //ввод команд
        System.out.print("Введите команду: ");
    }

    public void inputCommand() {
        String cmd = sc.next();
        if(My.cmpStr(cmd, Command.END.getKey())) {
            endProg = true;
        }
        else if(My.cmpStr(cmd, Command.PRINT_PEOPLE.getKey())) {
            printPeople();
        }
        else if(My.cmpStr(cmd, Command.PRINT_AVERAGE_AGE.getKey())) {
            printAverageAge();
        }
        else if(My.cmpStr(cmd, Command.PRINT_MAILS.getKey())) {
            printSpecifiedMail();
        }
        else if(My.cmpStr(cmd, Command.PRINT_WANT_WOMAN.getKey())) {
            printWomansAgeFrom18to30();
        }
        else if(My.cmpStr(cmd, Command.ADD.getKey())) {
            add();
            My.sleepAnimationLn(PAUSE);
//            printPeople();
        }
        else if(My.cmpStr(cmd, Command.DEL.getKey())) {
            if(del()) {
                System.out.printf("Осталось записей: %d \n", arr.size());
//                printPeople();
            }
            My.sleepAnimationLn(PAUSE);
        }
        //Неизвестная команда
        else{
            System.out.println("Неизвестная команда");
        }
    }

    //добавить человека или сотрудника
    private void add() {
        String name;
        int age;
        char chGender;
        Gender gender;
        String country;
        String mail;

        String department;
        String rank;
        double  salary;

        System.out.println();

        System.out.print("ФИО: ");
        name = sc.next();

        chGender = My.nextCharLowerCase(sc, "Пол (M - мужчина, F - женщина): ", 'M', 'м','F');  // русская и английская М

        gender = (chGender == 'm' || chGender == 'м') ? Gender.MAN : Gender.WOMAN;

        age = My.nextInt(sc, "Возраст (1-130 лет): ", 1, 130);

        System.out.print("Страна: ");
        country = sc.next();

        System.out.print("E-MAIL: ");
        mail = sc.next();
        char chStateEmploye =  My.nextCharLowerCase(sc, "Это сотрудник? (Y - да, N - нет): ",'Y','N');
        //сотрудник?
        if(chStateEmploye == 'y') {
            System.out.print("Отдел: ");
            department = sc.next();

            System.out.print("Должность: ");
            rank = sc.next();

            salary = My.nextDouble(sc,"Введите оклад: ",1, Double.MAX_VALUE);

            arr.add( new Employee(name, age, gender, country, mail, department, rank, salary));

        }
        else {
            arr.add( new User(name, age, gender, country, mail));
        }
        System.out.printf("Добавлено: %s \n", name );

    }

    private boolean del() {
        System.out.println();
        int index = My.nextInt(sc, "Введите номер записи для удаления: ");
        index--;
        User delUser = arr.remove(index);
        if( delUser == null) {
            System.out.println("Вы ввели неправильный номер записи для удаления");
            return false;
        }
        else {
            System.out.printf("Запись #%d %s успешно удалена \n", index + 1, delUser.getName());
            return true;
        }

    }


}
