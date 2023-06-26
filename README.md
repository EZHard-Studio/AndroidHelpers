
# Android Helpers

this library is here to simplify and shorten the usage of basic and more advanced Android utilities. check the [Roadmap](#Roadmap) for all development goals and progress



## Roadmap

- [x]  [Snackbars](#Snackbars)
- [x]  [Shared Preferences](#Shared-Preferences)
- [x]  [Alert Dialog](#Alert-Dialogs)
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
#### using `SnackbarHelper` static functions to show and build snackbars.

static class name: SnackbarHelper\
available static functions:
- showSnackbar
- buildSnackbar

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `view` | `View` | the root view to anchor Snackbar|
| `type` | `SnackbarType` | determains the background color, either SUCCESS, ERROR, WARNING or INFO |
| `message` | `String`| the message to display at the Snackbar | 
| `actionText`| `String` | if `null` action button hidden, otherwise, the action button label equals the `actionText` value |
|`actions` | `SnackbarHelperActions` | interface to pass on your actions, if null default action is to dismiss snackbar |
|`duration`|`int`| the duration to display the snackbar. use Snackbar.LENGTH_INDEFINITE to display snackbar indefinatly|

#### Examples:
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
you can use a builder function to get a snackbar instance instead of just showing it, just replace `showSnackbar` to `buildSnackbar`. the `buildSnackbar` function return a snackbar instance.

### Shared Preferences
#### using `SharedPreferencesHelper` to construct instances.

constractor overview:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context` | required for SharedPreferences initialization |
| `prefType`| `String` | the id for the desired SharedPreferences |
|`keepInstance`| `boolean` | if true, the instance of SharedPreferences is saved in an internal static map, query the desired SharedPreferences using `prefType` as key |

#### Examples:
standart initialization and usage:
```java
SharedPreferencesHelper helper = new SharedPreferencesHelper(this,"pref1",false); 
//setting primitive data
helper.setInt("intKey",0);
helper.setFloat("floatKey",0f);
helper.setLong("longKey",0l);
helper.setString("stringKey","value");
helper.setBoolean("booleanKey",true);

//getting primitive data, second argument is default value.
helper.getInt("intKey",0); 
helper.getFloat("floatKey",0f);
helper.getLong("longKey",0l);
helper.getString("stringKey","value");
helper.getBoolean("booleanKey",true);
```

instance map initialization and usage
```java
new SharedPreferencesHelper(this,"pref1",true);
  
//setting primitive data
SharedPreferencesHelper.instances.get("pref1").setInt("intKey",0);
SharedPreferencesHelper.instances.get("pref1").setFloat("floatKey",0f);
SharedPreferencesHelper.instances.get("pref1").setLong("longKey",0l);
SharedPreferencesHelper.instances.get("pref1").setString("stringKey","value");
SharedPreferencesHelper.instances.get("pref1").setBoolean("booleanKey",true);

//getting primitive data, second argument is default value.
SharedPreferencesHelper.instances.get("pref1").getInt("intKey",0);
SharedPreferencesHelper.instances.get("pref1").getFloat("floatKey",0f);
SharedPreferencesHelper.instances.get("pref1").getLong("longKey",0l);
SharedPreferencesHelper.instances.get("pref1").getString("stringKey","value");
SharedPreferencesHelper.instances.get("pref1").getBoolean("booleanKey",true);
```

#### setting and getting custom object:
Gson library is required:
```bash
implementation 'com.google.code.gson:gson:2.9.0'
```
you can use these functions on SharedPreferencesHelper instances
```java
setObject("objKey",new Obj(0,"str",true));
getObject("objkey",new TypeToken<Obj>() {}.getType(), new Obj(0,"str",true));
```
because the implementation of the set and get depends of turning the object to and from a Json, these functions can throw `SharedPreferencesHelperException` when the object you requested to set or get cannot turn to json successfully


### Alert Dialogs
#### using `AlertDialogHelper` static functions to show different typs of Dialogs.

static class name: AlertDialogHelper\
available static functions:
- [showBasicAlertDialog](#showBasicAlertDialog:)
- [showListAlertDialog](#showListAlertDialog:)
- [showSingleChoiceAlertDialog](#showSingleChoiceAlertDialog:)
- [showMultiChoiceAlertDialog](#showMultiChoiceAlertDialog:)
- [showDatePickerDialog](#showDatePickerDialog:)
- [showTimePickerDialog](#showTimePickerDialog:)


#### showBasicAlertDialog:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context`| The context in which the dialog should be shown.|
| `title` | `String` | title of the dialog|
| `message` | `String` | message of the dialog | 
| `positiveButtonLabel` | `String` | label for positive button (e.g. "Yes")|
| `negativeButtonLabel` | `String` | label for negative button (e.g. "No")|
| `backgroundColor` | `int` | *OPTIONAL, setting the dialog background color, and automatically calculate best color for text accordingly |
| `dialogCallback` | `BasicDialogCallback` | callback for dialog positive \ negative button click |


```java
AlertDialogHelper.showBasicAlertDialog(context, title, message, positiveButtonLabel, negativeButtonLabel, new AlertDialogHelper.DialogCallback() {
            @Override
            public void onPositiveClick() {
            }

            @Override
            public void onNegativeClick() {
            }
        });
```

#### showListAlertDialog:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context`| The context in which the dialog should be shown.|
| `title` | `String` | title of the dialog|
| `items` | `String[]` | array of items to display at dialog | 
| `backgroundColor` | `int` | *OPTIONAL, setting the dialog background color, and automatically calculate best color for text accordingly |
| `dialogCallback` | `ListDialogCallback` | callback for dialog item click action, return item index |

```java
AlertDialogHelper.showListAlertDialog(context, title, [item1,item2,item3],
 new AlertDialogHelper.DialogCallback() {
            @Override
             public void onItemClick(int position) {
            }
        });
```

#### showSingleChoiceAlertDialog:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context`| The context in which the dialog should be shown.|
| `title` | `String` | title of the dialog|
| `items` | `String[]` | array of items to display at dialog | 
| `checkedItem` | `int` | default index of selected item | 
| `backgroundColor` | `int` | *OPTIONAL, setting the dialog background color, and automatically calculate best color for text accordingly |
| `dialogCallback` | `SingleChoiceDialogCallback` | callback for dialog item select action, return item index |

```java
AlertDialogHelper.showSingleChoiceAlertDialog(context, title, [item1,item2,item3], defaultSelectionIndex, 
 new AlertDialogHelper.SingleChoiceDialogCallback() {
           @Override
           public void onItemSelected(int position) {
           }
       });
```

#### showMultiChoiceAlertDialog:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context`| The context in which the dialog should be shown.|
| `title` | `String` | title of the dialog|
| `items` | `String[]` | array of items to display at dialog | 
| `checkedItems` | `boolean[]` | must be same length of items, indicate default state of each item index respectively |
| `positiveButtonLabel` | `String` | label for positive button (e.g. "Yes")|
| `negativeButtonLabel` | `String` | label for negative button (e.g. "No")|
| `backgroundColor` | `int` | *OPTIONAL, setting the dialog background color, and automatically calculate best color for text accordingly |
| `dialogCallback` | `MultiChoiceDialogCallback` | callback for dialog item select action, onPositiveClick returns array of the selected items indexes|

```java
AlertDialogHelper.showMultiChoiceAlertDialog(context, title, [item1,item2,item3], [false,false,false], positiveButtonLabel, negativeButtonLabel,  
 new AlertDialogHelper.MultiChoiceDialogCallback() {
           @Override
           public void onItemSelected(int position, boolean isChecked) {
           }

           @Override
           public void onPositiveClick(Integer[] selectedItemsIndexes) {
           }

           @Override
           public void onNegativeClick() {
           }
       });
```

#### showDatePickerDialog:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context`| The context in which the dialog should be shown.|
| `year` | `int` |  The initially selected year|
| `month` | `int` | The initially selected month (0-11).| 
| `day` | `int` |The initially selected day of the month. |
| `backgroundColor` | `int` | *OPTIONAL, setting the dialog background color, and automatically calculate best color for text accordingly |
| `dialogCallback` | `DatePickerCallback` | after date selected, interface callback returning year, monnth and day|

```java
AlertDialogHelper.showDatePickerDialog(contextr, year, month, day, new AlertDialogHelper.DatePickerCallback() {
           @Override
           public void onDateSelected(int year, int month, int day) {

           }
       });
```

#### showTimePickerDialog:
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `context` | `Context`| The context in which the dialog should be shown.|
| `hour` | `int` |  The initially selected hour (0-23).|
| `minute` | `int` | The initially selected minute (0-59)| 
| `backgroundColor` | `int` | *OPTIONAL, setting the dialog background color, and automatically calculate best color for text accordingly |
| `dialogCallback` | `TimePickerCallback` | after date selected, interface callback returning hourOfDay and minute|

```java
  AlertDialogHelper.showTimePickerDialog(context, hour, minute, new AlertDialogHelper.TimePickerCallback() {
           @Override
           public void onTimeSelected(int hourOfDay, int minute) {

           }
       });
```
## Authors

- [@EZHard.studio](https://github.com/EZHard-Studio)

