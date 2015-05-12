package com.pplc9.telusurpesonaindonesia.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pplc9.telusurpesonaindonesia.R;

import java.util.HashMap;
import java.util.Map;

public class showMapTaman extends Activity {

    private static final String TAG_ID = "ID";
    private static final String TAG_NAMA = "Nama";
    private static final String TAG_LOKASI = "Lokasi";
    private static final String TAG_DESKRIPSI = "Deskripsi";

    GoogleMap gmap;
    private Map<Marker, String> allMarkersMap = new HashMap<Marker, String>();
    private Map<Marker, String> allMarkersID = new HashMap<Marker, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map_taman);
        createMapView();
        //12 Taman Nasional Jawa
        Marker tmn1 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.6577875,105.5829048))
                .title("Taman Nasional Ujung Kulon")
                .snippet("Labuan, Serang, Pandeglang, Banten"));
        allMarkersMap.put(tmn1, "Dinyatakan sebagai taman nasional pada tahun 1980, Taman Nasional Ujung Kulon merupakan perwakilan ekosistem hutan hujan tropis dataran rendah yang tersisa dan terluas di Jawa Barat, serta merupakan habitat yang ideal bagi kelangsungan hidup satwa langka badak Jawa (Rhinoceros sondaicus) dan satwa langka lainnya. Terdapat tiga tipe ekosistem di taman nasional ini yaitu ekosistem perairan laut, ekosistem rawa, dan ekosistem daratan.Taman Nasional Ujung Kulon bersama Cagar Alam Krakatau merupakan aset nasional, dan telah ditetapkan sebagai Situs Warisan Alam Dunia oleh UNESCO pada tahun 1991.");
        allMarkersID.put(tmn1, "12");

        Marker tmn2 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.4667865,106.0910225))
                .title("Taman Nasional Gunung Halimun")
                .snippet("Kabupaten Bogor, Kabupaten Sukabumi (Provinsi Jawa"));
        allMarkersMap.put(tmn2, "Taman Nasional Gunung Halimun merupakan perwakilan tipe ekosistem hutan hujan dataran rendah, hutan sub-montana dan hutan montana di Jawa. Hampir seluruh hutan di taman nasional ini berada di dataran pegunungan dengan beberapa sungai dan air terjun, yang merupakan perlindungan fungsi hidrologis di Kabupaten Bogor, Lebak, dan Sukabumi.Beberapa tumbuhan yang mendominasi hutan di Taman Nasional Gunung Halimun antara lain rasamala, jamuju, dan puspa. Juga terdapat sekitar 75 jenis anggrek terdapat di taman nasional ini dan beberapa jenis diantaranya merupakan jenis langka.Taman Nasional Gunung Halimun merupakan habitat dari beberapa satwa mamalia seperti owa, kancil, surili, lutung budeng, kijang, macan tutul, dan anjing hutan.Terdapat kurang lebih 204 jenis burung dan 90 jenis diantaranya merupakan burung yang menetap serta 35 jenis merupakan jenis endemik di Jawa termasuk burung elang Jawa . Selain itu terdapat dua jenis burung yang terancam punah yaitu burung cica matahari dan burung poksai kuda. Burung elang Jawa yang identik dengan lambang negara Indonesia (burung garuda), cukup banyak dijumpai di Taman Nasional Gunung Halimun.Keindahan alam dengan kehidupan liar, gemuruh air terjun dan gemericik aliran sungai kecil yang jernih; kesemuanya merupakan peristiwa alam yang dapat memberi pengalaman yang mungkin tidak akan terlupakan terutama bagi wisatawan dari kota-kota besar.");
        allMarkersID.put(tmn2, "5");

        Marker tmn3 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.4640573,106.3327217))
                .title("8. Balai Besar Taman Nasional Gunung Gede Pangrango")
                .snippet("Cibodas, Cipanas, Cianjur, Jawa Barat"));
        allMarkersMap.put(tmn3, "Taman Nasional Gunung Gede Pangrango (TNGGP) ditetapkan sebagai taman nasional pada tahun 1980. Kawasan Taman Nasional ini ditutupi oleh hutan hujan tropis pegunungan. Kawasan TNGGP juga merupakan habitat dari berbagai jenis satwa liar seperti Macan tutul, Kijang, dll.Ada dua iklim yaitu musim kemaru dari bulan Juni sampai Oktober dan musim penghujan dari bulan november ke april. Pada rentang bulan januari sampai februari hujan turun disertai angin yang cukup kencang dan terjadi cukup sering, sehingga berbahaya untuk kegiatan pendakian.");
        allMarkersID.put(tmn3, "8");

        Marker tmn4 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.8759832,107.6455879))
                .title("Taman Nasional Gunung Ciremai")
                .snippet("Kab. Kuningan, Kab. Majalengka, Provinsi Jawa Bar"));
        allMarkersMap.put(tmn4, "Taman Nasional Gunung Ciremai (TNGC) merupakan ekosistem yang relatif masih utuh dengan tipe hutan dataran rendah, hutan hujan pegunungan, dan hutan pegunungan yang diantaranya memiliki vegetasi hutan alam primer.TNGC memiliki keanekaragaman hayati yang tinggi antara lain berbagai jenis flora seperti Pinus, Saninten, Randu tiang, Nangsi, Mahang, Pasang, Medang, Beringin, diantaranya jenis langka seperti Lampeni, dan Kandaca, berbagai jenis satwa langka seperti Macan kumbang, Kijang, Landak, Surili, berbagai jenis burung yang dilindungi seperti Elang Jawa, berbagai jenis reptil seperti Ular sanca dan berbagai jenis burung.TNGC juga merupakan daerah resapan air bagi kawasan di bawahnya dan beberapa sungai penting di Kabupaten Kuningan, Majalengka dan Cirebon serta sumber beberapa mata air yang dimanfaatkan untuk kebutuhan masyarakat, pertanian, perikanan, suplai PDAM, dan industri, memiliki potensi ekowisata seperti panorama alam yang indah, keindahan air terjun Curug Sawer dan Curug Sabuk, hasil hutan non kayu seperti tumbuhan obat, budidaya lebah madu dan kupu-kupu, potensi untuk penelitian dan pendidikan, situs budaya, dan bangunan bersejarah.");
        allMarkersID.put(tmn4, "6");

        Marker tmn5 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-7.017757,109.7357368))
                .title("Taman Nasional Gunung Baluran")
                .snippet("Kabupaten Situbondo, Provinsi Jawa Timur"));
        allMarkersMap.put(tmn5, "Taman Nasional Baluran merupakan kawasan ekosistem hutan yang spesifik kering di Pulau Jawa. Kawasan ini terdiei dai beberapa tipe vegetasi savana seperti hutan mangrove, hutan musim, hutan pantai, hutan pegunungan bawah, hutan rawa dan hutan yang selalu hijau sepanjang tahun. Taman Nasional Baluran memiliki kurang lebih sebanyak 444 jenis tumbuh-tumbuhan, 26 jenis mamalia, serta 155 jenis burung yang beberapa diantaranya termasuk spesies langka.");
        allMarkersID.put(tmn5, "3");

        Marker tmn6 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-7.2521337,110.326252))
                .title("Taman Nasional Gunung Merbabu")
                .snippet("Boyolali, JawaTengah"));
        allMarkersMap.put(tmn6, "Taman Nasional Gunung Merbabu ditetapkan sebagai kawasan hutan lindung dan taman wisata alam pada tanggal 4 mei 2004. Puncak tertinggi Gunung Merbabu berada pada ketinggian 3.142 Mdpl yang dikenal dengan nama Puncak Kenteng Songo. Kawasan Taman Nasional Gunung Merbabu memiliki curah hujan berkisar antara 2000-3000 mm per tahun dan suhuh sepanjang tahun berkisar antara 17 - 30 derajat Celcius.");
        allMarkersID.put(tmn6, "7");

        Marker tmn7 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-7.2521337,110.326252))
                .title("Taman Nasional Gunung Merapi")
                .snippet("Kab. Klaten, Kab. Boyolali, Kab. Magelang, Kab. Sl"));
        allMarkersMap.put(tmn7, "Taman Nasional Gunung Merapi (TNGM) berdiri sejak 1931 sebagai kawasan perlindungan sumber air, sungai, dan penyangga sistem kehidupan untuk kabupaten/kota Sleman, Yogyakarta, Klaten, Boyolali, dan Magelang. TNGM mempunyai fungsi laboratorium alam untuk pengembangan ilmu pengetahuan, penelitian, pendidikan, peningkatan kesadaran konservasi alam, dan mendukung kepentingan budidaya.");
        allMarkersID.put(tmn7, "4");

        Marker tmn8 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.1309968,110.510273))
                .title("Taman Nasional Karimunjawa")
                .snippet("Kabupaten Jepara, Provinsi Jawa Tengah"));
        allMarkersMap.put(tmn8, "Taman Nasional Karimunjawa, ditetapkan sebagai taman nasional pada , merupakan gugusan 27 buah pulau yang memiliki tipe ekosistem hutan hujan dataran rendah, padang lamun, algae, hutan pantai, hutan mangrove, dan terumbu karang.Taman Nasional Karimunjawa memiliki tumbuhan khas yaitu dewodaru yang terdapat pada hutan hujan dataran rendah. Kelompok algae yang dapat ditemui yaitu algae hijau, algae coklat dan algae merah. Terdapat juga 51 jenis terumbu karang, 90 jenis karang keras, dan 242 jenis ikan hias.Keanekaragaman satwa darat di taman nasional ini tidak terlalu tinggi dibandingkan dengan satwa perairan. Satwa darat yang umum dijumpai antara lain rusa, kera ekor panjang; 40 jenis burung seperti pergam hijau, elang laut perut putih, trocokan/merbah cerukcuk, betet, penyu sisik, penyu hijau, dan ular edhor. Burung elang laut perut putih merupakan satwa yang terancam punah di dunia.");
        allMarkersID.put(tmn8, "10");

        Marker tmn9 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-7.7531766,112.33675))
                .title("Taman Nasional Bromo Tengger Semeru")
                .snippet("Kab. Pasuruan, Kab. Probolinggo, Kab.Lumajang, dan"));
        allMarkersMap.put(tmn9, "Taman Nasional Bromo Tengger Semeru memiliki tipe ekosistem sub-montana, montana dan sub-alphin dengan pohon-pohon yang besar dan berusia ratusan tahun. Beberapa jenis tumbuhan yang terdapat di Taman Nasional Bromo Tengger Semeru antara lain jamuju (Dacrycarpus imbricatus), cemara gunung (Casuarina sp.), eidelweis (Anaphalis javanica), berbagai jenis anggrek dan jenis rumput langka (Styphelia pungieus). Terdapat sekitar 137 jenis burung, 22 jenis mamalia dan 4 jenis reptilia di taman nasional ini. Beberapa diantaranya merupakan spesies langka seperti kijang, elang ular bido, belibis, macan tutul, serta kera ekor panjang.Taman Nasional Bromo Tengger Semeru merupakan satu-satunya kawasan konservasi di Indonesia yang memiliki keunikan berupa laut pasir seluas 5.250 hektar, yang berada pada ketinggian ± 2.100 meter dari permukaan laut. Di laut pasir ditemukan tujuh buah pusat letusan dalam dua jalur yang silang-menyilang yaitu dari timur-barat dan timur laut-barat daya. Dari timur laut-barat daya inilah muncul Gunung Bromo yang termasuk gunung api aktif yang sewaktu-waktu dapat mengeluarkan asap letusan dan mengancam kehidupan manusia di sekitarnya (± 3.500 jiwa). Gunung Bromo mempunyai sebuah kawah dengan garis tengah ± 800 meter (utara-selatan) dan ± 600 meter (timur-barat). Sedangkan daerah bahayanya berupa lingkaran dengan jari-jari 4 km dari pusat kawah Bromo. Selain itu, kawasan ini juga merupakan tempat tinggal dari Suku Tengger yang merupakan suku asli beragama Hindu.");
        allMarkersID.put(tmn9, "9");

        Marker tmn10 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-7.930035,113.3529854))
                .title("Taman Nasional Meru Betiri")
                .snippet("Jember"));
        allMarkersMap.put(tmn10, "Taman Nasional Meru Betiri ditetapkan sebagai taman Balai Taman Nasional pada tanggal 1 Februari 2007. Kawasan Taman Nasional Meru Betiri terletak di Kabupaten Jember dan Kabupaten Banyuwangi. TNMB memiliki tiga ekosistem berbeda yakni mangrove, hutan rawa, dan hutan hujan dataran rendah. Kawasan Taman Nasional Meru Betiri juga merupakan habitat tumbuhan langka dan potensi satwa dilindungi.Kawasan Taman Nasional Meru Betiri bagian utara dan tengah adalah daerah tanpa musim kering dan hutan hujan tropika yang selalu hijau, sedangkan dibagian lainnya adalah daerah musim kering dan merupakan peralihan hutan hujan tropika ke hutan musim. ");
        allMarkersID.put(tmn10, "11");

        Marker tmn11 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-8.0986598,113.6413765))
                .title("Taman Nasional Alas Purwo")
                .snippet("Kabupaten Banyuwangi, Provinsi Jawa Timur"));
        allMarkersMap.put(tmn11, "Taman Nasional Alas Purwo merupakan salah perwakilan tipe ekosistem hutan hujan dataran rendah di Pulau Jawa. Taman Nasional Alas Purwo merupakan habitat dari beberapa satwa liar seperti lutung budeng, banteng, ajag, burung merak, ayam hutan, rusa, macan tutul, dan kucing bakau. Satwa langka dan dilindungi seperti penyu lekang, penyu belimbing, penyu sisik, dan penyu hijau biasanya sering mendarat di pantai Selatan taman nasional ini pada bulan Januari s/d September.");
        allMarkersID.put(tmn11, "1");

        Marker tmn12 = gmap.addMarker(new MarkerOptions()
                .position(new LatLng(-5.6549354,106.5697861))
                .title("Taman Nasional Kepulauan Seribu")
                .snippet("Provinsi DKI Jakarta"));
        allMarkersMap.put(tmn12, "Taman Nasional Kepulauan Seribu merupakan salah satu perwakilan kawasan pelestarian alam bahari di Indonesia yang terletak kurang lebih 45 km sebelah Utara Jakarta.Taman Nasional Kepulauan Seribu merupakan tempat peneluran penyu sisik dan penyu hijau. Penyu sisik dan penyu hijau yang merupakan satwa langka dan jarang ditemukan di perairan lain terutama pantai Utara Pulau Jawa, ditangkarkan di Pulau Semak Daun.Bulan November sampai dengan Februari setiap tahunnya sering terjadi ombak yang besar (berbahaya), dan cuaca tidak begitu bagus biasanya terjadi pada bulan Mei sampai dengan Agustus.Musim kunjungan terbaik yaitu antara bulan Maret dan Mei setiap tahunnya.");
        allMarkersID.put(tmn12, "2");

        gmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String id = allMarkersID.get(marker);
                String nama = marker.getTitle();
                String lokasi = marker.getSnippet();
                String deskripsi = allMarkersMap.get(marker);
                Intent i = new Intent(getApplicationContext(), InfoTaman.class);
                i.putExtra(TAG_ID, id);
                i.putExtra(TAG_NAMA, nama);
                i.putExtra(TAG_LOKASI, lokasi);
                i.putExtra(TAG_DESKRIPSI, deskripsi);
                startActivity(i);
            }
        });

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.8759832,107.6455879), 3.0f));
    }


    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == gmap){
                gmap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == gmap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_map_taman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

