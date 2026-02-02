# CRUD em Java com JDBC e MySQL

Este projeto consiste em um sistema **CRUD (Create, Read, Update, Delete)** desenvolvido em **Java**, utilizando **JDBC** para comunicação com um banco de dados **MySQL**.  
O sistema é executado via **console** e segue uma arquitetura organizada, separando claramente as responsabilidades entre camadas.

---

## 📌 Objetivo do Projeto

O principal objetivo deste projeto foi **aprimorar minhas habilidades em Java**, especialmente no uso de:

- JDBC puro
- Integração com banco de dados MySQL
- Boas práticas de organização de código
- Tratamento de exceções
- Arquitetura em camadas (Application, DAO, Entity)

Este CRUD representa uma **evolução significativa** em relação ao projeto **ContactBook**, demonstrando maior domínio técnico, código mais limpo e uma estrutura mais profissional.

---

## 🛠️ Tecnologias Utilizadas

- Java 8+
- JDBC
- MySQL
- Eclipse IDE
- Git e GitHub

---

## 🧱 Estrutura do Projeto

O projeto foi organizado seguindo boas práticas:

- `application`  
  Contém a classe principal (`Program`), responsável pela interação com o usuário via console.

- `model.entities`  
  Contém a entidade `User`, que representa o modelo de dados.

- `model.dao`  
  Define a interface `UserDao`, garantindo desacoplamento entre aplicação e persistência.

- `model.dao.impl`  
  Implementação JDBC do DAO (`UserDaoJDBC`), responsável exclusivamente pelo acesso ao banco de dados.

- `db`  
  Gerenciamento da conexão com o banco de dados e tratamento de exceções personalizadas.

---

## ✅ Funcionalidades

- Cadastro de usuários
- Edição de usuários (com atualização parcial de dados)
- Listagem de usuários
- Busca de usuário por ID
- Exclusão de usuários
- Validação básica de dados (ex: email)
- Tratamento de exceções com mensagens claras
- Menu interativo via console

---

## 💡 Diferenciais do Projeto

- Código **bem estruturado e legível**
- Separação clara de responsabilidades
- DAO sem regras de negócio ou saídas no console
- Tratamento adequado de erros
- Uso correto de recursos JDBC
- Projeto desenvolvido **sem gambiarras**, seguindo padrões próximos aos utilizados em ambientes profissionais

---

## ▶️ Execução

O projeto pode ser executado diretamente via console ou empacotado em um arquivo `.jar` executável.

```bash
java -jar CRUD.jar
