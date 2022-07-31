package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("Douala");
        publisher.setState("LT");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book book = new Book("DDD","123456");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author john = new Author("John","Smith");
        Book springBook = new Book("Low code with Spring boot","3457689");
        john.getBooks().add(springBook);
        springBook.getAuthors().add(john);

        springBook.setPublisher(publisher);
        publisher.getBooks().add(springBook);

        authorRepository.save(john);
        bookRepository.save(springBook);
        publisherRepository.save(publisher);


        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of books assign to publisher: " + publisher.getBooks().size());



    }
}
