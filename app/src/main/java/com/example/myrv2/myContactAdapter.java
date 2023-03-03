package com.example.myrv2;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class myContactAdapter extends RecyclerView.Adapter<myContactAdapter.myViewHolder> {

    Context context;
    private int lastPosition = -1;
    ArrayList<contact_Model> myArray;

    myContactAdapter(Context context,ArrayList<contact_Model> myArray)
    {
        this.context = context;
        this.myArray = myArray;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
                holder.imageContact.setImageResource(myArray.get(position).img);
                holder.nameContact.setText(myArray.get(position).name);
                holder.numberContact.setText(myArray.get(position).number);
                setAnimation(holder.itemView,holder.getAdapterPosition());

                holder.myRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.addlayout);

                        TextView dtitle = dialog.findViewById(R.id.dtitle);
                        dtitle.setText("Update");
                        Button   dbtn   = dialog.findViewById(R.id.dbtn);
                        dbtn.setText("update");
                        EditText dname = dialog.findViewById(R.id.dname);
                        EditText dnumber= dialog.findViewById(R.id.dnumber);
                        dialog.show();
                        dname.setText(myArray.get(position).name);
                        dnumber.setText(myArray.get(position).number);

                        dbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String myname ="";
                                String mynumber="";
                                if(!dname.getText().toString().equals("") && !dnumber.getText().toString().equals(""))
                                {
                                    myname = dname.getText().toString();
                                    mynumber= dnumber.getText().toString();
                                    int img = R.drawable.a1;
                                    myArray.set(holder.getAdapterPosition(),new contact_Model(img,myname,mynumber));

                                    notifyItemChanged(holder.getAdapterPosition());
                                    Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                else {
                                    Toast.makeText(context, "Enter Some Fields", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });


                    }
                });


        //for delete row in recycler view
        holder.myRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure ? ");
                builder.setIcon(R.drawable.delete);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            myArray.remove(position);
                            notifyItemRemoved(position);
                        Toast.makeText(context, "Successfuly Delete", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();


                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{
            ImageView imageContact;
            TextView nameContact;
            TextView numberContact;
            LinearLayout myRow;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageContact    = itemView.findViewById(R.id.row_image);
            nameContact     = itemView.findViewById(R.id.row_name);
            numberContact   = itemView.findViewById(R.id.row_number);
            myRow           = itemView.findViewById(R.id.myrow);
        }
    }

    public void setAnimation(View viewtoanimation, int position){

        //use if condition if we want to show animation only 1 time at start
        if (position>lastPosition)
        {
            Animation slidein = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            viewtoanimation.setAnimation(slidein);
            lastPosition = position;
        }

    }
}
