package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.*;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.repositories.StatusRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.PersonService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final PersonService personService;

    private final ProductValidator productValidator;

    private final ProductService productService;

    private final CategoryRepository categoryRepository;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;

    public AdminController(PersonService personService, ProductValidator productValidator, ProductService productService, CategoryRepository categoryRepository,
                           PersonRepository personRepository,
                           OrderRepository orderRepository,
                           StatusRepository statusRepository) {
        this.personService = personService;
        this.productValidator = productValidator;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.personRepository = personRepository;
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
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
        model.addAttribute("category", categoryRepository.findAll());
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
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    @GetMapping("/user/info")
    public String getUserInfo(Model model) {
        model.addAttribute("users", personService.getAllPersons());
        List<Person> test =  personService.getAllPersons();
        System.out.println(test);
        return "admin/users_info";
    }

    @GetMapping("user/info/change/{id}")
    public String changePersonRole(@PathVariable("id") int id) {
        Person person = personService.getPersonById(id);

        if (person.getRole().equals("ROLE_USER")) {
            person.setRole("ROLE_ADMIN");
        } else {
            person.setRole("ROLE_USER");
        }
        person.setId(id);
        personRepository.save(person);
        return "redirect:/admin/user/info";
    }

    // Посмотреть информацию о заказах
    @GetMapping("/order/info")
    public String getOrdersInfo(Model model) {
        // Переменная в модели для поиска
        String search = "";
        model.addAttribute("search", search);

        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orders";
    }

    // Возвращает форму редактирования заказа
    @GetMapping("/order/edit/{id}")
    public String editOrderInfo(Model model, @PathVariable("id") int id) {
        model.addAttribute("editOrder", orderRepository.findById(id).orElse(null));
        List<Status> statusList = statusRepository.findAll();
        model.addAttribute("statusList", statusList);
        return "admin/editOrder";
    }

    // Получает данные с формы и редактирует заказ
    @PostMapping("/order/edit/{id}")
    public String editOrderInfo(@PathVariable int id, @ModelAttribute("editOrder") Order editOrder) {
        orderRepository.save(editOrder);
        return "redirect:/admin/order/info";
    }

    // Ищет заказы по последним 4 символам номера заказа
    @GetMapping("/order/search")
    public String searchOrder(@RequestParam("search") String search, Model model) {
        // Если параметра нет, то будет вводиться "!" т.к. он не используется в номере закзаза
        // В таком случае поиск не выдаст никаких заказов, если поиск пустой
        if (search == "") {
            search = "!пустой поиск";
        }
        List<Order> orderList = orderRepository.findByNumberContainingLastFourSymbols(search);
        model.addAttribute("searchOrder", orderList);
        return "admin/searchOrder";
    }
}
