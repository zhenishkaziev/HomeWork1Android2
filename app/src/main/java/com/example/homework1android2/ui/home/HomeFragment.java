package com.example.homework1android2.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.homework1android2.Adapter.TaskAdaptrer;
import com.example.homework1android2.App;
import com.example.homework1android2.Interface.OnItemClick;
import com.example.homework1android2.R;
import com.example.homework1android2.databinding.FragmentHomeBinding;
import com.example.homework1android2.model.TaskmOdel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TaskAdaptrer adaptrer;
    EditText etSearch;
    List<TaskmOdel> list = new ArrayList<>();
    public static boolean isChange = true; // для перемены меджду Liner and Grid


    @Override
    public void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        adaptrer = new TaskAdaptrer();

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
     //   Log.e("TAG", "onCreateView: "+ App.getInstance(requireContext()).getTaskDao().getAll());
        //ini();
        getDataForm();
        pushData();
        getFilter();
        getDataFromDB();
        return root;
    }

    private void getDataFromDB() {
        App.getInstance(requireContext()).getTaskDao().getAll().observe(requireActivity(), new Observer<List<TaskmOdel>>() {
            @Override
            public void onChanged(List<TaskmOdel> taskmOdels) {
                adaptrer.addListOfModel(taskmOdels);
            }

        });
    }
//        App.getInstance(requireContext()).getTaskDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<TaskmOdel>>() {
//            @Override
//            public void onChanged(List<TaskmOdel> taskmOdels) {
//                adaptrer.addListOfModel(list);
//            }
//        });

        private void getFilter () {
            binding.etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());

                }
            });

        }
        // код для поиска по словам в заметках
        private void filter (String text){
            List<TaskmOdel> filterList = new ArrayList<>();
            for (TaskmOdel model : adaptrer.list) {
                if (model.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filterList.add(model);
                }
            }
            // если наш поиск пустой то он дает нам лист который мы создали в адаптере и прировняли к основному листу
            if (binding.etSearch.getText().toString().isEmpty()) {
                adaptrer.emptySearch();
                // иначе он даст нам онсовной лист
            } else {
                adaptrer.filterList(filterList);
            }
        }


        private void getDataForm () {
            if (!isChange) {
                binding.recycleView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
            } else {
                binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            binding.recycleView.setAdapter(adaptrer);

            //binding.recycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            // это принимает добавленную заметку
            getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), ((requestKey, result) -> {
                TaskmOdel taskmOdel = (TaskmOdel) result.getSerializable("keyModel");
                if (taskmOdel != null) {
                    // adaptrer.addModel(taskmOdel, HomeFragment.this);
                    //   App.getInstance(requireContext()).getTaskDao().insertAll(taskmOdel);
                }
            }));

            // это принимает редактированнную заметку
            getParentFragmentManager().setFragmentResultListener("editData", getViewLifecycleOwner(), ((requestKey, result) -> {
                TaskmOdel taskmOdel = (TaskmOdel) result.getSerializable("keyModel");
                if (taskmOdel != null) {
                    adaptrer.editModel(taskmOdel, result.getInt("position"));
                }
            }));

        }

        @Override
        public void onViewCreated (@NonNull @NotNull View
        view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            //   getDataFromDB();
        }


        // это меняет с Liner and Grid
        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            if (item.getItemId() == R.id.tachbar_icon) {
                if (isChange) {
                    item.setIcon(R.drawable.ic_baseline_format_list_bulleted_24);
                    binding.recycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    isChange = false;
                } else {
                    item.setIcon(R.drawable.ic_baseline_dashboard_24);
                    binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isChange = true;
                }
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onCreateOptionsMenu (@NonNull Menu menu, @NonNull MenuInflater inflater){
            inflater.inflate(R.menu.main, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public void onDestroy () {
            super.onDestroy();

        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }

        public void pushData () {
            adaptrer.setOnItemClick(new OnItemClick() {
                @Override
                public void onItemClickk(int position, TaskmOdel taskmOdel) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mod", taskmOdel);
                    bundle.putInt("position", position);
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_nav_home_to_formFragment, bundle);
                }

                @Override
                public void deleteClick(TaskmOdel taskmOdel) {

                    ItemTouchHelper.SimpleCallback itemChek = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                        @Override
                        public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                            adaptrer.delete(viewHolder.getAdapterPosition());
                            App.getInstance(requireContext()).getTaskDao().delete(taskmOdel);


                        }
                    };
                    new ItemTouchHelper(itemChek).attachToRecyclerView(binding.recycleView);

                }


            });
        }}



    //  отсюда мы передаем  модельку и позицию и переходим на formfragment

//    @Override
//    public void onItemClickk(int position, TaskmOdel taskmOdel) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("mod", taskmOdel);
//        bundle.putInt("position", position);
//        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
//        navController.navigate(R.id.action_nav_home_to_formFragment, bundle);
//
//    }

//    @Override
//    public void onItemLongClick(int position) {
//
//    }



