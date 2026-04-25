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

---

## 🗓️ Phase 2: Feature Expansion & MVVM (Midterm)

### Goal

Introduce dynamic features and proper app architecture using the MVVM pattern, local data persistence via Room Database, and expanded UI components.

---

### ✅ Functional Requirements

#### 1. Task & Schedule Manager
- Add, edit, and delete tasks
- Date & time picker support
- Display task list using RecyclerView / LazyColumn

#### 2. Campus Announcements Module
- Stored using Room Database
- Mark announcements as read / unread
- Filter announcements by read status

#### 3. Local Data Persistence
- Room Database integration
- DAO, Entity, and ViewModel implementation

---

### 🏗️ Technical Focus

- **MVVM Architecture** – Separation of UI, business logic, and data layers
- **ViewModel & LiveData / StateFlow** – Reactive UI state management
- **Room Database** – Persistent local storage with DAO and Entity definitions
- **RecyclerView Patterns** – Efficient list rendering for tasks and announcements
- **Jetpack Compose** – Modern declarative UI toolkit
- **Kotlin Coroutines** – Asynchronous data operations

---

### 🌿 Git Requirements (Midterm)

- Feature branches used:
    - `feature/task-manager`
    - `feature/announcements`
- Pull Requests required for all feature merges
- At least **1 documented merge conflict**
- Release tagged: **`v1.0-midterm`**

---

### 📦 Midterm Deliverables

- ✅ Updated repository with all Phase 2 features
- ✅ Architecture diagram
- ✅ Reflection document (Git challenges & conflict resolution — see below)

---

### 📝 Midterm Reflection

#### Git Challenges

During Phase 2, the team worked across multiple feature branches simultaneously, which introduced several real-world Git coordination challenges:

- **Parallel development on `feature/task-manager` and `feature/announcements`** required careful branch management to avoid overwriting each other's work.
- **Database schema evolution** — as `AppDatabase` was updated incrementally (adding `TaskEntity`, `AnnouncementEntity`, DAOs, and repositories), keeping branches in sync became challenging, especially when multiple developers were modifying related files.
- **Iterative DAO refinements** required multiple rounds of commits (e.g., the `AnnouncementDao` had methods added and removed across commits to unify the update concept), which led to back-and-forth changes that needed careful merging.
- **Gradle dependency updates** (Kotlin, Compose, Room, Coroutines, Navigation) touched the build configuration shared across branches, which was a common source of merge friction.

#### Conflict Resolution

The team encountered and resolved at least one documented merge conflict during the integration of feature branches into `develop` and subsequently into `master`. The conflict arose when merging `feature/announcements` back into `develop` after `feature/task-manager` had already introduced changes to `AppDatabase`. The following steps were taken:

1. Identified conflicted files using `git status`
2. Manually resolved conflicts in `AppDatabase` by retaining both `TaskEntity` and `AnnouncementEntity` registrations
3. Verified the resolved file compiled and ran correctly before staging
4. Marked conflicts as resolved with `git add` and completed the merge commit

The team also documented a case in `AnnouncementDao` where methods were removed and later restored across commits — reflecting an iterative design decision that was clarified through team discussion before finalizing the DAO interface.

---

### 🔖 Midterm Commit History (Selected)

| Commit Message | Description |
|---|---|
| `Deployment of Announcement and Tasks Feature to Master Branch` | Final merge to master for midterm release |
| `Merge pull request #6 from EligsirCodes/feature/task-manager` | PR merge of task manager feature |
| `Midterm: TaskDao and Repository Fixes` | Bug fixes on DAO and repository layer |
| `Midterm: AnnouncementsScreen UI Update` | Updated Announcements UI via Jetpack Compose |
| `Midterm: TasksScreen UI Update` | Updated Tasks UI via Jetpack Compose |
| `Midterm: TaskViewModel and ViewModelFactory Implementation` | Introduced ViewModel and Factory for tasks |
| `Add update methods for tasks` | Extended task CRUD operations |
| `add query to fetch all tasks ordered by due date` | Sorted task retrieval from Room |
| `Add TaskDao interface` | Defined DAO for task operations |
| `Add getAllTasks / insertTask / updateTask / deleteTask functions` | Full CRUD implementation for tasks |
| `Create TaskRepository for task data operations` | Repository pattern for task data access |
| `Midterm: TaskEntity Implementation` | Defined Room entity for tasks |
| `Midterm: AppDatabase Update` | Added entities to the Room database |
| `Merge pull request #5 from EligsirCodes/feature/announcements` | PR merge of announcements feature |
| `Merge pull request #4 from EligsirCodes/feature/task-manager` | Earlier PR merge (initial task manager) |
| `Midterm: ViewModel and AnnouncementsScreen Updates` | ViewModel integration with announcements UI |
| `Midterm: App Database Implementation` | Initial Room database setup |
| `Merge remote-tracking branch 'origin/feature/announcements' into develop` | Remote merge — conflict resolution point |
| `Set up for function markAsRead and updateAnnouncement` | Read-status management for announcements |
| `Set up for function getUnreadAnnouncements / getReadAnnouncements / getAllAnnouncement` | Query functions for filtering announcements |
| `Step function AnnouncementRepository and intent insertAnnouncement` | Repository and insert setup |
| `Restore unread, mark-as-read, and update methods in AnnouncementDao` | Reverted to consistent update handling after review |
| `Remove unread, mark-as-read, and update methods from AnnouncementDao` | Temporary simplification during iteration |
| `Add Room DAO AnnouncementDao` | Initial DAO with insert, query, update, mark-as-read |
| `Add Room entity AnnouncementEntity` | Defined Room entity for announcements |
| `Midterm: Gradle Update` | Dependency configuration for Compose, Room, Coroutines, Navigation |

---

## Team Roles

The project is developed collaboratively with the following team members:

* **Team Leader:** Luiz Gabriel Rosales
* **Junior UI/UX Design | Documentation & Testing:** Joaquin Aaron Recio
* **Senior UI/UX Design:** John Lester Penafiel
* **Feature Developer:** Trisha Ann Rabano
* **Git Manager:** Marc Alvin Quitorio

---

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
master  ──●──────────────────────────────────────────●──→
           ↑                                          ↑
           │                                          │ (v1.0-midterm)
develop ───●──●──●──●──●──●──●──●──●──●──●──●──●────●──→
              ↑              ↑              ↑
              │              │              │
feature/announcements ───────●──────────────●
                                            │
feature/task-manager  ───────────●──────────●

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

---

#### NOTE: This serves as the Official Documentation of the Group according to what is provided.