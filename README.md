
# Android Helpers

this library is here to simplify and shorten the usage of basic and more advanced Android utilities. check the [Roadmap](#Roadmap) for all development goals and progress



## Roadmap

- [x]  [Snackbars](#Snackbars)
- [ ]  Shared Preferences
- [ ]  Alert Dialog
- [ ]  Notifications Builder
- [ ]  Basic Animations


## Installation & Implementations
  [![](https://jitpack.io/v/EZHard-Studio/AndroidHelpers.svg)](https://jitpack.io/#EZHard-Studio/AndroidHelpers)

### using gradle
Add it in your project settings.gradle at the end of repositories:
```bash
 dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }

    }
}
```
 Add the dependency at your app level build.gradle
```bash
 dependencies {
            ...
	        implementation 'com.github.EZHard-Studio:AndroidHelpers:[version]'
	}
```

### using maven
Add the JitPack repository to your build file
```bash
 <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
Add the dependency
```bash
<dependency>
	    <groupId>com.github.EZHard-Studio</groupId>
	    <artifactId>AndroidHelpers</artifactId>
	    <version>[version]</version>
	</dependency
```
## Usage/Examples

### Snackbars
set snackbar default colors:
```java
 SnackbarHelper.setSnackbarTypeColor(MainActivity.this,
                getColor(R.color.your_success_color),   // default Color.GREEN
                getColor(R.color.your_error_color),     // default Color.RED
                getColor(R.color.your_warning_color),   // default Color.YELLOW
                getColor(R.color.your_info_color));     // default Color.BLUE
```
or you can change one specific color at a time:
```java
 SnackbarHelper.setSnackbarTypeColor(MainActivity.this,
                SnackbarHelper.SnackbarType.SUCCESS,   // you can choose SUCSESS, ERROR, WARNING or INFO.
                getColor(R.color.your_success_color));
```
display snackbar with action:
```java
SnackbarHelper.showSnackbar(
                findViewById(R.id.container), //i would recommend to pass on the root view of the layout.
                SnackbarHelper.SnackbarType.SUCCESS,// you can choose SUCSESS, ERROR, WARNING or INFO.
                "display this message at the snackbar",
                "okey",
                () -> Toast.makeText(MainActivity.this, "snackbar dismissed", Toast.LENGTH_SHORT).show(),
                Snackbar.LENGTH_INDEFINITE);// you can choose to use Snackbar default durations, or just put the value to display in milliseconds
```
display snackbar with default dismiss action:
```java
 SnackbarHelper.showSnackbar(
                findViewById(R.id.container), //i would recommend to pass on the root view of the layout.
                SnackbarHelper.SnackbarType.SUCCESS,// you can choose SUCSESS, ERROR, WARNING or INFO.
                "display this message at the snackbar",
                "okey",
                null,
                Snackbar.LENGTH_INDEFINITE);// you can choose to use Snackbar default durations, or just put the value to display in milliseconds
```
display snackbar without any action: 
```java
 SnackbarHelper.showSnackbar(
                findViewById(R.id.container), //i would recommend to pass on the root view of the layout.
                SnackbarHelper.SnackbarType.SUCCESS,// you can choose SUCSESS, ERROR, WARNING or INFO.
                "display this message at the snackbar",
                null,
                null,
                Snackbar.LENGTH_SHORT);
//PAY ATTENTION!
// if you choose do hide any action from the snackbar. setting the duration to LENGTH_INDEFINITE
// would result in an undismissable snackbar.
``` 


