# Desafio Back-end ViaSoft

Este projeto é uma aplicação Spring Boot desenvolvida como parte do desafio técnico da ViaSoft. A aplicação expõe um endpoint REST para receber dados de e-mail, processá-los e adaptá-los para diferentes provedores de envio.

## Tecnologias Utilizadas

- **Java 17**: Versão da linguagem Java utilizada.
- **Spring Boot 3.x**: Framework principal para a construção da aplicação.
- **Maven**: Gerenciador de dependências e build do projeto.
- **Spring Web**: Para a criação de endpoints REST.
- **Spring Validation**: Para validação dos dados de entrada.
- **SpringDoc OpenAPI (Swagger UI)**: Para documentação e teste interativo da API.

---

## Pré-requisitos

Para executar o projeto, você precisará ter instalado:

- **JDK 17** ou superior.
- **Maven** (opcional, pois o projeto inclui o Maven Wrapper).

---

## Como Executar o Projeto

1.  **Clone o repositório** para a sua máquina local.

2.  **Abra um terminal** ou prompt de comando na pasta raiz do projeto.

3.  **Execute o comando de build e inicialização** usando o Maven Wrapper. Este comando irá baixar as dependências e iniciar o servidor da aplicação.

    -   No Windows:
        ```bash
        .\mvnw.cmd spring-boot:run
        ```

    -   No Linux ou macOS:
        ```bash
        ./mvnw spring-boot:run
        ```

4.  Após a inicialização, a aplicação estará rodando e acessível em `http://localhost:8000`.

---

## Como Testar com o Swagger UI

A aplicação vem com o Swagger UI integrado para facilitar a visualização e o teste dos endpoints da API.

1.  Com a aplicação em execução, acesse a seguinte URL no seu navegador:
    **http://localhost:8000/swagger-ui.html**

2.  Você verá a documentação da API. Expanda a seção **Emails**.

3.  Clique no endpoint `POST /emails` para expandir seus detalhes.

4.  Clique no botão **"Try it out"** no canto direito. Isso tornará o campo "Request body" editável.

### Testando um Cenário de Sucesso

Cole o seguinte JSON no campo "Request body":

```json
{
  "emailDestinatario": "destinatario.valido@example.com",
  "nomeDestinatario": "Nome do Destinatário",
  "emailRemetente": "remetente.valido@example.com",
  "assunto": "Teste de E-mail",
  "conteudo": "Este é o corpo do e-mail de teste."
}
```

Clique no botão azul **"Execute"**.

**Resultado Esperado:**
- **Código de Resposta:** `204 No Content`.
- **Response headers:** Incluirá um cabeçalho `Serialized-Data` com os dados do e-mail processados e serializados.

### Testando um Cenário de Erro (Validação)

Altere o JSON no "Request body" para conter dados inválidos, como um e-mail mal formatado ou um campo obrigatório em branco:

```json
{
  "emailDestinatario": "email-invalido",
  "nomeDestinatario": "",
  "emailRemetente": "remetente.valido@example.com",
  "assunto": "Teste de E-mail",
  "conteudo": "Este é o corpo do e-mail de teste."
}
```

Clique no botão **"Execute"**.

**Resultado Esperado:**
- **Código de Resposta:** `400 Bad Request`.
- **Response body:** Conterá um JSON detalhando os campos que falharam na validação e as respectivas mensagens de erro.