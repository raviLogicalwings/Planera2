package com.planera.mis.planera2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.planera.mis.planera2.R;
import com.planera.mis.planera2.models.ChemistResponse;
import com.planera.mis.planera2.models.Chemists;
import com.planera.mis.planera2.models.GooglePlacesModel.GooglePlaces;
import com.planera.mis.planera2.models.PatchListResponse;
import com.planera.mis.planera2.models.Patches;
import com.planera.mis.planera2.utils.AppConstants;
import com.planera.mis.planera2.utils.InternetConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAddChemist extends BaseActivity implements View.OnClickListener {
    protected Toolbar toolbar;
    private EditText textChemistCompanyName;
    private EditText textChemistMonthlyVolume;
    private EditText textChemistShopSize;
    private EditText textChemistFirstName;
    private EditText textChemistMiddleName;
    private EditText textChemistLastName;
    private EditText textChemistEmail;
    protected Spinner spinnerMeetTime;
    private Spinner spinnerPatchId;
    private EditText textChemistPhone;
    private EditText textChemistAddress1;
    private EditText textChemistAddress2;
    private EditText textChemistAddress3;
    private EditText textChemistAddress4;
    private EditText textChemistDistrict;
    private EditText textChemistCity;
    private EditText textChemistState;
    private EditText textChemistPinCode;
    private EditText textChemistBillingPhone1;
    private EditText textChemistBillingPhone2;
    private EditText textChemistBillingEmail;
    private EditText textChemistRating;
    private TextInputLayout inputLayoutCompanyNameChemist;
    private TextInputLayout inputLayoutMonthlyVolumeChemist;
    private TextInputLayout inputLayoutShopSizeChemist;
    private TextInputLayout inputLayoutFirstNameChemist;
    protected TextInputLayout inputLayoutMiddleNameChemist;
    private TextInputLayout inputLayoutLastNameChemist;
    private TextInputLayout inputLayoutEmailChemist;
    private TextInputLayout inputLayoutPhoneChemist;
    private TextInputLayout inputLayoutAddress1Chemist;
    private TextInputLayout inputLayoutAddress2Chemist;
    private TextInputLayout inputLayoutAddress3Chemist;
    private TextInputLayout inputLayoutAddress4Chemist;
    private TextInputLayout inputLayoutDistrictChemist;
    private TextInputLayout inputLayoutCityChemist;
    private TextInputLayout inputLayoutStateChemist;
    private TextInputLayout inputLayoutPincodeChemist;
    private TextInputLayout inputLayoutBillingPhone1Chemist;
    private TextInputLayout inputLayoutBillingPhone2Chemist;
    private TextInputLayout inputLayoutBillingEmailChemist;
    private TextInputLayout inputLayoutRatingChemist;
    protected Button buttonAddChemist;
    private List<String> preferredMeetTime;
    private List<Patches> patches;
    private Chemists chemists;
    private int patchId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chemist);
        initUi();
        initData();
    }

    @Override
    public void initUi() {
        super.initUi();

        toolbar = findViewById(R.id.toolbar);
        inputLayoutCompanyNameChemist = findViewById(R.id.input_layout_company_name_chemist);
        inputLayoutMonthlyVolumeChemist = findViewById(R.id.input_layout_monthly_volume_chemist);
        inputLayoutShopSizeChemist = findViewById(R.id.input_layout_shop_size_chemist);
        inputLayoutFirstNameChemist = findViewById(R.id.input_layout_first_name_chemist);
        inputLayoutMiddleNameChemist = findViewById(R.id.input_layout_middle_name_chemist);
        inputLayoutLastNameChemist = findViewById(R.id.input_layout_last_name_chemist);
        inputLayoutEmailChemist = findViewById(R.id.input_layout_email_chemist);
        inputLayoutPhoneChemist = findViewById(R.id.input_layout_phone_chemist);
        inputLayoutAddress1Chemist = findViewById(R.id.input_layout_address_1_chemist);
        inputLayoutAddress2Chemist = findViewById(R.id.input_layout_address_2_chemist);
        inputLayoutAddress3Chemist = findViewById(R.id.input_layout_address_3_chemist);
        inputLayoutAddress4Chemist = findViewById(R.id.input_layout_address_4_chemist);
        inputLayoutDistrictChemist = findViewById(R.id.input_layout_district_chemist);
        inputLayoutCityChemist = findViewById(R.id.input_layout_city_chemist);
        inputLayoutStateChemist = findViewById(R.id.input_layout_state_chemist);
        inputLayoutPincodeChemist = findViewById(R.id.input_layout_pincode_chemist);
        inputLayoutBillingPhone1Chemist = findViewById(R.id.input_layout_billing_phone1_chemist);
        inputLayoutBillingPhone2Chemist = findViewById(R.id.input_layout_billing_phone2_chemist);
        inputLayoutBillingEmailChemist = findViewById(R.id.input_layout_billing_email_chemist);
        inputLayoutRatingChemist = findViewById(R.id.input_layout_rating_chemist);


        textChemistCompanyName = findViewById(R.id.text_chemist_company_name);
        textChemistMonthlyVolume = findViewById(R.id.text_chemist_monthly_volume);
        textChemistShopSize = findViewById(R.id.text_chemist_shop);
        textChemistFirstName = findViewById(R.id.text_chemist_first_name);
        textChemistMiddleName = findViewById(R.id.text_chemist_middle_name);
        textChemistLastName = findViewById(R.id.text_chemist_last_name);
        textChemistEmail = findViewById(R.id.text_chemist_email);
        spinnerMeetTime = findViewById(R.id.spinner_meet_time);
        spinnerPatchId = findViewById(R.id.spinner_patch_id);
        textChemistPhone = findViewById(R.id.text_chemist_phone);
        textChemistAddress1 = findViewById(R.id.text_chemist_address1);
        textChemistAddress2 = findViewById(R.id.text_chemist_address2);
        textChemistAddress3 = findViewById(R.id.text_chemist_address3);
        textChemistAddress4 = findViewById(R.id.text_chemist_address4);
        textChemistDistrict = findViewById(R.id.text_chemist_district);
        textChemistCity = findViewById(R.id.text_chemist_city);
        textChemistState = findViewById(R.id.text_chemist_state);
        textChemistPinCode = findViewById(R.id.text_chemist_pincode);
        textChemistBillingPhone1 = findViewById(R.id.text_chemist_billing_phone1);
        textChemistBillingPhone2 = findViewById(R.id.text_chemist_billing_phone2);
        textChemistBillingEmail = findViewById(R.id.text_chemist_billing_email);
        textChemistRating = findViewById(R.id.text_chemist_rating);
        buttonAddChemist = findViewById(R.id.button_add_chemist);

        buttonAddChemist.setOnClickListener(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.add_chemist_details));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        preferredMeetTime = new ArrayList<>();
        preferredMeetTime.add(getString(R.string.morning));
        preferredMeetTime.add(getString(R.string.evening));
        setArrayAdapter(preferredMeetTime, spinnerMeetTime);

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityAddChemist.this, SingleListActivity.class);
        intent.putExtra(AppConstants.KEY_TOUCHED_FRAGMENT, AppConstants.CHEMIST_FRAGMENT);
        startActivity(intent);
    }

    public void uiValidation() {
        String strCompanyName = textChemistCompanyName.getText().toString().trim();
        String strChemistMonthlyVolume = textChemistMonthlyVolume.getText().toString().trim();
        String strChemistShopSize = textChemistShopSize.getText().toString().trim();
        String strFirstName = textChemistFirstName.getText().toString().trim();
        String strMiddleName = textChemistMiddleName.getText().toString().trim();
        String strLastName = textChemistLastName.getText().toString().trim();
        String strChemistEmail = textChemistEmail.getText().toString().trim();
        String strChemistPhone = textChemistPhone.getText().toString().trim();
        String strAddress1 = textChemistAddress1.getText().toString().trim();
        String strAddress2 = textChemistAddress2.getText().toString().trim();
        String strAddress3 = textChemistAddress3.getText().toString().trim();
        String strAddress4 = textChemistAddress4.getText().toString().trim();
        String strChemistDistrict = textChemistDistrict.getText().toString().trim();
        String strChemistCity = textChemistCity.getText().toString().trim();
        String strChemistState = textChemistState.getText().toString().trim();
        String strChemistPincode = textChemistPinCode.getText().toString().trim();
        String strChemistBillingEmail = textChemistBillingEmail.getText().toString().trim();
        String strChemistBillingPhone1 = textChemistBillingPhone1.getText().toString().trim();
        String strChemistBillingPhone2 = textChemistBillingPhone2.getText().toString().trim();
        String strChemistRating = textChemistRating.getText().toString().trim();


        if (TextUtils.isEmpty(strCompanyName)) {
            textChemistCompanyName.requestFocus();
            inputLayoutCompanyNameChemist.setError(getString(R.string.company_name));
        } else if (TextUtils.isEmpty(strChemistMonthlyVolume)) {
            textChemistMonthlyVolume.requestFocus();
            inputLayoutMonthlyVolumeChemist.setError(getString(R.string.monthly_potential));
        } else if (TextUtils.isEmpty(strChemistShopSize)) {
            textChemistShopSize.requestFocus();
            inputLayoutShopSizeChemist.setError(getString(R.string.shop_size));
        } else if (TextUtils.isEmpty(strFirstName)) {
            textChemistFirstName.requestFocus();
            inputLayoutFirstNameChemist.setError(getString(R.string.first_name_invalid));
        }
        else if (TextUtils.isEmpty(strLastName)) {
            textChemistLastName.requestFocus();
            inputLayoutLastNameChemist.setError(getString(R.string.last_name));
        } else if (TextUtils.isEmpty(strChemistEmail)) {
            textChemistEmail.requestFocus();
            inputLayoutEmailChemist.setError(getString(R.string.email_invalid));
        } else if (isValidEmailId(strChemistEmail)) {
            textChemistEmail.requestFocus();
            inputLayoutEmailChemist.setError("Invalid Email");
        } else if (TextUtils.isEmpty(strChemistPhone)) {
            textChemistPhone.requestFocus();
            inputLayoutPhoneChemist.setError(getString(R.string.phone));
        } else if (TextUtils.isEmpty(strAddress1)) {
            textChemistAddress1.requestFocus();
            inputLayoutAddress1Chemist.setError(getString(R.string.address));
        } else if (TextUtils.isEmpty(strAddress2)) {
            textChemistAddress2.requestFocus();
            inputLayoutAddress2Chemist.setError(getString(R.string.address));
        } else if (TextUtils.isEmpty(strAddress3)) {
            textChemistAddress3.requestFocus();
            inputLayoutAddress3Chemist.setError(getString(R.string.address));
        } else if (TextUtils.isEmpty(strAddress4)) {
            textChemistAddress4.requestFocus();
            inputLayoutAddress4Chemist.setError(getString(R.string.address));
        } else if (TextUtils.isEmpty(strChemistDistrict)) {
            textChemistDistrict.requestFocus();
            inputLayoutDistrictChemist.setError(getString(R.string.district));
        } else if (TextUtils.isEmpty(strChemistCity)) {
            textChemistCity.requestFocus();
            inputLayoutCityChemist.setError(getString(R.string.city));
        } else if (TextUtils.isEmpty(strChemistState)) {
            textChemistState.requestFocus();
            inputLayoutStateChemist.setError(getString(R.string.state));
        } else if (TextUtils.isEmpty(strChemistPincode)) {
            textChemistPinCode.requestFocus();
            inputLayoutPincodeChemist.setError(getString(R.string.invalid_input));
        } else if (TextUtils.isEmpty(strChemistBillingEmail)) {
            textChemistBillingEmail.requestFocus();
            inputLayoutBillingEmailChemist.setError(getString(R.string.billing_email));
        } else if (isValidEmailId(strChemistBillingEmail)) {
            textChemistBillingEmail.requestFocus();
            inputLayoutBillingEmailChemist.setError("Invalid Email");
        } else if (TextUtils.isEmpty(strChemistBillingPhone1)) {
            textChemistBillingPhone1.requestFocus();
            inputLayoutBillingPhone1Chemist.setError(getString(R.string.billing_phone));
        } else if (strChemistBillingPhone1.length() < 10) {
            textChemistBillingPhone1.requestFocus();
            inputLayoutBillingPhone1Chemist.setError("Invalid phone number.");
        } else if (TextUtils.isEmpty(strChemistBillingPhone2)) {
            textChemistBillingPhone2.requestFocus();
            inputLayoutBillingPhone2Chemist.setError(getString(R.string.invalid_input));
        } else if (strChemistBillingPhone2.length() < 10) {
            textChemistBillingPhone2.requestFocus();
            inputLayoutBillingPhone2Chemist.setError("Invalid phone number.");
        } else if (TextUtils.isEmpty(strChemistRating)) {
            textChemistRating.requestFocus();
            inputLayoutRatingChemist.setError(getString(R.string.rating));
        } else {
            chemists.setCompanyName(strCompanyName);
            chemists.setMonthlyVolumePotential(strChemistMonthlyVolume);
            chemists.setShopSize(strChemistShopSize);
            chemists.setFirstName(strFirstName);
            chemists.setMiddleName(strMiddleName);
            chemists.setLastName(strLastName);
            chemists.setEmail(strChemistEmail);
            chemists.setPhone(strChemistPhone);
            chemists.setPatchId(patchId + "");
            chemists.setAddress1(strAddress1);
            chemists.setAddress2(strAddress2);
            chemists.setAddress3(strAddress3);
            chemists.setAddress4(strAddress4);
            chemists.setDistrict(strChemistDistrict);
            chemists.setCity(strChemistCity);
            chemists.setState(strChemistState);
            chemists.setPincode(strChemistPincode);
            chemists.setBillingEmail(strChemistBillingEmail);
            chemists.setBillingPhone1(strChemistBillingPhone1);
            chemists.setBillingPhone2(strChemistBillingPhone2);
            chemists.setRating(strChemistRating);
            chemists.setStatus("1");
            chemists.setPreferredMeetTime(preferredMeetTime + "");
            if (InternetConnection.isNetworkAvailable(ActivityAddChemist.this)) {
                getAddressLatLong(strAddress1 + ", " + strChemistPincode);
            } else {
                Snackbar.make(rootView, getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
            }
        }
    }


    protected boolean isValidEmailId(String email) {

       return (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    @Override
    public void initData() {
        super.initData();
        chemists = new Chemists();
        if (InternetConnection.isNetworkAvailable(ActivityAddChemist.this)) {
            getPatchList(token);
        } else {
            Snackbar.make(rootView, getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
        }


        spinnerPatchId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                patchId = patches.get(i).getPatchId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                int pos = adapterView.getSelectedItemPosition();
                patchId = patches.get(pos).getPatchId();
            }
        });

    }

    public void setArrayAdapter(List<String> listString, Spinner spinner) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item,
                        listString);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

    }

    public void getAddressLatLong(String input) {
        processDialog.showDialog(ActivityAddChemist.this, false);
        Call<GooglePlaces> call = apbInterfaceForGooglePlaces.getPlaceLatLong(input, AppConstants.INPUT_TYPE, AppConstants.FIELDS, AppConstants.KEY_GOOGLE_PLACES);
        call.enqueue(new Callback<GooglePlaces>() {
            @Override
            public void onResponse(@NonNull Call<GooglePlaces> call, @NonNull Response<GooglePlaces> response) {
                processDialog.dismissDialog();
                assert response.body() != null;
                switch (response.body().getStatus()) {
                    case AppConstants.STATUS_OK:
                        if (chemists != null) {
                            chemists.setLatitude(response.body().getCandidates().get(0).getGeometry().getLocation().getLat() + "");
                            chemists.setLongitude(response.body().getCandidates().get(0).getGeometry().getLocation().getLng() + "");
                            if (InternetConnection.isNetworkAvailable(ActivityAddChemist.this))
                            {
                                addChemistDetailsApi(token, chemists);
                            }
                            else
                            {
                                Snackbar.make(rootView, getResources().getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
                            }
                        }
                        break;
                    case AppConstants.STATUS_ZERO_RESULTS:
                        Snackbar.make(rootView, getResources().getString(R.string.not_found_address), Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        Snackbar.make(rootView, getResources().getString(R.string.query_over_limit)
                               , Snackbar.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<GooglePlaces> call, @NonNull Throwable t) {
                processDialog.dismissDialog();
                Log.e("AddDoctorActivity", "onFailure: " + t.getMessage());
                Toast.makeText(ActivityAddChemist.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPatchList(String token) {
        Call<PatchListResponse> call = apiInterface.patchList(token);
        call.enqueue(new Callback<PatchListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PatchListResponse> call, @NonNull Response<PatchListResponse> response) {
                if (Objects.requireNonNull(response.body()).getStatusCode() == AppConstants.RESULT_OK) {
                    List<String> tempList = new ArrayList<>();
                    patches = response.body().getPatchesList();
                    for (int i = 0; i < patches.size(); i++) {
                        tempList.add(patches.get(i).getPatchName() + ", " + patches.get(i).getTerritoryName());
                    }

                    setArrayAdapter(tempList, spinnerPatchId);

                }
            }

            @Override
            public void onFailure(@NonNull Call<PatchListResponse> call, @NonNull Throwable t) {
                Toast.makeText(ActivityAddChemist.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void addChemistDetailsApi(String token, Chemists chemists) {
        processDialog.showDialog(ActivityAddChemist.this, false);
        Call<ChemistResponse> call = apiInterface.addChemist(token, chemists);
        call.enqueue(new Callback<ChemistResponse>() {
            @Override
            public void onResponse(@NonNull Call<ChemistResponse> call, @NonNull Response<ChemistResponse> response) {
                processDialog.dismissDialog();
                if (Objects.requireNonNull(response.body()).getStatusCode() == AppConstants.RESULT_OK) {
                    Intent intentSingleList = new Intent(ActivityAddChemist.this, SingleListActivity.class);
                    intentSingleList.putExtra(AppConstants.KEY_TOUCHED_FRAGMENT, AppConstants.CHEMIST_FRAGMENT);
                    startActivity(intentSingleList);
                    finish();
                } else {
                    Toast.makeText(ActivityAddChemist.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChemistResponse> call, @NonNull Throwable t
            ) {
                processDialog.dismissDialog();
                Toast.makeText(ActivityAddChemist.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_chemist:
                uiValidation();
                break;

        }

    }

}
