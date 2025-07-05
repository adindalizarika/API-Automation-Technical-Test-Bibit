# Bibit Technical Test - Mobile Automation Framework

This project contains automated tests for a mobile application using Appium, Cucumber, and Selenium WebDriver. 

NOTE, PLEASE READ THIS THANK YOU:
I created a web UI test using Selenium. Since the task asked me to create an automated test case in a logged-in state, I wasn't able to run the test because I couldn't mock it with production data. So instead, I wrote a test script for the logout flow. Apologies for the limitation :D

## 📋 Prerequisites

Before running the automation tests, ensure you have the following installed:

### 1. Java Development Kit (JDK)
- **Version**: JDK 8 or higher
- **Download**: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### 2. Maven
- **Version**: 3.6.0 or higher
- **Download**: [Apache Maven](https://maven.apache.org/download.cgi)

### 3. Android SDK
- **Android SDK Platform-Tools**
- **Android SDK Build-Tools**
- **Android Emulator** (for testing)

### 4. Appium Server
- **Version**: 2.0 or higher
- **Installation**: 
  ```bash
  npm install -g appium
  ```

### 5. Android Emulator or Device
- **Option 1**: Android Emulator (recommended for development)
- **Option 2**: Physical Android device with USB debugging enabled

## 🛠️ Setup Instructions

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd bibitTechnicalTest
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Start Appium Server
```bash
appium
```
The Appium server will start on `http://127.0.0.1:4723/`

### Step 4: Start Android Emulator
```bash
# List available emulators
emulator -list-avds

# Start an emulator (replace 'emulator_name' with your emulator name)
emulator -avd emulator_name
```

### Step 5: Install Test App
The framework is configured to test the **Sauce Labs Demo App**. You can download it from:
- [Sauce Labs Demo App](https://github.com/saucelabs/sample-app-mobile/releases)

Install the APK on your emulator/device:
```bash
adb install path/to/SauceLabs-Mobile-Sample-App-v1.0.0.apk
```

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Features

#### Login Tests
```bash
mvn test -Dcucumber.filter.tags="@login"
```

#### Buy Item Tests
```bash
mvn test -Dcucumber.filter.tags="@buyitem"
```

#### Sort/Filter Tests
```bash
mvn test -Dcucumber.filter.tags="@sortfilter"
```

### Run Tests with Specific Configuration
```bash
# Run with specific device
mvn test -Ddevice.name="your_device_name"

# Run with specific app package
mvn test -Dapp.package="com.saucelabs.mydemoapp.android"
```

