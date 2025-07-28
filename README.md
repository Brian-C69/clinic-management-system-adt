# clinic-management-system-adt

# Clinic Management System (BMCS2063 Assignment)

This is the team repo for the **Clinic Management System** project, developed for TAR UMT's BMCS2063: Data Structures and Algorithms course.

> This system is structured using the **Entity-Control-Boundary (ECB)** design and uses **custom collection ADTs** (no Java Collections Framework allowed).

---

## 📁 Project Structure

```
src/
├── adt/
├── boundary/
├── control/
├── dao/
├── entity/
├── report/
├── util/
```

---

## 🚀 Getting Started

### ✅ 1. Clone the Repository (First Time)

1. Make sure **Git is installed** on your machine:
   - [Download Git](https://git-scm.com/downloads)
2. In NetBeans:
   - `Team → Git → Clone`
   - Enter **repository URL**:  
     ```
     https://github.com/Brian-C69/clinic-management-system-adt.git
     ```
   - For **authentication**:
     - **Username**: Your GitHub username
     - **Password**: Use your **GitHub Personal Access Token** (not your GitHub password!)

---

## 🔐 GitHub Token Guide

GitHub no longer supports password authentication via Git. Follow these steps to generate a token:

1. Go to: [GitHub Personal Access Tokens](https://github.com/settings/tokens)
2. Click **"Generate new token (classic)"**
3. Set:
   - Note: `NetBeans Git`
   - Expiry: 30/90 days
   - Scopes: ✅ `repo`
4. Generate and **copy the token**
5. Use this token as your **Git password** in NetBeans

---

## 🔀 Branching Guidelines (Recommended)

Each team member can work directly on `main`, but it's **strongly recommended** to use branches:

```bash
git checkout -b feature/your-module-name
```

Example branches:
- `feature/patient-module`
- `feature/doctor-module`
- `adt/myarraylist-implementation`

After pushing, open a **Pull Request** to merge into `main`.

---

## 💻 Working in NetBeans

- Use versioned folders:
  - `entity` → POJOs like Patient, Doctor, etc.
  - `control` → Business logic
  - `boundary` → UI code
  - `adt` → Your team's custom ADT (e.g., MyArrayList)

- Right-click → Git → Commit / Push / Pull as needed.

---

## 🙋 Contribution Etiquette

- **Always pull before pushing**:
  ```bash
  git pull origin main
  ```
- Write clear commit messages:
  ```bash
  git commit -m "Add Doctor entity class"
  ```
- Let the team know if you're working directly on `main`

---

## 📦 `.gitignore` (Optional, already applied)

We recommend ignoring:
```
/build/
/nbproject/private/
/*.class
```

---

For any questions, ask in the group chat. Happy coding!
