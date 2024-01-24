package com.yoga.app.helpers;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Base64;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.yoga.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Common {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    static String PANPattern = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$";

    public static String validateString(String string) {
        if (string != null) {
            return string;
        } else return "";
    }

    public static boolean isOnline() {
        ConnectivityManager connectivity = (ConnectivityManager) AppConfig.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static Boolean isEmpty(TextInputLayout textInputLayout, String errMsg) {
        if (textInputLayout.getEditText().getText().toString().trim().equals("")) {
            textInputLayout.setHintTextColor(ColorStateList.valueOf(Color.RED));
            if (errMsg == null)
                textInputLayout.setError("Field required");
            else textInputLayout.setError(errMsg);
            return true;
        } else {
            textInputLayout.setHintTextColor(ColorStateList.valueOf(getColor(R.color.gray_dark)));
            textInputLayout.setErrorEnabled(false);
            return false;
        }
    }

/*
    public static void setDarkMode(Boolean enabled) {
        if (enabled) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        SharedPrefs.setBoolean(SharedPrefs.ISINDARK, enabled);
    }
*/

    public static void makeToast(String msg) {
        Toast.makeText(AppConfig.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void openSheet(BottomSheetDialogFragment sheet) {
        if (AppConfig.getCurrentActivity() != null) {
            if (AppConfig.getCurrentActivity().getSupportFragmentManager().findFragmentByTag(sheet.getClass().getSimpleName()) == null)
                sheet.show(AppConfig.getCurrentActivity().getSupportFragmentManager(), sheet.getClass().getSimpleName());
            else
                AppConfig.getCurrentActivity().getSupportFragmentManager().beginTransaction().show(sheet);
        }
    }

    public static void openFragment(DialogFragment fragment) {
        if (AppConfig.getCurrentActivity() != null) {
            FragmentTransaction fragmentTransaction = AppConfig.getCurrentActivity().getSupportFragmentManager().beginTransaction();
            if (AppConfig.getCurrentActivity().getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName()) == null)
                fragment.show(AppConfig.getCurrentActivity().getSupportFragmentManager(), fragment.getClass().getSimpleName());
            else fragmentTransaction.show(fragment).commitAllowingStateLoss();
        }
    }

    public static int getColor(int color) {
        return ContextCompat.getColor(AppConfig.getCurrentActivity(), color);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date parseDate(String strDate, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            makeToast("Incorrect Date Format");
        }
        return date;
    }

    public static String correctDate(int day, int month, int year, String pattern) {
        String strDay, strMonth, strYear;
        month = month + 1;
        if (month < 10) {
            strMonth = "0" + month;
        } else strMonth = String.valueOf(month);
        if (day < 10) {
            strDay = "0" + day;
        } else strDay = String.valueOf(day);
        strYear = String.valueOf(year);
        String date = strDay + "/" + strMonth + "/" + strYear;
        Date date1 = parseDate(date, "dd/MM/yyyy");
        String strDate = formatDate(date1, pattern);
        return strDate;
    }

    public static void setDateListener(Context context, EditText etFromDate, EditText etToDate, String pattern) {
        Calendar calendar = Calendar.getInstance();
        if (pattern == null)
            pattern = "yyyy-MM-dd";
        String finalPattern = pattern;
        if (etFromDate != null) {
            etFromDate.setText(formatDate(new Date(), pattern));
            etFromDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog picker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            etFromDate.setText(Common.correctDate(i2, i1, i, finalPattern));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    picker.show();
                }
            });
        }
        if (etToDate != null) {
            etToDate.setText(formatDate(new Date(), pattern));
            etToDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog picker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            etToDate.setText(Common.correctDate(i2, i1, i, finalPattern));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    picker.show();
                }
            });
        }
    }

    public static void performHapticFeedback(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }

    public static boolean checkStoragePermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static Bitmap decodeImage(String imageString) {
        String base64 = imageString;
        if (imageString.contains(",")) {
            base64 = imageString.split(",")[1];
        }
        byte[] decodedString2 = Base64.decode(base64, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        return bitmap;
    }

    @BindingAdapter({"adapter"})
    public static void setRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter recyclerViewAdapter) {
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public static boolean isPANValid(String panNo) {
        return panNo.matches(PANPattern);
    }

    public static boolean isEmailValid(String email) {
        return email.matches(emailPattern);
    }

    public static boolean isMobileValid(String mobile) {
        return mobile.length() == 10;
    }


}
