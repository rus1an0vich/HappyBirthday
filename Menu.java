import java.util.LinkedList;
import java.util.Scanner;

public class Menu{
    //Класс отвечающий за вывод:
    //- меню подключения бд
    //- главного меню
    //- меню выхода и сохранения бд

    public Menu(){
    }

    //меню подключения бд
    public int menuDataBaseOpen(){
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("1. Подключить локальную БД");
        System.out.println("2. Подключить другую БД");
        System.out.println("3. Не загружать данные из БД");
        System.out.print("Выберите пункт меню: ");
        while(true){
            choice = sc.nextInt();
            switch(choice){
                case 1: return choice;
                case 2: return choice;
                case 3: return choice;
                default: {
                    System.out.print("Выберите верный пункт меню: ");
                    break;
                }
            }
        }
    }

    //главное меню
    public int mainMenu(LinkedList<Person> persons){
        UncommingBirthdays uncommingBirthdays = new UncommingBirthdays(persons);
        uncommingBirthdays.Birh();
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("1. Все Дни Рождения");
        System.out.println("2. Добавить День Рождения");
        System.out.println("3. Редактировать День Рождения");
        System.out.println("4. Выход");
        System.out.print("Выберите пункт меню: ");
        while(true){
            choice = sc.nextInt();
            switch(choice){
                case 1: return choice;
                case 2: return choice;
                case 3: return choice;
                case 4: return choice;
                default: {
                    System.out.print("Выберите верный пункт меню: ");
                    break;
                }
            }
        }
    }

    //меню выхода и сохранения в бд
    public int menuExit(boolean localHost){
        Scanner sc = new Scanner(System.in);
        int choice;
        if(localHost)
            System.out.println("Загрузка произойдет в локальную БД");
        else
            System.out.println("Загрузка произойдет в другую БД");
        System.out.println("1. Сохранить данные в БД");
        System.out.println("2. Выход без сохранения");
        System.out.print("Выберите пункт меню: ");
        while(true){
            choice = sc.nextInt();
            switch(choice){
                case 1: return choice;
                case 2: return choice;
                default: {
                    System.out.print("Выберите верный пункт меню: ");
                    break;
                }
            }
        }
    }
}
