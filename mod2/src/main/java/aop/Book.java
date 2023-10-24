package aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Value("Жизнь Пи")
    public String name;

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    @Value("Яна Мартеля")
    public String author;

    @Value("2001")
    public int yearOfPublication;

    public String getName() {
        return name;
    }
}
