package gt.umg.viajes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import gt.umg.viajes.adapters.HotelServiceAdapter;
import gt.umg.viajes.entities.HotelDetail;
import gt.umg.viajes.ws.ResourceResponse;
import gt.umg.viajes.ws.ViajesWs;

public class HotelSearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private ViajesWs viajesWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search_result);

        listView = (ListView) findViewById(R.id.hotel_search_result_list_view);

        viajesWs = new ViajesWs();

        viajesWs.getHotelServices(getIntent().getExtras().getString("locationId"), getIntent().getExtras().getString("childrens"), getIntent().getExtras().getString("adults"), getIntent().getExtras().getString("bedrooms"), getIntent().getExtras().getLong("lDateIn"), getIntent().getExtras().getLong("lDateOut")).execute(new ResourceResponse<HotelDetail[]>() {
            @Override
            public void success(int statusCode, HotelDetail[] responseData) {
                if(statusCode == 200){
                    HotelServiceAdapter adapter = new HotelServiceAdapter(HotelSearchResultActivity.this, responseData);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void error(int errorCode, String error) {

            }
        });

    }
}
