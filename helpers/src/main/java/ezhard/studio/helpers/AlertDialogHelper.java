package ezhard.studio.helpers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AlertDialogHelper {
    public interface DialogCallback {
        default void onPositiveClick() {}
        default void onNegativeClick(){}
        default void onItemClick(int position){}
        default void onItemSelected(int position){}
        default void onItemSelected(int position, boolean isChecked){}
    }
    public interface DateTimePickerCallback {
        default void onDateSelected(int year, int month, int day){}
        default void onTimeSelected(int hourOfDay, int minute) {}

    }
    /**
     * Displays a basic Yes/No AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param message             The message to be displayed in the dialog.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param dialogCallback      The callback interface to handle button clicks.
     */
    public static void showBasicAlertDialog(Context context, String title, String message,String positiveButtonLabel, String negativeButtonLabel, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    /**
     * Displays a basic Yes/No AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param message             The message to be displayed in the dialog.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param backgroundColor     The background color of the dialog.
     * @param dialogCallback      The callback interface to handle button clicks.
     */
    public static void showBasicAlertDialog(Context context, String title, String message, String positiveButtonLabel, String negativeButtonLabel, int backgroundColor, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Set background color
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        // Calculate text color based on background color
        int textColor = calculateBestTextColor(backgroundColor);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(textColor);
        }
        if (negativeButton != null) {
            negativeButton.setTextColor(textColor);
        }
    }
    /**
     * Displays a List AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param items               The array of items to display in the list.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param dialogCallback      The callback interface to handle button clicks and item selection.
     */
    public static void showListAlertDialog(Context context, String title, String[] items, String positiveButtonLabel, String negativeButtonLabel, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallback != null) {
                            dialogCallback.onItemClick(which);
                        }
                    }
                })
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });;
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    /**
     * Displays a List AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param items               The array of items to display in the list.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param backgroundColor     The background color of the dialog.
     * @param dialogCallback      The callback interface to handle button clicks and item selection.
     */
    public static void showListAlertDialog(Context context, String title, String[] items, String positiveButtonLabel, String negativeButtonLabel, int backgroundColor, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallback != null) {
                            dialogCallback.onItemClick(which);
                        }
                    }
                })
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Set background color
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        // Calculate text color based on background color
        int textColor = calculateBestTextColor(backgroundColor);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(textColor);
        }
        if (negativeButton != null) {
            negativeButton.setTextColor(textColor);
        }
    }
    /**
     * Displays a SingleChoice AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param items               The array of items to display in the list.
     * @param checkedItem         The index of the item that should be checked initially.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param dialogCallback      The callback interface to handle button clicks and item selection.
     */
    public static void showSingleChoiceAlertDialog(Context context, String title, String[] items, int checkedItem, String positiveButtonLabel, String negativeButtonLabel, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallback != null) {
                            dialogCallback.onItemSelected(which);
                        }
                    }
                })
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    /**
     * Displays a SingleChoice AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param items               The array of items to display in the list.
     * @param checkedItem         The index of the item that should be checked initially.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param backgroundColor     The background color of the dialog.
     * @param dialogCallback      The callback interface to handle button clicks and item selection.
     */
    public static void showSingleChoiceAlertDialog(Context context, String title, String[] items, int checkedItem, String positiveButtonLabel, String negativeButtonLabel, int backgroundColor, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogCallback != null) {
                            dialogCallback.onItemSelected(which);
                        }
                    }
                })
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Set background color
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        // Calculate text color based on background color
        int textColor = calculateBestTextColor(backgroundColor);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(textColor);
        }
        if (negativeButton != null) {
            negativeButton.setTextColor(textColor);
        }
    }
    /**
     * Displays a MultiChoice AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param items               The array of items to display in the list.
     * @param checkedItems        The boolean array indicating the checked state of each item.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param dialogCallback      The callback interface to handle button clicks and item selection.
     */
    public static void showMultiChoiceAlertDialog(Context context, String title, String[] items, boolean[] checkedItems, String positiveButtonLabel, String negativeButtonLabel, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (dialogCallback != null) {
                            dialogCallback.onItemSelected(which, isChecked);
                        }
                    }
                })
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    /**
     * Displays a MultiChoice AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param title               The title of the dialog.
     * @param items               The array of items to display in the list.
     * @param checkedItems        The boolean array indicating the checked state of each item.
     * @param positiveButtonLabel The label for the positive button.
     * @param negativeButtonLabel The label for the negative button.
     * @param backgroundColor     The background color of the dialog.
     * @param dialogCallback      The callback interface to handle button clicks and item selection.
     */
    public static void showMultiChoiceAlertDialog(Context context, String title, String[] items, boolean[] checkedItems, String positiveButtonLabel, String negativeButtonLabel, int backgroundColor, final DialogCallback dialogCallback) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title)
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (dialogCallback != null) {
                            dialogCallback.onItemSelected(which, isChecked);
                        }
                    }
                })
                .setPositiveButton(positiveButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onPositiveClick();
                        }
                    }
                })
                .setNegativeButton(negativeButtonLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogCallback != null) {
                            dialogCallback.onNegativeClick();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Set background color
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        // Calculate text color based on background color
        int textColor = calculateBestTextColor(backgroundColor);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(textColor);
        }
        if (negativeButton != null) {
            negativeButton.setTextColor(textColor);
        }
    }

    /**
     * Displays a DatePicker AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param year                The initially selected year.
     * @param month               The initially selected month (0-11).
     * @param day                 The initially selected day of the month.
     * @param datePickerCallback The callback interface to handle date selection.
     */
    public static void showDatePickerDialog(Context context, int year, int month, int day, final DateTimePickerCallback datePickerCallback) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (datePickerCallback != null) {
                    datePickerCallback.onDateSelected(year, monthOfYear, dayOfMonth);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    /**
     * Displays a DatePicker AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param year                The initially selected year.
     * @param month               The initially selected month (0-11).
     * @param day                 The initially selected day of the month.
     * @param backgroundColor     The background color of the dialog.
     * @param datePickerCallback The callback interface to handle date selection.
     */
    public static void showDatePickerDialog(Context context, int year, int month, int day, int backgroundColor, final DateTimePickerCallback datePickerCallback) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (datePickerCallback != null) {
                    datePickerCallback.onDateSelected(year, monthOfYear, dayOfMonth);
                }
            }
        }, year, month, day);

        datePickerDialog.show();

        // Set background color
        Window window = datePickerDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        // Calculate text color based on background color
        int textColor = calculateBestTextColor(backgroundColor);
        Button positiveButton = datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(textColor);
        }
        if (negativeButton != null) {
            negativeButton.setTextColor(textColor);
        }
    }

    /**
     * Displays a TimePicker AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param hour                The initially selected hour (0-23).
     * @param minute              The initially selected minute (0-59).
     * @param timePickerCallback  The callback interface to handle time selection.
     */
    public static void showTimePickerDialog(Context context, int hour, int minute, final DateTimePickerCallback timePickerCallback) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (timePickerCallback != null) {
                    timePickerCallback.onTimeSelected(hourOfDay, minute);
                }
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
    /**
     * Displays a TimePicker AlertDialog.
     *
     * @param context             The context in which the dialog should be shown.
     * @param hour                The initially selected hour (0-23).
     * @param minute              The initially selected minute (0-59).
     * @param backgroundColor     The background color of the dialog.
     * @param timePickerCallback  The callback interface to handle time selection.
     */
    public static void showTimePickerDialog(Context context, int hour, int minute, int backgroundColor, final DateTimePickerCallback timePickerCallback) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (timePickerCallback != null) {
                    timePickerCallback.onTimeSelected(hourOfDay, minute);
                }
            }
        }, hour, minute, false);

        timePickerDialog.show();

        // Set background color
        Window window = timePickerDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        }

        // Calculate text color based on background color
        int textColor = calculateBestTextColor(backgroundColor);
        Button positiveButton = timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(textColor);
        }
        if (negativeButton != null) {
            negativeButton.setTextColor(textColor);
        }
    }


    private static int calculateBestTextColor(int backgroundColor) {
        int red = Color.red(backgroundColor);
        int green = Color.green(backgroundColor);
        int blue = Color.blue(backgroundColor);
        double luminance = (0.299 * red + 0.587 * green + 0.114 * blue) / 255;
        if (luminance > 0.5) {
            return Color.BLACK; // Use black text color for light backgrounds
        } else {
            return Color.WHITE; // Use white text color for dark backgrounds
        }
    }

}
