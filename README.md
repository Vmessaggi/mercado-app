# Mercado App

Sistema de gerenciamento de despensa e lista de compras desenvolvido em Java, como projeto de estudo prático de Programação Orientada a Objetos, persistência de dados com JDBC e boas práticas de desenvolvimento.

## Funcionalidades

- **Cadastro de produtos** com nome, categoria, unidade de medida e quantidade mínima
- **Controle de estoque (despensa)**: compra e consumo de itens, com validação para impedir consumo maior do que o disponível
- **Geração automática de lista de compras**, com base em itens abaixo da quantidade mínima
- **Histórico de eventos** (compras e esgotamentos) por produto
- **Previsão de esgotamento**, calculada a partir do ciclo médio de consumo do histórico
- **Cálculo de urgência de reposição**, com duas estratégias diferentes:
  - Baseada apenas na quantidade mínima
  - Baseada na previsão de esgotamento
- **Persistência em PostgreSQL**, com dados carregados automaticamente ao iniciar o sistema

## Arquitetura

O projeto segue princípios de Programação Orientada a Objetos, com as seguintes camadas:

- **Modelo de domínio**: `Produto`, `ItemEstoque`, `Despensa`, `ItemCompra`, `ListaDeCompras`, `TipoEvento`, `RegistroHistorico`, `Historico`
- **Orquestração**: `SistemaMercado`, que coordena as regras de negócio entre as classes de domínio e a persistência
- **Persistência**: `ProdutoRepository`, `ItemEstoqueRepository`, `HistoricoRepository`, usando JDBC puro com `PreparedStatement`
- **Interfaces e polimorfismo**: `CalculadoraUrgencia`, com implementações intercambiáveis (`UrgenciaPorEstoqueMinimo`, `UrgenciaPorPrevisao`)

## Tecnologias

- Java
- Maven
- JDBC (driver oficial do PostgreSQL)
- PostgreSQL
- Docker / Docker Compose
- JUnit 5 (testes automatizados)

## Como executar

### Pré-requisitos

- JDK instalado
- Docker e Docker Compose instalados
- IntelliJ IDEA (ou outra IDE de sua preferência)

### Configuração do banco de dados

1. Copie `.env.example` para `.env` e preencha com suas credenciais
2. Suba o banco com Docker:
```bash
   docker compose up -d
```
3. Crie as tabelas (via DataGrip, psql, ou outro cliente SQL de sua preferência):

```sql
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    unidade_medida VARCHAR(10),
    quantidade_minima NUMERIC(10, 2)
);

CREATE TABLE item_estoque (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER NOT NULL REFERENCES produtos(id),
    quantidade NUMERIC(10, 2) NOT NULL,
    data_compra DATE NOT NULL
);

CREATE TABLE registro_historico (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER NOT NULL REFERENCES produtos(id),
    tipo VARCHAR(20) NOT NULL,
    data_evento DATE NOT NULL
);
```

### Configuração das credenciais da aplicação

Crie um arquivo `config.properties` na raiz do projeto (esse arquivo é ignorado pelo Git, então cada pessoa configura o seu):

```properties
db.url=jdbc:postgresql://localhost:5432/mercado_app
db.usuario=seu_usuario
db.senha=sua_senha
```

### Executando

Com o Maven configurado, as dependências (incluindo o driver do PostgreSQL) são baixadas automaticamente. Basta rodar a classe `Main`.

## Testes

O projeto conta com testes automatizados via JUnit 5, cobrindo as regras de negócio principais (igualdade de produtos, consumo de estoque, geração de lista de compras, cálculo de urgência e previsão de esgotamento).

## Roadmap

Este projeto está em desenvolvimento contínuo como parte de um percurso de aprendizado. Próximos passos planejados:

- [x] Migração para Maven
- [ ] Refatoração e ampliação da camada de testes
- [ ] Migração para Spring Boot (API REST)
- [ ] Front-end web consumindo a API
- [ ] Deploy em nuvem (Azure)

## Autor

Desenvolvido por [Vinicius Messaggi](https://github.com/Vmessaggi) como parte de um percurso de estudos em desenvolvimento de software.
