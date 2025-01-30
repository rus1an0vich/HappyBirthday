import java.time.LocalDate;
import java.util.LinkedList;

public class UncommingBirthdays extends Decoration{
    //Класс отвечает за вывод данных о ближайших именинниках

    private final String header = "| № |                     Имя                     |                   Фамилия                   |";

    public UncommingBirthdays(LinkedList<Person> persons) {
        this.persons = persons;
    }

    //Методы Birh и UncBirthday выполняют поиск и вывод на экран ближайших именинников
    public void Birh(){
        LocalDate today = LocalDate.now();
        printHeader(header);
        UncBirthday("СЕГОДНЯ", today.toString());
        UncBirthday("ЗАВТРА", today.plusDays(1).toString());
        UncBirthday("ПОСЛЕЗАВТРА", today.plusDays(2).toString());
        System.out.println();
    }
    private void UncBirthday(String nameDay, String day){
        nameHeader(nameDay);
        int id = 1;
        for(Person person : persons){
            if(person.getBirthdayString().substring(5,10).equals(day.substring(5,10))){
                if (id<10)
                    System.out.print("| " + id + " |");
                else if(id<100)
                    System.out.print("|" + id + " |");
                else
                    System.out.print("|" + id + "|");
                System.out.print(person.getName());
                if(person.getName().length()<45)
                    printSpace(45-person.getName().length());
                System.out.print("|"+person.getSurname());
                if(person.getSurname().length()<45)
                    printSpace(45-person.getSurname().length());
                System.out.print("|");
                System.out.println();
                printLine(header.length());
                id++;
            }
        }
        if(id==1){
            nameHeader("---");
        }
    }
    //Метод nameHeader отвечает за оформление заголовков ближайших дней
    private void nameHeader(String name){
        System.out.print("|");
        printSpace(((header.length()-name.length())/2-1));
        System.out.print(name);
        if((header.length()-name.length()) % 2 == 0)
            printSpace(((header.length()-name.length())/2-1));
        else
            printSpace(((header.length()-name.length())/2));
        System.out.print("|");
        System.out.println();
        printLine(header.length());
    }
}
