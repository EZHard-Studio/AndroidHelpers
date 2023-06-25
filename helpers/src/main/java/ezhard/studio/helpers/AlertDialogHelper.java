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
