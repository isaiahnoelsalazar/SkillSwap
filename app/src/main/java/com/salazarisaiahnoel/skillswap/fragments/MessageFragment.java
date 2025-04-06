package com.salazarisaiahnoel.skillswap.fragments;

import static com.salazarisaiahnoel.skillswap.MainActivity.DATABASE_NAME;
import static com.salazarisaiahnoel.skillswap.MainActivity.MESSAGE_COLUMNS;
import static com.salazarisaiahnoel.skillswap.MainActivity.MESSAGE_TABLE_NAME;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.message_items;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.saiaaaaaaa.cod.EasySQL;
import com.salazarisaiahnoel.skillswap.R;
import com.salazarisaiahnoel.skillswap.adapters.MessageAdapter;
import com.salazarisaiahnoel.skillswap.adapters.SkillCategoryListAdapter;
import com.salazarisaiahnoel.skillswap.others.MessageItem;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MessageFragment extends Fragment {

    String instructor_name;

    public MessageFragment(String instructor_name) {
        // Required empty public constructor
        this.instructor_name = instructor_name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EasySQL easySQL = new EasySQL(requireContext());

        List<String> messagetemps = new ArrayList<>();
        String messagetemp = "";
        int messagecounter = 0;
        for (int a = 0; a < easySQL.getTableValues(DATABASE_NAME, MESSAGE_TABLE_NAME).size(); a++){
            System.out.println(easySQL.getTableValues(DATABASE_NAME, MESSAGE_TABLE_NAME).get(a));
            String t = easySQL.getTableValues(DATABASE_NAME, MESSAGE_TABLE_NAME).get(a).split(":")[1].trim();
            if (messagecounter != MESSAGE_COLUMNS.length - 1){
                messagetemp += t.substring(1, t.length() - 1) + "<topicseparator>";
                messagecounter++;
            } else {
                messagetemp += t.substring(1, t.length() - 1);
                messagetemps.add(messagetemp);
                messagetemp = "";
                messagecounter = 0;
            }
        }
        message_items.clear();
        for (int a = 0; a < messagetemps.size(); a++){
            message_items.add(new MessageItem(messagetemps.get(a).split("<topicseparator>")[0], messagetemps.get(a).split("<topicseparator>")[1]));
        }
        RecyclerView rv = view.findViewById(R.id.messages_rv);

        EditText message_area = view.findViewById(R.id.message_area);
        ImageView backarrow = view.findViewById(R.id.back_arrow);
        ImageView send_button = view.findViewById(R.id.send_message);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().remove(requireActivity().getSupportFragmentManager().findFragmentById(R.id.mainFrame)).commit();
            }
        });
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message_items.add(new MessageItem(instructor_name, message_area.getText().toString()));
                List<MessageItem> items = new ArrayList<>();
                for (MessageItem item : message_items){
                    if (item.getInstructor().equals(instructor_name)){
                        items.add(item);
                    }
                }
                MessageAdapter adapter = new MessageAdapter(requireContext(), items);
                rv.setAdapter(adapter);
                easySQL.insertToTable(DATABASE_NAME, MESSAGE_TABLE_NAME, new String[]{"instructor:" + instructor_name, "message_content:" + message_area.getText().toString()});
            }
        });

        TextView instructorName = view.findViewById(R.id.instructor_name_message);
        instructorName.setText(instructor_name);

        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true));

        List<MessageItem> items = new ArrayList<>();
        for (MessageItem item : message_items){
            if (item.getInstructor().equals(instructor_name)){
                items.add(item);
            }
        }
        MessageAdapter adapter = new MessageAdapter(requireContext(), items);
        rv.setAdapter(adapter);
    }
}