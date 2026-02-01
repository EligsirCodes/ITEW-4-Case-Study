# Smart Campus Companion (PNC Buddy)

## Overview

**Smart Campus Companion (PNC Buddy)** is a mobile application designed to assist university students by providing easy access to essential campus-related tools and information. The app aims to improve student experience by centralizing academic and campus services in one platform.

## Core Features

The application focuses on the following key functionalities:

* **Viewing Campus Information** – Access important campus details and resources
* **Managing Academic Tasks and Schedules** – Organize academic responsibilities efficiently
* **Reading Campus Announcements** – Stay updated with official university announcements
* **Personalizing App Settings** – Customize the app based on user preferences

## Development Phases

The Smart Campus Companion will be developed over **three periods**, with each phase introducing new features and improvements:

* Progressive addition of functionalities
* Continuous enhancement of code quality
* Refinement of user experience and interface design

## Team Roles

The project is developed collaboratively with the following team members:

* **Team Leader:** Luiz Gabriel Rosales
* **Junior UI/UX Design | Documentation & Testing:** Joaquin Aaron Recio
* **Senior UI/UX Design:** John Lester Penafiel
* **Feature Developer:** Trisha Ann Rabano
* **Git Manager:** Marc Alvin Quitorio

# Git Workflow

## Branch Structure

This repository follows a simplified Git workflow with two main branches:

### 🌳 Branch Overview

- **`master`** - Production-ready code
    - Contains stable, tested, and deployable code
    - All code in this branch should be production-ready
    - Protected branch with restricted direct commits

- **`develop`** - Integration branch for development
    - Main development branch where features are integrated
    - Contains the latest delivered development changes
    - Base branch for feature development

---

## 📋 Workflow Process

### 1. Starting New Work

When starting new development work:

```bash
# Ensure you have the latest changes
git checkout develop
git pull origin develop

# Create a feature branch from develop
git checkout -b feature/your-feature-name
```

### 2. Working on Features

While working on your feature:

```bash
# Make your changes
git add .
git commit -m "Descriptive commit message"

# Push your branch to remote
git push origin feature/your-feature-name
```

### 3. Merging Features to Develop

Once your feature is complete:

```bash
# Update your feature branch with latest develop
git checkout develop
git pull origin develop
git checkout feature/your-feature-name
git merge develop

# Resolve any conflicts if they exist
# Then push your updated feature branch
git push origin feature/your-feature-name
```

Create a Pull Request (PR) to merge `feature/your-feature-name` → `develop`

### 4. Releasing to Master

When develop is stable and ready for release:

```bash
# Ensure develop is up to date
git checkout develop
git pull origin develop

# Create a release branch (optional but recommended)
git checkout -b release/v1.0.0

# After final testing, merge to master
git checkout master
git pull origin master
git merge develop
git push origin master

# Tag the release
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

Create a Pull Request to merge `develop` → `master`

---

## 🔄 Common Commands

### Checking Branch Status

```bash
# See all branches
git branch -a

# See current branch
git branch --show-current

# Check status of working directory
git status
```

### Syncing with Remote

```bash
# Fetch all remote branches
git fetch origin

# Pull latest changes from current branch
git pull origin [branch-name]

# Push local changes to remote
git push origin [branch-name]
```

### Switching Branches

```bash
# Switch to existing branch
git checkout [branch-name]

# Create and switch to new branch
git checkout -b [new-branch-name]
```

---

## 📝 Branch Naming Conventions

Follow these naming conventions for consistency:

- **Feature branches**: `feature/description-of-feature`
    - Example: `feature/user-authentication`, `feature/payment-integration`

- **Bugfix branches**: `bugfix/description-of-bug`
    - Example: `bugfix/login-error`, `bugfix/api-timeout`

- **Hotfix branches**: `hotfix/description-of-fix`
    - Example: `hotfix/security-patch`, `hotfix/critical-bug`

- **Release branches**: `release/version-number`
    - Example: `release/v1.0.0`, `release/v2.1.0`

---

## 💡 Best Practices

1. **Always pull before you push**
   ```bash
   git pull origin [branch-name]
   git push origin [branch-name]
   ```

2. **Write meaningful commit messages**
    - Use present tense: "Add feature" not "Added feature"
    - Be descriptive but concise
    - Reference issue numbers when applicable

3. **Keep commits atomic**
    - Each commit should represent a single logical change
    - Makes it easier to track changes and revert if needed

4. **Never commit directly to master**
    - Always use pull requests for code review
    - Ensures code quality and team awareness

5. **Regularly sync your feature branch with develop**
   ```bash
   git checkout feature/your-feature
   git merge develop
   ```

6. **Delete merged branches**
   ```bash
   # Delete local branch
   git branch -d feature/your-feature
   
   # Delete remote branch
   git push origin --delete feature/your-feature
   ```

7. **Use descriptive branch names**
    - Clear and concise
    - Describes the work being done
    - Follows team conventions

---

## 🚨 Conflict Resolution

When you encounter merge conflicts:

1. **Identify conflicted files**
   ```bash
   git status
   ```

2. **Open conflicted files and resolve conflicts**
    - Look for conflict markers: `<<<<<<<`, `=======`, `>>>>>>>`
    - Keep the code you want, remove conflict markers
    - Test your changes

3. **Mark conflicts as resolved**
   ```bash
   git add [resolved-file]
   ```

4. **Complete the merge**
   ```bash
   git commit -m "Resolve merge conflicts"
   ```

---

## 📊 Workflow Diagram

```
master  ──●────────────────────────●────────────●──→
           ↑                        ↑            ↑
           │                        │            │
develop ───●──●──●──●──●──●──●──●──●────●──●───●──→
              ↑     ↑        ↑              ↑
              │     │        │              │
feature/1  ───●─────●        │              │
                             │              │
feature/2  ──────────────────●──────────────●

Legend:
● = Commit/Merge point
→ = Branch continues
↑ = Merge direction
```

---

## 🔍 Quick Reference

| Task | Command |
|------|---------|
| Clone repository | `git clone [repository-url]` |
| Check current branch | `git branch --show-current` |
| Create new branch | `git checkout -b [branch-name]` |
| Switch branch | `git checkout [branch-name]` |
| Stage changes | `git add .` |
| Commit changes | `git commit -m "message"` |
| Push to remote | `git push origin [branch-name]` |
| Pull from remote | `git pull origin [branch-name]` |
| View commit history | `git log --oneline` |
| View remote branches | `git branch -r` |

## 🆘 Need Help?

If you encounter issues:

1. Check Git status: `git status`
2. Review recent commits: `git log --oneline -5`
3. Consult team members
4. Refer to [Git documentation](https://git-scm.com/doc)

---

## 📚 Additional Resources

- [Pro Git Book](https://git-scm.com/book/en/v2)
- [GitHub Docs](https://docs.github.com)
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)
- [Atlassian Git Tutorials](https://www.atlassian.com/git/tutorials)

#### NOTE: This Serves as our Official Documentation of the Group according on what is provided.