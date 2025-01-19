package dam.pmdm.tarea3jala.bd;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

import dam.pmdm.tarea3jala.modelos.PokemonBD;

public class ControladoraBD {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static StringBuilder cadena=new StringBuilder();
    public static Task<Boolean> guardarPokemonBD(PokemonBD pokemonBD, String emailUsuario) {
        TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();
        db.collection("usuario").document(emailUsuario)
                .collection("PokemonCapturados").document(Integer.toString(pokemonBD.getId()))
                .set(pokemonBD)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        taskCompletionSource.setResult(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setResult(false);
                    }
                });
        return taskCompletionSource.getTask();
    }

public static void leerPokemonCapturados(String emailUsuario, Consumer<ArrayList<PokemonBD>> callback) {
    ArrayList<PokemonBD> lista = new ArrayList<>();

    db.collection("usuario")
            .document(emailUsuario)
            .collection("PokemonCapturados")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            lista.add(document.toObject(PokemonBD.class));
                        }
                    } else {

                    }
                    callback.accept(lista); // Llama al callback con la lista, esté llena o vacía
                }
            });
}

}
