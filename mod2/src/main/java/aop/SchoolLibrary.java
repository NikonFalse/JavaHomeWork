package aop;

import aop.AbstractLibrary;
import org.springframework.stereotype.Component;

@Component
public class SchoolLibrary extends AbstractLibrary {

//    @Override
    public void getBook(){
        System.out.println("Мы берём книгу из aop.SchoolLibrary");
    }
}
