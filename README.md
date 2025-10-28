# API-Fluxar 🚀

Repositório para o desenvolvimento do **back-end** do aplicativo e do site **Fluxar**.  
URL base de acesso à API: [https://api-fluxar.onrender.com](https://api-fluxar.onrender.com)

---

## Objetivo 🔍

Nete projeto foi desenvolvida a **API em Java (Spring Boot)** consumida pelo aplicativo mobile e pelo site do Fluxar.  
Ele contempla operações de CRUD, autenticação/autorização, lógica de negócio e integração com banco de dados **PostgreSQL**.  
A aplicação também está preparada para deployment via **Docker** e possui documentação automática via [**Swagger**](https://api-fluxar.onrender.com/swagger-ui/index.html#).

---

## Principais funcionalidades 🚀

- Endpoints REST para gerenciamento de recursos
- Autenticação JWT
- Documentação via [**Swagger**](https://api-fluxar.onrender.com/swagger-ui/index.html#)
- Integração com **PostgreSQL**
- Containerização com **Docker**

---

## Desenvolvimento 🛠️
<p>
  <img src="./.github/images/Java_Icon.png" alt="Java" width="29"/>
  <img src="./.github/images/Spring_Boot_Icon.png" alt="Spring Boot" width="29"/>
  <img src="./.github/images/PostgreSQL_icon.png" alt="PostgreSQL" height="30"/>
</p>

---

## Instalação e execução 👨‍💻

### 1️ - Clonar o repositório
```bash
git clone https://github.com/Fluxar-NeoTech/API-Fluxar.git
cd API-Fluxar
````

### 2️ - Configurar variáveis de ambiente (ENVs)

Edite o arquivo `src/main/resources/application.properties`, configurando-o de acordo com suas ENVs

### 3 - Executar o projeto

#### Via Maven:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

#### Via Docker:

```bash
docker build -t fluxar-api .
docker run -p 8080:8080 fluxar-api
```

Localmente, API estará disponível em [http://localhost:8080](http://localhost:8080)
Para consumo da versão deployada, estará disponível em 

---

## Estrutura do projeto 📐

```
API-Fluxar/
│  pom.xml
│  Dockerfile
│  mvnw, mvnw.cmd
│
└─ src/
    └─ main/
        ├─ java/
        │   └─ org/example/apifluxar/...     # Pacotes do projeto
        └─ resources/
            └─ application.properties       # Configurações
```

---

## Principais endpoints 🧾

Os principais endpoints da API podem ser todos encontrados no [**Swagger**](https://api-fluxar.onrender.com/swagger-ui/index.html#)

---
