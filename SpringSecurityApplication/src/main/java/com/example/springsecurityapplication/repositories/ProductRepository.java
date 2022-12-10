package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByTitle(String title);

    // Поиск по части наименования товара независимо отрегистра
    List<Product> findByTitleContainingIgnoreCase(String name);

    // Поиск по части наименования товара и фильтрация по диапазону цен
    @Query(value = "SELECT * FROM product WHERE ((lower(title) LIKE %?1%) OR (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') AND (price >= ?2 AND price <= ?3))", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThan(String title, float ot, float Do);

    // Поиск по части наименования товара и фильтрация по диапазону цен и сортировка по возрастанию
    @Query(value = "SELECT * FROM product WHERE ((lower(title) LIKE %?1%) OR (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') AND (price >= ?2 AND price <= ?3) ORDER BY price ASC)", nativeQuery = true)
    List<Product> findByTitleOrderByPrice(String title, float ot, float Do);

    // Поиск по части наименования товара и фильтрация по диапазону цен и сортировка по убыванию
    @Query(value = "SELECT * FROM product WHERE ((lower(title) LIKE %?1%) OR (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') AND (price >= ?2 AND price <= ?3) ORDER BY price DESC)", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float ot, float Do);

    // Поиск по части наименования товара и фильтрация по диапазону цен и сортировка по возрастанию и фильтрация по категории
    @Query(value = "SELECT * FROM product WHERE ((lower(title) LIKE %?1%) OR (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') AND (price >= ?2 AND price <= ?3) AND category_id=?4) ORDER BY price ASC", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPrice(String title, float ot, float Do, int category);

    // Поиск по части наименования товара и фильтрация по диапазону цен и сортировка по убыванию и фильтрация по категории
    @Query(value = "SELECT * FROM product WHERE ((lower(title) LIKE %?1%) OR (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') AND (price >= ?2 AND price <= ?3) AND category_id=?4) ORDER BY price DESC", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float ot, float Do, int category);
}
