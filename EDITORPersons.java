import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class EDITORPersons extends Decoration {
    //Класс создан для редактирования контактов пользователя

    public EDITORPersons(LinkedList<Person> persons) {
        this.persons = persons;
    }

    //Главный метод класса, с которого вызываются все остальные методы:
    //Сначала производиться поиск контакта
    //Если контакт существует, то вносим корректировки
    //Если контакт не существует, то можно начать поиск заново или выйти
    public LinkedList<Person> editorPerson() {
        Person person = searchPerson();
        if (person == null) {
            return persons;
        }else{
            changerPerson(person);
            return persons;
        }
    }

    //Поиск контакта по Имени и Фамилии
    private Person searchPerson() {
        boolean found = true;
        while(found){
            String name = notEmptyScanner("Введите имя: ");
            String surname = notEmptyScanner("Введите фамилию: ");
            for (Person person : persons) {
                if (person.getName().equals(name) && person.getSurname().equals(surname))
                    return person;
            }
            found = choiceOption("Такого контакта не существует. Продолжить поиск?");
        }
        return null;
    }

    //Работа с контактом после поиска
    private void changerPerson(Person person) {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean found = true;
        System.out.println("1. Редактировать контакт");
        System.out.println("2. Удалить контакт");
        System.out.print("Выберите пункт меню: ");
        while(found) {
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println();
                    chaPerson(person);
                    found = false;
                    break;
                }
                case 2: {
                    System.out.println();
                    delPerson(person);
                    found = false;
                    break;
                }
                default: {
                    System.out.print("Выберите верный пункт меню: ");
                    break;
                }
            }
        }
    }

    //Удаление контакта
    private void delPerson(Person p){
        boolean found;
        found = choiceOption("Вы точно хотите удалить контакт?");
        if (found) {
            persons.remove(p);
            System.out.println("Контакт удален");
        }
    }

    //Изменение контакта
    private void chaPerson(Person p) {
        System.out.println("Введите новые данные контакта");
        try{
            persons.add(inPerson());
        }catch(ParseException e){
        }
        persons.remove(p);
        System.out.println("Контакт изменен");
    }
}
