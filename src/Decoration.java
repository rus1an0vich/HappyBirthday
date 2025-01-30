import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Decoration {
    //По большей степени класс выполняет косметические функции
    //Так же хранит переменны, которые используют его дети
    //Дети: ADDPersons, ALLHappyBirthday, EDITORPersons, UncommingBirthdays
    protected LinkedList<Person> persons = new LinkedList<>();

    public Decoration() {
    }

    //Метод для печати шапок таблиц
    protected void printHeader(String header) {
        printLine(header.length());
        System.out.println(header);
        printLine(header.length());
    }

    //Метод для печати разделения строк таблиц
    protected void printLine(int line){
        for(int i = 0; i < line; i++){
            System.out.print("_");
        }
        System.out.println();
    }

    //Метод для печати пробелов в таблицах
    protected void printSpace(int space){
        for(int i = 0; i < space; i++){
            System.out.print(" ");
        }
    }

    //Метод для считывания любого значения с клавиатуры
    protected void printSomething(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Нажмите Enter");
        String name = scanner.nextLine();
    }

    //Метод для считывания не пустой строки с клавиатуры
    public String notEmptyScanner(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        String text = scanner.nextLine();
        while (text.equals("")) {
            System.out.print(s);
            text = scanner.nextLine();
        }
        return text;
    }

    //Метод для вывода вопросов, на которые требуется ответ да или нет
    protected boolean choiceOption(String option) {
        System.out.println(option);
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Выберите пункт меню: ");
        Scanner scanner = new Scanner(System.in);
        while(true){
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:{
                    return true;
                }
                case 2:{
                    return false;
                }
                default:{
                    System.out.print("Выберите верный пункт меню: ");
                    break;
                }
            }
        }
    }

    //Методы inPerson и HasDateofBirth созданы для корректного ввода данных Имени, Фамилии и Даты рождения с клавиатуры
    protected Person inPerson() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String name = notEmptyScanner("Введите имя: ");
        String surname = notEmptyScanner("Введите фамилию: ");
        return new Person(name, surname, HasDateofBirth());
    }
    private Date HasDateofBirth() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date date = null;
        while (date == null) {
            System.out.print("Введите дату рождения (дд/мм/гггг): ");
            String input = scanner.nextLine();
            try {
                date = dateFormat.parse(input);
                date.setYear(date.getYear() + 1900);
            } catch (ParseException e) {
                System.out.print("Введите верную дату рождения (дд/мм/гггг): ");
            }
        }
        return date;
    }
}
