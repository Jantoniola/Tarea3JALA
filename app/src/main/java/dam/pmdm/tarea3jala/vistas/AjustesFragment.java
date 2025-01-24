package dam.pmdm.tarea3jala.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

import dam.pmdm.tarea3jala.LoginActivity;
import dam.pmdm.tarea3jala.MainActivity;
import dam.pmdm.tarea3jala.R;

public class AjustesFragment extends PreferenceFragmentCompat {

    ListPreference listaidiomas;
    SwitchPreferenceCompat borrar;
    Preference about;
    Preference logoutPreference;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.ajustes, rootKey);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        listaidiomas = findPreference("idioma");
        listaidiomas.setSummary(getString(R.string.textoIdiomaActual) + (listaidiomas.getValue().equals("es") ? "Español" : "English"));
        if (listaidiomas != null) {
            listaidiomas.setOnPreferenceChangeListener((preference, newValue) -> {
                String lenguaje = (String) newValue;
                Locale locale = new Locale(lenguaje);
                Locale.setDefault(locale);

                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                actualizaPantalla((String)newValue);
                return true;
            });

        }
        borrar = findPreference("eliminar");
        about = findPreference("about");
        if (about != null) {
            about.setOnPreferenceClickListener(preference -> {
                new AlertDialog.Builder(getContext())
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setTitle(R.string.titulo_about)
                        .setMessage(R.string.dialog)
                        .setNegativeButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                return true;
            });
        }

        logoutPreference = findPreference("cerrar");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        logoutPreference.setSummary(getText(R.string.cerrarSesion) + " " + user.getDisplayName());
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(preference -> {
                new AlertDialog.Builder(getContext())
                        .setIcon(R.drawable.logout_icon)
                        .setTitle(R.string.cerrarSesion)
                        .setMessage(R.string.mensajeCerrarSesion)

                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cerrarSesion();
                            }
                        })
                        .setNegativeButton("No", null)

                        .show();

                return true;
            });
        }
    }

    private void actualizaPantalla(String newValue) {
        ((MainActivity) getActivity()).cambiarIdiomaMenu();
        listaidiomas.setSummary(getString(R.string.textoIdiomaActual) + (newValue.equals("es") ? "Español" : "English"));
        listaidiomas.setTitle(R.string.texto_lenguaje);
        borrar.setTitle(R.string.titulo_eliminar);
        borrar.setSummary(R.string.sumario_eliminar);
        about.setTitle(R.string.titulo_about);
        about.setSummary(R.string.sumario_about);
        logoutPreference.setTitle(R.string.titulo_cerrar);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        logoutPreference.setSummary(getText(R.string.cerrarSesion) + " " + user.getDisplayName());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ajustes);


    }


    private void cerrarSesion() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            AuthUI.getInstance()
                    .signOut(getContext())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), R.string.toastCerrarSesion, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), LoginActivity.class));
                            getActivity().finish();
                        }
                    });
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ajustes);
        }
    }
}