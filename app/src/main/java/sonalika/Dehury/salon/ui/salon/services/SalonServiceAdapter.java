package sonalika.Dehury.salon.ui.salon.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sonalika.Dehury.salon.R;
import sonalika.Dehury.salon.data.salon.SalonServiceModel;
import sonalika.Dehury.salon.data.salon.SalonViewModel;

public class SalonServiceAdapter extends RecyclerView.Adapter<SalonServiceAdapter.SalonViewHolder>  {
    private Context mContext;
    private List<SalonServiceModel> salonServiceModelList;
    private SalonViewModel salonViewModel;
    private List<SalonServiceModel> cartList;

    public SalonServiceAdapter(Context mContext,
                               List<SalonServiceModel> salonModelList,
                               SalonViewModel viewModel) {
        this.mContext = mContext;
        this.salonServiceModelList = salonModelList;
        this.salonViewModel = viewModel;
        this.cartList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalonViewHolder(LayoutInflater
                .from(mContext)
                .inflate(R.layout.salon_service_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder holder, int position) {
        SalonServiceModel serviceModel = salonServiceModelList.get(position);
        holder.serviceNameView.setText(serviceModel.getName());
        holder.servicePriceView.setText(serviceModel.getFormatedPrice());

        holder.addToCartBtn.setOnClickListener(view->{
            cartList.add(serviceModel);
            salonViewModel.servicesAddedToCartLiveData.postValue(cartList);
            holder.addToCartBtn.setVisibility(View.GONE);
            holder.removeToCartBtn.setVisibility(View.VISIBLE);
        });

        holder.removeToCartBtn.setOnClickListener(view->{

           // salonViewModel.servicesAddedToCartLiveData.getValue().remove(serviceModel);
            cartList.remove(serviceModel);
            salonViewModel.servicesAddedToCartLiveData.postValue(cartList);
            holder.removeToCartBtn.setVisibility(View.GONE);
            holder.addToCartBtn.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public int getItemCount() {
        return   (salonServiceModelList != null )? salonServiceModelList.size(): 0;
    }

    public class SalonViewHolder extends RecyclerView.ViewHolder{
        private TextView serviceNameView;
        private TextView servicePriceView;
        private AppCompatButton removeToCartBtn,addToCartBtn;

        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);

            serviceNameView = itemView.findViewById(R.id.serviceNameView);
            servicePriceView = itemView.findViewById(R.id.servicePriceView);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            removeToCartBtn = itemView.findViewById(R.id.removeToCartBtn);
        }
    }
}
