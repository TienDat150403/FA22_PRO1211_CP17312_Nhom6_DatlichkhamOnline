package com.example.cp17312_nhom6_duan1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.ViewHolder.ItemOrderNoCofirmViewHolder;
import com.example.cp17312_nhom6_duan1.dao.FileDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDAO;
import com.example.cp17312_nhom6_duan1.dao.OrderDoctorDAO;
import com.example.cp17312_nhom6_duan1.dto.FileDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;
import com.example.cp17312_nhom6_duan1.dto.OrderDoctorDTO;

import java.util.ArrayList;

public class AdapterOrderNocofirm extends RecyclerView.Adapter<ItemOrderNoCofirmViewHolder> {
    public ArrayList<OrderDoctorDTO> list = new ArrayList<>();
    public Context context;

    public AdapterOrderNocofirm(ArrayList<OrderDoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemOrderNoCofirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_no_confirm,parent,false);
        return new ItemOrderNoCofirmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemOrderNoCofirmViewHolder holder, int position) {
        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(context);
        OrderDoctorDTO orderDoctorDTO1 = list.get(position);
        OrderDAO orderDAO = new OrderDAO(context);
        holder.btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDTO orderDTO = orderDAO.getOrderDTOById(orderDoctorDTO1.getOrder_id());
                orderDTO.setStatus("Đã khám xong");
                int res = orderDAO.updateRow(orderDTO);
                notifyDataSetChanged();
            }
        });
        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDoctorDTO1.getId());

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        holder.tvFile.setText(fileDTO.getFullname());
        holder.tvDetailFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông tin chi tiết bệnh nhân");
                builder.setMessage("Tên bệnh nhân: "+fileDTO.getFullname()+"\nNgày sinh: "+fileDTO.getBirthday()+"\nCăn cước công dân: "+fileDTO.getCccd()+"\nQuốc tịch: "+fileDTO.getCountry()+"\nBảo hiểm y tế : "+fileDTO.getBhyt()+"\nCông việc :"+fileDTO.getJob()+"\nĐịa chỉ nơi ở : "+fileDTO.getAddress()+"\nLí do khám : "+fileDTO.getDes());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.tvStartDate.setText("Ngày khám: "+orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText("Giờ khám: "+orderDoctorDTO.getStart_time());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
