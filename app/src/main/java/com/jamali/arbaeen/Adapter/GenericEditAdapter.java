//package com.jamali.arbaeen.Adapter;
//
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.jamali.arbaeen.Kernel.Controller.Domain.DomainInfo;
//import com.jamali.arbaeen.Kernel.Controller.Domain.SpinnerDomain;
//import com.jamali.arbaeen.Kernel.Controller.Domain.ViewType;
//import com.jamali.arbaeen.Kernel.Controller.GenericListBll;
//import com.jamali.arbaeen.Kernel.Controller.Interface.CallBackSpinner;
//import com.jamali.arbaeen.Kernel.Helper.Waiting;
//import com.jamali.arbaeen.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class GenericEditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private static final int EDIT_TEXT = 0;
//    private static final int SPINNER = 1;
//    private static final int CHECK_BOX = 2;
//    private static final int TEXT_VIEW = 3;
//    private static final int NUMBER_EDIT_TEXT = 4;
//    private static final int DATE_EDIT_TEXT = 5;
//    private final HashMap<String, String> data;
//
//
//
//    public GenericEditAdapter(ArrayList<DomainInfo> domainInfos, HashMap<String, String> decodedData) {
//        this.domainInfos = domainInfos;
//        this.data = decodedData;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        if (viewType == EDIT_TEXT) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_edit_et, parent, false);
//            return new EditTextViewHolder(view);
//        } else if (viewType == NUMBER_EDIT_TEXT) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_number_edit_et, parent, false);
//            return new NumberEditTextViewHolder(view);
//
//        } else if (viewType == DATE_EDIT_TEXT) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_date_edit_et, parent, false);
//            return new DateEditTextViewHolder(view);
//
//        } else if (viewType == SPINNER) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_edit_sp, parent, false);
//            return new SpinnerViewHolder(view);
//        } else if (viewType == CHECK_BOX) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_edit_cb, parent, false);
//            return new CheckBoxViewHolder(view);
//        } else if (viewType == TEXT_VIEW) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_edit_tv, parent, false);
//            return new TextViewViewHolder(view);
//        } else {
//            throw new RuntimeException("only three view types are allowed: spinner, editText and checkbox");
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof EditTextViewHolder) {
//            initEditText((EditTextViewHolder) holder, position);
//        } else if (holder instanceof NumberEditTextViewHolder) {
//            initNumberEditText((NumberEditTextViewHolder) holder, position);
//
//        } else if (holder instanceof DateEditTextViewHolder) {
//            initDateEditText((DateEditTextViewHolder) holder, position);
//
//        } else if (holder instanceof CheckBoxViewHolder) {
//            initCheckBox((CheckBoxViewHolder) holder, position);
//        } else if (holder instanceof SpinnerViewHolder) {
//            initSpinner((SpinnerViewHolder) holder, position);
//        } else if (holder instanceof TextViewViewHolder) {
//            initTextView((TextViewViewHolder) holder, position);
//        }
//    }
//
//    private void initTextView(TextViewViewHolder holder, int position) {
//        holder.title.setText(domainInfos.get(position).getTitle());
//        if (!data.isEmpty()) {
//            holder.info.setText(data.get(domainInfos.get(position).getId()));
//        }
//    }
//
//    private void initCheckBox(CheckBoxViewHolder holder, int position) {
//        holder.checkBoxTitle.setText(domainInfos.get(position).getTitle());
//
//        // fill the view with data from 'data hashMap'
//        // abort if 'data hashMap' is empty
//        if (!data.isEmpty()) {
//            if (data.get(domainInfos.get(position).getId()).equals("true")) {
//                holder.checkBox.setChecked(true);
//            } else holder.checkBox.setChecked(false);
//        }
//
//        // listen for changes on checkBoxes
//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                data.put(domainInfos.get(position).getId(), "true");
//            } else data.put(domainInfos.get(position).getId(), "false");
//        });
//    }
//
//    private void initSpinner(SpinnerViewHolder holder, int position) {
//        holder.spinnerTitle.setText(domainInfos.get(position).getTitle());
//
//        //progressBar before the spinner is filled
//        Waiting waiting = new Waiting(holder.itemView.getContext());
//        MaterialDialog wait = waiting.alertWaiting();
//        wait.show();
//
//        //populate spinner
//        DomainInfo item = domainInfos.get(position);
//        GenericListBll bll = new GenericListBll(holder.itemView.getContext());
//        bll.populateSpinner(item.getId(), item, item.getViewType(), item.getApiAddress(), new CallBackSpinner() {
//            @Override
//            public void onSuccess(ArrayList<SpinnerDomain> response) {
//
//
//                // add a `none` option to the spinner
//                ArrayList<SpinnerDomain> result = new ArrayList<>();
//                result.add(new SpinnerDomain(" ", " ", null));
//                result.addAll(response);
//
//
//                int adapterPosition = holder.getAdapterPosition();
//                spinnerData.put(adapterPosition, result);
//
//                ArrayAdapter adapter = new ArrayAdapter<>(holder.itemView.getContext(), R.layout.spinner_item_blue, result);
//
//                holder.spinner.setAdapter(adapter);
//                SearchableField.setSpinner(holder.spinner, result);
//
//                // select selected item on load completion
//                // if data Map is not empty
//
//                if (!data.isEmpty()) {
//                    String id = domainInfos.get(adapterPosition).getId();
//
////                    String selectedId;
////                    if(id.equals("ParentId")){
////                         selectedId = data.get("InspectionTimingId");
////                    }else {
////                         selectedId = data.get(id);
////                    }
//
//                    String selectedId = data.get(id);
//
//                    for (int i = 0; i < spinnerData.get(adapterPosition).size(); i++) {
//                        ArrayList<SpinnerDomain> list = spinnerData.get(adapterPosition);
//                        for (int j = 0; j < list.size(); j++) {
//                            if (selectedId.equals(list.get(j).getValue())) {
//                                holder.spinner.setSelection(j, true);
//                            }
//                        }
//                    }
//                }
//
//                holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                        //save selected row
//                        SpinnerDomain selectedRow = result.get(position);
//
//                        if (selectedRow.getValue() != null) {
//                            data.put(domainInfos.get(holder.getAdapterPosition()).getId(), selectedRow.getValue());
//                        }
//
////                        Log.i("selectedId", selectedRow.getValue());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//                wait.dismiss();
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//    }
//
//    private void initEditText(EditTextViewHolder holder, int position) {
//        holder.editTextTitle.setText(domainInfos.get(position).getTitle());
//
//        //set value if data is not empty
//        if (!data.isEmpty()) {
//            holder.editText.setText(data.get(domainInfos.get(position).getId()).replace("null", ""));
//        }
//        //listen for editText changes
//        holder.editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                data.put(domainInfos.get(holder.getAdapterPosition()).getId(), s.toString());
//            }
//        });
//    }
//
//    private void initNumberEditText(NumberEditTextViewHolder holder, int position) {
//        holder.editTextTitle.setText(domainInfos.get(position).getTitle());
//
//        //set value if data is not empty
//        if (!data.isEmpty()) {
//            holder.editText.setText(data.get(domainInfos.get(position).getId()).replace("null", ""));
//        }
//        //listen for editText changes
//        holder.editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                data.put(domainInfos.get(holder.getAdapterPosition()).getId(), s.toString());
//            }
//        });
//    }
//
//    private void initDateEditText(DateEditTextViewHolder holder, int position) {
//        holder.editTextTitle.setText(domainInfos.get(position).getTitle());
//
//        //set value if data is not empty
//        if (!data.isEmpty()) {
//            holder.editText.setText(data.get(domainInfos.get(position).getId()).replace("null", ""));
//        }
//        //listen for editText changes
//        holder.editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                data.put(domainInfos.get(holder.getAdapterPosition()).getId(), s.toString());
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemViewType(int position) {
//        DomainInfo item = domainInfos.get(position);
//        if (item.getViewType().equals(ViewType.EDIT_TEXT.name())) {
//            return EDIT_TEXT;
//        } else if (item.getViewType().equals(ViewType.NUMBER_EDIT_TEXT.name())) {
//            return NUMBER_EDIT_TEXT;
//        } else if (item.getViewType().equals(ViewType.DATE_EDIT_TEXT.name())) {
//            return DATE_EDIT_TEXT;
//        } else if (!item.getApiAddress().isEmpty()) {
//            return SPINNER;
//        } else if (item.getViewType().equals(ViewType.CHECK_BOX.name())) {
//            return CHECK_BOX;
//        } else if (item.getViewType().equals(ViewType.TEXT_VIEW.name())) {
//            return TEXT_VIEW;
//        } else {
//            return -1;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return domainInfos.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    private void formateEditTextForDate(EditText editText) {
//        editText.addTextChangedListener(new TextWatcher() {
//            int len = 0;
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String str = editText.getText().toString();
//                if (str.length() == 4 && len < str.length()) {//len check for backspace
//                    editText.append("/");
//                }
//                if (str.length() == 7 && len < str.length()) {//len check for backspace
//                    editText.append("/");
//                }
//                if (str.length() == 10 && len < str.length()) {//len check for backspace
//                    editText.append("\u0020");
//                }
//                if (str.length() == 13 && len < str.length()) {//len check for backspace
//                    editText.append(":");
//                }
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//
//                String str = editText.getText().toString();
//                len = str.length();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//        });
//    }
//
//    class CheckBoxViewHolder extends RecyclerView.ViewHolder {
//        private CheckBox checkBox;
//        private TextView checkBoxTitle;
//
//        CheckBoxViewHolder(@NonNull View itemView) {
//            super(itemView);
//            checkBox = itemView.findViewById(R.id.check_box);
//            checkBoxTitle = itemView.findViewById(R.id.check_box_title);
//        }
//    }
//
//    class SpinnerViewHolder extends RecyclerView.ViewHolder {
//        private SearchableSpinner spinner;
//        private TextView spinnerTitle;
//
//        SpinnerViewHolder(@NonNull View itemView) {
//            super(itemView);
//            spinner = itemView.findViewById(R.id.spinner);
//            spinnerTitle = itemView.findViewById(R.id.spinner_title);
//        }
//    }
//
//    class EditTextViewHolder extends RecyclerView.ViewHolder {
//        private EditText editText;
//        private TextView editTextTitle;
//
//        EditTextViewHolder(@NonNull View itemView) {
//            super(itemView);
//            editText = itemView.findViewById(R.id.edit_text);
//            editTextTitle = itemView.findViewById(R.id.edit_text_title);
//        }
//    }
//
//    class NumberEditTextViewHolder extends RecyclerView.ViewHolder {
//        private EditText editText;
//        private TextView editTextTitle;
//
//        NumberEditTextViewHolder(@NonNull View itemView) {
//            super(itemView);
//            editText = itemView.findViewById(R.id.edit_text);
//            editTextTitle = itemView.findViewById(R.id.edit_text_title);
//        }
//    }
//
//    class DateEditTextViewHolder extends RecyclerView.ViewHolder {
//        private EditText editText;
//        private TextView editTextTitle;
//
//        DateEditTextViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            editText = itemView.findViewById(R.id.edit_text);
//            editTextTitle = itemView.findViewById(R.id.edit_text_title);
//            formateEditTextForDate(editText);
//
//        }
//    }
//
//    class TextViewViewHolder extends RecyclerView.ViewHolder {
//        private TextView title;
//        private TextView info;
//
//        TextViewViewHolder(@NonNull View itemView) {
//            super(itemView);
//            title = itemView.findViewById(R.id.text_view);
//            info = itemView.findViewById(R.id.title_info);
//        }
//    }
//
//}
