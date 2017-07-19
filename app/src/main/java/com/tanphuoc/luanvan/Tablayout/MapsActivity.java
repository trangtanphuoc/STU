package com.tanphuoc.luanvan.Tablayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tanphuoc.luanvan.Adapter.AdapterNganHang;
import com.tanphuoc.luanvan.Adapter.AdapterQuan;
import com.tanphuoc.luanvan.Direction.ATM.DirectionATMNHKC;
import com.tanphuoc.luanvan.Direction.ATM.DirectionATMNHQUAN;
import com.tanphuoc.luanvan.Direction.TramXang.DirectionTXKC;
import com.tanphuoc.luanvan.Direction.TramXang.DirectionTXQuan;
import com.tanphuoc.luanvan.Moudle.DirectionFinderListener;
import com.tanphuoc.luanvan.Moudle.NganHang;
import com.tanphuoc.luanvan.Moudle.Quan;
import com.tanphuoc.luanvan.Moudle.TramATM;
import com.tanphuoc.luanvan.Moudle.TramXang;
import com.tanphuoc.luanvan.MutilSpinner.MultiSelectionSpinner;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.ToaDo.ToaDo;
import com.tanphuoc.luanvan.huongdanduongdi.Moudle.DirectionFinder;
import com.tanphuoc.luanvan.huongdanduongdi.Moudle.Route;
import com.tanphuoc.luanvan.huongdanduongdi.Moudle.TimDuong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.tanphuoc.luanvan.R.id.map;
import static com.tanphuoc.luanvan.R.id.spnNH;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,DirectionFinderListener,TimDuong {
//    String linkNH="http://172.16.11.231/luanvan/GetNganHang.php";
//    String linkQuan="http://172.16.11.231/luanvan/GetQuan.php";
    String linkNH="http://192.168.1.51/luanvan/GetNganHang.php";
    String linkQuan="http://192.168.1.51/luanvan/GetQuan.php";
    String tentruong= "trường đại học công nghệ sài gòn";

    private GoogleMap mMap;

    Button btnATMOK,btnATMHuy,btnTXOK,btnTXHuy;
    ImageButton imgtim,imgvitrihientai;
    Spinner spnLuaChon,spnNH,spnATMKC,spnTXKC;
    ArrayList<String> arrLuachon;
    ArrayList<String> arrKhoangCach;
    Dialog ATMdialog,TXdialog;
    TextView txtshowm;// txt met

    public static ArrayList<NganHang> arrayListNH;
    public static int vitri=-1;
    ArrayList<Quan> arrayListQuan;
    AdapterNganHang adapterNganHang;
    AdapterQuan adapterQuan;



    private List<Marker> MarketTramATM = new ArrayList<>();
    private List<Marker> MarketTramXang = new ArrayList<>();
    private List<Marker> MarketViTri = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    //test
    static List <TramATM> listATMDanhSach;
    static List <TramXang> listXangDanhSach;
    private Circle mCircle;
    LatLng LSTU = new LatLng(10.738124, 106.677976);
    private List<Integer> listMaQuan;
    MultiSelectionSpinner spinnerATM,spinnerTX;
    EditText edtViTri;
    private int vitriNH;


    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        addControl();
        addEvent();

    }
    private void addEvent() {
        imgtim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vitri = spnLuaChon.getSelectedItemPosition();
                if (vitri == 0) {
//                    Intent intent =new Intent(MapsActivity.this, ATMDialogActivity.class);
//                    startActivity(intent);
                    DialogATM();
                } else{
                    DialogTX();
                }
            }
        });
        imgvitrihientai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LSTU, 18));
            }
        });

    }
    private void addControl() {
        imgtim = (ImageButton) findViewById(R.id.imgFind);
        imgtim.setBackground(null);
        imgtim.setBackgroundResource(R.drawable.kinhlup);

        imgvitrihientai = (ImageButton) findViewById(R.id.imgvitrihientai);
        imgvitrihientai.setBackground(null);
        imgvitrihientai.setBackgroundResource(R.drawable.vitrihientai);

        spnLuaChon= (Spinner) findViewById(R.id.spnLuachonTimTXorATM);

        arrLuachon=new ArrayList<>();
        arrLuachon.add("Tìm Trạm ATM");
        arrLuachon.add("Tìm Trạm Xăng");

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrLuachon);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnLuaChon.setAdapter(adapter);

        arrayListNH=new ArrayList<>();
        adapterNganHang=new AdapterNganHang(this,arrayListNH);
        GetDataNH(linkNH);

        arrayListQuan=new ArrayList<>();
        adapterQuan=new AdapterQuan(this,arrayListQuan);
        GetDataQuan(linkQuan);

        //test
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //xy ly map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng hcmus = LSTU;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        MarketViTri.add(mMap.addMarker(new MarkerOptions()
                .snippet("Đại học Công Nghệ Sài Gòn")
                .position(hcmus)));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                try{
                    if(marker.getSnippet().equals("Đại học Công Nghệ Sài Gòn")){
                        Toast.makeText(MapsActivity.this, "Hiện tại bạn đang ở đây", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            new DirectionFinder(MapsActivity.this, tentruong,
                                    marker.getSnippet()).execute();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    private void DialogATM(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogatm, null);
        ATMdialog = new Dialog(MapsActivity.this);
        // khởi tạo dialog
        ATMdialog.setContentView(alertLayout);
        // xét layout cho dialog
        ATMdialog.setTitle("Loại Tìm Kiếm Trạm ATM");
        // xét tiêu đề cho dialog

        spnNH= (Spinner) alertLayout.findViewById(R.id.spnNH);
//        spnATMQuan= (Spinner) alertLayout.findViewById(R.id.spnATMQuan);
        spnATMKC= (Spinner) alertLayout.findViewById(R.id.spATMKC);
        btnATMOK= (Button) alertLayout.findViewById(R.id.btndalogATMOK);
        btnATMHuy= (Button) alertLayout.findViewById(R.id.btndialogATMHuy);
        final RadioButton rbatmQuan,rbatmKC,rbVTHT,rbVTchon;
        rbatmQuan = (RadioButton) alertLayout.findViewById(R.id.rbATMQuan);
        rbatmKC = (RadioButton) alertLayout.findViewById(R.id.rbATMKC);
        rbVTHT = (RadioButton) alertLayout.findViewById(R.id.rbVTHT);
        rbVTchon = (RadioButton) alertLayout.findViewById(R.id.rbVTK);
        edtViTri = (EditText) alertLayout.findViewById(R.id.edtvitri);
        txtshowm = (TextView) alertLayout.findViewById(R.id.txtatmm);
        spinnerATM = (MultiSelectionSpinner) alertLayout.findViewById(R.id.mySpinner1);
        final RadioGroup radioGroupATM = (RadioGroup) alertLayout.findViewById(R.id.rg1);
        RadioGroup radioGroupvitri = (RadioGroup) alertLayout.findViewById(R.id.rg2);

        //load du lieu khoang cach
        ThemKhoangCach();

        //load du lieu ngan hang
        ArrayAdapter adapterNH=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayListNH);
        adapterNH.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNH.setAdapter(adapterNH);

        //load du lieu quan
//        ArrayAdapter adapterQuan=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayListQuan);
//        adapterQuan.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        spnATMQuan.setAdapter(adapterQuan);

        //load du lieu khoang cách
        ArrayAdapter adapterKC=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrKhoangCach);
        adapterKC.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnATMKC.setAdapter(adapterKC);

        //load du lieu quan
        spinnerATM.setItems(arrayListQuan);
        spinnerATM.setSelection(new int[]{1, 4});


        radioGroupvitri.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rbVTHT :
                        radioGroupATM.setVisibility(View.VISIBLE);
                        edtViTri.setVisibility(View.GONE);
                        spnNH.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbVTK :
                        radioGroupATM.setVisibility(View.GONE);
                        edtViTri.setVisibility(View.VISIBLE);
                        spnNH.setVisibility(View.VISIBLE);
                        spnATMKC.setVisibility(View.GONE);
                        txtshowm.setVisibility(View.GONE);
                        spinnerATM.setVisibility(View.GONE);
                        break;
                }
            }
        });

        //bat su kien chon loai tim kiem
        radioGroupATM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rbATMQuan :
                        spinnerATM.setVisibility(View.VISIBLE);
                        spnATMKC.setVisibility(View.GONE);
                        txtshowm.setVisibility(View.GONE);
                        break;
                    case R.id.rbATMKC :
                        spnATMKC.setVisibility(View.VISIBLE);
                        txtshowm.setVisibility(View.VISIBLE);
                        spinnerATM.setVisibility(View.GONE);
                        break;
                }
            }
        });



        //xu ly khi nhan nut tren dialog
        btnATMHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ATMdialog.dismiss();
            }
        });
        btnATMOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbVTHT.isChecked() && !rbVTchon.isChecked()) {
                    if (!rbatmQuan.isChecked() && !rbatmKC.isChecked()) {
                        Toast.makeText(MapsActivity.this, "Bạn hãy chọn loại tìm kiếm lại!!", Toast.LENGTH_SHORT).show();
                    } else if (rbatmQuan.isChecked() && !rbatmKC.isChecked()) {
                        if (rbatmQuan.isChecked()) {
                       Toast.makeText(MapsActivity.this,"Bạn đã chọn " + spnNH.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            ATMtheoQuan(spnNH.getSelectedItemPosition(), spinnerATM.getSelectedStrings());
                        }
                    } else if (!rbatmQuan.isChecked() && rbatmKC.isChecked()) {
                        if (rbatmKC.isChecked() && !rbatmQuan.isChecked()) {
//                           Toast.makeText(MapsActivity.this,arrKhoangCach.get(spnATMKC.getSelectedItemPosition()) , Toast.LENGTH_SHORT).show();
                            ATMtheoKC(LSTU.latitude,LSTU.longitude,spnNH.getSelectedItemPosition(),
                                    Integer.parseInt(arrKhoangCach.get(spnATMKC.getSelectedItemPosition())));
                            Toast.makeText(MapsActivity.this,"Bạn đã chọn " + spnNH.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else if(!rbVTHT.isChecked() && rbVTchon.isChecked()) {
                    final String vitri = edtViTri.getText().toString();
                    if (vitri != null) {
                        try {
                                vitriNH = spnNH.getSelectedItemPosition() + 1;
                                new ToaDo(MapsActivity.this,vitri,true).execute();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(MapsActivity.this, "bạn hãy nhập lại tên địa điểm" , Toast.LENGTH_SHORT).show();
                    }
                }
                ATMdialog.dismiss();
            }
        });

        ATMdialog.show();
        // hiển thị dialog
    }
    private void DialogTX(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogtramxang, null);
        TXdialog = new Dialog(MapsActivity.this);
        // khởi tạo dialog
        TXdialog.setContentView(alertLayout);
        // xét layout cho dialog
        TXdialog.setTitle("Loại Tìm Kiếm Trạm Xăng");
        // xét tiêu đề cho dialog


        spnTXKC = (Spinner) alertLayout.findViewById(R.id.spnTXKC);
        btnTXOK = (Button) alertLayout.findViewById(R.id.btndialogTXOK);
        btnTXHuy= (Button) alertLayout.findViewById(R.id.btndialogTXHuy);
        final RadioButton rbtxQuan,rbtxKC,rbtxVTHT,rbtxVTK;
        rbtxQuan = (RadioButton) alertLayout.findViewById(R.id.rbTXQuan);
        rbtxKC = (RadioButton) alertLayout.findViewById(R.id.rbTXKC);
        rbtxVTHT = (RadioButton) alertLayout.findViewById(R.id.rbtxVTHT);
        rbtxVTK = (RadioButton) alertLayout.findViewById(R.id.rbtxVTK);
        txtshowm = (TextView) alertLayout.findViewById(R.id.txttxm);
        final RadioGroup radioGroupTX = (RadioGroup) alertLayout.findViewById(R.id.rg2);
        RadioGroup radioGroupViti = (RadioGroup) alertLayout.findViewById(R.id.rg1);
        spinnerTX= (MultiSelectionSpinner) alertLayout.findViewById(R.id.mySpinner2);
        final EditText edttxvitri= (EditText) alertLayout.findViewById(R.id.edttxvitri);

        //load du lieu khoang cach
        ThemKhoangCach();

        //load du lieu quan
        spinnerTX.setItems(arrayListQuan);
        spinnerTX.setSelection(new int[]{1, 4});

        //load du lieu khoang cách
        ArrayAdapter adapterKC=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrKhoangCach);
        adapterKC.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTXKC.setAdapter(adapterKC);

        radioGroupTX.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbTXQuan :
                        spinnerTX.setVisibility(View.VISIBLE);
                        spnTXKC.setVisibility(View.GONE);
                        txtshowm.setVisibility(View.GONE);
                        break;
                    case R.id.rbTXKC :
                        spnTXKC.setVisibility(View.VISIBLE);
                        txtshowm.setVisibility(View.VISIBLE);
                        spinnerTX.setVisibility(View.GONE);
                        break;
                }
            }
        });
        radioGroupViti.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rbtxVTHT :
                        edttxvitri.setVisibility(View.GONE);
                        radioGroupTX.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbtxVTK :
                        edttxvitri.setVisibility(View.VISIBLE);
                        radioGroupTX.setVisibility(View.GONE);
                        spnTXKC.setVisibility(View.GONE);
                        txtshowm.setVisibility(View.GONE);
                        spinnerTX.setVisibility(View.GONE);
                        break;
                }
            }
        });

        btnTXHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TXdialog.dismiss();
            }
        });
        btnTXOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbtxVTHT.isChecked() && !rbtxVTK.isChecked()) {
                    if (!rbtxQuan.isChecked() && !rbtxKC.isChecked()) {
                        Toast.makeText(MapsActivity.this, "Bạn hãy chọn loại tìm kiếm lại!!", Toast.LENGTH_SHORT).show();
                    } else if (rbtxQuan.isChecked() && !rbtxKC.isChecked()) {
                        if (rbtxQuan.isChecked()) {
//                       Toast.makeText(MapsActivity.this, spinnerATM.getSelectedStrings().toString(), Toast.LENGTH_SHORT).show();
                            TXtheoQuan(spinnerTX.getSelectedStrings());
                        }
                    } else if (!rbtxQuan.isChecked() && rbtxKC.isChecked()) {
                        if (rbtxKC.isChecked() && !rbtxQuan.isChecked()) {
//                           Toast.makeText(MapsActivity.this,arrKhoangCach.get(spnATMKC.getSelectedItemPosition()) , Toast.LENGTH_SHORT).show();
                            TXtheoKC(LSTU.latitude,LSTU.longitude,
                                    Integer.parseInt(arrKhoangCach.get(spnATMKC.getSelectedItemPosition())));
                        }
                    }
                }
                else if(!rbtxVTHT.isChecked() && rbtxVTK.isChecked()) {
                    final String vitri = edttxvitri.getText().toString();
                    if (vitri != null) {
                        try {
                            new ToaDo(MapsActivity.this,vitri,false).execute();
                        } catch (UnsupportedEncodingException e) {
                            Toast.makeText(MapsActivity.this, "địa chỉ bạn nhập k đúng" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MapsActivity.this, "bạn hãy nhập lại tên địa điểm" , Toast.LENGTH_SHORT).show();
                    }
                }
                TXdialog.dismiss();
            }
        });
        TXdialog.show();
    }

    //load cac tram ATM theo yêu cầu
    private void ATMtheoQuan(int VTNH,List<String> vitri){
        try {
            new DirectionATMNHQUAN(this,
                    VTNH + 1,
                    TenchuyenMaQuan(vitri) )
                    .execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private void ATMtheoKC(double lat,double lng,int VTNH,int khoangcachATM){
        try {
            new DirectionATMNHKC(this,
                    lat,
                    lng,
                    VTNH+1,
                    khoangcachATM)
            .execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //load cac tram xăng theo yêu cầu
    private void TXtheoQuan(List<String> vitri){
        try {
            new DirectionTXQuan(this,
                    TenchuyenMaQuan(vitri)).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private void TXtheoKC(double lat,double lng,int a){
        try {
            new DirectionTXKC(this,lat,lng,a).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // khi bat dau
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);
        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }
    //huong dan duong di
    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();

        PolylineOptions polylineOptions = new PolylineOptions().
                geodesic(true).
                color(Color.BLUE).
                width(10);
        for (Route route : routes) {
            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    //add Market cac tram
    @Override
    public void onDirectionFinderStarttimtram() {
        mMap.clear();
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);
        if (MarketTramATM != null) {
            for (Marker marker : MarketTramATM) {
                marker.remove();
            }
        }

        if (MarketTramXang != null) {
            for (Marker marker : MarketTramXang) {
                marker.remove();
            }
        }
    }
    @Override
    public void onDirectionFinderSuccessATM(List<TramATM> tramATMs,int radius) {
        progressDialog.dismiss();
        MarketTramATM = new ArrayList<>();

        MarketTramATM.add(mMap.addMarker(new MarkerOptions()
                .title("Đại học Công Nghệ Sài Gòn")
                .position(LSTU)));

        if (tramATMs.size() == 0) {
            Toast.makeText(this, "Không có trạm ATM nào phù hợp với điều kiện bạn chọn", Toast.LENGTH_SHORT).show();
        }
        else {
                for (TramATM tramATM : tramATMs) {
                    LatLng toado = new LatLng(tramATM.getLat(), tramATM.getLng());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toado, 15));

                    MarketTramATM.add(mMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.tramatm))
                            .title(tramATM.getTenTram())
                            .snippet(tramATM.getDiachi())
                            .position(toado)));
                }
        }
    }
    @Override
    public void onDirectionFinderSuccessTX(List<TramXang> tramXang,int radius) {
        progressDialog.dismiss();
        MarketTramXang =new ArrayList<>();
        MarketTramXang.add(mMap.addMarker(new MarkerOptions()
                .title("Đại học Công Nghệ Sài Gòn")
                .position(LSTU)));
        try {
            if (tramXang.size() == 0) {
                Toast.makeText(this, "Không có trạm xăng nào phù hợp với điều kiện bạn chọn", Toast.LENGTH_SHORT).show();
            } else {

                    for (TramXang tramxang : tramXang) {
                        LatLng toado = new LatLng(tramxang.getLat(), tramxang.getLng());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toado, 15));

                        MarketTramXang.add(mMap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tramxang))
                                .title(tramxang.getTenTram())
                                .position(toado)));
                    }
            }
        }catch (Exception e){
            Toast.makeText(MapsActivity.this, "có lỗi xuất hiện", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void SendDataXang(List<TramXang> listTX) {
        listXangDanhSach = listTX;
    }
    @Override
    public void SendDataATM(List<TramATM> listATM) {
        listATMDanhSach = listATM;
    }
    @Override
    public void AddMarketVitriKhac(LatLng a,String vitri,boolean loai) {
        MarketViTri.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a, 20));
        MarketViTri.add(mMap.addMarker(new MarkerOptions()
                .title("Đại học Công Nghệ Sài Gòn")
                .position(LSTU)));
        if(loai ==true){
            ATMtheoKC(a.latitude,a.longitude,vitriNH,500);
            MarketTramATM.add(mMap.addMarker(new MarkerOptions()
                    .position(a)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    .snippet(vitri)));
        }
        else{
            TXtheoKC(a.latitude,a.longitude,500);
            MarketTramXang.add(mMap.addMarker(new MarkerOptions()
                    .position(a)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                    .snippet(vitri)));
        }
    }

    // bat sukien khi click nut quay lai
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return super.onKeyDown(keyCode, event);
    }

    //chuyen danh sach ten quan thanh ma quan
    public List<Integer> TenchuyenMaQuan(List<String> tenquan){
        listMaQuan=new ArrayList<>();
        for(int i=0;i<tenquan.size();i++){
            for(int j=0;j<arrayListQuan.size();j++){
                if(arrayListQuan.get(j).getTenQuan().equals(tenquan.get(i))){
                    listMaQuan.add(arrayListQuan.get(j).getId());
                    break;
                }
            }
        }
        return listMaQuan;
    }

    //load du lieu trong spinner Khoảng Cách
    private void ThemKhoangCach(){
        arrKhoangCach =new ArrayList<>();
        arrKhoangCach.add("500");
        arrKhoangCach.add("1000");
        arrKhoangCach.add("1500");
        arrKhoangCach.add("2000");
        arrKhoangCach.add("2500");
    }

    //load dữ liệu Ngân Hàng vs Quận vào mảng
    private void GetDataNH(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListNH.add(new NganHang(object.getInt("id"), object.getString("ten")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterNganHang.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MapsActivity.this,"bị lỗi rồi",Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDataQuan(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListQuan.add(new Quan(object.getInt("id"), object.getString("ten")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterQuan.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MapsActivity.this,"bị lỗi rồi",Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
