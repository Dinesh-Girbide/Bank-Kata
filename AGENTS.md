# AGENTS.md — Bank-Kata

## 1. Project Identity

**Bank-Kata** is a Test-Driven Development (TDD) practice project that implements a simple bank account system. It demonstrates the Outside-In TDD workflow by building features (deposit, withdraw, print statement) from the outside (acceptance test) inward to unit-level components.

All production code lives in the `co.incubyte` package under `src/main/java/co/incubyte/`. The five main classes are:

| Class | Role |
|---|---|
| `Account` | Public API — exposes `deposit(int)`, `withdraw(int)`, `printStatement()` |
| `TransactionRepository` | Storage — records transactions with a date-stamped `Transaction` object |
| `StatementPrinter` | Output — formats and prints the statement in reverse-chronological order |
| `Transaction` | Value object — holds a date string and an integer amount |
| `Clock` / `Console` | Infrastructure — concrete classes (also usable as test doubles via subclassing or mocking) |

---

## 2. Tech Stack

| Concern | Choice |
|---|---|
| Language | Java 11 |
| Build tool | Maven (`pom.xml`) |
| Unit test framework | JUnit 4 (`junit:junit:4.13.2`) |
| Mocking library | Mockito 3 (`org.mockito:mockito-core:3.9.0`) |
| Database | None |
| External API / services | None |

**Build & test command:**
```
mvn test
```

There is no database, no REST API, and no external service dependencies. Everything runs in-process.

---

## 3. Architecture

```
Account
  ├── TransactionRepository  (injected — stores Transaction records)
  │     └── Clock            (injected — provides today's date as a string)
  └── StatementPrinter       (injected — formats and prints all transactions)
        └── Console          (injected — writes lines to stdout)
```

- **`Account`** is the single entry point. It delegates all persistence concerns to `TransactionRepository` and all formatting/output concerns to `StatementPrinter`. It holds no state itself.
- **`TransactionRepository`** keeps an `ArrayList<Transaction>` and stamps each entry with the current date via `Clock.todayAsString()`.
- **`StatementPrinter`** collects running-balance lines, reverses them, then writes each line through `Console.printLine(String)`.
- **`Clock`** and **`Console`** are concrete classes designed for easy test-double creation: `Clock` exposes a `protected LocalDate today()` hook that tests can override by subclassing, and both classes are Mockito-mockable in integration tests (`BankKataTest`).

---

## 4. Testing

Test files live in `src/test/java/co/incubyte/`.

| Test class | Subject |
|---|---|
| `AccountShould` | `Account` (mocks `TransactionRepository` + `StatementPrinter`) |
| `StatementPrinterShould` | `StatementPrinter` (mocks `Console`) |
| `TransactionRepositoryShould` | `TransactionRepository` (mocks `Clock`) |
| `ClockShould` | `Clock` (subclass-based test double via `TestableClock`) |
| `BankKataTest` | End-to-end acceptance test (mocks `Clock` + `Console` only) |

**Conventions:**
- Test classes are named `[Subject]Should` (e.g., `AccountShould`).
- Each test method name reads as a sentence describing the expected behaviour.
- Use `@RunWith(MockitoJUnitRunner.class)` at the class level and `@Mock` field annotations for dependencies.
- Prefer `given(...).willReturn(...)` (BDD-style) for stubbing and `verify(...)` for interaction assertions.
- Run all tests with:
  ```
  mvn test
  ```

---

## 5. What to Ignore

- **`target/`** — Maven build output. Never modify files here; they are regenerated on every build.
- **`.idea/`** — IntelliJ IDEA project configuration. Do not modify; it is IDE-managed.
- **`.anthara/`** — Managed exclusively by the Anthara platform. Do **not** edit any file inside this directory; changes will be overwritten on the next deploy.

---

## 6. Compliance (from `.anthara/guardrails.md`)

> **Note:** `.anthara/guardrails.md` is managed by Anthara and must not be edited directly. The rules below are embedded here so that AI tools can apply them without needing import resolution.

### HIPAA PHI Protection

- **No PHI in logs** — Never log PHI fields (patient names, SSNs, MRNs, DOBs, addresses, phone numbers, email, insurance IDs).
- **No PHI in URLs** — Never expose PHI in URL paths or query parameters.
- **No PHI in errors** — Never include PHI in error responses or stack traces.
- **No PHI in browser storage** — Never store PHI in browser localStorage, sessionStorage, or cookies.
- **Require TLS for PHI** — Never transmit PHI over unencrypted channels (require TLS).
- **No hardcoded patient IDs** — Never hardcode patient identifiers in source code.
- **No PHI in comments** — Never include PHI in code comments or documentation.
- **No PHI to third parties** — Never pass PHI to third-party analytics or tracking services.
- **No full body logging** — Never log full request/response bodies that may contain PHI.
- **No unencrypted temp files** — Never store PHI in temporary files without encryption.
- **No PHI in test data** — Never include PHI in automated test fixtures or seed data.
- **Field-level authorization** — Never expose PHI through API responses without field-level authorization.
- **No unencrypted caching** — Never cache PHI in unencrypted caches (Redis, Memcached, CDN).
- **No debug PHI in prod** — Never allow PHI in debug/verbose logging modes in production.
- **No PHI in tokens** — Never embed PHI in JWT tokens or session identifiers.
- **No PHI in notifications** — Never send PHI in email subject lines or push notification previews.
- **No PHI in version control** — Never store PHI in version control (git) or CI/CD logs.
- **No PHI in API docs** — Never expose PHI through auto-generated API documentation (Swagger responses).

### Security Baseline

- **No SQL concatenation** — Never use string concatenation for SQL queries (use parameterized queries).
- **Sanitize user input** — Never render user input without sanitization (XSS prevention).
- **Hash passwords** — Never store passwords in plain text (require hashing with bcrypt/argon2).
- **Keep CSRF protection** — Never disable CSRF protection.
- **No hardcoded secrets** — Never use hard-coded secrets, API keys, or credentials in source code.
- **Hide internal errors** — Never expose stack traces or internal error details to end users.
- **No weak crypto** — Never use deprecated or insecure cryptographic algorithms (MD5, SHA1 for security).
- **Rate limit APIs** — Never allow unlimited request rates without rate limiting.
- **Validate all inputs** — Never skip input validation on API endpoints.
- **No wildcard CORS** — Never use wildcard CORS origins in production.
- **Keep TLS verification** — Never disable TLS certificate verification.
- **No token logging** — Never log authentication tokens or session IDs.
- **No dynamic eval** — Never use dynamic code execution with user-controlled input.
- **Authenticate endpoints** — Never expose internal service endpoints publicly without authentication.
- **Validate file uploads** — Never allow file uploads without type and size validation.
- **No insecure deserialization** — Never use insecure deserialization of untrusted data.

---

## 7. Conventions (from `.anthara/standards.md`)

> **Note:** `.anthara/standards.md` is managed by Anthara and must not be edited directly. The standards below are embedded here for AI tool visibility. Where an org-wide standard references JavaScript/TypeScript tooling, the Java equivalent is noted.

### Engineering Standards

- **Error Handling** — Use typed error classes with centralized error boundaries. In Java, prefer custom checked or unchecked exception classes (e.g., `class AccountException extends RuntimeException`).
- **Database Access** — Use the repository pattern with parameterized queries (this project uses `TransactionRepository`; no SQL is present).
- **Logging** — Use structured logging with correlation IDs; avoid logging sensitive data. Use a safe logging wrapper for PHI-adjacent contexts.
- **API Response Format** — Use a standardized envelope format when building APIs (`status`, `data`, `error`, `pagination`). *(Not applicable to this CLI-only project.)*
- **Authentication** — Use JWT with short-lived access tokens and refresh token rotation. *(Not applicable to this CLI-only project.)*
- **Input Validation** — Validate all inputs at the API boundary. The org-wide standard references Zod/Joi (JavaScript libraries); in Java use Bean Validation (JSR 380 / Hibernate Validator) or manual guard clauses.
- **Environment Config** — Use environment variables for all configuration; never use defaults for secrets.
- **Date/Time** — Use UTC for all server-side storage and logic; convert to local time only at the display layer. *(This project formats dates as `dd/MM/yyyy` strings for display; production systems should store `Instant` or `LocalDate` with explicit timezone handling.)*
- **Naming Conventions** — `camelCase` for variables/methods, `PascalCase` for classes, `SCREAMING_SNAKE_CASE` for constants. (Consistent with standard Java conventions.)

### PHI Data Access Patterns

- **PHI Read Access** — Access PHI through a dedicated data-access service with mandatory audit logging.
- **PHI Write Access** — All PHI mutations go through a validated write service with before/after audit trail.
- **PHI Redaction** — Use a field-level redaction utility before logging or external transmission.
- **PHI Encryption** — Use AES-256 for PHI at rest; use application-level encryption for sensitive fields.
- **PHI Minimum Necessary** — Only query PHI fields actually needed for the use case.
- **PHI Display** — Mask PHI in UI by default; require explicit user action to reveal full values.
- **PHI Export** — All PHI exports (CSV, PDF, API) must go through the audit-logged export service.
