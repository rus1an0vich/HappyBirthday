import java.text.ParseException;
import java.util.LinkedList;

public class ADDPersons extends Decoration{
    //Класс отвечающий за добавление нового контакта

    public ADDPersons(LinkedList<Person> persons) {
        this.persons = persons;
    }

    public LinkedList<Person> addPerson(){
        try{
            persons.add(inPerson());
        }catch (ParseException e){
        }
        return persons;
    }
}
