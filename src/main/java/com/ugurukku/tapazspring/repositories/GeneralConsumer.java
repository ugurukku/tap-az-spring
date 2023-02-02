package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.dto.product.ProductFromConsumer;
import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.services.CategoryService;
import com.ugurukku.tapazspring.services.CityService;
import com.ugurukku.tapazspring.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class GeneralConsumer {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final CityService cityService;

    public GeneralConsumer(CategoryService categoryService, ProductService productService, CityService cityService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.cityService = cityService;
    }


    @Bean
    public void initializeCities() {

        List<City> cities = List.of(
                new City( "Ağcabədi"),
                new City( "Ağdam"),
                new City( "Ağdaş"),
                new City( "Ağdərə"),
                new City( "Ağstafa"),
                new City( "Ağsu"),
                new City( "Astara"),
                new City( "Bakı"),
                new City( "Balakən"),
                new City( "Beyləqan"),
                new City( "Bərdə"),
                new City( "Biləsuvar"),
                new City( "Cəbrayıl"),
                new City( "Cəlilabad"),
                new City( "Culfa"),
                new City( "Daşkəsən"),
                new City( "Füzuli"),
                new City( "Gədəbəy"),
                new City( "Gəncə"),
                new City( "Goranboy"),
                new City( "Göyçay"),
                new City( "Göygöl"),
                new City( "Göytəpə"),
                new City( "Hacıqabul"),
                new City( "Horadiz"),
                new City( "İmişli"),
                new City( "İsmayıllı"),
                new City( "Kəlbəcər"),
                new City( "Kürdəmir"),
                new City( "Laçın"),
                new City( "Lerik"),
                new City( "Lənkəran"),
                new City( "Masallı"),
                new City( "Mingəçevir"),
                new City( "Nabran"),
                new City( "Naftalan"),
                new City( "Naxçıvan"),
                new City( "Neftçala"),
                new City( "Oğuz"),
                new City( "Ordubad"),
                new City( "Qax"),
                new City( "Qazax"),
                new City( "Qəbələ"),
                new City( "Qobustan"),
                new City( "Quba"),
                new City( "Qubadlı"),
                new City( "Qusar"),
                new City( "Saatlı"),
                new City( "Sabirabad"),
                new City( "Şabran"),
                new City( "Şahbuz"),
                new City( "Salyan"),
                new City( "Şamaxı"),
                new City( "Samux"),
                new City( "Şəki"),
                new City( "Şəmkir"),
                new City( "Şərur"),
                new City( "Şirvan"),
                new City( "Siyəzən"),
                new City( "Sumqayıt"),
                new City( "Şuşa"),
                new City( "Tərtər"),
                new City( "Tovuz"),
                new City( "Ucar"),
                new City( "Xaçmaz"),
                new City( "Xankəndi"),
                new City( "Xırdalan"),
                new City( "Xızı"),
                new City( "Xocalı"),
                new City( "Xocavənd"),
                new City( "Xudat"),
                new City( "Yardımlı"),
                new City( "Yevlax"),
                new City( "Zaqatala"),
                new City( "Zəngilan"),
                new City( "Zərdab")
        );

        cityService.saveAll(cities);
    }



    @Bean
    public void initializeProducts() {
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
                .map(Category::new)
                .toList();

        categoryService.saveAll(categories);

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
                                            categoryService.findCategoryByName(product.getCategory()).getId()),
                                    new City(random.nextLong(76)+1),
                                    product.getImage()));
        }

        productService.saveAll(productList);

    }


}