package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Позволяет вернуть всех пользователей
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Позволяет вернуть товар по ID
    public Product getProductId(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    // Позволяет сохранить объект продукта, который пришёл с формы
    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Позволяет обновить информацию о продукте
    @Transactional
    public void updateProduct(int id, Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    // Позволяет удалить товар по ID
    @Transactional
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}