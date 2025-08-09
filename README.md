# Java-com-Spring-Boot
Praticando Java com Spring Boot

# Projeto UserDept - Spring Boot + H2 Database

Este projeto é um exemplo simples de aplicação Spring Boot usando arquitetura em camadas, banco de dados em memória **H2** e três endpoints REST para gerenciar usuários e departamentos.

## 📌 Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- Banco H2 (em memória)
- Maven

---

## 1️⃣ Criação do Projeto no Spring Initializr

Acesse [https://start.spring.io/](https://start.spring.io/) e configure:

- **Project:** Maven
- **Language:** Java
- **Spring Boot:** 3.5.4
- **Group:** `com.Spring01`
- **Artifact:** `userdept01`
- **Name:** `userdept01`
- **Description:** Aula Java Web com Spring Boot
- **Package name:** `com.Spring01.userdept01`
- **Packaging:** Jar
- **Java:** 21
- **Dependencies:**
  - Spring Web
  - Spring Data JPA
  - H2 Database

Clique em **Generate** para baixar o projeto e extraia o arquivo `.zip`.

---

## 2️⃣ Estrutura de Pacotes

Dentro da pasta `src/main/java/com/Spring01/userdept01`, crie os seguintes pacotes:

com.Spring01.userdept01
├── controllers
├── entities
├── repositories

## 3️⃣ Criação das Entidades
### **Department.java**
package com.Spring01.userdept01.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Department(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


### **User.java**
package com.Spring01.userdept01.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartament() {
        return department;
    }

    public void setDepartament(Department department) {
        this.department = department;
    }
}

## **4️⃣ Repositórios
**UserRepository.java**

package com.Spring01.userdept01.repositories;

import com.Spring01.userdept01.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

## **5️⃣ Controlador REST
**UserController.java**

package com.Spring01.userdept01.controllers;

import com.Spring01.userdept01.entities.User;
import com.Spring01.userdept01.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> findAll(){
        List<User> result = repository.findAll();
        return result;
    }

    @GetMapping (value = "/{id}")
    public User findById(@PathVariable Long id){
        User result = repository.findById(id).get();
        return result;
    }

    @PostMapping
    public User insert(@RequestBody User user){
        User result = repository.save(user);
        return result;
    }

}

## **6️⃣ Configuração do Banco H2 (acesso e propriedades)
**No arquivo src/main/resources/application.properties:**

# Dados de conexão com o banco H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# Configuração do cliente web do banco H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuração para mostrar o SQL no console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

## **7️⃣ Populando o Banco de Dados Automaticamente
**Você pode popular o banco de dados usando o arquivo import.sql (criado em src/main/resources):**

INSERT INTO tb_department(name) VALUES ('Gestão');
INSERT INTO tb_department(name) VALUES ('Informática');

INSERT INTO tb_user(department_id, name, email) VALUES (1, 'Maria', 'maria@gmail.com');
INSERT INTO tb_user(department_id, name, email) VALUES (1, 'Bob', 'bob@gmail.com');
INSERT INTO tb_user(department_id, name, email) VALUES (2, 'Alex', 'alex@gmail.com');
INSERT INTO tb_user(department_id, name, email) VALUES (2, 'Ana', 'ana@gmail.com');
O Spring Boot executa automaticamente o import.sql ao iniciar a aplicação.

## **8️⃣ Testando no Postman
**GET - Listar todos usuários**
GET http://localhost:8080/users

**GET - Buscar por ID**
GET http://localhost:8080/users/1

**POST - Inserir novo usuário**
POST http://localhost:8080/users

Content-Type: application/json

{
    "name": "Carlos",
    "email": "carlos@gmail.com",
    "department": {
        "id": 1
    }
}

## **9️⃣ Console do Banco H2
Acesse:

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (em branco)

