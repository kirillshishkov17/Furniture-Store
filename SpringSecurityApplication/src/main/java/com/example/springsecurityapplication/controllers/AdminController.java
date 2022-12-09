package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;

    private final ProductService productService;

    public AdminController(ProductValidator productValidator, ProductService productService) {
        this.productValidator = productValidator;
        this.productService = productService;
    }

    // Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping()
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();

        if (role.equals("ROLE_USER")) {
            return "redirect:/index";
        }
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

    // Метод по отображению формы добавления
    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "product/addProduct";
        }

        // Проверка на пустоту файла
        if (file_one != null) {
            // Объект для хранения пути сохранения
            File uploadDir = new File(uploadPath);
            // Если данный путь не существует
            if (!uploadDir.exists()) {
                // То его создаём
                uploadDir.mkdir();
            }
            // Создаём уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идетификатор
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            // Загружаем файл по указанному пути
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if (file_two != null) {
            // Объект для хранения пути сохранения
            File uploadDir = new File(uploadPath);
            // Если данный путь не существует
            if (!uploadDir.exists()) {
                // То его создаём
                uploadDir.mkdir();
            }
            // Создаём уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идетификатор
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            // Загружаем файл по указанному пути
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if (file_three != null) {
            // Объект для хранения пути сохранения
            File uploadDir = new File(uploadPath);
            // Если данный путь не существует
            if (!uploadDir.exists()) {
                // То его создаём
                uploadDir.mkdir();
            }
            // Создаём уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идетификатор
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            // Загружаем файл по указанному пути
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if (file_four != null) {
            // Объект для хранения пути сохранения
            File uploadDir = new File(uploadPath);
            // Если данный путь не существует
            if (!uploadDir.exists()) {
                // То его создаём
                uploadDir.mkdir();
            }
            // Создаём уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идетификатор
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            // Загружаем файл по указанному пути
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if (file_five != null) {
            // Объект для хранения пути сохранения
            File uploadDir = new File(uploadPath);
            // Если данный путь не существует
            if (!uploadDir.exists()) {
                // То его создаём
                uploadDir.mkdir();
            }
            // Создаём уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идетификатор
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            // Загружаем файл по указанному пути
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    // Метод по удалению товара по ID
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по получению товара по ID и отображения шаблона редактирования
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("editProduct", productService.getProductId(id));
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }
}
