# Games

Projeto Java de exemplo que persiste cadastros de jogos em **Oracle** usando **Jakarta Persistence (JPA)** e **Hibernate 6**. Inclui uma entidade `Game`, DAO simples e classe de conexão que monta o `EntityManagerFactory` de forma segura, **sem gravar usuário e senha no repositório**.

## Requisitos

- [JDK 24](https://openjdk.org/) (ou compatível com `maven-compiler-plugin` configurado com `<release>24</release>`)
- [Apache Maven 3.9+](https://maven.apache.org/)
- Instância **Oracle Database** acessível pela URL JDBC configurada em `src/main/resources/META-INF/persistence.xml`

## Tecnologias

| Camada | Tecnologia |
|--------|------------|
| Persistência | Hibernate ORM 6.3, Jakarta Persistence 3.0 |
| Banco | Oracle JDBC Driver (`ojdbc10`) |
| Build | Maven |

## Como clonar e compilar

```bash
git clone <URL_DO_REPOSITORIO>
cd nome-da-pasta-do-projeto
mvn clean compile
```

Para gerar o JAR:

```bash
mvn package
```

## Credenciais do banco (obrigatório)

Usuário e senha **não** ficam no `persistence.xml`. Informe-os por **uma** das opções abaixo (a primeira valor encontrado em cada campo é usada).

### 1. Variáveis de ambiente (recomendado em CI e servidores)

| Variável | Descrição |
|----------|-----------|
| `GAMES_DB_USER` | Usuário Oracle |
| `GAMES_DB_PASSWORD` | Senha Oracle |

**Windows (PowerShell):**

```powershell
$env:GAMES_DB_USER = "seu_usuario"
$env:GAMES_DB_PASSWORD = "sua_senha"
mvn exec:java
```

**Linux / macOS:**

```bash
export GAMES_DB_USER=seu_usuario
export GAMES_DB_PASSWORD=sua_senha
mvn exec:java
```

### 2. Propriedades da JVM

Útil na configuração de execução da IDE:

```text
-Dgames.db.user=seu_usuario
-Dgames.db.password=sua_senha
```

### 3. Arquivo local (desenvolvimento)

1. Copie `src/main/resources/database-local.properties.example` para `src/main/resources/database-local.properties`.
2. Preencha `jakarta.persistence.jdbc.user` e `jakarta.persistence.jdbc.password`.

O arquivo `database-local.properties` está no `.gitignore` e **não deve ser commitado**.

Se nenhuma opção fornecer usuário e senha, a aplicação falha na inicialização com uma mensagem explicando estas alternativas.

## Como executar

Com credenciais já configuradas:

```bash
mvn exec:java
```

Classe principal: `br.com.evelyn.Main`.

Na IDE, execute `Main` e defina variáveis de ambiente ou propriedades VM conforme acima.

## Configuração da URL JDBC

A URL padrão está em `src/main/resources/META-INF/persistence.xml` (`jakarta.persistence.jdbc.url`). Para outro host, porta ou service name/SID, altere esse arquivo ou adapte o projeto para ler URL também por variável de ambiente, se preferir.

Com `hibernate.hbm2ddl.auto` em `update`, o Hibernate cria ou atualiza tabelas conforme os metadados das entidades (use com cuidado em produção).

## Estrutura do projeto

```text
src/main/java/br/com/evelyn/
├── Main.java                      # Ponto de entrada
├── model/
│   └── Game.java                  # Entidade JPA (@Entity tbl_games)
└── games/
    ├── dao/
    │   └── GameDao.java           # Persistência básica (salvar)
    └── utils/
        └── Conexao.java           # EntityManagerFactory e resolução de credenciais

src/main/resources/
├── META-INF/persistence.xml       # Unidade de persistência "games"
├── database-local.properties.example
└── database-local.properties      # (opcional, local, não versionado)
```

## Licença

Informe aqui a licença do repositório (por exemplo MIT), se aplicável.
