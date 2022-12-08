package com.example.springsecurityapplication.config;

import com.example.springsecurityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Основной конфиг для конфигурации безопасности в приложении
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // Метод по настройке аутентификации
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // Производим аутентификацию с помощью сервиса
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    // Конфигурируем сам Spring Security
    // конфигурируем авторизацию
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // Указываем какой url запрос будет отправляться при заходе на закрытые страницы

        // Отключаем защиту от межсайтовой подделки запросов (использовалось http.csrf().disable())
        http
                // Указываем что все страницы должны быть защищены аутентификации
                .authorizeRequests()
                // Указываем, что /admin доступен пользователю с ролью администратора
//                .antMatchers("/admin").hasAnyRole("ADMIN")
                // Указываем что не аутентифицированные пользователи могут заходить на страницу с формой аутентификации и на объект ошибки
                // С помощью permitAll указывакем что данные страницы по умолчанию доступны всем пользователям
                .antMatchers("/auth/login", "/error", "/auth/registration", "/product").permitAll()
                // Указываем, что все остальные страницы доступны пользователю с ролью USER и ADMIN
                .anyRequest().hasAnyRole("USER", "ADMIN")
                // Указываем что для всех остальных страниц необходимо вызывать метод authenticated, который открываем форму аутентификации
                // Указываем что дальше конфигурироваться будет аутентификация и соединяем аутентификацию с настройкой доступа
                .and()
                .formLogin().loginPage("/auth/login")
                // Указываем на какой url адрес будут отправляться данные с формы. Нам уже не нужно будет создавать метод в контроллере и обрабатывать данные с формы. Мы задали url по умолчанию, который позволяет обрабатывать форму аутентификации в спринг секьюрити. Спринг секьюрити будет ждать логин и пароль с формы и затем сверить их с данными в БД
                .loginProcessingUrl("/process_login")
                // Указываем на какой url необходимо направить пользователя после успешной аутентификации
                // Вторым аргументом ставим true чтобы перенаправление на данную страницу шло в любом случае при успешной аутентификации
                .defaultSuccessUrl("/index", true)
                // Указываем куда необходимо перенаправить пользователя при проваленной аутентификации
                // В url будет передан объект. Данный объект мы будем проверять на форме и если он есть будет выводить сообщение "Неправильный логин или пароль"
                .failureUrl("/auth/login")
                .and()
                // Указываем что при переходе на  /logout будет очищена сессия пользовате и перенаправление на /auth/login
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
