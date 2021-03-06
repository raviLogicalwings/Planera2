package com.planera.mis.planera2.Retrofit;

import com.planera.mis.planera2.models.AdminPlanResponse;
import com.planera.mis.planera2.models.BrandsListResponse;
import com.planera.mis.planera2.models.ChemistImport;
import com.planera.mis.planera2.models.ChemistListResponse;
import com.planera.mis.planera2.models.ChemistResponse;
import com.planera.mis.planera2.models.Chemists;
import com.planera.mis.planera2.models.DoctorImport;
import com.planera.mis.planera2.models.DoctorResponse;
import com.planera.mis.planera2.models.Doctors;
import com.planera.mis.planera2.models.DoctorsListResponce;
import com.planera.mis.planera2.models.GiftListResponse;
import com.planera.mis.planera2.models.GooglePlacesModel.GooglePlaces;
import com.planera.mis.planera2.models.Input;
import com.planera.mis.planera2.models.InputGift;
import com.planera.mis.planera2.models.InputGiftResponce;
import com.planera.mis.planera2.models.InputOrders;
import com.planera.mis.planera2.models.InputResponce;
import com.planera.mis.planera2.models.LoginResponse;
import com.planera.mis.planera2.models.MainResponse;
import com.planera.mis.planera2.models.ObtainReport;
import com.planera.mis.planera2.models.PatchListResponse;
import com.planera.mis.planera2.models.Plans;
import com.planera.mis.planera2.models.RegistrationResponse;
import com.planera.mis.planera2.models.ReportListResponce;
import com.planera.mis.planera2.models.RoleWiseUsersResponse;
import com.planera.mis.planera2.models.StateListResponse;
import com.planera.mis.planera2.models.TerritoryListResponse;
import com.planera.mis.planera2.models.UserData;
import com.planera.mis.planera2.models.UserImport;
import com.planera.mis.planera2.models.UserListResponse;
import com.planera.mis.planera2.models.UserPlanListRespnce;
import com.planera.mis.planera2.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @POST(AppConstants.LOGIN)
    Call<LoginResponse>  userLoginApi(@Body UserData userData);

    @POST(AppConstants.USER_REGISTRATION)
    Call<RegistrationResponse> userRegistrationApi(@Body UserData userData);


    @POST(AppConstants.CHANGE_PASSWORD)
    Call<ChemistResponse> changePasswordApi(@Header("Authorization") String token, @Body UserData userData);

    @GET(AppConstants.FORGET_PASSWORD)
    Call<MainResponse> forgetPassword( @Query("EmailId") String emailId);

    @POST(AppConstants.SET_PASSWORD)
    Call<MainResponse> setPassword(@Header("Authorization") String token, @Body UserData userData);

    @GET(AppConstants.GIFT_LIST)
    Call<GiftListResponse> giftListApi(@Header("Authorization") String token);

    @GET(AppConstants.BRANDS_LIST)
    Call<BrandsListResponse> brandsListApi(@Header("Authorization") String token,
                                           @Query("IsBrand") int isBrand);

    @GET(AppConstants.STATE_LIST)
    Call<StateListResponse> statesListApi(@Header("Authorization") String token);


    @GET(AppConstants.PATCH_LIST)
    Call<PatchListResponse> patchList(@Header("Authorization") String token);

    @GET(AppConstants.TERRITORY_WISE_PATCHES)
    Call<PatchListResponse> patchListByTerritory(@Header("Authorization") String token,
                                                 @Query("TerritoryId") int territoryId);
    @GET(AppConstants.PATCH_WISE_DOCTORS_LIST)
    Call<DoctorsListResponce> patchesWiseDoctorList(@Header("Authorization") String token,
                                                    @Query("PatchId") int patchId);

    @GET(AppConstants.PATCH_WISE_CHEMIST_LIST)
    Call<ChemistListResponse> patchesWiseChemistList(@Header("Authorization") String token,
                                                     @Query("PatchId") int patchId);

    @GET(AppConstants.TERRITORY_LIST)
    Call<TerritoryListResponse> territoryList(@Header("Authorization") String token);

    @GET(AppConstants.DOCTORS_LIST)
    Call<DoctorsListResponce> doctorsList(@Header("Authorization") String token);

    @GET(AppConstants.CHEMIST_LIST)
    Call<ChemistListResponse> chemistList(@Header("Authorization") String token);

    @GET(AppConstants.USER_LIST)
    Call<UserListResponse> usersList(@Header("Authorization") String token);

    @GET(AppConstants.USER_LIST_BY_PATCHES)
    Call<RoleWiseUsersResponse> userListByPatches(@Header("Authorization") String token, @Query("PatchId") int patchId);


    @GET(AppConstants.PLAN_LIST)
    Call<AdminPlanResponse> planList(@Header("Authorization") String token);

    @GET(AppConstants.USER_PLAN_LIST)
    Call<UserPlanListRespnce> userPlanList(@Header("Authorization") String token);

    @POST(AppConstants.CHEMIST_REPORTS_LIST)
    Call<ReportListResponce> reportListChemist(@Header("Authorization") String token,
                                               @Body ObtainReport report);

    @POST(AppConstants.DOCTOR_REPORTS_LIST)
    Call<ReportListResponce> reportListDoctor(@Header("Authorization") String token,
                                               @Body ObtainReport report);

    @POST(AppConstants.USER_REPORT_LIST)
    Call<ReportListResponce> reportListUser(@Header("Authorization") String token,
                                              @Body ObtainReport report);

    @GET(AppConstants.ADD_STATE)
    Call<MainResponse> addState(@Header("Authorization") String token,
                                @Query("Name") String state);

    @GET(AppConstants.ADD_TERRITORY)
    Call<MainResponse> addTerritory(@Header("Authorization") String token,
                                    @Query("Name") String name,
                                    @Query("StateId") int stateId);

    @GET(AppConstants.ADD_GIFT)
    Call<MainResponse> addGift(@Header("Authorization") String token,
                               @Query("Name") String gift);

    @GET(AppConstants.ADD_PATCH)
    Call<MainResponse> addPatch(@Header("Authorization") String token,
                                @Query("Name") String patch,
                                @Query("TerritoryId") int territoryId);

    @GET(AppConstants.ADD_PRODUCTS)
    Call<MainResponse> addProduct(@Header("Authorization") String token,
                                  @Query("Name") String name,
                                  @Query("IsBrand") int isBrand);


    @POST(AppConstants.ADD_DOCTORS)
    Call<DoctorResponse>  addDoctor(@Header("Authorization") String token,
                                    @Body Doctors doctors);


    @POST(AppConstants.ADD_CHEMIST)
    Call<ChemistResponse> addChemist(@Header("Authorization") String token,
                                     @Body Chemists chemists);

    @POST(AppConstants.ADD_PLAN)
    Call<MainResponse> addPlan(@Header("Authorization") String token,
                               @Body Plans plans);


    @POST(AppConstants.ADD_INPUT_PRODUCT)
    Call<MainResponse> addInputProductList(@Header("Authorization") String token,
                                           @Body List<InputOrders> orders);

    @POST(AppConstants.ADD_INPUT_GIFT)
    Call<InputGiftResponce> addInputGift(@Header("Authorization") String token, @Body List<InputGift> inputGift);

    @POST(AppConstants.ADD_INPUT)
    Call<InputResponce> addInput(@Header("Authorization") String token, @Body Input input);

    @GET(AppConstants.DELETE_STATE)
    Call<MainResponse> deleteState(@Header("Authorization") String token,
                                   @Query("StateId") int stateId);

    @GET(AppConstants.DELETE_GIFT)
    Call<MainResponse> deleteGift(@Header("Authorization") String token,
                                  @Query("GiftId") int giftId);

    @GET(AppConstants.DELETE_TERRITORY)
    Call<MainResponse> deleteTerritory(@Header("Authorization") String token,
                                       @Query("TerritoryId") int territoryId);

    @GET(AppConstants.DELETE_PATCH)
    Call<MainResponse> deletePatch(@Header("Authorization") String token,
                                   @Query("PatchId") int patchId);

    @GET(AppConstants.DELETE_DOCTORS)
    Call<MainResponse> deleteDoctor(@Header("Authorization") String token,
                                    @Query("DoctorId") int doctorId);

    @GET(AppConstants.DELETE_PRODUCTS)
    Call<MainResponse> deleteProduct(@Header("Authorization") String token,
                                     @Query("ProductId") int productId);

    @GET(AppConstants.DELETE_USERS)
    Call<MainResponse> deleteUser(@Header("Authorization") String token,
                                  @Query("UserId") int userId);

    @GET(AppConstants.DELETE_CHEMIST)
    Call<MainResponse> deleteChemist(@Header("Authorization") String token,
                                     @Query("ChemistId") int chemistId);

    @GET(AppConstants.DELETE_PLAN)
    Call<MainResponse> deletePlan (@Header("Authorization") String token,
                                   @Query("PatchId") int patchId);

    @GET(AppConstants.UPDATE_STATE)
    Call<MainResponse> updateStateDetails(@Header("Authorization") String token,
                                          @Query("StateId") int stateId,
                                          @Query("Name") String name);

    @GET(AppConstants.UPDATE_TERRITORY)
    Call<MainResponse> updateTerritoryDetails(@Header("Authorization") String token,
                                              @Query("TerritoryId") int territoryId,
                                              @Query("StateId") int stateId,
                                              @Query("Name") String name);

    @POST(AppConstants.UPDATE_DOCTOR)
    Call<MainResponse> updateDoctorDetails(@Header("Authorization") String token,
                                           @Body Doctors doctors);

    @GET(AppConstants.UPDATE_PRODUCT)
    Call<MainResponse> updateProductDetails(@Header("Authorization") String token,
                                            @Query("ProductId") int productId,
                                            @Query("IsBrand") String isBrand,
                                            @Query("Name") String name);

    @GET(AppConstants.UPDATE_PATCH)
    Call<MainResponse> updatePatchDetails(@Header("Authorization") String token,
                                          @Query("PatchId") int patchId,
                                          @Query("Name") String name,
                                          @Query("TerritoryId") int territoryId);

    @GET(AppConstants.UPDATE_GIFT)
    Call<MainResponse> updateGItDetails(@Header("Authorization") String token,
                                        @Query("GiftId") int giftId,
                                        @Query("Name") String name);

    @POST(AppConstants.UPDATE_CHEMIST)
    Call<MainResponse> updateChemist(@Header("Authorization") String token,
                                     @Body Chemists chemists);

    @POST(AppConstants.UPDATE_USER)
    Call<MainResponse> updateUserDetails(@Header("Authorization")String token,
                                         @Body UserData userData);

    @POST(AppConstants.UPDATE_PLAN)
    Call<MainResponse>  updatePlanDetails(@Header("Authorization") String token,
                                          @Body Plans plans);

    @POST(AppConstants.UPDATE_MR_INPUT)
    Call<MainResponse> updateMrInput(@Header("Authorization") String token, @Body Input input);


    @POST(AppConstants.IMPORT_DOCTORS_DATA)
    Call<MainResponse> importDoctorFromExcel(@Header("Authorization") String token,
                                              @Body DoctorImport doctorImport);

    @POST(AppConstants.IMPORT_CHEMIST_DATA)
    Call<MainResponse> importChemistFromExcel(@Header("Authorization") String token,
                                               @Body ChemistImport chemistImports);
    @POST(AppConstants.IMPORT_USER_DATA)
    Call<MainResponse> importDoctorFromExcel(@Header("Authorization") String token,
                                              @Body UserImport userImport);

    @POST(AppConstants.SEND_TO_EMAIL)
    Call<MainResponse> sendFileToEmail(@Header("Authorization") String token, @Body ObtainReport obtainReport);
    //Google  Places api Call
    @GET(AppConstants.FIND_PALCE)
    Call<GooglePlaces> getPlaceLatLong(@Query("input") String input,
                                       @Query("inputtype") String inputType,
                                       @Query("fields") String fields,
                                       @Query("key") String key);


}

