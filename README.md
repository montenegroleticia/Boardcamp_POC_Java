# Boardcamp API com Spring

## Descrição

A Boardcamp API é um projeto que visa facilitar o gerenciamento de uma locadora de jogos de tabuleiro. Com esta API, você pode realizar operações como adicionar clientes, cadastrar jogos, realizar aluguel de jogos, finalizar aluguel e obter informações sobre clientes, jogos e aluguéis.

## Endpoints

### 1. Obter Cliente por ID

- **Endpoint:** `GET /customers/{id}`
- **Descrição:** Retorna informações sobre um cliente específico com o ID fornecido.
- **Status de Resposta:**
  - `200 (OK)`: Cliente encontrado, retorna as informações.
  - `404 (NOT FOUND)`: Cliente não encontrado.

### 2. Adicionar Cliente

- **Endpoint:** `POST /customers`
- **Descrição:** Adiciona um novo cliente com base nas informações fornecidas no corpo da requisição.
- **Status de Resposta:**
  - `201 (CREATED)`: Cliente adicionado com sucesso.
  - `400 (BAD REQUEST)`: CPF ou nome inválidos.
  - `409 (CONFLICT)`: Cliente com o mesmo CPF já existe.

### 3. Listar Aluguéis

- **Endpoint:** `GET /rentals`
- **Descrição:** Retorna a lista de aluguéis disponíveis.
- **Status de Resposta:**
  - `200 (OK)`: Lista de aluguéis retornada com sucesso.

### 4. Finalizar Aluguel

- **Endpoint:** `PUT /rentals/{id}/return`
- **Descrição:** Finaliza o aluguel com o ID fornecido, adicionando a data de retorno e calculando a multa (caso aplicável).
- **Status de Resposta:**
  - `200 (OK)`: Aluguel finalizado com sucesso.
  - `404 (NOT FOUND)`: Aluguel não encontrado.
  - `422 (UNPROCESSABLE ENTITY)`: Aluguel já finalizado anteriormente.

### 5. Adicionar Aluguel

- **Endpoint:** `POST /rentals`
- **Descrição:** Adiciona um novo aluguel com base nas informações fornecidas no corpo da requisição.
- **Status de Resposta:**
  - `201 (CREATED)`: Aluguel adicionado com sucesso.
  - `400 (BAD REQUEST)`: Informações inválidas.
  - `404 (NOT FOUND)`: Cliente ou jogo não encontrados.
  - `422 (UNPROCESSABLE ENTITY)`: Jogo não disponível para aluguel.

## Regras de Negócio

1. Ao adicionar um cliente ou aluguel, as informações devem ser válidas para evitar erros e conflitos.
2. Ao finalizar um aluguel, a data de retorno e a multa (se aplicável) devem ser calculadas corretamente.
3. A API fornece respostas adequadas para cada situação, como sucesso, cliente não encontrado, conflitos, entre outros.

## Como Executar o Projeto

1. Clonar o repositório: `git clone https://github.com/montenegroleticia/Boardcamp_poc_Java.git`
2. Acessar o diretório do projeto: `cd boardcamp-api`
3. Executar o projeto: `./mvnw spring-boot:run`

Lembre-se de configurar corretamente o ambiente Java e Maven para executar o projeto.

## Tecnologias Utilizadas

- Spring Boot
- Java
- Banco de Dados

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para enviar sugestões, relatar problemas ou enviar pull requests para melhorar a Boardcamp API.

## Licença

Este projeto está sob a licença [MIT](LICENSE).
