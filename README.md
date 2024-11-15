**GePS - YIL = India; GePS - YEF = Europe; GePS - YEA = Singapore; GePS - YCA = America; GePS - YCN = China; GePS - GHT = Japan.**

**E-Procurement Functional End To End Flow Test Script Using PlayWright Java.**

-----------------------------------------------------------------------------------


**To maintain code quality and consistency. Here's a set of common instructions:**

**1. Code Readability**

**Write Clean and Understandable Code:**

Code should be easy to read and understand. Always write with the mindset that someone else (or future you) will need to maintain it.

Use meaningful variable and function names.

Avoid unnecessary complexity; simpler solutions are usually better.

**Commenting Code:**

Add comments only where necessary, especially for complex logic or code that is hard to understand.

Don’t over-comment.

Use comments to explain why, not what the code does.

**Example:** // Use binary search to find the element in a sorted array.

**2. Consistent Formatting**

**Indentation:** Follow a consistent indentation style (typically 2 or 4 spaces) across the project.

**Line Length:** Limit each line of code to a maximum of 80-100 characters to improve readability.

**Use Whitespaces:** Add appropriate whitespaces around operators and after commas for clarity.

**Example:** int sum = a + b; // instead of int sum=a+b;

**3. Follow the DRY Principle (Don't Repeat Yourself)**

Avoid duplicating code by creating reusable functions or methods. If you notice the same block of code appearing in multiple places, refactor it into a function.

**4. Modular Code**

**Single Responsibility Principle:** Each function or module should have a single responsibility. Break down large functions into smaller, well-defined pieces.

**Encapsulate Functionality:** Group related code together. This makes the codebase easier to maintain and debug.

**5. Error Handling**

Always handle potential errors and edge cases in your code.

**Try-Catch Blocks:** Use try-catch blocks for handling exceptions.

**Validate Input:** Check input parameters and return meaningful error messages when something goes wrong.

**6. Follow Naming Conventions**

Use camelCase for variable and function names (e.g., calculateTotal, isValidInput).

Use PascalCase for class names (e.g., UserProfile, ProductController).

Use all caps with underscores (CONSTANT_CASE) for constants (e.g., MAX_LIMIT, API_URL).

Keep names descriptive and meaningful. Avoid abbreviations unless they are widely understood.

**Example:** int maxUserLimit = 100;

**8. Version Control Best Practices**

**Commit Frequently:** Break your work into smaller chunks and commit frequently. This makes it easier to review and revert changes if needed.

**Meaningful Commit Messages:** Follow the commit message guidelines to make it easier to track what each change does.

**9. Testing and Debugging**

Write tests (unit tests, integration tests, etc.) for the code you write to ensure it works as expected.

Test for edge cases (e.g., empty inputs, large inputs, incorrect types).

**Debugging:** Use debugging tools to step through your code and understand how it runs.

**10. Code Reviews**

Before submitting code, review it yourself. Make sure it follows all best practices.

Be open to feedback from code reviews and use it to improve your code.

-----------------------------------------------------------------------------------

**Git Commit and Branching Guidelines To ensure a smooth collaboration, please follow these guidelines for commits and branching:**

**Commit Messages:** Write clear, concise messages (e.g., "Add user authentication"). Reference issue numbers where applicable.

**Branch Naming:** Use descriptive names like feature/user-authentication or bugfix/fix-login-error. This helps everyone understand the purpose of each branch.

**Frequent Commits:** Commit often and keep each commit focused on a single change. Avoid large commits.

**Pull Requests:** Always create PRs for merging into develop or main, and provide a clear description of the changes.

**Sync Regularly:** Pull the latest changes frequently and resolve any conflicts promptly.

-----------------------------------------------------------------------------------

**Develop Branch**
**Purpose:**
This is the default branch for pushing new code changes from your local repository.

**Usage:**
* Each team member should create feature branches from the develop branch for individual tasks or features.
* After completing and testing the feature, they can open a Pull Request (PR) to merge their feature branch back into develop.
* Regularly pull the latest changes from develop to keep your local branch up to date and resolve conflicts early.

# Create a new feature branch from develop
* git checkout develop.

* git pull origin develop.

* git checkout -b new-feature.

# After working, push your changes to remote
* git add ..

* git commit -m "Added new feature".

* git push origin new-feature.

# Open a Pull Request to merge feature into develop

**Main Branch:**
**Purpose:**
This branch is used for code reviews and represents a stable version of your application.

**Usage:**
* Before merging code into main, it should be reviewed and tested.
* PRs should be created from develop to main after testing and approval.
* Code from develop is merged into main only after it is considered stable and ready for the next release.

# After the review process, merge the develop branch into main
* git checkout main.

* git pull origin main.

* git merge develop.

* git push origin main.

# Create a new release from the main branch

**Release Branch:**
**Purpose:**
This branch is dedicated to preparing the code for production or pipeline release.

**Usage:**
* When the main branch is stable and ready for deployment, you create a release branch.
* Use this branch to perform final testing, bug fixing, and deployment configuration without affecting the main or develop branches.
* Once the release is successful, merge the release branch back into both main and develop to ensure that all changes are consistent across the branches.

# Create a release branch from the main branch
* git checkout main.

* git pull origin main.

* git checkout -b release/v1.0.

# Push the release branch to remote and deploy
* git push origin release/v1.0.

# After the release, merge back into main and develop
* git checkout main.

* git merge release/v1.0.

* git push origin main.

* git checkout develop.

* git merge release/v1.0.

* git push origin develop.
