package prog01;

import java.util.ArrayList;

public class Prog {

    private final static String FIND_MAIL1 = "@mail.ru";
    private final static String FIND_MAIL2 = "@gmail.com";
    private final static String[] MAILS = {FIND_MAIL1, FIND_MAIL2};

    ArrayList<User> arr;

    public Prog(){
        arr = new ArrayList<>();
        arr.add(new User("Андрей Ярмоленко", 8, Gender.MAN, "Украина", "andrey@mail.ru"));
        arr.add(new User("Ирина Королёва", 15, Gender.WOMAN, "Россия", "lapochka@rambler.ru"));
        arr.add(new User("Дарио Сарна", 22, Gender.MAN, "Хорватия", "dario@rambler.ru"));
        arr.add(new User("Маргарита Курило", 17, Gender.WOMAN, "Белоруссия", "amargo@rambler.ru"));
        arr.add(new User("Саманта Вирджис", 23, Gender.WOMAN, "США", "sexySamanta@gmail.com"));

        arr.add(new Employee("Владислав Калитвинцев", 34, Gender.MAN, "Украина", "vlad@mail.ru", "IT", "сисадмин", 17750));
        arr.add(new Employee("Александр Караваев", 27, Gender.MAN, "Узбекистан", "alex@toop.ru", "SEO", "менеджер", 13000));
        arr.add(new Employee("Анна Лазарева", 19, Gender.WOMAN, "Украина", "anna@yandex.ru", "Бухгалтерия", "главбух", 14500));
        arr.add(new Employee("Жуниор Мораес", 26, Gender.MAN, "Бразилия", "brasilio@gmail.com", "Маргетинг", "маркетолог", 15200));
        arr.add(new Employee("Света Красавина", 18, Gender.WOMAN, "Украина", "sweta@pochta.ru", "Бухгалтерия", "бухгалтер", 11800));
    }

    public void go() {
        printList();
        System.out.println("Средний возраст: " + averageAge());
        System.out.println("Средний возраст несовершеннолетних: " + averageAge(18));
        printSpecifiedMail();
        printWomansAgeFrom18to30();
    }

    public void printList() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%4s %-40s %-11s %-10s %-17s %-30s %-20s %-20s %s \n", "","ФИО", "ПОЛ","ВОЗРАСТ","СТРАНА","EMAIL","ОТДЕЛ","ДОЛЖНОСТЬ","ОКЛАД, ГРН");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < arr.size(); i++) {
            String str = (i + 1) + ".";
            System.out.printf("%-4s %s ", str, arr.get(i));
            boolean employee = arr.get(i) instanceof Employee;
            if(!employee) {
                System.out.printf("%-20s %-20s %s", "-","-","-");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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

        for (User user : arr) {
            String mail = lastPartMail(user.getMail());
            if (mail == null) {
                continue;
            }
            for (String tmp : MAILS) {
                if(cmpStr(tmp, mail)) {
                    System.out.println("• " + user.getName() + " " + ", " + user.getMail());
                }
            }
        }
    }

    public void printWomansAgeFrom18to30() {
        System.out.println();
        System.out.println("Сотрудницы, проживающие в Украине, в возрасте от 18 до 30 лет включительно:" );
        for (User user : arr) {
            if(user instanceof Employee) {
                if((user.getAge() >= 18 && user.getAge() <= 30) && cmpStr(user.getCountry(), "Украина")) {
                    System.out.println("• " + user.getName() + " " + ", " + user.getMail());
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

    public static boolean cmpStr(String str1, String str2) {
        return (str1.compareToIgnoreCase(str2) == 0);
    }

}
