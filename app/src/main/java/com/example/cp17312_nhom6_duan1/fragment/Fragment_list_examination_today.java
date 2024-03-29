package com.example.cp17312_nhom6_duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.adapter.AdapterUpdateOrder;
import com.example.cp17312_nhom6_duan1.dao.OrderDetailDAO;
import com.example.cp17312_nhom6_duan1.dto.OrderDetailDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_list_examination_today extends Fragment {
    private RecyclerView rcv_list_examination_today;
    private TextView tvSumNumberExaminationToDay;
    private OrderDetailDAO orderDetailDAO;
    private String today;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_examination_to_day,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_examination_today = view.findViewById(R.id.rcv_list_examination_today);
        tvSumNumberExaminationToDay = view.findViewById(R.id.tvSumNumberExaminationToDay);

        //Lay ngay hien tai
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(day>=10){
            today = year+"/"+(month+1)+"/"+day;
        }
        else{
             today = year+"/"+(month+1)+"/0"+day;
        }

        orderDetailDAO = new OrderDetailDAO(getContext());
        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getListExaminationToDay(today);
        AdapterUpdateOrder adapterOrder = new AdapterUpdateOrder(listOrderDetail,getContext());
        LinearLayoutManager manager  =new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv_list_examination_today.setLayoutManager(manager);
        rcv_list_examination_today.setAdapter(adapterOrder);
        tvSumNumberExaminationToDay.setText("Tổng đơn đặt: "+listOrderDetail.size());
    }
    public void onResume() {
        super.onResume();
        //Lay ngay hien tai
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(day>=10){
            today = year+"/"+(month+1)+"/"+day;
        }
        else{
            today = year+"/"+(month+1)+"/0"+day;
        }
        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getListExaminationToDay(today);
        AdapterUpdateOrder adapterOrder = new AdapterUpdateOrder(listOrderDetail,getContext());
        LinearLayoutManager manager  =new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv_list_examination_today.setLayoutManager(manager);
        rcv_list_examination_today.setAdapter(adapterOrder);
        tvSumNumberExaminationToDay.setText("Tổng đơn đặt: "+listOrderDetail.size());
    }

}
