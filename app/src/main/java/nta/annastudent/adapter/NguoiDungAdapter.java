package nta.annastudent.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nta.annastudent.R;
import nta.annastudent.dao.NguoiDungDAO;
import nta.annastudent.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    List<NguoiDung> arrNguoiDung;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoidung, null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtName = convertView.findViewById(R.id.tvName);
            holder.txtPhone = convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(position).getUserName());
                    arrNguoiDung.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        NguoiDung _entry = arrNguoiDung.get(position);
        if (position % 3 == 0) {
            holder.img.setImageResource(R.drawable.emone);
        } else if (position % 3 == 1) {
            holder.img.setImageResource(R.drawable.emtwo);
        } else {
            holder.img.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText(_entry.getHoTen());
        holder.txtPhone.setText(_entry.getPhone());
        return convertView;
    }

    public void changeDataset(List<NguoiDung> items) {
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }
}

