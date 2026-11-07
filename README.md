Cursorify

Your mouse. Your cursor. Your style.

Cursorify is an open-source Android application that lets you customize the appearance of your mouse cursor when using a physical mouse or trackball.

Originally based on "OpenMouse" (https://github.com/owenpkent/openmouse), Cursorify is being developed to provide a more customizable cursor experience, with support for user-selected images and further improvements planned.

Features

Currently available

- Custom cursor image selection from your device
- Support for image files, including PNG and JPG
- Experimental GIF support
- Cursor overlay that follows mouse movement
- Adjustable cursor size
- Android Accessibility Service integration
- Works with physical USB OTG and Bluetooth mice

In development

- Replacing the native Android mouse pointer instead of displaying an image underneath it
- Shizuku integration for privileged system-level cursor customization
- Improved animated GIF support
- More cursor appearance settings
- Additional customization options

«Development status: Cursorify is an experimental project. Some features are still being developed and may not work correctly on every Android device.»

How it works

Cursorify uses Android's Accessibility Service to observe mouse movement and display a customizable cursor overlay.

The project is also exploring Shizuku integration to communicate with Android's system-level input services. The goal is to replace the native mouse pointer with the user's chosen cursor image, rather than simply drawing another cursor on top of it.

Requirements

- Android 7.0 or newer
- A physical USB OTG or Bluetooth mouse
- Accessibility Service enabled for Cursorify

Some advanced features may require "Shizuku" (https://shizuku.rikka.app/).

Installation

1. Download the latest available APK from the project's GitHub Actions artifacts or Releases, when available.
2. Install the APK on your Android device.
3. Open Cursorify.
4. Enable the Cursorify Accessibility Service when prompted.
5. Connect a physical mouse and start customizing your cursor.

Building

The project can be built using Android Studio or a local Android development environment with JDK 17+ and the Android SDK.

To build a debug APK:

./gradlew assembleDebug

To run unit tests:

./gradlew test

Project background

Cursorify is a fork of "OpenMouse" (https://github.com/owenpkent/openmouse), originally created by Owen Kent.

The original project provided the foundation for mouse accessibility features and cursor tracking. Cursorify continues from that foundation with a focus on visual customization and system-level cursor replacement.

Cursorify is an independent project and is not affiliated with or endorsed by the original OpenMouse author.

Contributing

Suggestions, bug reports, and contributions are welcome!

If you find a bug or have an idea for a new feature, feel free to open an issue or submit a pull request.

License

This project retains the original project's MIT license.

See "LICENSE" (LICENSE) for details.
