package com.example.homework1android2.FormFrag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.homework1android2.App;
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
    boolean isEdit = false;
    int pos;
    String background;
    RadioGroup radioGroup;
    RadioButton radioBlack, radioWhite, radioRed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        initView(view);
        initClick();
        getData();
        getInform();
        initButton();
        checkRadio();
        return view;
    }

    //  мы принимаем даннные с HomerF и иниц наши данные и принимает данные по позиции и меняем isEdit на true
    private void getInform() {
        if (getArguments() != null) {
            model = (TaskmOdel) getArguments().getSerializable("mod");
            txtMain.setText(model.getTitle());
            pos = getArguments().getInt("position");
            isEdit = true;
        }
    }

    private void getData() {
        // добавялет дату в live режиме
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
                radioGroup.check(R.id.radio_black);
                background = "black";
                YoYo.with(Techniques.RubberBand).duration(500).repeat(2).playOn(v.findViewById(R.id.bnt_black));

            }
        });
        bntWhite.setOnClickListener(v -> {
            radioGroup.check(R.id.radio_white);
            background = "white";
            YoYo.with(Techniques.RubberBand).duration(500).repeat(2).playOn(v.findViewById(R.id.bnt_white));
        });
        bntRed.setOnClickListener(v -> {
            radioGroup.check(R.id.radio_red);
            background = "red";
            YoYo.with(Techniques.RubberBand).duration(500).repeat(2).playOn(v.findViewById(R.id.bnt_red));
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

    private void checkRadio() {
        radioBlack.setOnClickListener(v -> {
            radioGroup.check(R.id.radio_black);
        });
        radioWhite.setOnClickListener(v -> {
            radioGroup.check(R.id.radio_white);
        });
        radioRed.setOnClickListener(v -> {
            radioGroup.check(R.id.radio_red);
        });
    }

    public void initClick() {
        txtReady.setOnClickListener(v -> {
            if (isEdit) {
                // это редактирование
                String title = txtMain.getText().toString();
                model = new TaskmOdel(title, background);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyModel", model);
                bundle.putInt("position", pos);
                getParentFragmentManager().setFragmentResult("editData", bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            } else {
                // это добавление в HomeF
                String title = txtMain.getText().toString();
                model = new TaskmOdel(title, background);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyModel", model);
                bundle.putInt("position", pos);
                getParentFragmentManager().setFragmentResult("key", bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            }
            App.getInstance(requireContext()).getTaskDao().insert(model);
            Log.e("tag", "miss");
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
        radioGroup = view.findViewById(R.id.radios);
        radioBlack = view.findViewById(R.id.radio_black);
        radioWhite = view.findViewById(R.id.radio_white);
        radioRed = view.findViewById(R.id.radio_red);
//        aSwitch = view.findViewById(R.id.switch_black);
    }


      //блокирует клавиратуру при входе
    @Override
    public void onAttach(@NotNull Context context) {
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