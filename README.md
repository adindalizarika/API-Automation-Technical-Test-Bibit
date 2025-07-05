🚀 Project Setup

### Step 1: Clone or Download the Project
```bash
# If using git
git clone <repository-url>
cd apiautomationbibit

## 🏃‍♂️ Running the Tests

### Step 2: Clone or Download the Project### Method 1: Using Maven Command Line

1. **Open terminal/command prompt**
2. **Navigate to project directory**
   ```bash
   cd /path/to/apiautomationbibit
   ```

3. **Run all tests**
   ```bash
   mvn clean test
   ```

4. **Run specific test**
   ```bash
   # Run only Create Post test
   mvn test -Dtest=CreatePostTest
   
   # Run only Retrieve Posts test
   mvn test -Dtest=RetrievePostsTest
   
   # Run only Delete Post test
   mvn test -Dtest=DeletePostTest
   
   # Run only Update Post test
   mvn test -Dtest=UpdatePostTest
   ```
