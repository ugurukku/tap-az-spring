package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.entities.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class GeneralConsumer {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CityRepository cityRepository;

    public GeneralConsumer(CategoryRepository categoryRepository, ProductRepository productRepository, CityRepository cityRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.cityRepository = cityRepository;
    }



    @Bean
    public void initializeCities() {

        List<City> cities = List.of(
                new City(null, "Ağcabədi"),
                new City(null, "Ağdam"),
                new City(null, "Ağdaş"),
                new City(null, "Ağdərə"),
                new City(null, "Ağstafa"),
                new City(null, "Ağsu"),
                new City(null, "Astara"),
                new City(null, "Bakı"),
                new City(null, "Balakən"),
                new City(null, "Beyləqan"),
                new City(null, "Bərdə"),
                new City(null, "Biləsuvar"),
                new City(null, "Cəbrayıl"),
                new City(null, "Cəlilabad"),
                new City(null, "Culfa"),
                new City(null, "Daşkəsən"),
                new City(null, "Füzuli"),
                new City(null, "Gədəbəy"),
                new City(null, "Gəncə"),
                new City(null, "Goranboy"),
                new City(null, "Göyçay"),
                new City(null, "Göygöl"),
                new City(null, "Göytəpə"),
                new City(null, "Hacıqabul"),
                new City(null, "Horadiz"),
                new City(null, "İmişli"),
                new City(null, "İsmayıllı"),
                new City(null, "Kəlbəcər"),
                new City(null, "Kürdəmir"),
                new City(null, "Laçın"),
                new City(null, "Lerik"),
                new City(null, "Lənkəran"),
                new City(null, "Masallı"),
                new City(null, "Mingəçevir"),
                new City(null, "Nabran"),
                new City(null, "Naftalan"),
                new City(null, "Naxçıvan"),
                new City(null, "Neftçala"),
                new City(null, "Oğuz"),
                new City(null, "Ordubad"),
                new City(null, "Qax"),
                new City(null, "Qazax"),
                new City(null, "Qəbələ"),
                new City(null, "Qobustan"),
                new City(null, "Quba"),
                new City(null, "Qubadlı"),
                new City(null, "Qusar"),
                new City(null, "Saatlı"),
                new City(null, "Sabirabad"),
                new City(null, "Şabran"),
                new City(null, "Şahbuz"),
                new City(null, "Salyan"),
                new City(null, "Şamaxı"),
                new City(null, "Samux"),
                new City(null, "Şəki"),
                new City(null, "Şəmkir"),
                new City(null, "Şərur"),
                new City(null, "Şirvan"),
                new City(null, "Siyəzən"),
                new City(null, "Sumqayıt"),
                new City(null, "Şuşa"),
                new City(null, "Tərtər"),
                new City(null, "Tovuz"),
                new City(null, "Ucar"),
                new City(null, "Xaçmaz"),
                new City(null, "Xankəndi"),
                new City(null, "Xırdalan"),
                new City(null, "Xızı"),
                new City(null, "Xocalı"),
                new City(null, "Xocavənd"),
                new City(null, "Xudat"),
                new City(null, "Yardımlı"),
                new City(null, "Yevlax"),
                new City(null, "Zaqatala"),
                new City(null, "Zəngilan"),
                new City(null, "Zərdab")
        );

        cityRepository.saveAll(cities);
    }



    @Bean
    public void consume() {
        final String URL = "https://fakestoreapi.com/products";

        RestTemplate restTemplate = new RestTemplate();
        ProductFromConsumer[] products = restTemplate.getForEntity(URL, ProductFromConsumer[].class).getBody();

        assert products != null;
        List<ProductFromConsumer> products1 = Arrays.stream(products).toList();

        List<Category> categories = products1
                .stream()
                .map(ProductFromConsumer::getCategory)
                .collect(Collectors.toSet())
                .stream()
                .map(name -> new Category(null, name))
                .toList();

        categoryRepository.saveAll(categories);

        //Products
        List<Product> productList = new ArrayList<>();

        Random random = new Random();

        for (ProductFromConsumer product : products1) {
            productList
                    .add(
                            new Product(
                                    product.getTitle(),
                                    product.getPrice(),
                                    product.getDescription(),
                                    new Category(
                                            categoryRepository.findCategoryByName(product.getCategory()).get().getId(),
                                            null),
                                    product.getImage(),
                                    new City(random.nextLong(76)+1, null)));
        }

        productRepository.saveAll(productList);

    }


}