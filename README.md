# Projeto API de Reservas de Salas 

Olá! Esse é um projeto feito com Java + Spring Boot com o objetivo de praticar a criação de uma API REST para reservas de salas.

Com ele é possível:

- Cadastrar salas  
- Reservar uma sala para um horário específico  
- Ver todas as reservas de uma sala  
- Cancelar uma reserva  
- Aplicar regras de negócio (ex: evitar reservas sobrepostas)

---

##  Tecnologias usadas

- Java 24
- Spring Boot 
- Spring Data JPA
- H2 (banco de dados em memória)
- Maven
- Lombok

---

##  Como rodar o projeto

Siga os passos abaixo para rodar no seu computador:

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/api-reservas-salas.git
cd gerenciamentoSalas

Em seguida rode o projeto, com a api rodando com sucesso, chegou a hora dos testes.

##  Testando a API no Insomnia ou Postman

###  Criar sala (POST)

**URL:** `POST http://localhost:8080/salas`

**Body (JSON):**
```json
{
  "nome": "Sala de Reunião 1",
  "capacidadeMax": 10,
  "localizacao": "Bloco A - 2º andar"
}


### Criar reserva (POST)
**URL:** POST http://localhost:8080/reservas

**Body: (JSON):**
```json
{
  "sala": { "id": 1 },
  "nomeResponsavel": "Ana Maria",
  "dataHoraInicio": "2025-06-01T14:00:00",
  "dataHoraFim": "2025-06-01T15:00:00"
}

### Ver reservas por sala (GET)
**URL:** GET http://localhost:8080/reservas/sala/1

### Cancelar reserva (DELETE)
**URL:** DELETE http://localhost:8080/reservas/1
---

###  Regras de Negócio

- Não é possível criar uma reserva com data/hora de início no passado
- O horário de início deve ser anterior ao horário de término
- Não pode haver duas reservas para a mesma sala com horários sobrepostos
- Não é permitido criar sala com nome já existente
