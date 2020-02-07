package com.homework.bookstore.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.homework.dao.inmemory.BookRepositoryInMemoryImpl;
import com.homework.dao.inmemory.BookStoreRepositoryInMemoryImpl;
import com.homework.dao.inmemory.CategoryInMemoryImpl;
import com.homework.model.Book;
import com.homework.model.BookStore;
import com.homework.model.Category;
import com.homework.model.Price;
import com.homework.services.BookStoreServiceImpl;
import com.homework.services.BookStoreServices;

import net.bytebuddy.implementation.Implementation;
/*Test işlemleri sırasında not found 404 hatası alabiliyorum, bunun sebeplerinden biri post işlemi yapılabiliyor 201 başarılı dönüyor ve gönderilen bilgiyi get ederken sunucu tabanlı hata
 * alabiliyorum. Bunun için postman üzerinden testlerde doğru çalıştığını test ettim fakat eclipe üzerinden hata dönebiliyor.Çözüm araştırdım araştırmalarıma göre ya springapplication.run metodunun 
 * olduğu yerde componenscan ile controller sınıfını kendimi göstereceğiz yada prefix id ile dao da ki id lerde arasında uyuşmazlık olduğundan ...*/
public class BookStoreControllerTest {

	private CategoryInMemoryImpl impl = new CategoryInMemoryImpl();
	private RestTemplate restTemplate;
	public BookRepositoryInMemoryImpl inMemoryImpl = new BookRepositoryInMemoryImpl();
	BookStoreServices bookstoreservice = new BookStoreServiceImpl();

	@Before
	public void testUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testCreateBookstore() {
		BookStore bookStore = new BookStore();
		bookStore.setName("Bursa");
		bookStore.setId(new Date().getTime());
		List<Book> books2 = new ArrayList<Book>();
		books2.add(inMemoryImpl.getABook("Danzo"));
		books2.add(inMemoryImpl.getABook("Danzo"));
		books2.add(inMemoryImpl.getABook("Great planet"));
		books2.add(inMemoryImpl.getABook("Great planet"));
		bookStore.setBooks(books2);

		URI location = restTemplate.postForLocation("http://localhost:8080/rest/bookstore", bookStore);
		BookStore bookStore2 = restTemplate.getForObject(location, BookStore.class);
		MatcherAssert.assertThat(bookStore2.getName(), Matchers.equalTo(bookStore.getName()));

	}

	@Test
	public void testCreateCategory() {
		Category category = new Category(new Date().getTime(), "Gizem");

		URI location = restTemplate.postForLocation("http://localhost:8080/rest/category", category);

		Category category2 = restTemplate.getForObject(location, Category.class);
		MatcherAssert.assertThat(category2.getName(), Matchers.equalTo(category.getName()));

	}

	@Test/*

    22:04:26.803 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [Book [id=0, name=Sanat, price=null, category=Category [id=4, name=Tarih]]] with org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
    22:04:26.828 [main] DEBUG org.springframework.web.client.RestTemplate - Response 201 CREATED
   */
	public void testCreateBook() {
		Book book = new Book();
		book.setName("Sanat");
		Category category = impl.getCategory("Tarih");
		book.setCategory(category);
		URI location = restTemplate.postForLocation("http://localhost:8080/rest/book", book);
		Book book2 = restTemplate.getForObject(location, Book.class);
		MatcherAssert.assertThat(book2.getName(), Matchers.equalTo(book.getName()));
		MatcherAssert.assertThat(book2.getCategory(), Matchers.equalTo(book.getCategory()));
	}

	@Test
	//Test hata fırlatabilir. Sebebi ; silme işlemi sırasında kitaplardan birini silmiş olabilirim.
	public void testGetAllBooks() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/books", List.class);

		MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = responseEntity.getBody();

		List<String> bookNames = body.stream().map(e -> e.get("name")).collect(Collectors.toList());
		MatcherAssert.assertThat(bookNames,
				Matchers.containsInAnyOrder("My name is Red", "Lord of the Ring", "Bleach", "Danzo", "Great planet"));
	}

	@Test
	public void testGetAllBookstores() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/bookstores",
				List.class);

		MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = responseEntity.getBody();

		List<String> bookstoreNames = body.stream().map(e -> e.get("name")).collect(Collectors.toList());
		MatcherAssert.assertThat(bookstoreNames, Matchers.containsInAnyOrder("Ankara", "Adana"));
	}

	@Test
	public void testGetAllCategories() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/categories",
				List.class);

		MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = responseEntity.getBody();

		List<String> categriessName = body.stream().map(e -> e.get("name")).collect(Collectors.toList());
		MatcherAssert.assertThat(categriessName, Matchers.containsInAnyOrder("Edebiyat", "Sanat", "Fantasy", "Tarih"));
	}

	@Test
	public void testDeleteBookstore() {
		restTemplate.delete("http://localhost:8080/rest/bookstore/1");

		try {
			restTemplate.getForObject("", BookStore.class);
			Assert.fail("should have not returned Bookstore");
		} catch (RestClientException e) {

		}
	}

	@Test
	public void testUpdateCategory() {
		Category category = restTemplate.getForObject("http://localhost:8080/rest/category/2", Category.class);

		MatcherAssert.assertThat(category.getName(), Matchers.equalTo("Sanat"));

		category.setName("Macera");
		restTemplate.put("http://localhost:8080/rest/category/1", Category.class);

		MatcherAssert.assertThat(category.getName(), Matchers.equalTo("Macera"));

	}

	@Test
	public void testAddAbookToBookstore() {

		BookStore bookStore = restTemplate.getForObject("http://localhost:8080/rest/category/bookstores?bsn=Ankara",
				BookStore.class);
		MatcherAssert.assertThat(bookStore.getName(), Matchers.equalTo("Adana"));
		restTemplate.put("http://localhost:8080/rest/category/bookstores?bsn=Ankara", bookStore);

	}

}
