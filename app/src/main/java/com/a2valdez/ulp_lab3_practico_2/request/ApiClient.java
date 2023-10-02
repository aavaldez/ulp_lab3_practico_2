package com.a2valdez.ulp_lab3_practico_2.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.a2valdez.ulp_lab3_practico_2.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if( sp == null ){
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){
        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta,"usuario.dat");
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream ous = new ObjectOutputStream(bos);
            ous.writeObject(usuario);
            bos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al guardar", Toast.LENGTH_LONG).show();
        } catch (IOException io){
            Toast.makeText(context,"Error de E/S",Toast.LENGTH_LONG).show();
        }
    }

    public static Usuario leer(Context context){
        Usuario usuario = null;
        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta,"usuario.dat");
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Usuario u = (Usuario)ois.readObject();
            while (u != null){
                u = (Usuario)ois.readObject();
            }
            fis.close();
            if(u != null ){
                usuario = u;
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al guardar",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context,"Error de Clase",Toast.LENGTH_LONG).show();
        } catch (IOException io) {
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }

    public static Usuario login(Context context, String email, String pass){
        Usuario usuario = null;
        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta,"usuario.dat");
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Usuario u = (Usuario)ois.readObject();
            while (u != null){
                u = (Usuario)ois.readObject();
            }
            fis.close();
            if( u.getMail().equals(email) && u.getPassword().equals(pass)){
                usuario = u;
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al guardar",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context,"Error de Clase",Toast.LENGTH_LONG).show();
        } catch (IOException io) {
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }
}
