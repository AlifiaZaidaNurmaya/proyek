package org.proyek.parkirassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.proyek.parkirassistant.ProfileActivity;
import org.proyek.parkirassistant.R;

public class RiwayatTransaksiAdapter extends BaseAdapter {
    String []tanggalTransaksi;
    int[]totalTransaksi;
    Context context;
    private static LayoutInflater inflater=null;

    public RiwayatTransaksiAdapter(ProfileActivity profileActivity, String[]tanggal, int[]total){
        tanggalTransaksi = tanggal;
        totalTransaksi = total;
        context = profileActivity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tanggalTransaksi.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder{
        TextView tvTotal;
        TextView tvTanggal;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.riwayat_transaksi_layout, null);
        holder.tvTanggal = (TextView) rowView.findViewById(R.id.tanggal_transaksi);
        holder.tvTotal = (TextView) rowView.findViewById(R.id.total_transaksi);

        holder.tvTanggal.setText(tanggalTransaksi[i]);
        holder.tvTotal.setText(String.valueOf(totalTransaksi[i]));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You Clicked "+tanggalTransaksi[i], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }
}
