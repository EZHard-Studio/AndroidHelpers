package ezhard.studio.helpers;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

import com.google.android.material.snackbar.Snackbar;


public class SnackbarHelper {

    public interface SnackbarHelperActions {
        void onActionClick();
    }


    /**
     * @implNote the default colors are: SUCCESS - Color.GREEN, ERROR - Color.RED, WARNING - Color.YELLOW, INFO - Color.BLUE
     * @param view is for the root view of the snackbar
     * @param type one of: SUCCESS, ERROR, WARNING, or INFO. use setSnackbarTypeColor to adjust the snackbar background color.
     * @param message the message to display at the snackbar
     * @param actionText for the label of the button to display at the snackbar, use null to hide the action button
     * @param actions an interface to use at the action click, pass null to use default action of dismissing the snackbar. every action result eventually in snackbar dismissing itself
     * @param duration , the duration to display the snack bar. its recommended to use Snackbar.LENGTH_SHORT, Snackbar.LENGTH_LONG or Snackbar.LENGTH_INDEFINITE.
     */
    public static void showSnackbar(@NonNull View view, @NonNull SnackbarType type, @NonNull String message, String actionText, SnackbarHelperActions actions, @NonNull int duration) {

        int backgroundColor = getColorForType(type, view.getContext());
        int textColor = getContrastingTextColor(backgroundColor);
        Snackbar snackbar = Snackbar.make(view, message, duration);
        ViewCompat.setBackgroundTintList(snackbar.getView(), ColorStateList.valueOf(backgroundColor)); // Set background tint
        if(actionText != null) {
            snackbar.setAction(actionText, v -> {
                if (actions != null) {
                    actions.onActionClick();
                }
                snackbar.dismiss();
            });
            snackbar.setActionTextColor(textColor);
        }

        // Set max lines for the Snackbar text view
        TextView snackbarTextView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        snackbarTextView.setMaxLines(Integer.MAX_VALUE);
        snackbarTextView.setTextColor(textColor);

        snackbar.show();

    }
    public static Snackbar buildSnackbar(@NonNull View view, @NonNull SnackbarType type, @NonNull String message, String actionText, SnackbarHelperActions actions, @NonNull int duration) {
        int backgroundColor = getColorForType(type, view.getContext());
        int textColor = getContrastingTextColor(backgroundColor);
        Snackbar snackbar = Snackbar.make(view, message, duration);
        ViewCompat.setBackgroundTintList(snackbar.getView(), ColorStateList.valueOf(backgroundColor)); // Set background tint
        if(actionText != null) {
            snackbar.setAction(actionText, v -> {
                if (actions != null) {
                    actions.onActionClick();
                }
                snackbar.dismiss();
            });
            snackbar.setActionTextColor(textColor);
        }

        // Set max lines for the Snackbar text view
        TextView snackbarTextView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        snackbarTextView.setMaxLines(Integer.MAX_VALUE);
        snackbarTextView.setTextColor(textColor);

        return snackbar;

    }



    private static final String PREF_SNACK_HELPER = "snackbarHelperPref";
    /**
     * @textColor  is automatically set to be contrast color to be as visible as it could
     * @implNote  the snackbar background color is controlled by SharedPrefrences, make sure to init the colors at the SplashScreen or any other designated loading scope to make sure the colors are defined even after memory wipe or new device installation
     * @param context is required for sharedPrefrences initialization
     * @param type which snackbar type used to set the color (SUCCESS, ERROR, WARNING, or INFO).
     * @param color the color to set
     */
    public static void setSnackbarTypeColor(Context context, SnackbarType type, int color){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_SNACK_HELPER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(type.toString(),color);
        editor.apply();
    }
    /**
     * @textColor  is automatically set to be contrast color to be as visible as it could
     * @implNote the snackbar background color is controlled by SharedPrefrences, make sure to init the colors at the SplashScreen or any other designated loading scope to make sure the colors are defined even after memory wipe or new device installation
     * @param context is required for sharedPrefrences initialization
     * @param colorSuccess the color to set for SUCCESS type snackbars
     * @param colorError the color to set for ERROR type snackbars
     * @param colorWarning the color to set for WARNING type snackbars
     * @param colorInfo the color to set for error INFO snackbars
     */
    public static void setSnackbarTypeColor(Context context, int colorSuccess,  int colorError, int colorWarning, int colorInfo){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_SNACK_HELPER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SnackbarType.SUCCESS.toString(),colorSuccess);
        editor.putInt(SnackbarType.ERROR.toString(),colorError);
        editor.putInt(SnackbarType.WARNING.toString(),colorWarning);
        editor.putInt(SnackbarType.INFO.toString(),colorInfo);

        editor.apply();
    }
    private static int getSnackbarTypeColor(Context context, SnackbarType type){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_SNACK_HELPER, Context.MODE_PRIVATE);
        int def = -1;
        switch (type){
            case ERROR:
                def = Color.RED;
                break;
            case WARNING:
                def = Color.YELLOW;
                break;
            case SUCCESS:
                def = Color.GREEN;
                break;
            case INFO:
                def = Color.BLUE;
                break;
            default:
                def = Color.GRAY;
                break;
        }
        return sharedPreferences.getInt(type.toString(),def);
    }



    private static int getColorForType(SnackbarType type, Context context) {
        return getSnackbarTypeColor(context,type);
    }

    private static int getContrastingTextColor(int backgroundColor) {
        // Calculate the perceived brightness of the background color
        double brightness = (Color.red(backgroundColor) * 299 +
                Color.green(backgroundColor) * 587 +
                Color.blue(backgroundColor) * 114) / 1000;

        // Determine the appropriate text color based on brightness
        return brightness < 128 ? Color.WHITE : Color.BLACK;
    }

    public enum SnackbarType {
        ERROR,
        WARNING,
        SUCCESS,
        INFO
    }
}
