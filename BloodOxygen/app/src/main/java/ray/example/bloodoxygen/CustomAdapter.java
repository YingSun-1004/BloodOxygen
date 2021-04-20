package ray.example.bloodoxygen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.UserInfoViewHolder> {
    private Context context;
    private ArrayList<DataBean_information> usersList;

    CustomAdapter(Context context, ArrayList<DataBean_information> userList){
        this.context= context;
        this.usersList = userList;
    }

    @NonNull
    @Override
    public UserInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaydataadapter, parent, false);
        CustomAdapter.UserInfoViewHolder holder = new CustomAdapter.UserInfoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoViewHolder holder, int position) {
        DataBean_information userInfo = usersList.get(position);

        holder.name.setText(userInfo.name);
        holder.age.setText("" + userInfo.age);
        holder.sex.setText(userInfo.sex);
        holder.ht.setText(userInfo.height.toString());
        holder.wt.setText(userInfo.weight.toString());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UserInfoViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, sex, ht, wt;
        public UserInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            sex = itemView.findViewById(R.id.sex);
            ht = itemView.findViewById(R.id.height);
            wt = itemView.findViewById(R.id.weight);
        }
    }

}
