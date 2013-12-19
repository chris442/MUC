package com.example.muc_coursework;

import java.util.Calendar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity  {
	
	//the map
	private GoogleMap googleMap;
	
	//edittexts used for user enter location
	EditText edt1;
	EditText edt2;
	
	//initial map type
	private int mapType =GoogleMap.MAP_TYPE_NORMAL;
	
	//string to store event details
	private String sports;
	
	//markers for games 2014
	Marker secc;
	Marker barryBuddonShooting;
	Marker parkhead;
	Marker cathkinBraes;
	Marker veledrome;
	Marker internationalHockey;
	Marker hampden;
	Marker ibrox;
	Marker kelvingroveBowls;
	Marker scotstoun;
	Marker tollcross;
	Marker strathclyde;
	Marker edinburghSwim;
	
	//markers for past games
	Marker delhi;
	Marker melbourne;
	Marker manchester;
	Marker kualaLumpur;
	Marker victoria;
	Marker auckland;
	Marker edinburgh;
	Marker brisbane;
	Marker edmonton;
	Marker christchurch;
	
	//latLng for 2014 games
	static final LatLng GLASGOW = new LatLng(55.8580,-4.2590);
	static final LatLng SECC = new LatLng(55.8607,-4.2871);
	static final LatLng BARRY_BUDDON_SHOOTING = new LatLng(56.499, -2.7543);
	static final LatLng PARKHEAD = new LatLng(55.8497, -4.2055);
	static final LatLng CATHKIN_BRAES= new LatLng(55.79434, -4.2193);
	static final LatLng VELODROME = new LatLng(55.847, -4.2076);
	static final LatLng INTERNATIONAL_HOCKEY = new LatLng(55.8447, -4.236);
	static final LatLng HAMPDEN = new LatLng(55.8255, -4.2520);
	static final LatLng IBROX = new LatLng(55.853,-4.309);
	static final LatLng KELVINGROVE_BOWLS= new LatLng(55.867,-4.2871);
	static final LatLng SCOTSTOUN = new LatLng(55.8813,-4.3405);
	static final LatLng TOLLCROSS = new LatLng(55.845, -4.177);
	static final LatLng STRATHCLYDE = new LatLng(55.7971971, -4.0342997);
	
	//latLng for past games
	static final LatLng DELHI= new LatLng(28.61, 77.23);
	static final LatLng MELBOURNE = new LatLng(-37.813611, 144.963056);
	static final LatLng MANCHESTER = new LatLng(53.466667, -2.233333);
	static final LatLng KUALALUMPUR = new LatLng(3.1475, 101.693333);
	static final LatLng VICTORIA = new LatLng(48.428611, -123.365556);
	static final LatLng AUCKLAND= new LatLng(-36.840417, 174.739869);
	static final LatLng BRISBANE = new LatLng(-27.467917, 153.027778);
	static final LatLng EDMONTON = new LatLng(53.533333, -113.5);
	static final LatLng CHRISTCHURCH = new LatLng(-43.53, 172.620278);
	static final LatLng EDINBURGH = new LatLng(55.939, -3.172);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try
        {
        	//start map
        	initializeMap();
        }catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    //startup map
    private void initializeMap()
    {
    	if(googleMap==null)
    	{
    		//build map
    		googleMap=((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    		
    		//set up initial start for 2014 games
    		setUpGamesPresent();
    		
    		//show users location
    		googleMap.setMyLocationEnabled(true);
    		//allow zoom functionality
    		googleMap.getUiSettings().setZoomControlsEnabled(true);
    		//allow user location to be recovered by pressing this button
    		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    		//enable compass
    		googleMap.getUiSettings().setCompassEnabled(true);
    		
    		//build custom snippets to display event information in
    		googleMap.setInfoWindowAdapter(new InfoWindowAdapter()
    		{
    			@Override
    			public View getInfoWindow(Marker arg0)
    			{
    				return null;
    			}
    			
    			public View getInfoContents(Marker marker)
    			{
    				//new view to display
    				View v = getLayoutInflater().inflate(R.layout.marker,null);
        			
    				//image to display
    				ImageView iv= (ImageView) v.findViewById(R.id.ivInfoWindowMain);
    				
    				//texts to display
        			TextView info=(TextView) v.findViewById(R.id.info);
        			TextView info2=(TextView) v.findViewById(R.id.info2);
        			TextView info3=(TextView) v.findViewById(R.id.info3);
        			
        			//function to set text and colour of first textview
        			info.setText(marker.getTitle());
        			info.setTextColor(Color.BLACK);
        			
        			//function to set text and colour of 2nd textiew
        			info2.setText(marker.getSnippet());
        			info2.setTextColor(Color.RED);
        			
        			//check which marker we have, then load releveant details to display
        			if(marker.equals(secc))
        			{
        				sports="Boxing, Gymnastics,\nJudo, Netball,\nWrestling, Weightlifting";
        				iv.setImageResource(R.drawable.i_secc);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(barryBuddonShooting))
        			{
        				sports="Shooting";
        				iv.setImageResource(R.drawable.i_barrybuddonshooting);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(parkhead))
        			{
        				sports="Opening Ceremony";
        				iv.setImageResource(R.drawable.i_parkhead);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(cathkinBraes))
        			{
        				sports="Mountain Bike";
        				iv.setImageResource(R.drawable.i_cathkinbraes);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(veledrome))
        			{
        				sports="Badminton, Cycling";
        				iv.setImageResource(R.drawable.i_emirates);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(internationalHockey))
        			{
        				sports="Hockey";
        				iv.setImageResource(R.drawable.i_internationalhockey);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(hampden))
        			{
        				sports="Athletics";
        				iv.setImageResource(R.drawable.i_hampden);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(ibrox))
        			{
        				sports="Rugby Sevens";
        				iv.setImageResource(R.drawable.i_ibrox);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(kelvingroveBowls))
        			{
        				sports="Lawn Bowls";
        				iv.setImageResource(R.drawable.i_kelvingrovebowls);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(scotstoun))
        			{
        				sports="Squash, Table Tennis";
        				iv.setImageResource(R.drawable.i_scotstoun);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(tollcross))
        			{
        				sports="Swimming";
        				iv.setImageResource(R.drawable.i_tollcross);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(strathclyde))
        			{
        				sports="Triathlon";
        				iv.setImageResource(R.drawable.i_strathclyde);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(edinburghSwim))
        			{
        				sports="Diving";
        				iv.setImageResource(R.drawable.i_edinburghswim);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			
        			else if(marker.equals(delhi))
        			{
        				sports="Scottish Medals:\n9 Gold\n10 Silver\n7 Bronze\nTotal 26\nRanked 10th";
        				iv.setImageResource(R.drawable.i_delhi);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(melbourne))
        			{
        				sports="Scottish Medals:\n11 Gold\n7 Silver\n11 Bronze\nTotal 29\nRanked 6th";
        				iv.setImageResource(R.drawable.i_melbourne);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(manchester))
        			{
        				sports="Scottish Medals:\n6 Gold\n8 Silver\n15 Bronze\nTotal 29\nRanked 10th";
        				iv.setImageResource(R.drawable.i_manchester);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(kualaLumpur))
        			{
        				sports="Scottish Medals:\n3 gold\n2 Silver\n7 Bronze\nTotal 12\nRanked 11th";
        				iv.setImageResource(R.drawable.i_kuala);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(victoria))
        			{
        				sports="Scottish Medals:\n6 Gold\n3 Silver\n11 Bronze\nTotal 20\nRanked 7th";
        				iv.setImageResource(R.drawable.i_victoria);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(auckland))
        			{
        				sports="Scottish Medals:\n5 Gold\n7 Silver\n10 Bronze\nTotal 22\nRanked 9th";
        				iv.setImageResource(R.drawable.i_auckland);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(edinburgh))
        			{
        				sports="Scottish Medals:\n3 Gold\n12 Silver\n18 Bronze\nTotal 33\nRanked 6th";
        				iv.setImageResource(R.drawable.i_edinburgh);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(brisbane))
        			{
        				sports="Scottish Medals:\n8 Gold\n6 Silver\n12 Bronze\nTotal 26\nRanked 4th";
        				iv.setImageResource(R.drawable.i_brisbane);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(edmonton))
        			{
        				sports="Scottish Medals:\n3 Gold\n6 Silver\n5 Bronze\nTotal 14\nRanked 7th";
        				iv.setImageResource(R.drawable.i_edmunton);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			else if(marker.equals(christchurch))
        			{
        				sports="Scottish Medals:\n3 Gold\n5 Silver\n11 Bronze\nTotal 19\nRanked 7th";
        				iv.setImageResource(R.drawable.i_christchurch);
        				info3.setText(sports);
        				info3.setTextColor(Color.BLUE);
        			}
        			//return the view object to display
        			return v;
    			}
    		});
    		
    		
    		if(googleMap==null)
    		{
    			Toast.makeText(getApplicationContext(), "WARNING! Unable to create map", Toast.LENGTH_SHORT).show();
    		}
    	}
    } 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	//create options menu for user to select items
    	super.onCreateOptionsMenu(menu);
    	getMenuInflater().inflate(R.menu.map_styles_menu, menu);
		return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	super.onOptionsItemSelected(item);
    	
    	//switch statement to select item
    	switch(item.getItemId())
    	{
    	case R.id.normal_map:
    		//set map type normal
    		mapType=GoogleMap.MAP_TYPE_NORMAL;
    		break;
    		
    	case R.id.satellite_map:
    		//set map type satellite
    		mapType=GoogleMap.MAP_TYPE_SATELLITE;
    		break;
    		
    	case R.id.terrain_map:
    		//set map type terrain
    		mapType=GoogleMap.MAP_TYPE_TERRAIN;
    		break;
    		
    	case R.id.hybrid_map:
    		//set map type hybrid
    		mapType=GoogleMap.MAP_TYPE_HYBRID;
    		break;
    		
    	case R.id.games2014:
    		//call function to load 2014 game markers
    		setUpGamesPresent();
    		break;
    		
    	case R.id.games_past:
    		//call function to load past markers
    		setUpGamesPast();
    		break;
    		
    	case R.id.location:
    		//allow user to enter location
    		enterLocation();
    		break;
    	}
    	//update map type
    	googleMap.setMapType(mapType);
    	return true;
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	initializeMap();
    }
    
    private void setUpGamesPresent()
    {
			//set up camera over glasgow
    		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GLASGOW, 11));
    		
    		//build marker with defined position, marker icon, title and snippet
    		secc =googleMap.addMarker(new MarkerOptions().position(SECC)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
    				.title("SECC").snippet("Exhibition Way\nGlasgow\nG3 8YW"));
    		
    		barryBuddonShooting =googleMap.addMarker(new MarkerOptions().position(BARRY_BUDDON_SHOOTING)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.shooting)).title("Barry Buddon Shooting Centre")
    				.snippet("Barry\nCarnoustie\nAngus\nDD7 7RY"));
    		
    		parkhead =googleMap.addMarker(new MarkerOptions().position(PARKHEAD)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
    				.title("Parkhead Stadium").snippet("Celtic Park\nGlasgow\nG40 3RE"));
    		
    		cathkinBraes =googleMap.addMarker(new MarkerOptions().position(CATHKIN_BRAES)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike)).title("Cathkin Braes")
    				.snippet("Cathkin Rd\nRutherglen\nGlasgow\nG73 4SE"));
    		
    		veledrome =googleMap.addMarker(new MarkerOptions().position(VELODROME)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
    				.title("Emirates & Veledrome").snippet("1000 London Rd\nGlasgow\nG40 3HY"));
    		
    		internationalHockey =googleMap.addMarker(new MarkerOptions().position(INTERNATIONAL_HOCKEY)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.hockey)).title("Glasgow National Hockey Centre")
    				.snippet("Greendykes St\nSaltmarket\nGlasgow\nG1 5DB"));
    		
    		hampden =googleMap.addMarker(new MarkerOptions().position(HAMPDEN)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.running)).title("Hampden Stadium")
    				.snippet("Letherby Dr\nGlasgow\nG42 9BA"));
    		
    		ibrox =googleMap.addMarker(new MarkerOptions().position(IBROX)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.rugby)).title("Ibrox Stadium")
    				.snippet("150 Edmiston D\n,Glasgow\nG51 2XD"));
    		
    		kelvingroveBowls =googleMap.addMarker(new MarkerOptions().position(KELVINGROVE_BOWLS)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.bowls)).title("Kelvingrove Lawn Bowls")
    				.snippet("Kelvin Way\nGlasgow\nG3 7TA"));
    		
    		scotstoun =googleMap.addMarker(new MarkerOptions().position(SCOTSTOUN)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("Scotstoun Leisure Centre")
    				.snippet("72 Danes Dr\nGlasgow\nG14 9HD"));
    		
    		tollcross =googleMap.addMarker(new MarkerOptions().position(TOLLCROSS)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.swim)).title("Tollcross Swimming Centre")
    				.snippet("367 Wellshot Rd\nGlasgow\nG32 7QP"));
    		
    		strathclyde =googleMap.addMarker(new MarkerOptions().position(STRATHCLYDE)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("Strathclyde Country Park")
    				.snippet("366 Hamilton Rd\nMotherwell\nML1 3ED"));
    		
    		edinburghSwim =googleMap.addMarker(new MarkerOptions().position(EDINBURGH)
    				.icon(BitmapDescriptorFactory.fromResource(R.drawable.swim)).title("Royal Commonwealth Pool")
    				.snippet("21 Dalkeith Rd\nEdinburgh\nEH16 5BB"));
    		
    		//nullify markers than are not required for current setting
    		delhi=null;
    		melbourne=null;
    		manchester=null;
    		kualaLumpur=null;
    		victoria=null;
    		auckland=null;
    		edinburgh=null;
    		brisbane=null;
    		edmonton=null;
    		christchurch=null;
    }
    
    private void setUpGamesPast()
    {
    		//set up camera over majority of markers
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BRISBANE, 2));
			
			//build marker with defined position, marker icon, title and snippet
    		delhi =googleMap.addMarker(new MarkerOptions().position(DELHI)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
    				.title("Delhi 2010").snippet("India"));
    		
    		melbourne =googleMap.addMarker(new MarkerOptions().position(MELBOURNE)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
    				.title("Melbourne 2006").snippet("Australia"));
    		
    		manchester =googleMap.addMarker(new MarkerOptions().position(MANCHESTER)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
    				.title("Manchester 2002").snippet("England"));
    		
    		kualaLumpur =googleMap.addMarker(new MarkerOptions().position(KUALALUMPUR)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
    				.title("Kuala Lumpur 1998").snippet("Malaysia"));
    		
    		victoria =googleMap.addMarker(new MarkerOptions().position(VICTORIA)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
    				.title("Victoria 1994").snippet("Cananda"));
    		
    		auckland =googleMap.addMarker(new MarkerOptions().position(AUCKLAND)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
    				.title("Auckland 1990").snippet("New Zeland"));
    		
    		edinburgh =googleMap.addMarker(new MarkerOptions().position(EDINBURGH)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
    				.title("Edinburgh 1986").snippet("Scotland"));
    		
    		brisbane =googleMap.addMarker(new MarkerOptions().position(BRISBANE)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
    				.title("Brisbane 1982").snippet("Australia"));
    		
    		edmonton =googleMap.addMarker(new MarkerOptions().position(EDMONTON)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
    				.title("Edmonton 1978").snippet("Canada"));
    		
    		christchurch =googleMap.addMarker(new MarkerOptions().position(CHRISTCHURCH)
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    				.title("Christchurch 1974").snippet("New Zeland"));
    		
    		//nullify markers than are not required for current setting
    		secc=null;
    		barryBuddonShooting=null;
    		parkhead=null;
    		cathkinBraes=null;
    		veledrome=null;
    		internationalHockey=null;
    		hampden=null;
    		ibrox=null;
    		kelvingroveBowls=null;
    		scotstoun=null;
    		tollcross=null;
    		strathclyde=null;
    		edinburghSwim=null;
    }
    
    private void enterLocation()
    {
    	//set up the view to be called when asked
    	//LayoutInflater inflater = (LayoutInflater) this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    	View v = getLayoutInflater().inflate(R.layout.enter_location,null);
    	
    	//set up textviews with information for user
		TextView tv1=(TextView) v.findViewById(R.id.tv1);
		TextView tv2=(TextView) v.findViewById(R.id.tv2);
		
		//set the text for the textviews
		tv1.setText("Enter latitude");
		tv2.setText("Enter longitude");
		
		//set up edit texts for user to enter co-ordinates
		edt1=(EditText) v.findViewById(R.id.et1);
		edt2=(EditText) v.findViewById(R.id.et2);
		
		//set up button to be called when user has input location and check for click
		Button bt=(Button) v.findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{	
				//location variables
				double longitude=0;
				double latitude=0;
				
				//convert edittext to double
				latitude=Double.valueOf(edt1.getText().toString()).doubleValue();
				longitude=Double.valueOf(edt2.getText().toString()).doubleValue();
				
				//create new latlng
				LatLng newLoc = new LatLng(latitude,longitude);
				
				//move camera to user location
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLoc, 11));
			}
		});
    }
}
