package ray.example.bloodoxygen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryDataAdapter extends RecyclerView.Adapter<HistoryDataAdapter.ViewHolder>{

    private Context context;
    private List<DataBean> list;

    public HistoryDataAdapter(Context context, List<DataBean> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_history_data, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBean dataBean = list.get(position);
        if (position == 0){
            holder.time.setText(dataBean.getCreateTime());
            holder.number.setText(dataBean.getNumber());
            holder.analysis.setText("Analyze the results");
        }else {
            holder.time.setText(dataBean.getCreateTime() == null ? "" : dataBean.getCreateTime());
            holder.number.setText(dataBean.getNumber() == null ? "" : dataBean.getNumber());
            if (dataBean.getNumber() != null){
                int numberInt = Integer.parseInt(dataBean.getNumber());
                if (numberInt<90){
                    holder.analysis.setText("Danger");
                }else if (numberInt<96){
                    holder.analysis.setText("normal");
                }else {
                    holder.analysis.setText("health");
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time,number,analysis;

        public ViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            number = view.findViewById(R.id.number);
            analysis = view.findViewById(R.id.analysis);

        }
    }
}
