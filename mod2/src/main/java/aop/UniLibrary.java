package aop;

import aop.AbstractLibrary;
import aop.Book;
import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary {

    public void getBook(){
        System.out.println("Мы берем книгу из aop.UniLibrary");
        System.out.println("----------------------------");
    }

     public String returnBook(){

        int a = 10/0; //ArithmeticException / by zero

        System.out.println("Мы возвращаем книгу в aop.UniLibrary");
        return "Гарри Поттер и Философский камень";
    }

    public void getMagazine(){
        System.out.println("Мы берем журнал из aop.UniLibrary");
        System.out.println("----------------------------");
    }

    public void returnMagazine(){
        System.out.println("Мы возвращаем журнал в aop.UniLibrary");
        System.out.println("----------------------------");
    }

    public void addBook(String person_name, Book book){
        System.out.println("Мы добавляем книгу в aop.UniLibrary");
        System.out.println("----------------------------");
    }

    public void addMagazine(){
        System.out.println("Мы добавляем журнал в aop.UniLibrary");
        System.out.println("----------------------------");
    }
}
