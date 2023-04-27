
public class main {

 	//RAW FILE
	
	public main() {
		  //TODO Auto-generated constructor stub
	}

}


 public class EmailValidationActivity extends AppCompatActivity {
 
     private EditText emailEditText;
     private Button submitButton;
 
     private SharedPreferences sharedPreferences;
 
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_email_validation);
 
         emailEditText = findViewById(R.id.email_edit_text);
         submitButton = findViewById(R.id.submit_button);
 
         //Mendapatkan referensi SharedPreferences
         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
 
         submitButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Mendapatkan email dari EditText
                 String email = emailEditText.getText().toString().trim();
 
                 //Memvalidasi apakah email kosong atau tidak memenuhi ketentuan
                 if (TextUtils.isEmpty(email) || !email.contains("@") || !email.endsWith(".com")) {
                     Toast.makeText(EmailValidationActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                     return;
                 }
 
                 //Memvalidasi apakah email sudah terdaftar pada SharedPreferences atau Firebase Authentication
                 if (isEmailRegistered(email)) {
                     Toast.makeText(EmailValidationActivity.this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(EmailValidationActivity.this, "Email belum terdaftar", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }
 
     //Method untuk memvalidasi apakah email sudah terdaftar pada SharedPreferences atau Firebase Authentication
     private boolean isEmailRegistered(String email) {
         //Cek apakah email sudah terdaftar pada SharedPreferences
         if (sharedPreferences.getString("email", "").equals(email)) {
             return true;
         }
 
         //Cek apakah email sudah terdaftar pada Firebase Authentication
         FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
         if (firebaseUser != null && firebaseUser.getEmail().equals(email)) {
             return true;
         }
 
         return false;
     }
 }

 private EditText passwordEditText;
 
  //...
 
 passwordEditText = findViewById(R.id.passwordEditText);
 
  //...
 
 private boolean validatePassword() {
  String passwordInput = passwordEditText.getText().toString().trim();
 
  if (TextUtils.isEmpty(passwordInput)) {
      passwordEditText.setError("Password is required");
      return false;
  } else {
      passwordEditText.setError(null);
      return true;
  }
 }


 private FirebaseAuth mAuth;
 
  //...
 
 mAuth = FirebaseAuth.getInstance();
 
  //...
 
 private void signIn(String email, String password) {
  mAuth.signInWithEmailAndPassword(email, password)
      .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()) {
                    //Sign in success
              } else {
                    //Sign in failed
              }
          }
      });
 }


 public class BimbelIDGenerator {
 
     public static String generateID(String email, String name, String password, String confirm_password) {
         //Check email format
         if (!email.matches(".+@.+\\.com")) {
             throw new IllegalArgumentException("Email format is invalid");
         }
         //Check name length
         if (name.length() < 5) {
             throw new IllegalArgumentException("Name should have at least 5 characters");
         }
         //Check password and confirm_password match
         if (!password.equals(confirm_password)) {
             throw new IllegalArgumentException("Password and confirm password do not match");
         }
         //Generate ID
         String id = name.substring(0, 5) + email.substring(0, email.indexOf("@"));
         return id;
     }
 
 }

 public class CounterFragment extends Fragment {
 
     private TextView counterTextView;
     private Button addButton;
     private Button subtractButton;
     private Button resetButton;
     private int counter = 0;
     private SharedPreferences sharedPreferences;
 
     public CounterFragment() {
           //Required empty public constructor
     }
 
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
 
         //Get shared preferences
         sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
 
         //Get the counter value from shared preferences
         counter = sharedPreferences.getInt("counter", 0);
     }
 
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         //Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_counter, container, false);
 
         //Initialize views
         counterTextView = view.findViewById(R.id.counterTextView);
         addButton = view.findViewById(R.id.addButton);
         subtractButton = view.findViewById(R.id.subtractButton);
         resetButton = view.findViewById(R.id.resetButton);
 
           Set counter value to text view
         counterTextView.setText(String.valueOf(counter));
 
           Set click listeners for buttons
         addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 counter++;
                 counterTextView.setText(String.valueOf(counter));
                 saveCounterToSharedPreferences();
             }
         });
 
         subtractButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 counter--;
                 counterTextView.setText(String.valueOf(counter));
                 saveCounterToSharedPreferences();
             }
         });
 
         resetButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 counter = 0;
                 counterTextView.setText(String.valueOf(counter));
                 saveCounterToSharedPreferences();
             }
         });
 
         return view;
     }
 
     private void saveCounterToSharedPreferences() {
         //Save the counter value to shared preferences
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putInt("counter", counter);
         editor.apply();
     }
 }


 public class HitungLuasFragment extends Fragment implements View.OnClickListener {
 
     private EditText inputSisiPersegi, inputAlasSegitiga, inputTinggiSegitiga, inputJariLingkaran;
     private Button btnHitungPersegi, btnHitungSegitiga, btnHitungLingkaran;
     private TextView hasilLuasPersegi, hasilLuasSegitiga, hasilLuasLingkaran;
 
     public HitungLuasFragment() {
         //Required empty public constructor
     }
 
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         //Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_hitung_luas, container, false);
         
         //Menginisialisasi semua elemen UI pada fragment
         inputSisiPersegi = view.findViewById(R.id.input_sisi_persegi);
         inputAlasSegitiga = view.findViewById(R.id.input_alas_segitiga);
         inputTinggiSegitiga = view.findViewById(R.id.input_tinggi_segitiga);
         inputJariLingkaran = view.findViewById(R.id.input_jari_lingkaran);
         btnHitungPersegi = view.findViewById(R.id.btn_hitung_persegi);
         btnHitungSegitiga = view.findViewById(R.id.btn_hitung_segitiga);
         btnHitungLingkaran = view.findViewById(R.id.btn_hitung_lingkaran);
         hasilLuasPersegi = view.findViewById(R.id.hasil_luas_persegi);
         hasilLuasSegitiga = view.findViewById(R.id.hasil_luas_segitiga);
         hasilLuasLingkaran = view.findViewById(R.id.hasil_luas_lingkaran);
         
         //Menambahkan listener pada button untuk menghitung luas bangun datar
         btnHitungPersegi.setOnClickListener(this);
         btnHitungSegitiga.setOnClickListener(this);
         btnHitungLingkaran.setOnClickListener(this);
 
         return view;
     }
 
     @Override
     public void onClick(View v) {
         switch (v.getId()) {
             case R.id.btn_hitung_persegi:
                 hitungLuasPersegi();
                 break;
             case R.id.btn_hitung_segitiga:
                 hitungLuasSegitiga();
                 break;
             case R.id.btn_hitung_lingkaran:
                 hitungLuasLingkaran();
                 break;
         }
     }
 
     private void hitungLuasPersegi() {
         if (inputSisiPersegi.getText().toString().isEmpty()) {
             Toast.makeText(getActivity(), "Masukkan nilai sisi persegi", Toast.LENGTH_SHORT).show();
         } else {
             double sisi = Double.parseDouble(inputSisiPersegi.getText().toString());
             double luas = sisi * sisi;
             DecimalFormat df = new DecimalFormat("#.##");
             hasilLuasPersegi.setText(df.format(luas));
         }
     }
 
     private void hitungLuasSegitiga() {
         if (inputAlasSegitiga.getText().toString().isEmpty() || inputTinggiSegitiga.getText().toString().isEmpty())
 


 public class BangunRuangFragment extends Fragment {
 
     private EditText etPanjang, etLebar, etTinggi, etJariJari;
     private RadioGroup rgBangunRuang;
     private RadioButton rbBalok, rbPiramid, rbTabung;
     private TextView tvHasil;
     private Button btnHitung;
 
     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_bangun_ruang, container, false);
 
         etPanjang = view.findViewById(R.id.et_panjang);
         etLebar = view.findViewById(R.id.et_lebar);
         etTinggi = view.findViewById(R.id.et_tinggi);
         etJariJari = view.findViewById(R.id.et_jari_jari);
         rgBangunRuang = view.findViewById(R.id.rg_bangun_ruang);
         rbBalok = view.findViewById(R.id.rb_balok);
         rbPiramid = view.findViewById(R.id.rb_piramid);
         rbTabung = view.findViewById(R.id.rb_tabung);
         tvHasil = view.findViewById(R.id.tv_hasil);
         btnHitung = view.findViewById(R.id.btn_hitung);
 
         btnHitung.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int selectedId = rgBangunRuang.getCheckedRadioButtonId();
                 if (selectedId == -1) {
                     tvHasil.setText("Pilih bangun ruang terlebih dahulu");
                 } else {
                     double panjang = Double.parseDouble(etPanjang.getText().toString());
                     double lebar = Double.parseDouble(etLebar.getText().toString());
                     double tinggi = Double.parseDouble(etTinggi.getText().toString());
                     double jariJari = Double.parseDouble(etJariJari.getText().toString());
                     switch (selectedId) {
                         case R.id.rb_balok:
                             double volumeBalok = panjang * lebar * tinggi;
                             tvHasil.setText("Volume balok: " + volumeBalok);
                             break;
                         case R.id.rb_piramid:
                             double volumePiramid = (panjang * lebar * tinggi) / 3;
                             tvHasil.setText("Volume piramid: " + volumePiramid);
                             break;
                         case R.id.rb_tabung:
                             double volumeTabung = Math.PI * jariJari * jariJari * tinggi;
                             tvHasil.setText("Volume tabung: " + volumeTabung);
                             break;
                     }
                 }
             }
         });
 
         return view;
     }
 }