package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Startup class to run
 */
@Component
public class BootstrapData implements CommandLineRunner {
    // injecting repositories
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher1 = new Publisher("Big Bad Wolves", "413B Fernvale Link", "Singapore", "Singapore", "792413");
        publisherRepository.save(publisher1);

        Author eric = new Author("Eric", "Evans");
        Book newBook = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(newBook);
        newBook.getAuthors().add(eric);

        newBook.setPublisher(publisher1);
        publisher1.getBooks().add(newBook);

        authorRepository.save(eric);
        bookRepository.save(newBook);
        publisherRepository.save(publisher1);

        Author rod = new Author("Rod", "Johnson");
        Book rodBook = new Book("J2EE", "123457");
        rod.getBooks().add(rodBook);
        rodBook.getAuthors().add(rod);

        rodBook.setPublisher(publisher1);
        publisher1.getBooks().add(rodBook);

        authorRepository.save(rod);
        bookRepository.save(rodBook);
        publisherRepository.save(publisher1);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher1.getBooks().size());
    }
}
