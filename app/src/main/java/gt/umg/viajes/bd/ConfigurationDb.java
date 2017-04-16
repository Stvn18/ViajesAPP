package gt.umg.viajes.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gt.umg.viajes.dto.SessionDto;

/**
 * Created by wilver on 15/04/17.
 */

public class ConfigurationDb extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Configuration.db";

    public ConfigurationDb(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE Session (" +
                " name TEXT NOT NULL, " +
                " email INTEGER NOT NULL," +
                " token TEXT NOT NULL, " +
                " helpActive INTEGER NOT NULL, " +
                " UNIQUE(token) " +
                ");";

        sqLiteDatabase.execSQL(sql);

        sql = " CREATE TABLE Configuration ( " +
                " name TEXT NOT NULL, " +
                " value TEXT NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(sql);

        /*
        * Inserta parametros por default
        * */

        sql = "INSERT INTO Configuration(name, value) VALUES('WS_URL', 'http://192.168.8.101:8080/ViajesWS/api/');";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public void saveSession(String name, String email, String token) throws Exception{

        String sql = "INSERT INTO Session(name, email, token, helpActive) VALUES('" + name + "', '" + email + "', '" + token + "', 1);";

        getWritableDatabase().execSQL(sql);

        //cierra la conexion al sqlite
        close();

    }

    public void deleteSession(String token) throws Exception{

        String sql = "DELETE FROM Session WHERE token = '" + token + "'";

        getWritableDatabase().execSQL(sql);

        //cierra la conexion al sqlite
        close();

    }

    public SessionDto getSession() throws Exception{

        SessionDto session = null;

        String sql = "SELECT name, email, token, helpActive FROM Session LIMIT 1;";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            session = new SessionDto();
            session.setName(cursor.getString(0));
            session.setEmail(cursor.getString(1));
            session.setToken(cursor.getString(2));

            if(cursor.getInt(3) == 1){
                session.setHelpActive(true);
            } else {
                session.setHelpActive(false);
            }

            cursor.close();

        }

        //cierra la conexion al sqlite
        close();

        return session;
    }

    public void saveConfiguration(String name, String value) throws Exception{

        String sql = "INSERT INTO Configuration(name, value) VALUES('" + name + "', '" + value + "');";

        getWritableDatabase().execSQL(sql);

        //cierra la conexion al sqlite
        close();

    }

    public void updateConfiguration(String name, String value) throws Exception{

        String sql = "UPDATE Configuration SET value = '" + value + "' WHERE name = '" + name + "'";

        getWritableDatabase().execSQL(sql);

        //cierra la conexion al sqlite
        close();

    }

    public String getConfigurationByName(String name) throws Exception{

        String sql = " SELECT value FROM Configuration WHERE name = '" + name + "' LIMIT 1; ";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        String value = null;

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            value = cursor.getString(0);

            cursor.close();

        }

        close();

        return value;
    }

}
