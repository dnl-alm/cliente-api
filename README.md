## How to:

Antes de começar, você precisa ter instalado na sua VM:

* Docker
* Git
* Portas liberadas:

  * `3306` (MySQL)
  * `8080` (API)

---

## 🌐 1. Criar rede Docker

```bash
docker network create innovations-network
```

---

## 📥 2. Clonar o projeto

```bash
git clone https://github.com/dnl-alm/cliente-api.git
cd cliente-api
```

---

## 🗄️ 3. Criar Volume e subir o banco de dados (MySQL)
```bash
docker volume create mysql-innovations-data
```

```bash
docker run --name mysql-innovations-rm563045 -d \
-e MYSQL_ROOT_PASSWORD=senhai \
-e MYSQL_DATABASE=dbi \
-e MYSQL_USER=useri \
-e MYSQL_PASSWORD=senhai \
--network innovations-network \
-p 3306:3306 \
-v mysql-innovations-data:/var/lib/mysql \
-v $(pwd)/docker-entrypoint-initdb.d/init.sql:/docker-entrypoint-initdb.d/init.sql \
mysql:8.0
```

---

## 📜 4. Ver logs do banco

```bash
docker logs -f mysql-innovations-rm563045
```

> ⏳ Aguarde até aparecer a mensagem: **"ready for connections"**

---

## 🚀 5. Subir a aplicação (Spring Boot)

```bash
docker run --name api-innovations-rm563045 -d \
-v $(pwd):/app \
-w /app \
-p 8080:8080 \
--network innovations-network \
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-innovations-rm563045:3306/dbi \
-e SPRING_DATASOURCE_USERNAME=useri \
-e SPRING_DATASOURCE_PASSWORD=senhai \
maven:3.9-eclipse-temurin-17 \
bash -c "mvn clean package -DskipTests && java -jar target/*.jar"
```

---

## 🧪 6. Testar a API

### 🔍 Buscar todos os clientes
```bash
curl -X GET http://localhost:8080/api/clientes
```

### 🔍 Buscar cliente por ID
```bash
curl -X GET http://localhost:8080/api/clientes/1
```

### ➕ Criar novo cliente
```bash
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "idade": 25,
    "cpf": "12345678901"
  }'
```

### ✏️ Atualizar cliente
```bash
curl -X PUT http://localhost:8080/api/clientes/6 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Atualizado",
    "idade": 28,
    "cpf": "12345678901"
  }'
```

### ❌ Deletar cliente
```bash
curl -X DELETE http://localhost:8080/api/clientes/6
```
