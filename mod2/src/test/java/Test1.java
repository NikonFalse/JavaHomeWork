import aop.Book;
import aop.MyConfig;
import aop.UniLibrary;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        UniLibrary uniLibrary = context.getBean("uniLibrary", UniLibrary.class);
        Book book = context.getBean("book", Book.class);
        uniLibrary.getBook();
        uniLibrary.addBook("Ivan", book);
        uniLibrary.addMagazine();

//        uniLibrary.getMagazine();

//        uniLibrary.returnMagazine();
//        uniLibrary.addBook();

//        uniLibrary.returnBook();
//
//        aop.SchoolLibrary schoolLibrary = context.getBean("schoolLibrary", aop.SchoolLibrary.class);
//        schoolLibrary.getBook();

        context.close();
    }
}
