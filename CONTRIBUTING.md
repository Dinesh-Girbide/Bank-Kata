# Contributing Guide

Thank you for contributing to this project! Please follow the guidelines below to keep the codebase consistent and maintainable.

---

## Commit Message Format — Conventional Commits

All commit messages must follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>(<optional scope>): <short description>

[optional body]

[optional footer(s)]
```

### Supported Types

| Type       | When to use                                                   |
|------------|---------------------------------------------------------------|
| `feat:`     | A new feature                                                 |
| `fix:`      | A bug fix                                                     |
| `test:`     | Adding or updating tests (no production code changes)         |
| `chore:`    | Maintenance tasks, dependency updates, tooling, config        |
| `refactor:` | Code change that neither fixes a bug nor adds a feature       |
| `docs:`     | Documentation only changes                                    |
| `style:`    | Formatting, missing semicolons, etc. (no logic changes)       |
| `ci:`       | Changes to CI/CD configuration files and scripts              |

### Examples

```
feat(account): add overdraft protection logic
fix(statement): correct date formatting in printed output
test(account): add edge-case tests for zero-balance deposit
chore: upgrade mockito to 4.x
refactor(transaction): extract date parsing into helper method
ci: add Maven caching to GitHub Actions workflow
```

---

## Branch Naming Conventions

Branches should be named according to the following patterns:

| Pattern                        | Purpose                                          |
|-------------------------------|--------------------------------------------------|
| `feat/<short-description>`     | New feature work                                 |
| `fix/<short-description>`      | Bug fixes                                        |
| `chore/<short-description>`    | Maintenance / tooling / dependency updates       |
| `refactor/<short-description>` | Refactoring without changing external behaviour  |
| `test/<short-description>`     | Adding or improving tests                        |
| `docs/<short-description>`     | Documentation updates                            |
| `ci/<short-description>`       | CI/CD pipeline changes                           |
| `ai/<short-description>`       | AI-generated branches (e.g. from Claude Code)    |

### Examples

```
feat/overdraft-protection
fix/statement-date-format
chore/upgrade-mockito
ai/add-ci-workflow
```

Use lowercase letters and hyphens only — no spaces, no underscores.

---

## Pull Request Guidelines

1. Open a PR against the `main` branch.
2. Fill in the pull request template completely.
3. Ensure all CI checks pass before requesting a review.
4. At least one approval from a code owner is required before merging.
5. Squash or rebase commits to keep the history clean.

---

## Running Tests Locally

```bash
mvn verify
```

All tests must pass before opening a PR.
