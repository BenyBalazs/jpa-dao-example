package book;

import book.model.Book;
import com.github.javafaker.Faker;

import java.time.ZoneId;

public class BookGenerator {

    private static Faker faker = new Faker();

    public static Book generateRandomBook() {
        Book book = Book.builder()
                .isbn13(faker.code().isbn13())
                .author(faker.book().author())
                .title(faker.book().title())
                .format(faker.options().option(Book.Format.class))
                .publisher(faker.book().publisher())
                .publicationDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .pages(faker.number().numberBetween(50, 400))
                .available(faker.bool().bool())
                .build();
        return book;
    }
}
