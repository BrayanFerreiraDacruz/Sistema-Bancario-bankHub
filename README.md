# 🏦 BankHub — Core Banking System

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=spring-security&logoColor=white)](https://spring.io/projects/spring-security)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)

O **BankHub** é uma solução de Core Banking robusta, projetada para gerenciar operações financeiras críticas com foco absoluto em **Integridade de Dados**, **Segurança** e **Escalabilidade**. Este projeto demonstra a aplicação prática de conceitos avançados de engenharia de software em um domínio de alta complexidade.

---

## 🏗️ Arquitetura e Engenharia

O BankHub não é apenas um sistema de transações; é um estudo de caso em **Arquitetura Limpa (Clean Architecture)** e **Design Patterns**.

- **SOLID Principles:** Código altamente modular e fácil de manter.
- **Design Patterns Aplicados:** 
  - *Strategy* para diferentes métodos de cálculo de taxas.
  - *Factory* para criação dinâmica de tipos de conta.
  - *Observer* para sistemas de notificação e logs de auditoria.
- **Camada de Persistência:** Otimizada com Spring Data JPA e suporte a múltiplas instâncias de banco de dados.

---

## 🚀 Funcionalidades Principais

- **👤 Gestão de Clientes & Contas:** Suporte a Contas Correntes e Poupança com regras de negócio distintas.
- **💸 Transações Atômicas:** Depósitos, saques e transferências (TED/PIX) garantindo que nenhuma transação fique em estado inconsistente.
- **🔐 Segurança Bancária:** Autenticação via JWT, controle de acesso por níveis (RBAC) e proteção contra ataques comuns (OWASP).
- **📊 Auditoria & Logs:** Rastreabilidade completa de todas as operações financeiras.
- **📈 Extratos Inteligentes:** Geração de históricos detalhados com filtros avançados.

---

## 🛠️ Stack Tecnológica

- **Linguagem:** Java 17 (LTS)
- **Framework Principal:** Spring Boot 3.x
- **Persistência:** Hibernate / JPA
- **Segurança:** Spring Security + JWT
- **Banco de Dados:** MySQL (Produção) / H2 (Testes)
- **Containerização:** Docker & Docker Compose
- **Testes:** JUnit 5, Mockito e Testcontainers

---

## ⚙️ Configuração e Instalação

### Pré-requisitos
- JDK 17
- Maven 3.8+
- Docker (Opcional, mas recomendado)

### Passos para rodar localmente
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/BrayanFerreiraDacruz/SISTEMA-BANCARIO-BANKHUB.git
   ```
2. **Configuração do Banco:**
   O projeto utiliza Docker Compose para subir o banco rapidamente:
   ```bash
   docker-compose up -d
   ```
3. **Build e Execução:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

---

## 🤝 Autor e Contato

**Brayan Ferreira da Cruz**  
*Especialista em Desenvolvimento Backend & Arquitetura de Sistemas.*

- **LinkedIn:** [brayan-ferreira-da-cruz](https://www.linkedin.com/in/brayan-ferreira-da-cruz-6156591a8)
- **Portfolio:** [BrayanFerreiraDacruz](https://github.com/BrayanFerreiraDacruz)

---

<div align="center">
  <p><i>"Transformando lógica complexa em soluções bancárias confiáveis."</i></p>
</div>
