
import java.util.LinkedList;

public class ALLHappyBirthday extends Decoration{
    //Класс отвечающий за вывод всех контактов на экран
    private final String header = "| № |                     Имя                     |                   Фамилия                   |Дата Рождения|";

    public ALLHappyBirthday(LinkedList<Person> persons) {
        this.persons = persons;
    }

    public void getAllPersons() {
        printHeader(header);
        int id = 1;
        for (Person person : persons) {
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
            System.out.print("|"+person.getBirthdayStringDDMMYYYY()+"   |");
            System.out.println();
            printLine(header.length());
            id++;
        }
        printSomething();
    }
}
