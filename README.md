# GePS - Testing

**E-Procurement Functional End-to-End Flow Test Script using Playwright (Java)**

---

## 🌍 Project Regions

| Project Code    | Region                  |
|-----------------|-------------------------|
| GePS-YIL        | India                   |
| GePS-YEF        | Europe                  |
| GePS-YEA        | Singapore               |
| GePS-YCA        | America                 |
| GePS-HOPES_YIL  | Hopes Japan & India     |
| GePS-HOPES_YEF  | Hopes Japan & Europe    |
| GePS-HOPES_YEA  | Hopes Japan & Singapore |

---

## 🧰 Code Guidelines

### 1. Code Readability

- Write clean, understandable code with meaningful names.
- Avoid over-commenting; comment **why**, not **what**.
    - Example: `//TODO Use binary search to find the element in a sorted array`

### 2. Consistent Formatting

- Use 2 or 4 spaces for indentation.
- Limit line length to 80–100 characters.
- Use whitespace around operators and commas.
    - Example: `int sum = a + b;`

### 3. DRY Principle (Don't Repeat Yourself)

- Refactor repeated logic into reusable methods.

### 4. Modular Code

- Each function/module should do one thing (Single Responsibility Principle).
- Group related logic together.

### 5. Error Handling

- Use `try-catch` blocks and validate inputs with clear error messages.

### 6. Naming Conventions

| Type        | Convention         | Example                |
|-------------|--------------------|------------------------|
| Variable    | `camelCase`        | `maxUserLimit`         |
| Function    | `camelCase`        | `validateInput()`      |
| Class       | `PascalCase`       | `UserProfile`          |
| Constant    | `UPPER_SNAKE_CASE` | `MAX_LIMIT`, `API_URL` |

### 7. Version Control Best Practices

- Commit frequently in small chunks.
- Use clear and descriptive commit messages.
- Use feature branches for all changes — even if `main` is the only permanent branch.

---

## 🔀 Git Workflow

### 📌 Current Strategy

> ✅ The **only permanent branch is `main`** (both locally and remotely).  
> ✅ **Testers create feature branches locally**, push them to remote, and raise PRs to merge into `main`.

---

### ✅ Creating and Working on a Feature Branch

```bash
# Start from the latest main branch
git checkout main
git pull origin main

# Create a new local branch for your feature
git checkout -b feature/my-new-feature

# Work on your changes, then stage & commit
git add .
git commit -m "Add my new feature"

# Push the local branch to remote
git push origin feature/my-new-feature