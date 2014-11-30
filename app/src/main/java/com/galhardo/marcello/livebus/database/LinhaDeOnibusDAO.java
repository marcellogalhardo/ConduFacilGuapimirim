package com.galhardo.marcello.livebus.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.galhardo.marcello.livebus.model.LinhaDeOnibus;

import java.util.ArrayList;

/**
 * Created by marcello on 27/11/14.
 */
public class LinhaDeOnibusDAO {
    public static String NOME_DA_TABELA = "linha_de_onibus";
    public static String CREATE_SCRIPT =   "CREATE TABLE " + NOME_DA_TABELA
                                         + "("
                                         + "   _id INTEGER PRIMARY KEY"
                                         + "  ,nomeDaCompanhia TEXT"
                                         + "  ,localDaIda TEXT"
                                         + "  ,localDaVolta TEXT"
                                         + ")";
    public static String DROP_SCRIPT = "DROP TABLE IF EXISTS " + NOME_DA_TABELA;
    private int COLUNA_ID = 0;
    private int COLUNA_NOME_DA_COMPANHIA = 1;
    private int COLUNA_LOCAL_DA_IDA = 2;
    private int COLUNA_LOCAL_DA_VOLTA = 3;

    private SQLiteDatabase database;

    public LinhaDeOnibusDAO(SQLiteDatabase database) {
        this.database = database;
    }

    public long inserir(LinhaDeOnibus linhaDeOnibus) {
        ContentValues values = new ContentValues();
        values.put("nomeDaCompnhia", linhaDeOnibus.getNomeDaCompanhia());
        values.put("localDaIda", linhaDeOnibus.getLocalDaIda());
        values.put("localDaVolta", linhaDeOnibus.getLocalDaVolta());
        return this.database.insert(NOME_DA_TABELA, null, values);
    }

    public ArrayList<LinhaDeOnibus> obterTodos() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT");
        sql.append("  *");
        sql.append("FROM");
        sql.append("  " + NOME_DA_TABELA);

        Cursor cursor = this.database.rawQuery(sql.toString(), null);

        cursor.moveToFirst();
        ArrayList<LinhaDeOnibus> linhasDeOnibus = new ArrayList<LinhaDeOnibus>();
        for (int i = 0; i < cursor.getCount(); i++) {
            LinhaDeOnibus linhaDeOnibus = new LinhaDeOnibus();
            linhaDeOnibus.set_id(cursor.getLong(COLUNA_ID));
            linhaDeOnibus.setNomeDaCompanhia(cursor.getString(COLUNA_NOME_DA_COMPANHIA));
            linhaDeOnibus.setLocalDaIda(cursor.getString(COLUNA_LOCAL_DA_IDA));
            linhaDeOnibus.setLocalDaVolta(cursor.getString(COLUNA_LOCAL_DA_VOLTA));
            linhasDeOnibus.add(linhaDeOnibus);
            cursor.moveToNext();
        }
        cursor.close();
        return linhasDeOnibus;
    }

    public ArrayList<LinhaDeOnibus> obterTodosAonde(String textoPesquisado) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("   *");
        sql.append(" FROM");
        sql.append("   " + NOME_DA_TABELA);
        sql.append(" WHERE");
        sql.append(String.format("       nomeDaCompanhia MATCH '%s'", textoPesquisado));
        sql.append(String.format("    OR localDaIda MATCH '%s'", textoPesquisado));
        sql.append(String.format("    OR localDaVolta MATCH '%s'", textoPesquisado));

        Cursor cursor = this.database.rawQuery(sql.toString(), null);

        cursor.moveToFirst();
        ArrayList<LinhaDeOnibus> linhasDeOnibus = new ArrayList<LinhaDeOnibus>();
        for (int i = 0; i < cursor.getCount(); i++) {
            LinhaDeOnibus linhaDeOnibus = new LinhaDeOnibus();
            linhaDeOnibus.set_id(cursor.getLong(COLUNA_ID));
            linhaDeOnibus.setNomeDaCompanhia(cursor.getString(COLUNA_NOME_DA_COMPANHIA));
            linhaDeOnibus.setLocalDaIda(cursor.getString(COLUNA_LOCAL_DA_IDA));
            linhaDeOnibus.setLocalDaVolta(cursor.getString(COLUNA_LOCAL_DA_VOLTA));
            linhasDeOnibus.add(linhaDeOnibus);
            cursor.moveToNext();
        }
        cursor.close();
        return linhasDeOnibus;
    }

}
