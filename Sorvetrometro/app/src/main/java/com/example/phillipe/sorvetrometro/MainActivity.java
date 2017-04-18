package com.example.phillipe.sorvetrometro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.phillipe.sorvetrometro.dao.SorveteDAO;
import com.example.phillipe.sorvetrometro.model.Sorvete;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvSorvetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvSorvetes = (TextView) findViewById(R.id.tvSorvetes);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,NovoSorveteActivity.class),NovoSorveteActivity.CODE_NOVO_SORVETE); } });
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
                carregasorvetes();
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
                } else if (requestCode == NovoSorveteActivity.CODE_NOVO_SORVETE) {
                    carregasorvetes();
                }
            }

            private void carregasorvetes() {
                tvSorvetes.setText("");
                SorveteDAO sorveteDAO = new SorveteDAO(this);
                StringBuilder sb = new StringBuilder();
                List<Sorvete> sorvetes = sorveteDAO.getAll();
                for (Sorvete s : sorvetes) {
                    sb = new StringBuilder(tvSorvetes.getText());
                    sb.append("\n");
                    sb.append(s.getNome());
                    sb.append(" - ");
                    sb.append(s.getSorveteria().getNome());
                    tvSorvetes.setText(sb.toString());
                }
            }

            @Override
            public void onBackPressed() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.sorvete, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }

            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_camera) { // Handle the camera action } else if (id == R.id.nav_gallery) {
                } else if (id == R.id.nav_slideshow) {
                } else if (id == R.id.nav_manage) {
                } else if (id == R.id.nav_share) {
                } else if (id == R.id.nav_send) {
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        }

