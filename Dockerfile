FROM openjdk:17-slim

# Cài các công cụ cần thiết
RUN apt-get update && apt-get install -y \
    wget unzip git curl zip lib32stdc++6 lib32z1

# Thiết lập thư mục Android SDK
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools

# Tải và cài Android Command Line Tools
RUN mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O /tmp/tools.zip && \
    unzip /tmp/tools.zip -d ${ANDROID_SDK_ROOT}/cmdline-tools && \
    mv ${ANDROID_SDK_ROOT}/cmdline-tools/cmdline-tools ${ANDROID_SDK_ROOT}/cmdline-tools/latest && \
    rm /tmp/tools.zip

# Accept license và cài nền tảng SDK + build tools
RUN yes | sdkmanager --licenses && \
    sdkmanager "platform-tools" \
               "platforms;android-34" \
               "build-tools;34.0.0" \
               "ndk;25.2.9519653"

# Copy project vào Docker container
WORKDIR /app
COPY . /app

# Cho phép thực thi Gradle wrapper
RUN chmod +x ./gradlew

# Build APK
RUN ./gradlew clean assembleDebug
