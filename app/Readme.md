# 🌐 InsightHub

> **Understand your device. Monitor your surroundings. Track your movement.**

**InsightHub** is an Android application developed in **Java** that brings together multiple device sensors and GPS-based features into one clean and user-friendly application.

Instead of creating separate applications for every sensor task, InsightHub provides a **single dashboard** where users can access environmental monitoring, location tracking, speed monitoring, motion detection, and complete sensor snapshots.

---

## ✨ Features

### ☀️ 1. Ambient Light Monitor

* Reads the device's ambient light sensor.
* Displays light intensity in **lux**.
* Classifies the environment into:

    * 🌙 Dark Environment
    * ☀ Normal Light
    * 🔆 Bright Environment

---

### 🌡️ 2. Ambient Temperature

* Reads the device's **ambient temperature sensor**.
* Displays temperature in **°C**.
* Shows an appropriate message if the device does not contain an ambient temperature sensor.

> ℹ️ Ambient temperature sensors are not available on many smartphones.

---

### 📍 3. GPS Location

* Retrieves the device's current GPS location.
* Displays:

    * Latitude
    * Longitude
* Location is obtained when the user presses **Get My Location**.

---

### 🚗 4. Speed Tracker

Provides continuous GPS-based monitoring of:

* Latitude
* Longitude
* Speed in **km/h**

The application also provides a warning when:

```text
Speed > 40 km/h
```

Speed conversion:

```text
m/s × 3.6 = km/h
```

---

### ✦ 5. Complete Reading Capture

The **Capture Reading** feature combines multiple sensors into a single snapshot.

It captures:

📍 Current Latitude
📍 Current Longitude
☀ Current Light Intensity
🌡️ Current Temperature
🕒 Current Date & Time

Example:

```text
✦ CAPTURED READING

📍 LOCATION
Latitude: 28.613900
Longitude: 77.209000

☀ LIGHT
450.20 lux

🌡 TEMPERATURE
27.50 °C

🕒 CAPTURED AT
29 Sep 2026, 11:30:45 PM
```

---

### 📊 6. Five-Reading Light Average

This module maintains the **last five light sensor readings**.

The average is calculated using:

```text
Average = Sum of 5 readings / Number of readings
```

The application displays:

* Current light value
* Average light value
* Number of samples collected

Only the latest **5 readings** are retained.

---

### 📱 7. Motion Monitor

Uses the device's **accelerometer** to monitor movement.

Displays:

```text
X-axis acceleration
Y-axis acceleration
Z-axis acceleration
```

The application also detects significant movement/shaking.

When significant movement is detected:

```text
📳 SHAKE DETECTED!
```

Otherwise:

```text
Device is stable
```

---

# 🎨 Application Design

InsightHub follows a simple dashboard-style design.

The home screen organizes features into three sections:

### ENVIRONMENT

* ☀ Light Monitor
* 🌡 Temperature
* 📊 Light Average

### LOCATION & MOVEMENT

* 📍 My Location
* 🚗 Speed Tracker
* 📱 Motion Monitor

### QUICK SNAPSHOT

* ✦ Capture Complete Reading

The goal is to make the application feel like **one complete sensor-monitoring application**, rather than a collection of unrelated assignments.

---

# 🏗️ Project Architecture

The project uses separate Activities for individual features while sharing common sensor and GPS logic through helper classes.

```text
InsightHub
│
├── MainActivity
│   └── Application Dashboard
│
├── LightActivity
│   └── Ambient Light Monitoring
│
├── TemperatureActivity
│   └── Ambient Temperature
│
├── LocationActivity
│   └── One-Time GPS Location
│
├── SpeedActivity
│   └── Continuous GPS + Speed
│
├── CaptureActivity
│   └── Combined Sensor Snapshot
│
├── AverageLightActivity
│   └── Last 5 Light Readings
│
├── AccelerometerActivity
│   └── Motion + Shake Detection
│
├── SensorHelper
│   └── Shared Sensor Management
│
└── LocationHelper
    └── Shared GPS Management
```

---

# 🧩 Project Structure

```text
app/
│
├── src/main/java/com/example/insighthub/
│   │
│   ├── MainActivity.java
│   ├── LightActivity.java
│   ├── TemperatureActivity.java
│   ├── LocationActivity.java
│   ├── SpeedActivity.java
│   ├── CaptureActivity.java
│   ├── AverageLightActivity.java
│   ├── AccelerometerActivity.java
│   │
│   ├── SensorHelper.java
│   └── LocationHelper.java
│
└── src/main/res/
    │
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_light.xml
    │   ├── activity_temperature.xml
    │   ├── activity_location.xml
    │   ├── activity_speed.xml
    │   ├── activity_capture.xml
    │   ├── activity_average_light.xml
    │   └── activity_accelerometer.xml
    │
    ├── values/
    │   ├── colors.xml
    │   └── themes.xml
    │
    └── values-night/
        └── themes.xml
```

---

# 🛠️ Technologies Used

| Technology             | Purpose                       |
| ---------------------- | ----------------------------- |
| ☕ Java                 | Application development       |
| 📱 Android SDK         | Android application framework |
| 🎨 XML                 | UI design                     |
| ☀️ Light Sensor        | Ambient light measurement     |
| 🌡️ Temperature Sensor | Ambient temperature           |
| 📍 GPS                 | Location tracking             |
| 🚗 Location API        | Speed calculation             |
| 📱 Accelerometer       | Motion detection              |
| 🔐 Runtime Permissions | Location access               |
| 🧩 SensorManager       | Sensor management             |
| 📍 LocationManager     | GPS management                |

---

# 🔐 Permissions

InsightHub requires location permissions for its GPS-related features.

```xml
<uses-permission
    android:name="android.permission.ACCESS_FINE_LOCATION" />

<uses-permission
    android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

Location permission is requested at runtime before accessing GPS data.

---

# 📱 Sensor Compatibility

Sensor availability depends on the physical Android device.

| Feature        | Required Hardware          |
| -------------- | -------------------------- |
| Light Monitor  | Ambient Light Sensor       |
| Temperature    | Ambient Temperature Sensor |
| GPS Location   | GPS / Location Provider    |
| Speed Tracker  | GPS / Location Provider    |
| Light Average  | Ambient Light Sensor       |
| Motion Monitor | Accelerometer              |

If a required sensor is unavailable, InsightHub displays an appropriate message instead of crashing.

---

# 🚀 How to Run

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project using **Android Studio**.

### 3. Wait for Gradle Sync

Allow Android Studio to download and configure the required dependencies.

### 4. Connect an Android device

You can use:

* Physical Android phone
* Android Emulator

For accurate sensor and GPS testing, a **physical Android device is recommended**.

### 5. Run the application

Click:

```text
▶ Run
```

Then open **InsightHub**.

---

# 📍 Testing GPS Features

When the application requests location permission:

```text
Allow InsightHub to access this device's location
```

Select:

```text
Allow while using the app
```

For an emulator, configure a mock location through the emulator's location controls.

---

# 🧠 How the Application Works

InsightHub uses Android's `SensorManager` to communicate with hardware sensors.

For example:

```text
Physical Sensor
      ↓
Android SensorManager
      ↓
SensorHelper
      ↓
Activity
      ↓
User Interface
```

For GPS:

```text
GPS Provider
      ↓
LocationManager
      ↓
LocationHelper
      ↓
Activity
      ↓
Latitude / Longitude / Speed
```

This separation keeps the application organized and avoids unnecessarily duplicating sensor-management code.

---

# 📐 Light Classification

InsightHub uses the following simple classification:

| Light Level   | Classification        |
| ------------- | --------------------- |
| `< 100 lux`   | 🌙 Dark Environment   |
| `100–999 lux` | ☀ Normal Light        |
| `≥ 1000 lux`  | 🔆 Bright Environment |

These thresholds are application-defined values used for classification.

---

# 🚗 Speed Calculation

Android provides GPS speed in **meters per second (m/s)**.

InsightHub converts it to kilometers per hour:

```text
Speed (km/h) = Speed (m/s) × 3.6
```

Example:

```text
10 m/s × 3.6
= 36 km/h
```

The application displays a warning when:

```text
Speed > 40 km/h
```

---

# 📊 Average Calculation

The Light Average module keeps only the latest five readings.

Example:

```text
Reading 1 → 100 lux
Reading 2 → 200 lux
Reading 3 → 300 lux
Reading 4 → 400 lux
Reading 5 → 500 lux
```

Average:

```text
(100 + 200 + 300 + 400 + 500) / 5

= 300 lux
```

When a sixth reading arrives, the oldest reading is removed.

---

# 📱 Shake Detection

The accelerometer provides acceleration values along three axes:

```text
X → Left / Right
Y → Up / Down
Z → Forward / Backward
```

InsightHub compares the current acceleration with the previous reading.

A significant combined change indicates possible shaking or significant movement.

---

# 📂 Main Classes

### `MainActivity`

Acts as the application's home dashboard and navigation screen.

### `SensorHelper`

Centralizes interaction with:

* Light sensor
* Ambient temperature sensor
* Accelerometer

### `LocationHelper`

Centralizes GPS update handling.

### `LightActivity`

Displays live ambient light and classification.

### `TemperatureActivity`

Displays ambient temperature when supported.

### `LocationActivity`

Obtains one current GPS location.

### `SpeedActivity`

Continuously monitors GPS position and speed.

### `CaptureActivity`

Combines multiple readings into one timestamped snapshot.

### `AverageLightActivity`

Calculates the average of the latest five light readings.

### `AccelerometerActivity`

Displays X/Y/Z acceleration and detects significant movement.

---

# 🎯 Project Objectives

The main objectives of InsightHub are:

* Learn Android sensor APIs.
* Understand `SensorManager`.
* Work with GPS and `LocationManager`.
* Implement runtime permissions.
* Process real-time sensor data.
* Perform simple calculations on sensor readings.
* Detect device movement using an accelerometer.
* Design a multi-screen Android application.
* Reduce duplicate sensor-management code using helper classes.
* Build a practical application around multiple Android hardware capabilities.

---

# 🔮 Future Improvements

Possible future additions include:

* 🌙 Complete dark-mode UI
* 🗺️ Google Maps integration
* 📈 Sensor graphs and charts
* 💾 Save readings locally
* ☁️ Cloud synchronization
* 📊 Historical sensor analytics
* 🔔 Custom speed alerts
* 📍 Location history
* 📤 Export readings as CSV/PDF
* 🔋 Battery-aware sensor monitoring
* 🎨 More advanced animations and UI

---

# 👨‍💻 Learning Outcomes

Through this project, the following Android concepts are practiced:

```text
Activities
     ↓
XML Layouts
     ↓
Event Handling
     ↓
SensorManager
     ↓
SensorEventListener
     ↓
LocationManager
     ↓
Runtime Permissions
     ↓
GPS Updates
     ↓
Real-Time Data Processing
     ↓
UI Updates
```

---

# ⚠️ Important Notes

* Not every Android phone contains an ambient temperature sensor.
* GPS accuracy depends on the device and surrounding environment.
* Sensor readings can vary between different devices.
* Location permission is required for GPS features.
* For realistic sensor testing, a physical Android device is recommended.
* The application stops sensor/GPS updates when an Activity is paused to avoid unnecessary resource usage.

---

# 📸 Screenshots

Add your application screenshots here after running the project:

```text
screenshots/
├── home.png
├── light.png
├── temperature.png
├── location.png
├── speed.png
├── capture.png
├── average.png
└── motion.png
```

Example:

```markdown
![Home Screen](screenshots/home.png)
```

---

# 📌 Project Information

**Project Name:** InsightHub
**Platform:** Android
**Language:** Java
**UI:** XML
**IDE:** Android Studio
**Application Type:** Sensor & Location Monitoring
**Minimum SDK:** 23

---

## ⭐ Project Summary

**InsightHub** is a multi-feature Android sensor application that demonstrates how smartphone hardware can be accessed, processed, and presented through a single intuitive interface.

It combines **environmental sensing, GPS tracking, speed monitoring, statistical processing, motion detection, and real-time data visualization** into one application.

> **One app. Multiple sensors. Complete insight.**

---

## 📄 License

This project is created for **educational and academic purposes**.
