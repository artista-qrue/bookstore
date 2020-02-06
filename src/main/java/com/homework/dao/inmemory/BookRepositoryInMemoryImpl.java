package com.homework.dao.inmemory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.homework.dao.BookRepository;
import com.homework.model.Book;
import com.homework.model.Price;

@Repository /*
			 * spring continer bu sınıftan çalışma zamanında bir bean yaratacak. ve bu
			 * bean'i çalışma zamamında service beani icersine enjecte edecek
			 */
public class BookRepositoryInMemoryImpl implements BookRepository {

	//
	private Map<Long, Book> booksMap = new HashMap<>();
    CategoryInMemoryImpl cimi = new CategoryInMemoryImpl();
    PriceRepositoryInMemoryImpl prc = new PriceRepositoryInMemoryImpl();
	/*
	 * örnek nesneleri yaratacağımız bir no-arc const oluşturduk ve oluşturduğum
	 * nesneleri bir map icersine key value olarak ekleyerek test amaclı bir veri
	 * elde ediyorum.Bundaki sebep bir db kullanmamam ve db olmamasında inmemory de
	 * verileri tutmak
	 */
	public BookRepositoryInMemoryImpl()  {
		
		Price prc = new Price(3);
		Price prc2 = new Price(4);
		Price prc3 = new Price(5);
		Price prc4 = new Price(6);
		
		Book book = new Book(1L,"My name is Red",prc,cimi.getCategory("Fantasy"));
		
//		Book book5 =(Book) book.clone();
//		book5.getPrice().setPrice(5);
		
		Book book1 = new Book();
		book1.setId(2L);
		book1.setName("Lord of the Ring");
		book1.setCategory(cimi.getCategory("Fantasy"));
		book1.setPrice(prc4);

		Book book2 = new Book();
		book2.setId(3L);
		book2.setName("Bleach");
		book2.setCategory(cimi.getCategory("Fantasy"));
		book2.setPrice(prc3);

	
		Book book3 = new Book();
		book3.setId(4L);
		book3.setName("Danzo");
		book3.setCategory(cimi.getCategory("Fantasy"));
		book3.setPrice(prc2);
		
		Book book4 = new Book();
		book4.setId(5L);
		book4.setName("Great planet");
		book4.setCategory(cimi.getCategory("Fantasy"));
		book4.setPrice(prc3);
	

		booksMap.put(book.getId(), book);
		booksMap.put(book1.getId(), book1);
		booksMap.put(book2.getId(), book2);
		booksMap.put(book3.getId(), book3);
		booksMap.put(book4.getId(), book4);
		//booksMap.put(book5.getId(), book5);

	}

	@Override
	public List<Book> getAllBooks() {

		return new ArrayList<Book>(booksMap.values());
	}

	@Override
	public void create(Book book) {

		book.setId(new Date().getTime());
		booksMap.put(book.getId(), book);
	}
	
	/*
	 * Verilen case'lerin icerisinde price değerinin bookstory'e göre değişeceği bildiriliyordu.Çözümü için iki türlü yaklaşım benimsemek aklıma geldi
	 * birincisi Inheritance kullanıp sınıfları bağımlı yapacaktım bu coupling 'e sebep olacaktım ve metod owerride etsem bile değer tüm sınıflarda ezilecekti. 
	 * Araştırmalar sonucunda clone yöntemini kullanarak nesnenin orjin'e dokunmada bir clone yaratarak o clone değiştirdim.Bu şekilde her store'de farklı price lar 
	 * görünecek fakat orjin book nesnesindeki price varlığını koruyacaktı.
	 * */
	@Override
	public Book getABook(String name) {
		for (Book book : booksMap.values()) {
			if (name.equals(book.getName())) {
				return book;
			}
		}
		return null;
	}

}
