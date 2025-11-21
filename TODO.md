# APD Tema #1 - Agregator de Știri TODO

**Termen de predare:**
- Soft: 07 Decembrie 2025 - 23:59
- Hard: 10 Decembrie 2025 - 23:59

---

## 1. Core Implementation Requirements

### 1.1 Program Structure
- [ ] Create main class `Tema1` with proper command line argument parsing
  - `java Tema1 <numar_threads> <fisier_articole> <fisier_suplimentar>`
- [ ] Implement fixed thread pool (create all threads once at startup)
- [ ] Parse input files structure (articles.txt, inputs.txt)

### 1.2 JSON Article Processing
- [ ] Set up JSON parsing library (Jackson or Gson)
- [ ] Parse article fields: `uuid`, `title`, `author`, `url`, `text`, `published`, `language`, `categories`
- [ ] Handle multiple JSON files efficiently (memory-conscious approach)

### 1.3 Duplicate Elimination
- [ ] Remove duplicates based on `uuid` OR `title`
- [ ] Ensure both duplicates are removed (not just one)
- [ ] Track count of duplicates found

### 1.4 Category Organization
- [ ] Read and parse `categories.txt`
- [ ] Filter articles by valid categories only
- [ ] Normalize category names (remove commas, replace spaces with `_`)
- [ ] Generate category files (e.g., `Economy_Business_and_Finance.txt`)
- [ ] Sort UUIDs lexicographically within each file
- [ ] Handle articles belonging to multiple categories

### 1.5 Language Organization
- [ ] Read and parse `languages.txt`
- [ ] Filter articles by valid languages only
- [ ] Generate language files (e.g., `english.txt`)
- [ ] Sort UUIDs lexicographically within each file

### 1.6 Global Articles File
- [ ] Create `all_articles.txt` with format: `<uuid> <YYYY-MM-DDTHH:MM:SSZ>`
- [ ] Sort by `published` date (descending, chronological)
- [ ] Use lexicographic UUID ordering for ties

### 1.7 Keywords Analysis (English only)
- [ ] Read `english_linking_words.txt` for exclusion list
- [ ] Process text: convert to lowercase, split by space, remove non-letters
- [ ] Count articles containing each keyword (not word frequency)
- [ ] Generate `keywords_count.txt` sorted by count (desc), then lexicographically
- [ ] Format: `<word> <count>`

### 1.8 Statistical Reports
- [ ] Create `reports.txt` with the following lines (in order):
  - [ ] `duplicates_found - <count>`
  - [ ] `unique_articles - <count>`
  - [ ] `best_author - <author> <count>` (alphabetic tie-breaker)
  - [ ] `top_language - <language> <count>` (alphabetic tie-breaker)
  - [ ] `top_category - <normalized_category> <count>` (alphabetic tie-breaker)
  - [ ] `most_recent_article - <timestamp> <url>` (UUID tie-breaker)
  - [ ] `top_keyword_en - <word> <count>` (lexicographic tie-breaker)

---

## 2. Parallelization Strategy

### 2.1 Thread Management
- [ ] Create exactly N threads at program start
- [ ] **CRITICAL**: No repeated thread creation/starting (penalty: 0 points)
- [ ] **CRITICAL**: No pseudo-synchronization (sleep, busy waiting) (penalty: -100p)
- [ ] Main thread should not execute in parallel with worker threads

### 2.2 Parallel Processing Design
- [ ] Design work distribution strategy across threads
- [ ] Implement synchronization mechanisms (synchronized, ConcurrentHashMap, etc.)
- [ ] Identify which stages can be parallelized
- [ ] Ensure deterministic results across multiple runs

---

## 3. Build System

### 3.1 Makefile
- [ ] `build` rule: compile sources to `out/` directory
- [ ] `run` rule: execute program with command line arguments
- [ ] `clean` rule: remove compiled artifacts
- [ ] `check` rule: run checker script

### 3.2 Compilation
- [ ] Test with OpenJDK-25
- [ ] Ensure only Jackson/Gson JSON library is used (no other external libs)
- [ ] Verify compilation in Linux environment

---

## 4. Documentation (README.pdf)

### 4.1 Section 1 - Feedback
- [ ] Personal feedback about the assignment
- [ ] What could be improved
- [ ] What you liked
- [ ] Implementation duration

### 4.2 Section 2 - Parallelization Strategy
- [ ] Explain work distribution between threads
- [ ] Describe synchronization mechanisms used
- [ ] Justify correctness and efficiency of design
- [ ] Explain processing stages (sequential vs parallel)

### 4.3 Section 3 - Performance & Scalability Analysis
- [ ] **Test Setup**:
  - [ ] Document system configuration (CPU, cores, RAM, OS)
  - [ ] Document Java version
  - [ ] Document dataset size used for testing
- [ ] **Results**:
  - [ ] Table with execution times for different thread counts (1, 2, 3, 4, ...)
  - [ ] Calculate speedup: S(p) = T(1)/T(p)
  - [ ] Calculate efficiency: E(p) = S(p)/p
  - [ ] Include at least one graph (execution time or speedup)
- [ ] **Analysis**:
  - [ ] Explain observed behavior (where performance improves/plateaus)
  - [ ] Identify bottlenecks (synchronization overhead, dataset size, I/O, etc.)
  - [ ] State optimal thread count for your system
- [ ] **Methodology**:
  - [ ] Run each configuration at least 3 times
  - [ ] Record all times and compute averages

**NOTE**: Missing README.pdf or performance analysis section = 0 points for entire assignment

---

## 5. Testing

### 5.1 Local Testing
- [ ] Test with `test_small` example
- [ ] Run `./checker.sh test_small` to verify basic functionality
- [ ] Test individual tests: `./checker.sh test_1`, etc.
- [ ] Verify output files are generated correctly
- [ ] Test with different thread counts (1, 2, 4)

### 5.2 Correctness Verification
- [ ] Ensure deterministic results across multiple runs
- [ ] Verify all output files match expected format
- [ ] Check sorting orders (lexicographic, chronological)
- [ ] Validate duplicate removal logic
- [ ] Test edge cases (empty categories, ties in statistics)

### 5.3 Performance Testing
- [ ] Measure execution times for scalability tests
- [ ] Verify speedup meets expected thresholds (1.40x for 2 threads, 1.75x for 4 threads)
- [ ] Test on dataset similar to checker environment (4 CPUs, 8GB RAM)

---

## 6. Submission Preparation

### 6.1 Archive Contents
- [ ] All Java source files
- [ ] Makefile (with build, run, clean directives)
- [ ] README.pdf (mandatory, comprehensive)
- [ ] NO compiled files (.class, .jar)
- [ ] NO test scripts or checker files

### 6.2 Pre-Submission Checklist
- [ ] Code compiles without errors
- [ ] All tests pass locally
- [ ] README.pdf is complete with all 3 sections
- [ ] Performance analysis includes graphs and data
- [ ] Code is clean and well-commented
- [ ] Git repository is up to date
- [ ] Archive structure is correct (files in root)

### 6.3 Grading Criteria
- [ ] 45p - Scalability (conditional on correctness)
- [ ] 30p - Correctness (deterministic results)
- [ ] 25p - Code clarity + README quality

---

## 7. Development Best Practices

### 7.1 Version Control
- [ ] Set up private Git repository
- [ ] Make frequent commits
- [ ] Use meaningful commit messages
- [ ] Push regularly to remote

### 7.2 Development Tools
- [ ] Use IDE (IntelliJ IDEA or VS Code with Java extensions)
- [ ] Set up debugging capabilities
- [ ] Use Java concurrent utilities documentation
- [ ] Test JSON parsing library thoroughly

### 7.3 Code Quality
- [ ] Write clean, readable code
- [ ] Add meaningful comments
- [ ] Follow Java naming conventions
- [ ] Handle exceptions properly
- [ ] Avoid memory leaks (especially with large datasets)

---

## 8. Critical Reminders

⚠️ **ZERO POINTS IF:**
- Pseudo-synchronization (sleep/busy-waiting)
- Creating/stopping threads repeatedly
- Using wrong number of threads
- Missing or incomplete README.pdf
- Code doesn't compile
- Code fails all tests

⚠️ **IMPORTANT:**
- Only Jackson/Gson for JSON parsing
- Test on Linux/WSL
- Memory-efficient implementation required
- Results must be deterministic
- All output files must match exact format specifications