# BabyCareAPI

# 👶 BabyCare API

API REST para gerenciamento de cuidados com bebês: rotinas diárias e controle de vacinas.

## 🚀 Tecnologias

- **Java 17** | **Spring Boot 3.x** | **Spring Data JPA** | **PostgreSQL** | **Swagger/OpenAPI**

## ⚙️ Como Rodar

### 1. Pré-requisitos
- Java 17+
- PostgreSQL
- Gradle

### 2. Configure o banco
```sql
CREATE DATABASE babycare;
```

### 3. Configure as credenciais
Edite `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/babycare
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute
```bash
mvn spring-boot:run
```

## 📚 Testando a API

### Acesse o Swagger (Documentação Interativa)
```
http://localhost:8080/swagger-ui.html
```

### 🧪 Fluxo de Teste Completo

Siga essa ordem no Swagger para testar todos os endpoints:

#### **1️⃣ Criar Usuário**
`POST /auth/register`
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senhaHash": "senha123"
}
```
**📋 Anote o `userId` retornado** (ex: 1)

---

#### **2️⃣ Fazer Login**
`POST /auth/login`
```json
{
  "email": "joao@email.com",
  "senhaHash": "senha123"
}
```

---

#### **3️⃣ Criar Bebê**
`POST /babies?userId=1`
```json
{
  "nome": "Maria",
  "dataNascimento": "2024-06-15",
  "user": {
    "id": 1
  }
}
```
**📋 Anote o `id` do bebê retornado** (ex: 1)

---

#### **4️⃣ Listar Bebês do Usuário**
`GET /babies?userId=1`

---

#### **5️⃣ Registrar Rotinas**
`POST /babies/1/rotinas`

**Exemplo - Mamada:**
```json
{
  "tipo": "mamada",
  "timeStamp": "2026-01-11T10:30:00",
  "detalhes": {
    "quantidade": "150ml",
    "lado": "direito"
  }
}
```

**Exemplo - Sono:**
```json
{
  "tipo": "sono",
  "timeStamp": "2026-01-11T14:00:00",
  "detalhes": {
    "duracao": "2h30min"
  }
}
```

**Exemplo - Fralda:**
```json
{
  "tipo": "fralda",
  "timeStamp": "2026-01-11T11:00:00",
  "detalhes": {
    "tipo": "xixi e cocô"
  }
}
```

---

#### **6️⃣ Listar Rotinas do Bebê**
`GET /babies/1/rotinas`

---

#### **7️⃣ Adicionar Vacinas**
`POST /vacinas/baby/1`

**Vacina tomada:**
```json
{
  "nome": "BCG",
  "data": "2024-08-21",
  "status": true
}
```

**Vacina futura:**
```json
{
  "nome": "Hepatite B",
  "data": "2024-10-20",
  "status": false
}
```

---

#### **8️⃣ Listar Vacinas**
- **Tomadas:** `GET /vacinas/baby/1/tomadas`
- **Futuras:** `GET /vacinas/baby/1/futuras`

---

#### **9️⃣ Dados Completos do Usuário**
`GET /users/1/completo`

Retorna usuário com todos os bebês, rotinas e vacinas em uma única resposta.

---

#### **🔟 Testar Exclusões**
- **Deletar Rotina:** `DELETE /babies/1/rotinas/1`
- **Deletar Vacina:** `DELETE /vacinas/1`
- **Deletar Bebê:** `DELETE /babies/1` (deletando o bebe, automaticamente já deleta rotinas e vacinas desse bebê)

---



