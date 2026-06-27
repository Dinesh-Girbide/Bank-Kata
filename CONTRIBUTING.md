# Contributing Guide

Thank you for contributing\! Please read these guidelines before opening a pull request.

## Commit Message Format

This project follows the [Conventional Commits](https://www.conventionalcommits.org/) specification. Each commit message must have a **type** prefix:

| Type | When to use |
|------|-------------|
| `feat:` | A new feature |
| `fix:` | A bug fix |
| `test:` | Adding or updating tests |
| `chore:` | Maintenance tasks (dependency bumps, tooling, CI) |
| `refactor:` | Code changes that neither fix a bug nor add a feature |

**Examples:**
```
feat: add patient search endpoint
fix: handle null pointer in report generation
test: add unit tests for billing service
chore: upgrade Spring Boot to 3.2.4
refactor: extract validation logic into helper class
```

### Rules
- Use the **imperative mood** in the subject line ("add" not "added" or "adds").
- Keep the subject line under **72 characters**.
- Reference issue numbers when applicable: `fix: correct date parsing (#42)`.

---

## Branch Naming Conventions

| Pattern | Purpose |
|---------|---------|
| `feat/<short-description>` | New feature work |
| `fix/<short-description>` | Bug fix |
| `chore/<short-description>` | Maintenance / tooling |
| `refactor/<short-description>` | Refactoring |
| `test/<short-description>` | Test-only changes |
| `ai/<short-description>` | AI-generated or AI-assisted branches |

Use **kebab-case** for the description, e.g. `feat/patient-search`, `ai/add-ci-workflow`.

---

## Pull Request Checklist

Before requesting a review, make sure:

- [ ] The PR title follows Conventional Commits format.
- [ ] All tests pass (`mvn verify`).
- [ ] No hardcoded secrets or PHI (Protected Health Information) are present.
- [ ] Relevant documentation has been updated.
