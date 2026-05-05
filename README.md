# Games

Projeto Java de exemplo que persiste cadastros de jogos em **Oracle** usando **Jakarta Persistence (JPA)** e **Hibernate 6**. Inclui uma entidade `Game`, DAO simples e classe de conexão. Por padrão, usuário e senha vêm de `META-INF/persistence.xml` (adequado ao laboratório FIAP); você pode **sobrescrever** com variáveis de ambiente ou propriedades JVM para não versionar segredos em repositórios públicos.

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

## Credenciais do banco

Por padrão, **`jakarta.persistence.jdbc.user`** e **`jakarta.persistence.jdbc.password`** estão em `src/main/resources/META-INF/persistence.xml`, junto com a URL JDBC. Com isso o projeto **roda direto** na IDE ou com `mvn exec:java`, se o Oracle estiver acessível.

Se definir **ao mesmo tempo** `GAMES_DB_USER` e `GAMES_DB_PASSWORD` (ou `-Dgames.db.user` e `-Dgames.db.password`), esses valores **substituem** apenas usuário e senha do XML — útil em CI ou em forks públicos.

**Atenção:** em repositório público, commitar senha no XML é arriscado; prefira sobrescrever por ambiente ou mantenha o XML apenas local.

### Variáveis de ambiente (sobrescrita opcional)

| Variável | Descrição |
|----------|-----------|
| `GAMES_DB_USER` | Usuário Oracle |
| `GAMES_DB_PASSWORD` | Senha Oracle |

**Windows (PowerShell):**

```powershell
$env:GAMES_DB_USER = "outro_usuario"
$env:GAMES_DB_PASSWORD = "outra_senha"
mvn exec:java
```

**Linux / macOS:**

```bash
export GAMES_DB_USER=outro_usuario
export GAMES_DB_PASSWORD=outra_senha
mvn exec:java
```

### Propriedades da JVM (sobrescrita opcional)

```text
-Dgames.db.user=outro_usuario
-Dgames.db.password=outra_senha
```

## Como executar

```bash
mvn exec:java
```

Classe principal: `br.com.evelyn.Main`.

Na IDE, execute `Main` sem configuração extra se o `persistence.xml` já estiver correto para o seu usuário FIAP.

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
        └── Conexao.java           # EntityManagerFactory (XML padrão; sobrescrita opcional por env/JVM)

src/main/resources/
└── META-INF/persistence.xml       # URL JDBC, usuário e senha; unidade "games"
```

## Licença

Informe aqui a licença do repositório (por exemplo MIT), se aplicável.
