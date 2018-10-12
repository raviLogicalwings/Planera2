package com.planera.mis.planera2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.planera.mis.planera2.R;
import com.planera.mis.planera2.activities.models.Doctors;
import com.planera.mis.planera2.activities.models.GooglePlacesModel.GooglePlaces;
import com.planera.mis.planera2.activities.utils.AppConstants;
import com.planera.mis.planera2.activities.utils.RuntimePermissionCheck;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUploadDoctor extends BaseActivity implements View.OnClickListener{

    public static ActivityUploadDoctor instance;
    private CardView cardUploadPlanView;
    private ImageView imageSheetView;
    private Button buttonUploadPlanSheet;
    private RuntimePermissionCheck permissionCheck;
    private String displayName;
    private TextView textFileName;
    private ImageView imageClose;
    private List<Doctors> listDoctors;
    private Doctors doctors;
    public static final String TAG = ActivityUploadDoctor.class.getSimpleName();
    private File myFile;
    private boolean isCellNull = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmend_upload_plan);
        initUi();
        initData();


    }

    @Override
    public void initUi() {
        super.initUi();
        cardUploadPlanView = findViewById(R.id.card_upload_Plan_view);
        imageSheetView = findViewById(R.id.image_sheet_view);
        buttonUploadPlanSheet = findViewById(R.id.button_upload_plan_sheet);
        textFileName = findViewById(R.id.text_file_name);
        imageClose = findViewById(R.id.img_close);
        imageSheetView.setOnClickListener(this);
        cardUploadPlanView.setOnClickListener(this);
        buttonUploadPlanSheet.setOnClickListener(this);
    }

    public void checkRequiredPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Do the file write
            pickFile();
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
            try {
                permissionCheck.requestPermissionForWriteExternalStorage();
                permissionCheck.requestPermissionForReadExtertalStorage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void pickFile(){
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Set your required file type
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"), 100);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
//        startActivityForResult(Intent.createChooser(intent, "Choose CSV"), 100);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = null;
                    if (data != null) {
                        uri = data.getData();
                    }
                    String uriString = uri.toString();
                    myFile = new File(uriString);

                    readExcelData(uri);
                    String path = myFile.getAbsolutePath();
                    Log.e(TAG, "FILE PATH"+path);
                    displayName = null;



//                    Toast.makeText(ActivityUploadDoctor.this, myFile.toString(), Toast.LENGTH_LONG).show();


                    if (uriString.startsWith("content://")) {
                        Cursor cursor = null;
                        try {
                            cursor = ActivityUploadDoctor.this.getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                toggleView(displayName);
                            }
                        } finally {
                            cursor.close();
                        }
                    } else if (uriString.startsWith("file://")) {
                        displayName = myFile.getName();
                        toggleView(displayName);

//                        Toast.makeText(this, displayName, Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void toggleView(String name){
        if(name!= null){
            cardUploadPlanView.setVisibility(View.GONE);
            imageSheetView.setVisibility(View.VISIBLE);
            textFileName.setVisibility(View.VISIBLE);
            textFileName.setText(name);

        }
    }

    @Override
    public void initData() {
        super.initData();
        listDoctors = new ArrayList<>();

        permissionCheck = new RuntimePermissionCheck(ActivityUploadDoctor.this);
    }


    private void readExcelData(Uri uri) {

            Toast.makeText(this, "Working fine", Toast.LENGTH_LONG).show();
            try {
//            File fileB = new File(filePath);
                InputStream inputStream = getContentResolver().openInputStream(uri);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null){
//                    stringBuilder.append(line);
//                }
//
//                inputStream.close();
//              Log.e("String Result", stringBuilder.toString());






//
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);
                int rowsCount = sheet.getPhysicalNumberOfRows();
                Toast.makeText(this, "rowCount"+rowsCount, Toast.LENGTH_LONG).show();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                StringBuilder sb = new StringBuilder();
                //loop, loops through rows
                for (int r = 1; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    int cellsCount = row.getPhysicalNumberOfCells();

                    //inner loop, loops through columns
                    if (isCellNull){
                        break;
                    }
                    for (int c = 0; c < cellsCount; c++) {
                        String value = getCellAsString(row, c, formulaEvaluator);
                        doctors = new Doctors();
                        if (c==0 && value.isEmpty()){
                            isCellNull = true;
                            break;
                        }
                        if (c== AppConstants.PATCH_ID_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setPatchId(value);
                            }
                        }
                        if (c == AppConstants.FIRST_NAME_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setFirstName(value);
                            }
                        }
                        if (c == AppConstants.MIDDLE_NAME_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setMiddleName(value);
                            }
                        }
                        if (c == AppConstants.LAST_NAME_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setLastName(value);
                            }
                        }
                        if (c == AppConstants.DOB_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setDOB(value);
                            }
                        }
                        if (c == AppConstants.EMAIL_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setEmail(value);
                            }
                        }
                        if (c == AppConstants.QUALIFICATION_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setQualifications(value);
                            }
                        }
                        if (c == AppConstants.SPECIALIZATION_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setSpecializations(value);
                            }
                        }
                        if (c == AppConstants.PREFERRED_MEET_TIME_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setPreferredMeetTime(value);
                            }
                        }
                        if (c == AppConstants.MEET_FREQUENCY_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setMeetFrequency(value);
                            }
                        }
                        if (c == AppConstants.PHONE_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setPhone(value);
                            }
                        }
                        if (c == AppConstants.ADDRESS_1_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setAddress1(value);
                            }
                        }
                        if (c == AppConstants.ADDRESS_2_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setAddress2(value);
                            }
                        }
                        if (c == AppConstants.ADDRESS_3_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setAddress3(value);
                            }
                        }
                        if (c == AppConstants.ADDRESS_4_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setAddress4(value);
                            }
                        }
                        if (c == AppConstants.CITY_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setCity(value);
                            }
                        }
                        if (c == AppConstants.DISTRICT_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setDistrict(value);
                            }
                        }
                        if (c == AppConstants.STATE_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setState(value);
                            }
                        }

                        if (c == AppConstants.PINCODE_EXCEL){
                            if (!value.isEmpty()){
                                doctors.setPincode(value);
                            }
                        }

                        else{

                            String cellInfo = "r:" + r + "; c:" + c + "; v:" + value;
                            Log.d(TAG, "readExcelData: Data from row: " + cellInfo);
                            sb.append(value + ", ");
                            Toast.makeText(this, new String(sb), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                sb.append(":");

            }catch (Exception e) {
                Log.e(TAG, "readExcelData: FileNotFoundException. " + e.getMessage() );
            }



    }


    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {

            Log.e(TAG, "getCellAsString: NullPointerException: " + e.getMessage() );
        }
        return value;
    }

    public void getAddressLatLong(String input) {
        processDialog.showDialog(ActivityUploadDoctor.this, false);
        Call<GooglePlaces> call = apbInterfaceForGooglePlaces.getPlaceLatLong(input, AppConstants.INPUT_TYPE, AppConstants.FIELDS, AppConstants.KEY_GOOGLE_PLACES);
        call.enqueue(new Callback<GooglePlaces>() {
            @Override
            public void onResponse(Call<GooglePlaces> call, Response<GooglePlaces> response) {
                processDialog.dismissDialog();
                Log.e("AddDoctorActivity", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body().getStatus().equals(AppConstants.STATUS_OK)) {
                    if (doctors != null) {
                        doctors.setLatitude(response.body().getCandidates().get(0).getGeometry().getLocation().getLat() + "");
                        doctors.setLongitude(response.body().getCandidates().get(0).getGeometry().getLocation().getLng() + "");
                        Log.e("Doctors Object", "onResponse: " + new Gson().toJson(doctors));

                    }
                } else if (response.body().getStatus().equals(AppConstants.STATUS_ZERO_RESULTS)) {
                    Snackbar.make(rootView, "Address not found, Please Try again", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(rootView, "Query Over Limit ! You have exceeded your daily request quota for this API. " +
                            "If you did not set a custom daily request quota, verify your project has an active billing account: http://g.co/dev/maps-no-account", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GooglePlaces> call, Throwable t) {
                processDialog.dismissDialog();
                Log.e("AddDoctorActivity", "onFailure: " + t.getMessage());
                Toast.makeText(ActivityUploadDoctor.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_upload_Plan_view:
                checkRequiredPermission();
                break;
            case R.id.button_upload_plan_sheet:
                break;
            case R.id.image_sheet_view:
                checkRequiredPermission();
                break;
            case R.id.text_file_name:
                break;
        }
    }
}