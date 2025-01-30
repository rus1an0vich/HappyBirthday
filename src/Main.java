import java.util.ArrayList;
import java.util.LinkedList;

//БД для импорта на сервер лежит в основном каталоге
//По умолчанию локальный сервер:
//LocalURL = "jdbc:mysql://localhost:3306/hbdatabase";
//LocalUSERNAME = "root";
//LocalPASSWORD = "root";

public class Main extends Decoration{
    //Main класс для запуска все программы


    public static void main(String[] args) {
        Menu menu = new Menu(); //Класс Menu содержит методы вывода текстового меню
        LinkedList<Person> persons = new LinkedList<>(); //Класс Person содержит данные об 1 контакте
        ReaderDataBase reader = new ReaderDataBase(); //Класс ReaderDataBase содержит данные и методы по работе с БД
        int choice;

        //Начало приложения с возможностью загрузки данных из БД
        boolean DataBase = false;
        while (!DataBase) {
            choice = menu.menuDataBaseOpen();
            switch (choice) {
                case 1: {
                    persons = reader.connectDataBase();
                    DataBase = errDataBase(persons);
                    break;
                }
                case 2: {
                    reader = new ReaderDataBase(authorizeDataBase());
                    persons = reader.connectDataBase();
                    DataBase = errDataBase(persons);
                    break;
                }
                case 3:{
                    persons = new LinkedList<>();
                    DataBase = true;
                    break;
                }
            }
        }
        System.out.println();

        //Пробежка по главному меню
        choice = menu.mainMenu(persons);
        while (choice!=4){
            switch (choice) {
                case 1: {
                    ALLHappyBirthday happyBirthday = new ALLHappyBirthday(persons);
                    happyBirthday.getAllPersons();
                    System.out.println();
                    break;
                }
                case 2: {
                    ADDPersons addPersons = new ADDPersons(persons);
                    persons = addPersons.addPerson();
                    System.out.println();
                    break;
                }
                case 3: {
                    EDITORPersons editPersons = new EDITORPersons(persons);
                    persons = editPersons.editorPerson();
                    System.out.println();
                    break;
                }
            }
            choice = menu.mainMenu(persons);
        }
        System.out.println();

        //Выход из приложения с возможностью выгрузки данных в БД
        choice = menu.menuExit(reader.getLocalHost());
        DataBase = false;
        while (choice!=0){
            switch (choice) {
                case 1: {
                    while(!DataBase){
                        DataBase = reader.disconnectDataBase(persons);
                        if(DataBase){
                            System.out.println();
                            System.out.println("Данные сохранены");
                            break;
                        }
                        System.out.println();
                        Decoration decoration = new Decoration();
                        if(!decoration.choiceOption("Не удалось загрузить данные в БД. Повторить попытку?")){
                            DataBase = true;
                            System.out.println();
                            System.out.println("Данные не сохранены");
                        }
                    }
                    choice = 0;
                    break;
                }
                case 2: {
                    System.out.println();
                    Decoration decoration = new Decoration();
                    boolean choicebool = decoration.choiceOption("Вы точно хотите выйти без сохранения?");
                    if(choicebool){
                        choice = 0;
                        System.out.println();
                        System.out.println("Данные не сохранены");
                    }else{
                        System.out.println();
                        choice = menu.menuExit(reader.getLocalHost());
                    }
                    break;
                }
            }
        }
        System.out.println();
    }

    //Ввод данных для авторизации БД
    private static ArrayList<String> authorizeDataBase() {
        Decoration decoration = new Decoration();
        ArrayList<String> listHost = new ArrayList<>();
        listHost.add(decoration.notEmptyScanner("Введите URL адрес БД: "));
        listHost.add(decoration.notEmptyScanner("Введите логин: "));
        listHost.add(decoration.notEmptyScanner("Введите пароль: "));
        return listHost;
    }

    //Вывод сообщения об ошибке при подключении к БД
    private static boolean errDataBase(LinkedList<Person> persons) {
        if (persons == null){
            System.out.println();
            System.out.println("Не удалось подключиться к БД");
            System.out.println();
            return false;
        }else if(persons.isEmpty()){
            System.out.println();
            System.out.println("Подключение с БД установлено, таблица пустая");
            System.out.println();
            return false;
        }
        else
            return true;
    }
}