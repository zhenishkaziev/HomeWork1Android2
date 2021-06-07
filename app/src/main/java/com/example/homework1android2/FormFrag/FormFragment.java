package com.example.homework1android2.FormFrag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homework1android2.R;
import com.example.homework1android2.model.TaskmOdel;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormFragment extends Fragment {

    private EditText txtMain;
    private TextView txtReady, txtDate, dateTime;
    TaskmOdel model = new TaskmOdel();
    boolean isColor = true;
    Button bntBlack, bntWhite, bntRed;
    View screenView;
//    Switch aSwitch;
    boolean isEdit = false;
    int pos;
    String background;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        initView(view);
        initClick();
        getData();
        getInform();
        initButton();
        return view;
    }

    private void getInform() {
         if (getArguments() != null){
             model = (TaskmOdel) getArguments().getSerializable("mod");
             txtMain.setText(model.getTitle());
             pos = getArguments().getInt("position");
             isEdit = true;
         }
    }

    private void getData() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM");
        SimpleDateFormat time = new SimpleDateFormat(" HH:mm");
        String strDate = sdf.format(c.getTime());
        String strTime = time.format(c.getTime()); 
        txtDate.setText(strDate);
        dateTime.setText(strTime);
////        Date currentDate = new Date();
////        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
////        String dateText = dateFormat.format(currentDate);
////        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
////        String timeText = timeFormat.format(currentDate);
//        txtDate.setText(dateText);
//        dateTime.setText(timeText);
    }

    private void initButton() {
        bntBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                aSwitch.setChecked(true);
                background = "black";
            }
        });
          bntWhite.setOnClickListener(v -> {
              background = "white";
          });
          bntRed.setOnClickListener(v -> {
              background = "red";
          });

//        bntBlack.setOnClickListener(v -> {
//             if (isColor){
//                 screenView.setBackgroundColor(Color.WHITE);
//                 txtMain.setBackgroundColor(Color.BLACK);
//                 isColor = false;
//             }
//             else {
//                 screenView.setBackgroundColor(Color.BLACK);
//                 isColor = true;
//             }
////            color = new int[]{Color.BLACK, Color.BLUE, Color.WHITE};
////            int aryLenth = color.length;
////            Random random = new Random();
////            int run = random.nextInt(aryLenth);
////            screenView.setBackgroundColor(color[run]);
////        });
//
    }

    public void initClick() {
        txtReady.setOnClickListener(v -> {
            if (isEdit){
                String title = txtMain.getText().toString();
                model = new TaskmOdel(title, model.getBackground());
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyModel", model);
                bundle.putInt("position", pos);
                getParentFragmentManager().setFragmentResult("editData", bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            } else {
                String title = txtMain.getText().toString();
                model = new TaskmOdel(title, background);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyModel", model);
                bundle.putInt("position", pos);
                getParentFragmentManager().setFragmentResult("key", bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            }
        });
    }
    private void initView(View view) {
        txtMain = view.findViewById(R.id.edit_et);
        txtReady = view.findViewById(R.id.ready_text);
        txtDate = view.findViewById(R.id.date_text);
        dateTime = view.findViewById(R.id.time_text);
        bntBlack = view.findViewById(R.id.bnt_black);
        bntWhite = view.findViewById(R.id.bnt_white);
        bntRed = view.findViewById(R.id.bnt_red);
//        aSwitch = view.findViewById(R.id.switch_black);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        showKeyBoard(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showKeyBoard(false);
    }

    private void showKeyBoard(boolean showKeyBoard) {
        final Activity activity = getActivity();
        final InputMethodManager manager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),showKeyBoard ? InputMethodManager.SHOW_FORCED : InputMethodManager.HIDE_NOT_ALWAYS);
    }
}